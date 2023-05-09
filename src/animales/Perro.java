package animales;


public class Perro extends Animal{

    private String raza;

    public Perro(String codigo, String fechaNacimiento, char sexo, double peso ,String raza) throws IllegalArgumentException {
        super(codigo, fechaNacimiento, sexo, peso);
        if (!codigo.matches("p*")) {
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
        
        if (!codigo.matches("p*")) {
            throw new IllegalArgumentException();
        }
        super.setCodigo(codigo);
    }
    
    
    
}
