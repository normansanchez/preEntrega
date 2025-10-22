package productos;

public abstract class Producto implements Vendible, interfaces.Identificable {

    private int id;              //Identificador del Producto
    private String nombre;       //Nombre del producto
    private double precio;       //Precio del producto

    /* Este contador permitirá crear identificadores únicos para cada instancia de Producto */
    private static int contador = 1; 

    public Producto(String nombre, double precio) { // Constructor
        this.id = contador++;       // Definimos un id para la instancia a crear e incrementados el contador
        this.nombre = nombre;       // Guardamos nombre
        this.precio = precio;       // Guardamos precio
    }

    public int getId(){
        return id;
    }

    public String getNombre(){
        return nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public double getPrecio(){
        return precio;
    }

    public void setPrecio(double precio){
        this.precio = precio;
    }

    /* Esta función deberá ser definida por todas las clases hijas de Producto pues heredaran
     * tambien la implementación de la interfaz Vendible
     */
    public abstract double calcularDescuento(); 

    @Override
    public String toString() {      // Representación legible
        return "Producto{id=" + id + ", nombre='" + nombre + "', precio=" + precio + "}";
    }
}