package productos;

public class Servicio extends Producto {

    private int duracionHoras;         // Atributo propio

    public Servicio(String nombre, double precio, int duracionHoras) { // Constructor
        super(nombre, precio);
        this.duracionHoras = duracionHoras;
    }

    public int getDuracionHoras(){ 
        return duracionHoras;
    }

    public void setDuracionHoras(int duracionHoras){
        this.duracionHoras = duracionHoras;
    }

    @Override
    public double calcularDescuento(){
        return getPrecio() * 0.80;
    }

    @Override
    public String toString() {
        return super.toString() + ", duracion=" + duracionHoras + "h";
    }
}