package zona_fit.datos;

import zona_fit.dominio.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static zona_fit.conexion.Conexion.getConexion;

public class ClienteDAO implements IClienteDAO {

    @Override
    public List<Cliente> listarClientes() {
        List<Cliente> clientes = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        Connection con = getConexion();
        var sql = "SELECT * FROM cliente ORDER BY id";

        try{
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                var cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setMembresia(rs.getInt("membresia"));
                clientes.add(cliente);
            }
        } catch (Exception e) {
            System.out.println("Error al listar clientes: " + e.getMessage());
        }

        finally {
            try {
                con.close();
            }catch (Exception e){
                System.out.println("Error al cerrar la conexion: " + e.getMessage());
            }
        }
        return clientes;
    }

    @Override
    public boolean buscarClientePorId(Cliente cliente) {
        PreparedStatement ps;
        ResultSet rs;
        var con = getConexion();
        var sql = "SELECT * FROM cliente WHERE id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, cliente.getId());
            rs = ps.executeQuery();
            if (rs.next()){
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setMembresia(rs.getInt("membresia"));
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error al buscar Cliente por su ID" + e.getMessage());
        }
        finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar la conexion" + e.getMessage());
            }
        }
        return false;
    }

    @Override
    public boolean agregarCliente(Cliente cliente) {
        PreparedStatement ps;
        Connection con = getConexion();
        String sql = "INSERT INTO cliente (nombre, apellido, membresia) " + "VALUES(?,?,?)";
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setInt(3, cliente.getMembresia());
            ps.execute();
            return true;

        } catch (Exception e) {
            System.out.println("Algo falló al insertar el ciente: "+ e.getMessage());
        }finally {
            try{
                con.close();
            }catch (Exception e){
                System.out.println("Error al cerrar la conexion: " + e.getMessage());
            }
        }
        return false;
    }

    @Override
    public boolean modificarCliente(Cliente cliente) {
        PreparedStatement ps;
        Connection con = getConexion();

        var sql = "UPDATE cliente SET nombre=?, apellido=?, membresia=? " +
                " WHERE id = ?";
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setInt(3, cliente.getMembresia());
            ps.setInt(4, cliente.getId());
            ps.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Error al actualizar el cliente: " + e.getMessage());
        }

        finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar la conexion." + e.getMessage());
            }
        }
        return false;
    }

    @Override
    public boolean eliminarCliente(Cliente cliente) {
        PreparedStatement ps;
        Connection con = getConexion();

        String sql = "DELETE FROM cliente WHERE id = ?";

        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1, cliente.getId());
            ps.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Error al eliminar el cliente: " + e.getMessage());
        }

        finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar la conexion." + e.getMessage());
            }
        }
        return false;
    }

    //pruebas desde el psvm
    public static void main(String[] args) {
        //System.out.println("Listar Clientes");
        IClienteDAO clienteDao = new ClienteDAO();
        //var clientes = clienteDAO.listarClientes();
        //clientes.forEach(System.out::println);

        //Buscar por id
        //var cliente1 = new Cliente(10);
        //boolean encontrado = clienteDAO.buscarClientePorId(cliente1);
        //if (encontrado){
        //    System.out.println("Cliente con id (" + cliente1.getId() + ") fue encontrado: " + cliente1);
        //}else{
        //    System.out.println("El cliente con id ("+ cliente1.getId() + ") no fue encontrado.");
        //}

        //agregar Cliente

        //var nuevoCliente = new Cliente("Felipe", "Mano", 19);
        //var agregado = clienteDao.agregarCliente(nuevoCliente);
        //if (agregado) {
        //    System.out.println("El cliente (" + nuevoCliente.getNombre() + ") fue agregado correctamente.");
        //    var clientes = clienteDao.listarClientes();
        //    clientes.forEach(System.out::println);
        //}else{
        //    System.out.println("El cliente (" + nuevoCliente.getNombre() + ") NO fue agregado correctamente.");
        //}

        //Modificar Cliente
        //var modificarCliente = new Cliente(11,"Juliancito","Marin",1111);
        //var modificado = clienteDao.modificarCliente(modificarCliente);
        //if (modificado)
        //    System.out.println("El cliente ("+ modificarCliente.getNombre() +") fue modificado correctamente.");
        //else
        //    System.out.println("Algo salió mal al modificar el cliente");

        //eliminar Cliente
        var eliminarCliente = new Cliente(11);
        var eliminado = clienteDao.eliminarCliente(eliminarCliente);
        if (eliminado)
            System.out.println("El cliente ("+ eliminarCliente.getId() +") fue eliminado correctamente.");
        else
            System.out.println("Algo salió mal al eliminar el cliente");
    }
}
