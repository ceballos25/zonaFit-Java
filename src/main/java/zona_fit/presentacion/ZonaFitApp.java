package zona_fit.presentacion;

import zona_fit.datos.ClienteDAO;
import zona_fit.datos.IClienteDAO;
import zona_fit.dominio.Cliente;

import java.util.Scanner;

public class ZonaFitApp {
    public static void main(String[] args) {
        zonaFitApp();
    }

    public static void zonaFitApp() {
        var salir = false;
        var consola = new Scanner(System.in);

        IClienteDAO clienteDao = new ClienteDAO();

        while (!salir){
            try {
                 var opcion = mostrarMenu(consola);
                salir = ejecutarOpciones(consola, opcion, clienteDao);

            } catch (Exception e) {
                System.out.println("Algo salió mal: "+ e.getMessage());
            }
            System.out.println();
        }

    }

    private static int mostrarMenu(Scanner consola){
        System.out.print("""
                ==== Menu App Zona Fit ====
                1. Listar Clientes
                2. Buscar Cliente
                3. agregar Cliente
                4. Modificar Cliente
                5. Eliminar Cliente
                6. Salir
                
                Escoge una opción:\s""");

        return Integer.parseInt(consola.nextLine());
    }

    private static boolean ejecutarOpciones(Scanner consola, int opcion, IClienteDAO clienteDAO){
        var salir = false;
        switch (opcion) {
            case 1 -> {
                System.out.println("=== Listado de Clientes ===");
                var clientes = clienteDAO.listarClientes();

                clientes.forEach(System.out::println);
            }

            case 2 -> {
                System.out.println("Introduce el ID del cliente:");
                var idCliente = Integer.parseInt(consola.nextLine());
                var cliente = new Cliente((idCliente));
                var encontrado = clienteDAO.buscarClientePorId(cliente);
                if (encontrado) {
                    System.out.println("Cliente encontrado: " + cliente);
                } else {
                    System.out.println("Cliente no encontrado (" + cliente.getId() + ")");
                }
            }

            case 3 -> {
                System.out.println("=== Agregar Clientes ===");
                System.out.print("Nombre: ");
                var nombre = consola.nextLine();
                System.out.print("Nombre: ");
                var apellido = consola.nextLine();
                System.out.print("Membresia: ");
                var membresia = Integer.parseInt(consola.nextLine());
                //crear el cliente
                var cliente = new Cliente(nombre, apellido, membresia);
                var agregado = clienteDAO.agregarCliente(cliente);
                if (agregado) {
                    System.out.println("Cliente agregado exitosamente.");
                } else {
                    System.out.println("Algo salió mal al crear el cleinte.");
                }
            }

            case 4 -> {
                System.out.println("=== Modificar Clientes ===");
                System.out.print("Id del cliente a modificar: ");
                var idCliente = Integer.parseInt(consola.nextLine());
                System.out.print("Nombre: ");
                var nombre = consola.nextLine();
                System.out.print("apellido: ");
                var apellido = consola.nextLine();
                System.out.print("Membresia: ");
                var membresia = Integer.parseInt(consola.nextLine());
                var cliente = new Cliente(idCliente, nombre, apellido, membresia);
                var modificado = clienteDAO.modificarCliente(cliente);

                if (modificado) {
                    System.out.print("Cliente modificado correctamente.");
                } else {
                    System.out.print("Algo salió mal al actualizar el cliente.");
                }
            }

            case 5 -> {
                System.out.println("=== Eiminar Clientes ===");
                System.out.print("Id del Cliente: ");
                var ideliminar = Integer.parseInt(consola.nextLine());
                var cliente = new Cliente(ideliminar);
                var eliminado = clienteDAO.eliminarCliente(cliente);
                if (eliminado) {
                    System.out.print("Cliente eliminado Correctamente.");
                }else {
                    System.out.print("Algo salió mal al eliminar el cliente.");
                }
            }

            case 6 -> {
                System.out.print("Gracias por utilizar nuestros servicios..");
                salir = true;
            }
            default -> System.out.print("Opción no reconocida.");
        }
        return salir;
    }
}
