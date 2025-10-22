package categorias;

public class Categoria implements interfaces.Identificable{

    private int id;                 // Identificador único
    private String nombre;          // Nombre de la categoría
    private boolean estaReferenciada; //Nos indica si hay algún artículo que está usando esta categoría

    private static int contador = 1; // Contador compartido

    public Categoria(String nombre) { // Constructor
        this.id = contador++;         // Asigna id autoincremental
        this.nombre = nombre;         // Setea nombre
        this.estaReferenciada = false;
    }

    public int getId() {              // Getter id
        return id;
    }

    public String getNombre() {       // Getter nombre
        return nombre;
    }

    public void setNombre(String nombre) { // Setter nombre
        this.nombre = nombre;
    }

    public boolean isEstaReferenciada() {
        return estaReferenciada;
    }

    public void setEstaReferenciada(boolean estaReferenciada) {
        this.estaReferenciada = estaReferenciada;
    }

    @Override
    public String toString() {         // Representación legible
        return "Categoria{id=" + id + ", nombre='" + nombre + " Esta en uso : " + estaReferenciada + "'}";
    }
}
