package productos;

import categorias.Categoria;

public class Articulo extends Producto {

    private Categoria categoria;      //Atributo único para esta clase

    public Articulo(String nombre, double precio, Categoria categoria) { // Constructor
        super(nombre, precio);          //Usamos constructor de la clase padre
        this.categoria = categoria;     //Asigno el atributo propio de esta clase
    }

    public Categoria getCategoria() { //;
        return categoria;
    }

    public void setCategoria(Categoria categoria) { // Setter categoría
        this.categoria = categoria;
    }

     /*Implementamos la función que se hereda de la desde la clase padre, la cual implementa
       la interfaz Vendible*/
    @Override
    public double calcularDescuento(){ // Implementación concreta
        return getPrecio() * 0.90;      // 10% de descuento
    }

    
    @Override
    public String toString(){
        return super.toString() + ", categoria=" + (categoria != null ? categoria.getNombre() : "Sin categoría");
    }
}
