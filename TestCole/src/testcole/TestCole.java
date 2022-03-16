/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package testcole;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
//Comentario prueba sincronización local

/**
 *
 * @author Alejandro Álvarez Salas
 */
public class TestCole {

  static Colegio colegio;
  
  /**
   * Instancia un objeto colegio, lo llena leyendo de un fichero de texto mediante
   * un  bufferedReader, instancia un objeto ventana y lo hace visible
   * @author Alejandro Álvarez Salas
   * @see Colegio#Colegio() 
   * @see IO#abreEscritura(java.lang.String) 
   * @see IO#cierraEscritura(java.io.BufferedWriter) 
   * @see #leerAlumnos(java.io.BufferedReader) 
   * @see Ventana#Ventana(testcole.Colegio)  
   * @param args 
   */
  
  
    public static void main(String[] args) {
        
       colegio = new Colegio();
              
       
       BufferedReader entrada = IO.abreLectura("ficheroExportar.txt");
       leerAlumnos(entrada);
       IO.cierraLectura(entrada);
       
       Ventana ventana = new Ventana(colegio);
       ventana.setVisible(true)
                
;
      //menu();
      /*
      System.out.println("Colegio ordenado por curso y nombre de alumnos: ");
      colegio.ordenaPorCursoNombre();
      System.out.println(colegio.toString());
       */
    }
    
    /**
     * Lee el un fichero y si contiene los datos de los alumnos en un formato válido inserta al alumnos con sus correspondientes asignaturas
     * en el arrayList de alumnos en el objeto colegio. 
     * Si el formato es inválido lanza excepciones y crea ficheros de error.
     * @author Alejandro Álvarez Salas
     * @param entrada Es el bufferReader que está leyendo del fichero de texto del que queremos sacar la información
     * @throws Exception Si se produce algún error al intentar insertar el alumno o las asignaturas
     * @throws IOException Si se produce algún error al intentar leer o escribir los ficheros
     * @throws MiExcepcion Si se comete algún error a la hora de instanciar un alumno
     * @throws NumberFormatException Si la letra del Dni no es la correspondiente a la parte numérica
     * @see IO#leeLinea(java.io.BufferedReader) 
     * @see Alumno#Alumno(int, java.lang.String, java.lang.String, java.lang.String) 
     * @see Asignatura#Asignatura(java.lang.String) 
     * @see Colegio#insertaAlumno(testcole.Alumno) 
     * @see #escribeError(java.lang.String, java.lang.String)
     * @see Alumno#setCurso(int) 
     * @see Alumno#setDni(java.lang.String) 
     * @see Alumno#setFechaNacimiento(java.lang.String) 
     * @see Alumno#setNombre(java.lang.String) 
     */
    
    static void leerAlumnos(BufferedReader entrada){
        String linea;
        String nombreFichero="errores_"+LocalDateTime.now().toString().replace(":", "_")+".txt";
        String[] parametros;   
        ArrayList<String> asignaturas= new ArrayList();
        linea = IO.leeLinea(entrada);
        Alumno alu;
        while(linea!=null){

            parametros = linea.split("/");
            System.out.println(Arrays.toString(parametros));
            
            try{
               alu = new Alumno(Integer.parseInt(parametros[0]), parametros[1], parametros[2], parametros[3]); 
               //meto en un arraylist las asignaturas que le pasan junto con el alumno
               for(int i=0; i<Colegio.CURSO_ASIGNATURAS.length ;i++){
                   for(int j=0; j<Colegio.CURSO_ASIGNATURAS[i].length ;j++){
                        for(String param : parametros){
                            if(Colegio.CURSO_ASIGNATURAS[i][j].equalsIgnoreCase(param)){
                                asignaturas.add(param);
                            }

                        }
                   }
               }
               //compruebo si los 3 ultimos parametros, que son las asignaturas se corresponden con alguna del arraylist de antes
               for(int i=parametros.length-1; i>3 ;i--){
                    if(asignaturas.contains(parametros[i])){
                            alu.getAsignaturas().add(new Asignatura(parametros[i]));
                    }
               }
               
               
               colegio.insertaAlumno(alu);
            }catch(Exception e){
                escribeError(nombreFichero, linea+"---Error: "+e.getMessage());
            }
                  
            linea = IO.leeLinea(entrada);
            
        }

    }
    
    /**
     * Crea un fichero de texto que contien el mensaje de error
     * @author Alejandro Álvarez Salas
     * @param ficheroError El nombre del fichero en que vamos a crear el error
     * @param error El mensaje del error a crear
     * @throws IOException Cuando se tiene algún problema al escribir en el fichero
     * @see IO#abreEscritura(java.lang.String) 
     * @see IO#escribeLinea(java.io.BufferedWriter, java.lang.String) 
     * @see IO#cierraEscritura(java.io.BufferedWriter) 
     */
    
    
    static void escribeError(String ficheroError, String error){
        BufferedWriter salidaErrores = IO.abreEscrituraAnnadir(ficheroError);
        IO.escribeLinea(salidaErrores, "---Error: "+error);
        IO.cierraEscritura(salidaErrores);
    }
    
    
    /**
     * Muestra un menu por consola y pide un códio de acción, así como unos datos, y 
     * realiza opreaciones en base a estos. Estas opciones son: Insertar aluno, extraer alumno, mostrar por nombre y curso
     * y mostrar por fecha de nacimiento.
     * Al insertar se escribe un alumno en el fichero que contiene los datos de los alumnos.
     * Al extraerlo se borran los datos del alumno y se escriben en un fichero de alumnos expulsados
     * @author Alejandro Álvarez Salas
     * @throws IOException ocurre cuando se da un error con la lectura o escritura de archivos
     * @throws MiExcepcion ocurre al cometer un error al intentar instanciar un alumno
     * @throws NumberFormatException Si la letra del Dni no es la correspondiente a la parte numérica
     * @see Alumno#Alumno(int, java.lang.String, java.lang.String, java.lang.String) 
     * @see Colegio#insertaAlumno(testcole.Alumno) 
     * @see IO#abreEscritura(java.lang.String) 
     * @see IO#escribeLinea(java.io.BufferedWriter, java.lang.String)
     * @see IO#cierraEscritura(java.io.BufferedWriter) 
     * @see #escribeError(java.lang.String, java.lang.String) 
     * @see Colegio#ordenaPorCursoNombre() 
     * @see Colegio#ordenaPorFechaDeNacimiento() 
     * @see Alumno#setCurso(int) 
     * @see Alumno#setDni(java.lang.String) 
     * @see Alumno#setFechaNacimiento(java.lang.String) 
     * @see Alumno#setNombre(java.lang.String) 
     */
    
    static void menu(){
        Scanner miScanner = new Scanner(System.in);
       int opcion = 55;
       
       
        System.out.println("-------------MENU-------------");
        System.out.println("1. Insertar alumno");
        System.out.println("2. Extraer alumno");
        System.out.println("3. Mostrar por nombre y curso");
        System.out.println("4. Mostrar por fecha de nacimiento");
       
        while(opcion<0 || opcion>3){
            opcion=miScanner.nextInt();
            if(opcion<0 || opcion>3){
                System.out.println("Introduzca un valor válido");
            }
        }
        miScanner.nextLine();
        switch (opcion){
            case 1:
                System.out.println("Introduzca los datos del alumno (curso/dni/nombre/fecha de nacimiento (AAAA-MM-DD))");
                String datosBrutos;
                //uso el darle el valor porque me da problemas al no esperar el scanner y seguir el codigo
                datosBrutos=miScanner.nextLine();
                String nombreFichero="errores_"+LocalDateTime.now().toString().replace(":", "_")+".txt";
                String[] datos=datosBrutos.split("/");
                Alumno alumno;
          
                try {
                    alumno = new Alumno(Integer.parseInt(datos[0]), datos[1], datos[2], datos[3]);
                    colegio.insertaAlumno(alumno);
                    BufferedWriter salidaAlumno = IO.abreEscrituraAnnadir("alumnos.txt");
                    IO.escribeLinea(salidaAlumno, datosBrutos);
                    IO.cierraEscritura(salidaAlumno);
                } catch (MiExcepcion ex) {
                    escribeError(nombreFichero, datosBrutos+"---Error: "+ex.getMessage());
                    
                }
                menu();
                
            case 2:
                System.out.println("Introduzca el dni del alumno a extraer");
                //uso el darle el valor porque me da problemas al no esperar el scanner y seguir el codigo
                String dniAlumno = miScanner.nextLine();
                String datosExtraccion = "";
                
                for(int i=0; i<colegio.getAlumnos().size();i++){
                    if(colegio.getAlumnos().get(i).getDni().compareToIgnoreCase(dniAlumno)==0){
                        datosExtraccion = colegio.getAlumnos().get(i).getCurso()+"/"+colegio.getAlumnos().get(i).getDni()+"/"+colegio.getAlumnos().get(i).getNombre()+"/"+colegio.getAlumnos().get(i).getFechaNacimiento();
                        colegio.getAlumnos().remove(colegio.getAlumnos().get(i));
                        
                        BufferedWriter salidaExpulsados = IO.abreEscrituraAnnadir("ficheroExpulsados.txt");
                        IO.escribeLinea(salidaExpulsados, datosExtraccion);
                        IO.cierraEscritura(salidaExpulsados);
                    }
                
                
                }
                menu();
                
        
            case 3:
                colegio.ordenaPorCursoNombre();
                System.out.println(colegio.toString());
                
                menu();
                
            case 4:
                colegio.ordenaPorFechaDeNacimiento();
                System.out.println(colegio.toString());
                
                menu();
                
                
        
        
        }
    
    
    
    }
    
}
