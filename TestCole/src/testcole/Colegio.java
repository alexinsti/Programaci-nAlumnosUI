/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testcole;

import java.util.ArrayList;
import java.util.Comparator;
import static testcole.TestCole.colegio;

/**
 *
 * @author Juan Carlos
 */
public class Colegio {
    public static final String[][] CURSO_ASIGNATURAS  = {{"Lengua","Matemáticas","Inglés","Física"},
                                                  {"Latín","Geografía","Literatura"},
                                                  {"Química","Biología", "Geología"}
                                                };

    public static String[][] getCURSO_ASIGNATURAS() {
        return CURSO_ASIGNATURAS;
    }
    private ArrayList<Alumno> alumnos;

    public ArrayList<Alumno> getAlumnos() {
        return alumnos;
    }

    public Colegio() {
        
        alumnos = new ArrayList<Alumno>();
        
    }
    //cambio insertaalummno a boolean para poder comprobar cuando se realice con exito
    public boolean insertaAlumno(Alumno alumno){
        boolean resultado=false;
        if(!alumnos.contains(alumno)){
            alumnos.add(alumno);
            resultado=true;
        }
            return resultado;

    }
    
    public void ordenaPorNombre(){
        alumnos.sort(null);
        
    }
    
    public void ordenaPorFechaDeNacimiento(){
        alumnos.sort(new ComparaFecha());
    }
    
    public void ordenaPorNotaMedia(){
        
    }
    
    public void ordenaPorCursoNombre(){
        alumnos.sort(new ComparaCursoNombre());
    }
    
    public String toString(){
        String salida = "Colegio: \n";
        for (Alumno alu:alumnos) {
            salida += alu.toString();
            
        }
        
        return salida;
        
        
        
    }
    
    class ComparaCursoNombre implements Comparator<Alumno>{

        @Override
        public int compare(Alumno o1, Alumno o2) {
            int numero = 0;
            
            if (o1.getCurso() > o2.getCurso()) {
                numero = 1;
            } else {
                if (o1.getCurso() < o2.getCurso()) {
                    numero = -1;
                } else {
                    /*if (o1.getCurso() == o2.getCurso()) {*/
                        numero = o1.getNombre().compareToIgnoreCase(o2.getNombre());
                    //}
                }
            }
            
            return numero;
            
        }
        
        
        
        
    }
   
    public String toStringAsig(){
        String resultado="";
        for(Alumno alu : alumnos){
            resultado=resultado+(alu.getCurso()+"/"+alu.getDni()+"/"+alu.getNombre()+"/"+alu.getFechaNacimiento()+"/");
            for(Asignatura asig :alu.getAsignaturas()){
                resultado=resultado+asig.getNombreAsig()+"/";
            
            }
            resultado=resultado+"\n";
        
        }
        
        
        return resultado;
    }
    
    
}
