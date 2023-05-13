package granja;


import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
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
        Perro  perro2 = null;
        
        int cantidadCorrectos = 0;
        
        cantidadPerros = sc.nextInt();
        sc.nextLine();

        System.out.println("Procesando perros de la granja");
        System.out.println("----------------------------------");
        
        
        for (int i = 0; i < cantidadPerros; i++) {

            try {
                codigo = sc.nextLine();
                fechaNacimiento = sc.nextLine();
                sexo = sc.nextLine().charAt(0);
                peso = sc.nextDouble();
                sc.nextLine();
                raza = sc.nextLine();
                
                perro = new Perro(codigo, fechaNacimiento, sexo, peso, raza);
                cantidadCorrectos++;
                
                if (cantidadCorrectos == 2 && perro.equals(perro2)) {
                    System.out.println(perro.toString() + " y " + perro2.toString() + " son el mismo");
                    cantidadCorrectos = 1;
                }else if (cantidadCorrectos == 2) {
                    System.out.println(perro.toString() + " y " + perro2.toString() + " son el mismo");
                    cantidadCorrectos = 1;
                }
                perro2 = perro;
                
            } catch (IllegalArgumentException e) {
            
                System.out.println("ERROR. Procesando siguiente perro");
                
            }

        }

    }
}

abstract class Animal {

    protected String codigo;
    private LocalDate fechaNacimiento;
    private char sexo;
    private Double peso;

    final private DateTimeFormatter FORMATO_GUION = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    final private DateTimeFormatter FORMATO_BARRA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    final private DateTimeFormatter FORMATO_TOSTRING = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
    //CREO LOS CONSTRUCTORES
    public Animal(String codigo, String fechaNacimiento, char sexo, double peso) throws IllegalArgumentException {

        if (!esCodigoValido(codigo) || !esSexoValido(sexo) || peso <= 0) {
            throw new IllegalArgumentException();
        }

        this.codigo = codigo;
        this.fechaNacimiento = generarFecha(fechaNacimiento);
        this.sexo = sexo;
        this.peso = peso;
    }
 
    public Animal(Animal otroAnimal) {
        this.codigo = otroAnimal.codigo;
        this.fechaNacimiento = otroAnimal.fechaNacimiento;
        this.sexo = otroAnimal.sexo;
        this.peso = otroAnimal.peso;
    }

    //HACEMOS LOS SETTER
    public void setCodigo(String codigo) {

        if (!esCodigoValido(codigo)) {
            throw new IllegalArgumentException();
        }

        this.codigo = codigo;

    }

    public void setFechaNacimiento(String fechaNacimiento) throws IllegalArgumentException {

        this.fechaNacimiento = generarFecha(fechaNacimiento);

    }

    public void setSexo(char sexo) throws IllegalArgumentException {
        if (!esSexoValido(sexo)) {
            throw new IllegalArgumentException();
        }

        this.sexo = sexo;
    }

    public void setPeso(double peso) {
        if (!esPesoValido(peso)) {
           throw new IllegalArgumentException();
        }
        
        this.peso = peso;
    }

    //CREAR METODO PRIVADO PARA GENERAR FECHA EN EL SETTER
    private LocalDate generarFecha(String fechaNacimiento) throws DateTimeException, IllegalArgumentException {
        String[] fechaNacimientoArrayS = fechaNacimiento.split("[/-]");
        int[] fechaNacimientoArrayI = new int[3];
        LocalDate fechaDevolver;
        if (!fechaNacimiento.matches("[0-9]{2}[/][0-9]{2}[/][0-9]{4}")
                && !fechaNacimiento.matches("[0-9]{2}[-][0-9]{2}[-][0-9]{4}")) {
            throw new IllegalArgumentException();
        }

        try {

            for (int i = 0; i < fechaNacimientoArrayS.length; i++) {

                fechaNacimientoArrayI[i] = Integer.parseInt(fechaNacimientoArrayS[i]);

            }
            fechaDevolver = LocalDate.of(fechaNacimientoArrayI[2], fechaNacimientoArrayI[1], fechaNacimientoArrayI[0]);
        } catch (DateTimeException e) {
            throw new IllegalArgumentException();

        } catch (ArrayIndexOutOfBoundsException e1) {
            throw new IllegalArgumentException();
        }

        return fechaDevolver;
    }

    //COMPROBAR CODIGO VALIDO
    private boolean esCodigoValido(String codigo) {

        return codigo.matches("[0-9a-z]{5}");

    }

    //COMPROBAR SEXO VALIDO
    private boolean esSexoValido(char sexo) {

        return sexo == 'M' || sexo == 'H';

    }

    //COMPROBAR PESO VALIDO
    private boolean esPesoValido(double peso){
    
        return peso > 0;
        
    }
    
    // CREAMOS LOS GETTER

    public String getCodigo() {
        return this.codigo;
    }

    public char getSexo() {
        return this.sexo;
    }

    public double getPeso() {
        return this.peso;
    }
    
    public String getFechaNacimiento(char separador) {

        String FechaDevolver = "";

        if (separador == '-') {

            FechaDevolver = this.fechaNacimiento.format(FORMATO_GUION);

        } else if (separador == '/') {

            FechaDevolver = this.fechaNacimiento.format(FORMATO_BARRA);

        } else {

            throw new IllegalArgumentException();

        }

        return FechaDevolver;
    }

    
    public String getFechaNacimiento() {

        String fechaDevolver = "";

        if (this.fechaNacimiento == null) {
            fechaDevolver = null;
        } else {
            fechaDevolver = getFechaNacimiento('-');
        }
        return fechaDevolver;
    }
    
    //HACEMOS LOS OVERRIDE

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.codigo);
        hash = 37 * hash + Objects.hashCode(this.fechaNacimiento);
        hash = 37 * hash + this.sexo;
        hash = 37 * hash + (int) (Double.doubleToLongBits(this.peso) ^ (Double.doubleToLongBits(this.peso) >>> 32));
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
        final Animal other = (Animal) obj;
        if (this.sexo != other.sexo) {
            return false;
        }
        if (Double.doubleToLongBits(this.peso) != Double.doubleToLongBits(other.peso)) {
            return false;
        }
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        return Objects.equals(this.fechaNacimiento, other.fechaNacimiento);
    }

    @Override
    public String toString() {
        return "Animal{" + "codigo=" + codigo + ", fechaNacimiento=" + fechaNacimiento.format(FORMATO_TOSTRING) + ", sexo=" + sexo + ", peso=" + peso.floatValue() + '}';
    }
    
    
    //Definimos nuestro metodos abstracto
    public abstract String hacerSonido();
    
    public abstract String alegrarse();
    
    public abstract String enfadarse();
    
    public abstract String queSoy();
    
    
}
class Perro extends Animal {

    private String raza;

    public Perro(String codigo, String fechaNacimiento, char sexo, double peso, String raza) throws IllegalArgumentException {
        super(codigo, fechaNacimiento, sexo, peso);
        if (!codigo.matches("p.*")) {
            throw new IllegalArgumentException();
        }
        this.codigo = codigo;
        this.raza = raza;
    }

    public Perro(String raza, Animal otroAnimal) {
        super(otroAnimal);
        this.raza = raza;
    }

    @Override
    public String hacerSonido() {
        return "Guau";
    }

    @Override
    public String alegrarse() {
        return "Salto de alegria y muevo la cola";
    }

    @Override
    public String enfadarse() {
        return "Grunio y ensenio los dientes";
    }

    @Override
    public String queSoy() {
        return "Soy un perro";
    }

    public String pasear() {
        return "Me encanta que me saquen a pasear";
    }

    //Hacemos getter y setter de raza
    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    //Comprobamos si el codigo es valido
    @Override
    public void setCodigo(String codigo) {

        if (!codigo.matches("p.*")) {
            throw new IllegalArgumentException();
        }
        super.setCodigo(codigo);
    }

    //CREAMOS EL OVERRAID DE LOS METODOS SUPERCLASE OBJET

    @Override
    public String toString() {
        return "Perro{"+ super.toString() + "raza=" + raza + '}';
    }
    

    @Override
    public boolean equals(Object obj) {
        
        if (!super.equals(obj)) {
            return false;
        }
        
        final Perro other = (Perro) obj;
        
        if (!Objects.equals(this.raza, other.raza)) {
            return false;
        }
        
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.codigo);
        hash = 37 * hash + Objects.hashCode(this.getFechaNacimiento());
        hash = 37 * hash + this.getSexo();
        hash = 37 * hash + (int) (Double.doubleToLongBits(this.getPeso()) ^ (Double.doubleToLongBits(this.getPeso()) >>> 32));
        hash = 59 * hash + Objects.hashCode(this.raza);
        return hash;
    }
    

}

