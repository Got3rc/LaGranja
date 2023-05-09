package granja;

import animales.Animal;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Scanner;

public class LaGranjaDeAnimales {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int cantidadAnimales = 0;

        String codigo;
        String fecha;
        char sexo;
        double peso;

        cantidadAnimales = sc.nextInt();
        
        Animal animal = null;
        Animal animal2 = null;
        
        System.out.println("Procesando animales de la granja");
        System.out.println("----------------------------------");
        
        int cantidadCorrecta = 0;
        for (int i = 0; i < cantidadAnimales; i++) {
            sc.nextLine();
        
            codigo = sc.nextLine();
            fecha = sc.nextLine();
            sexo = sc.nextLine().charAt(0);
            peso = sc.nextFloat();
            
            try {
                
                animal = new Animal(codigo, fecha, sexo, peso);
                cantidadCorrecta++;
                if (cantidadCorrecta == 2) {
                    
                    if (animal.equals(animal2)) {
                        System.out.println(animal.toString() + " y " + animal2.toString() + " son el mismo");
                    } else {
                        System.out.println(animal.toString() + " y " + animal2.toString() + " son distintos");
                    }
                    
                    
                    cantidadCorrecta = 1;
                }
                
                animal2 = new Animal(animal);
                
            } catch (IllegalArgumentException e) {
                System.out.println("ERROR. Procesando siguiente animal");
            }
            
        }
        
    }

}