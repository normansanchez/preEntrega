package cruds;

import java.util.ArrayList;
import categorias.Categoria;
import productos.Servicio;
import productos.Articulo;
import productos.Producto;

public class CrudProductos extends CrudConsola<Producto> {
    
    private final ArrayList<Producto> productos;
    private final ArrayList<Categoria> categorias;

    public CrudProductos(ArrayList<Producto> productos, ArrayList<Categoria> categorias){
        this.productos = productos;
        this.categorias = categorias;
    }

    @Override
    public void crear(){
        System.out.println("1) Crear Artículo");
        System.out.println("2) Crear Servicio");
        int op = leerEntero("\nElegí una opción: ");

        switch (op) {
            case 1 -> {
                if(categorias.isEmpty()){
                    System.out.println("No hay categorías. Creá una primero.");
                }else{
                    String nombre = leerTexto("\nNombre del nuevo artículo : ");
                    double precio = leerDouble("Precio: ");
                    
                    Categoria categoria = null;

                    do{
                        categoria = buscarElemento("categorias", categorias);
                        if(categoria == null) System.out.println("Categoria no encontrada, reingresar categoria");
                    }while(categoria == null);

                    categoria.setEstaReferenciada(true);

                    Producto nuevo = new Articulo(nombre, precio, categoria);

                    productos.add(nuevo);
                }
            }
            
            case 2 -> {
                String nombre = leerTexto("\nNombre: ");
                double precio = leerDouble("Precio: ");
                int duracion = leerEntero("Duración (horas): ");
                productos.add(new Servicio(nombre, precio, duracion));
                System.out.println("\nServicio creado.");
            }
        
            default -> {
                System.out.println("Opción inválida.");
            }
        }
    }

    @Override
    public void listar() {
        if (productos.isEmpty()) {
            System.out.println("(sin productos)");
        } else {
            for (Producto p : productos) {
                System.out.println(p);
            }
        }
    }

    @Override
    public void actualizar() {
        int id = leerEntero("Id del producto: ");
        for (Producto p : productos) {
            if (p.getId() == id) {
                String nuevoNombre = leerTexto("Nuevo nombre: ");
                double nuevoPrecio = leerDouble("Nuevo precio: ");
                p.setNombre(nuevoNombre);
                p.setPrecio(nuevoPrecio);

                if (p instanceof Articulo) {
                    System.out.println("\nCambiar categoría? 1=Sí / 0=No");
                    int cam = leerEntero("Opción: ");
                    if (cam == 1) {
                        for (Categoria c : categorias) {
                            System.out.println(c.getId() + ") " + c.getNombre());
                        }
                        int idCat = leerEntero("\nElegí id de categoría: ");
                        for (Categoria c : categorias) {
                            if (c.getId() == idCat) { ((Articulo)p).setCategoria(c); break; }
                        }
                    }
                }

                if (p instanceof Servicio) {
                    System.out.println("\n¿Cambiar duración (horas)? 1=Sí / 0=No");
                    int cam = leerEntero("\nOpción: ");
                    if (cam == 1) {
                        int dur = leerEntero("Nueva duración (horas): ");
                        ((Servicio)p).setDuracionHoras(dur);
                    }
                }

                System.out.println("\nActualizado: " + p);
                return;
            }
        }
        System.out.println("\nNo se encontró producto con id " + id);
    }

    @Override
    public void eliminar() {
        int id = leerEntero("\nId del producto a eliminar: ");
        boolean eliminado = productos.removeIf(p -> p.getId() == id);
        System.out.println(eliminado ? "\nProducto eliminado." : "\nNo existía ese id.");
    }
}
