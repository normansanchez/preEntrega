package cruds;

import java.util.ArrayList;
import categorias.Categoria;
import excepciones.borrarCategoriaReferenciada;
import productos.Articulo;
import productos.Producto;

public class CrudCategorias extends CrudConsola<Categoria> {

    private final ArrayList<Categoria> categorias;
    private final ArrayList<Producto> productos;

    public CrudCategorias(ArrayList<Categoria> categorias, ArrayList<Producto> productos) {
        this.categorias = categorias;
        this.productos = productos;
    }

    @Override
    public void crear() {
        String nombre = leerTexto("\nNombre de la nueva categoría: ");
        categorias.add(new Categoria(nombre));
        System.out.println("\nCategoría creada.");
    }

    @Override
    public void listar() {
        if (categorias.isEmpty()) {
            System.out.println("(\nsin categorías)");
        } else {
            for (Categoria c : categorias) {
                System.out.println(c);
            }
        }
    }

    @Override
    public void actualizar() {
        int id = leerEntero("\nId de la categoría: ");
        for (Categoria c : categorias) {
            if (c.getId() == id) {
                String nuevo = leerTexto("\nNuevo nombre: ");
                c.setNombre(nuevo);
                System.out.println("\nActualizada: " + c);
                return;
            }
        }
        System.out.println("\nNo se encontró categoría con id " + id);
    }

    @Override
    public void eliminar() {
        Categoria aEliminar = null;
        aEliminar = buscarElemento("Categorias", categorias);

        if(aEliminar !=null){
            try {
                borraCategoria(aEliminar);
            } catch (borrarCategoriaReferenciada e) {
                String opcion = leerTexto("Desea continuar (s/n): ");
                if(opcion.equals("s")){
                    for(Producto p : productos){
                        if(p instanceof Articulo){
                            Articulo articulo = (Articulo) p;
                            if(articulo.getCategoria() != null && articulo.getCategoria().getId() == aEliminar.getId()){
                                articulo.getCategoria().setNombre("sin categoria");
                            }
                        }
                    }
                }
            }
        }
        
        int id = leerEntero("\nId de la categoría a eliminar: ");
        boolean eliminado = categorias.removeIf(c -> c.getId() == id);
        System.out.println(eliminado ? "\nCategoría eliminada." : "\nNo existía ese id.");
    }

    public void borraCategoria(Categoria cat) throws excepciones.borrarCategoriaReferenciada{
        if(cat.isEstaReferenciada()){
            throw new borrarCategoriaReferenciada("La categoria esta referenciada");
        }
        boolean eliminado = categorias.removeIf(c -> c.getId() == cat.getId());
        System.out.println(eliminado ? "\nCategoría eliminada." : "\nNo existía ese id.");
    }
}