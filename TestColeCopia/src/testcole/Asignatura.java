/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testcole;

import java.util.Arrays;

/**
 *
 * @author Juan Carlos
 */
public class Asignatura {

    private String nombreAsig;
    private double[] notas;

    public Asignatura(String nombreAsig) {
        this.nombreAsig = nombreAsig;
        notas = new double[3];
        ponNotas();
    }

    public String getNombreAsig() {
        return nombreAsig;
    }

    public double[] getNotas() {
        return notas;
    }

    public void setNombreAsig(String nombreAsig) {
        this.nombreAsig = nombreAsig;
    }

    public void setNotas(double[] notas) {
        this.notas = notas;
    }

    private void ponNotas(){
        for (int i = 0; i <notas.length; i++) {
            notas[i]=Math.random()*10;
        }
    }
    
    private void ponNota(double nota, int trimestre){
        //if() comprobar que la tota y el trimetre es bueno
        this.notas[trimestre-1] = nota;
    }
    public double notaMedia(){
        double media = 0;
        for (int i = 0; i < notas.length; i++) {
            media += notas[i];            
        }
        return media/notas.length;
    }    
   
    @Override
    public String toString() {
        return "Asignatura{" + "nombreAsig=" + nombreAsig + ", notas="
                + Arrays.toString(notas) + '}' +", nota media ="+this.notaMedia()+ "\n";
    }
    
}
