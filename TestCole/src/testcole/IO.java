/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testcole;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author juan
 */
public class IO {
     static FileReader abreLecturaCaracter(String nomFichero) {
        FileReader input = null; //declaro un objeto de la clase FileReader
                                  //la clasae que soporta la entrada de caracters dede un fichero 
        try {
            //FileReader fichero de lectura(Entrada) de carateres
            //BufferedReader de lectura(Entrada) de carateres con buffer
             input = new FileReader(nomFichero);
            //input = new BufferedReader(new FileReader(nomFichero));
            return input;
        } catch (IOException ex) {
            System.out.println("Algo va mal");
        }
        return input;
    }
     
      static int leeCaracter(FileReader input) {
        int caracter;
        try {
            caracter =(char) input.read();
            return caracter;
        } catch (IOException ex) {
            System.out.println("Algo va mal");
        }
        return caracter=-1;
    }
     
       static void cierraLecturaCaracter(FileReader input) {
        
        try {
            //FileReader fichero de lectura(Entrada) de carateres
            //BufferedReader de lectura(Entrada) de carateres con buffer
             input.close();
            //input = new BufferedReader(new FileReader(nomFichero));
           
        } catch (IOException ex) {
            System.out.println("Algo va mal");
        }
       
    }

    static BufferedReader abreLectura(String nomFichero) {
        BufferedReader input = null;
        try {
            //FileReader fichero de lectura(Entrada) de carateres
            //BufferedReader de lectura(Entrada) de carateres con buffer
            input = new BufferedReader(new FileReader(nomFichero));
            return input;
        } catch (IOException ex) {
            System.out.println("Algo va mal");
        }
        return input;
    }
    
    

    static BufferedReader abreLecturaB(String nomFichero) {
        File fich = new File(nomFichero);
        BufferedReader input = null;
        try {
            //FileReader fichero de lectura(Entrada) de carateres
            //BufferedReader de lectura(Entrada) de carateres con buffer
            input = new BufferedReader(new FileReader(fich));
            return input;
        } catch (IOException ex) {
            System.out.println("Algo va mal");
        }
        return input;
    }

    static BufferedWriter abreEscritura(String nomFichero) {
        BufferedWriter output = null;
        try {
            //FileWriter  fichero de escritura(Salida) de carateres
            //BufferedWriter fichero de escritura(Salida) de carateres con buffer
            output = new BufferedWriter(new FileWriter(nomFichero));
            return output;
        } catch (IOException ex) {
            System.out.println("Algo va mal");
        }
        return output;
    }
    
    static BufferedWriter abreEscrituraAnnadir(String nomFichero) {
        BufferedWriter output = null;
        try {
            //FileWriter  fichero de escritura(Salida) de carateres
            //BufferedWriter fichero de escritura(Salida) de carateres con buffer
            output = new BufferedWriter(new FileWriter(nomFichero,true));
            return output;
        } catch (IOException ex) {
            System.out.println("Algo va mal");
        }
        return output;
    }

    static String leeLinea(BufferedReader input) {
        String linea = "";
        try {
            linea = input.readLine();
            return linea;
        } catch (IOException ex) {
            System.out.println("Algo va mal");
        }
        return linea;
    }
    
      static String leeFichero(BufferedReader input) {
        String salida = "";
        try {
            String linea = input.readLine();
            while(linea!=null){
                salida+=linea;
                linea = input.readLine();
                salida +='\n';
            }
            return salida;
        } catch (IOException ex) {
            System.out.println("Algo va mal");
        }
        return salida;
    }

    static void escribeLinea(BufferedWriter output, String linea) {

        try {
            output.write(linea);
//            output.newLine();
              output.write(System.lineSeparator());
              
           
        } catch (IOException ex) {
            System.out.println("Algo va mal");
        }
    }

    static void cierraLectura(BufferedReader input) {
        try {
            input.close();
        } catch (IOException ex) {
            System.out.println("Algo va mal");
        }
    }

    static void cierraEscritura(BufferedWriter output) {
        try {
            output.close();

        } catch (IOException ex) {
            System.out.println("Algo va mal");
        }
    }
    
    
    
}
