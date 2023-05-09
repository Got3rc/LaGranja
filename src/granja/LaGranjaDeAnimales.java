package granja;


import animales.Perro;
import java.util.Scanner;

public class LaGranjaDeAnimales {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        
        int cantidadPerros = 0;
        
        String codigo = "";
        String raza = "";
        char sexo = ' ';
        double peso = 0;
        String fechaNacimiento = "";
        
        Perro perro;
        
        cantidadPerros = sc.nextInt();
        
        for (int i = 0; i < cantidadPerros; i++) {
            sc.nextLine();
            codigo = sc.nextLine();
            fechaNacimiento = sc.nextLine();
            sexo = sc.nextLine().charAt(0);
            
            try {
                
                perro = new Perro(codigo, fechaNacimiento, sexo, peso, raza);
                
            } catch (IllegalArgumentException e) {
                System.out.println("ERROR. Procesando siguiente perro");
            }
            
            
        }
        
        
    }
}