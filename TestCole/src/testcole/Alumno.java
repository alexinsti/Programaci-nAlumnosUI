/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testcole;

import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Objects;






public class Alumno implements Comparable<Alumno> {
// hay que gestionar que los parámetros sean correctos,. curso 1,2,3, dni bueno 
//    y nombre no vacío con excepciones: invalidParameterException.....
// usando throw InvalidParameterException
    private int curso; //valores válidos: 1,2,3
    private String dni;// comprobar dni
    private String nombre; // que no esté vacio
    private LocalDate fechaNacimiento; //que sea buena, crear excepción propia.
    private ArrayList<Asignatura> asignaturas;

    public ArrayList<Asignatura> getAsignaturas() {
        return asignaturas;
    }

    public Alumno(int curso, String dni, String nombre, LocalDate fechaNacimiento) {
        setCurso(curso);
        setDni(dni);
        setNombre(nombre);
        setFechaNacimiento(fechaNacimiento);;
        asignaturas = new ArrayList<Asignatura>();
        //this.asignaAsignaturas(curso);
    }
    
    public Alumno(int curso, String dni, String nombre, String fechaNacimiento) throws MiExcepcion {
        setCurso(curso);
        setDni(dni);
        setNombre(nombre);
        setFechaNacimiento(fechaNacimiento);
        asignaturas = new ArrayList<Asignatura>();
        //this.asignaAsignaturas(curso);
    }

    public int getCurso() {
        return curso;
    }

    public void setCurso(int curso) {
        if (curso >= 1 && curso <=3) {
            this.curso = curso;
        }else{
            throw new InvalidParameterException("error curso");
        }
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        if(esBuenDni(dni)){
           this.dni = dni; 
        }else{
             throw new InvalidParameterException("dni no válido");
        }
 
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    public void setFechaNacimiento(String fechaNacimiento) throws MiExcepcion{
        try{
            this.fechaNacimiento = LocalDate.parse(fechaNacimiento);
            
        }catch(DateTimeParseException e){
            throw new MiExcepcion("mala fecha");
        }
    }

    public void asignaAsignaturas(int curso) {
        Asignatura asig;

        for (int i = 0; i < Colegio.CURSO_ASIGNATURAS[curso - 1].length; i++) {
            asig = new Asignatura(Colegio.CURSO_ASIGNATURAS[curso - 1][i]);
            asignaturas.add(asig);
        }

    }
    
    
    public double notaMedia(){
        double notaMedia = 0;
        for (Asignatura asignatura : asignaturas) {
            notaMedia+=asignatura.notaMedia();            
        }
        
//        for (int i = 0; i < asignaturas.size(); i++) {
//            notaMedia += asignaturas.get(i).notaMedia();
//            
//        }
       
        return notaMedia/asignaturas.size();
    }

    @Override
    public String toString() {
        return "Alumno{" + "curso=" + curso + ", dni=" + dni + ", nombre=" + nombre +
                ", fechaNacimiento=" + fechaNacimiento + ", \n      "
                + "asignaturas=" + asignaturas + '}' +" nota media Alumno= "+this.notaMedia()+ "\n\n";
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Alumno other = (Alumno) obj;
        if (!Objects.equals(this.dni, other.dni)) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(Alumno o) {     
        return nombre.compareToIgnoreCase(o.nombre);
            
    }
    
    public static boolean esBuenDni(String dni){
        String numeros;
        char letra;
        int valorNumerico;
        String codigo = "TRWAGMYFPDXBNJZSQVHLCKE";
        if(dni.length()==9){
            numeros = dni.substring(0, 8);
            letra = dni.charAt(8);
            if(Character.isAlphabetic(letra)){
                if(letra>90){
                    letra = (char) (letra - ('a'-'A'));
                }
                
                try{
                    valorNumerico = Integer.parseInt(numeros);
                    if(codigo.charAt(valorNumerico%23)==letra){
                        return true;
                    }else{
                        return false;
                    }
                }catch (NumberFormatException ex){
                    return false;
                }
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
    
    }



