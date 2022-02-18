/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testcole;

import java.time.LocalDate;
import java.util.Comparator;

/**
 *
 * @author Juan Carlos
 */
public class ComparaFecha implements Comparator<Alumno> {

    @Override
    public int compare(Alumno o1, Alumno o2) {
        return o1.getFechaNacimiento().compareTo(o2.getFechaNacimiento());
    }
    
}
