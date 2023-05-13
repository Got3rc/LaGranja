package animales;

import java.util.Objects;

public class Perro extends Animal {

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
       return "Perro{" + super.toString() + "raza=" + raza + '}';
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
