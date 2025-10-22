package main;

import java.util.ArrayList;
import java.util.Scanner;

import categorias.Categoria;
import cruds.CrudCategorias;
import cruds.CrudProductos;
import productos.Producto;

public class Main{

    static Scanner lectura = new Scanner(System.in);
    public static void main(String[] args) {

        final ArrayList<Producto> productos = new ArrayList<>();
        final ArrayList<Categoria> categorias = new ArrayList<>();

        categorias.add(new Categoria("Tecnología"));
        categorias.add(new Categoria("Hogar"));
        categorias.add(new Categoria("Libros"));
        
        final CrudProductos crudProductos = new CrudProductos(productos, categorias);
        final CrudCategorias crudCategorias = new CrudCategorias(categorias, productos);

        int opcion;
        
        do{
            System.out.println("\n=== Menú Principal ===");
            System.out.println("1) CRUD de Productos");
            System.out.println("2) CRUD de Categorías");
            System.out.println("0) Salir");
            System.out.print("\nIngrese una opción: ");
            String linea = lectura.nextLine();
        
            try {
                opcion = Integer.parseInt(linea.trim());
            } catch (NumberFormatException e) {
                opcion = -1;
            }

            switch (opcion) {
                case 1 -> {
                    int op;
                    do {
                        crudProductos.mostrarOpciones("producto");
                        op = crudProductos.leerEntero("");
                        System.out.println();
                        switch (op) {
                            case 1 -> crudProductos.crear();
                            case 2 -> crudProductos.listar();
                            case 3 -> crudProductos.actualizar();
                            case 4 -> crudProductos.eliminar();
                            case 0 -> System.out.println("Volviendo al menú principal...");
                            default -> System.out.println("Opción inválida");
                        }
                    } while (op != 0);
                }
                case 2 -> {
                    int op;
                    do {
                        System.out.println();
                        crudCategorias.mostrarOpciones("categoria");
                        op = crudCategorias.leerEntero("");
                        switch (op) {
                            case 1 -> crudCategorias.crear();
                            case 2 -> crudCategorias.listar();
                            case 3 -> crudCategorias.actualizar();
                            case 4 -> crudCategorias.eliminar();
                            case 0 -> System.out.println("Volviendo al menú principal...");
                            default -> System.out.println("Opción inválida");
                        }
                    } while (op != 0);
                }
                case 0 -> {System.out.println("\n¡Hasta luego!\n"); lectura.close();}
                default -> System.out.println("\nOpción inválida");
            }
        } while (opcion != 0);
    }
}