package cruds;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class CrudConsola<T> {

    protected final Scanner scanner = new Scanner(System.in);

    public abstract void crear();
    public abstract void listar();
    public abstract void actualizar();
    public abstract void eliminar();

    public void mostrarOpciones(String tipo){
        String titulo = "";

        switch (tipo) {
            case "producto" -> {titulo = "Productos";}
            case "categoria" -> {titulo = "Categorias";}
            default -> {System.out.println("Crud no encontrado");}
        }
        
        if(!titulo.equals("")){
            System.out.println("\n=== Menú CRUD " + titulo + " ===");
            System.out.println("1) Crear " + tipo);
            System.out.println("2) Listar " + tipo);
            System.out.println("3) Actualizar " + tipo);
            System.out.println("4) Eliminar " + tipo);
            System.out.println("0) Volver/Salir\n");
            System.out.print("Ingrese una opción: ");
        }
    }
 
    public int leerEntero(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                String linea = scanner.nextLine();
                return Integer.parseInt(linea.trim());
            } catch (NumberFormatException e) {
                System.out.println("Debe ser un número entero.");
            }
        }
    }

    protected double leerDouble(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                String linea = scanner.nextLine();
                return Double.parseDouble(linea.trim());
            } catch (NumberFormatException e) {
                System.out.println("Debe ser un número (use punto).");
            }
        }
    }

    protected String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine();
    }

    protected <T extends interfaces.Identificable> T buscarElemento(String mensaje, ArrayList<T> ts){
        T res = null;

        System.out.print("\nIngrese Id, nombre de los/las " + mensaje + " o listar(para listar " + mensaje +"): ");
        String entrada = scanner.nextLine();

        if(esEntero(entrada)){
            res = buscarId(Integer.parseInt(entrada), ts);
        }else if(entrada.equals("listar") || entrada.equals("Listar")){
            listar(ts);
            int id = leerEntero("\nIngrese Id: ");
            res = buscarId(id, ts);
        }else{
            ArrayList<T> nombres = new ArrayList<>();
            
            for(T t : ts){
                if(t.getNombre().equals(entrada)) nombres.add(t);
            }

            if(nombres.size() < 2){
                if(nombres.size() == 1) res = nombres.get(0);
            }else{
                listar(nombres);
                int id = leerEntero("\nIngrese Id: ");
                res = buscarId(id, ts);
            }
        }

        return res;
    }

    protected <T extends interfaces.Identificable> T buscarId(int id, ArrayList<T> ts){
        T res = null;

        for(T t : ts) if(t.getId() == id) res = t;

        return res;
    }

    protected <T extends interfaces.Identificable> void listar(ArrayList<T> ts){
        for(T t : ts){
            System.out.println(t);
        }
    }

    public boolean esEntero(String entrada) {
        boolean res;

        try{
            Integer.parseInt(entrada);
            res = true;
        }catch(NumberFormatException e){
            res = false;
        }

        return res;
    }
}

