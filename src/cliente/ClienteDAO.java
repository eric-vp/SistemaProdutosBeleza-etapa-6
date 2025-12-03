package cliente;

import conexao.Conexao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ClienteDAO {
    public boolean cadastrar(Cliente cliente) {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        
        String sql = "insert into clientes (nome, data_nascimento, email, cpf, endereco, telefone) values (?,?,?,?,?,?)";
        
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, cliente.getNome());
            ps.setDate(2, new java.sql.Date(cliente.getDataNascimento().getTime()));
            ps.setString(3, cliente.getEmail());            
            ps.setString(4, cliente.getCpf());            
            ps.setString(5, cliente.getEndereco());            
            ps.setString(6, cliente.getTelefone());            
            ps.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Cliente inserido com sucesso!");
            return true;
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Erro no acesso ao Banco de Dados : "+ sqle.getMessage());
        }
        return false;
    }
    
    public void editar(Cliente cliente) {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        
        String sql = "update clientes set nome = ?, data_nascimento = ?, email = ?, cpf = ?, endereco = ?, telefone = ? where id = ?";
        
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, cliente.getNome());
            ps.setDate(2, new java.sql.Date(cliente.getDataNascimento().getTime()));
            ps.setString(3, cliente.getEmail());    
            ps.setString(4, cliente.getCpf());            
            ps.setString(5, cliente.getEndereco());            
            ps.setString(6, cliente.getTelefone());            
            ps.setInt(7, cliente.getId());            
            ps.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Cliente editado com sucesso!");
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Erro no acesso ao Banco de Dados : "+ sqle.getMessage());
        }
    }
    
    public void excluir(Cliente cliente) {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();        
        String sql = "delete from clientes where id = ?";
        
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, cliente.getId());
            ps.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Cliente deletado com sucesso!");    
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Erro no acesso ao Banco de Dados : "+ sqle.getMessage());
        }
    }
    
    public List<Cliente> listar() {
        List<Cliente> lista = new ArrayList<>();
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        
        if (conn == null) {
            System.err.println("Erro de conexão ao listar clientes.");
            return lista;
        }
        
        String sql = "select * from clientes";
        
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Cliente c = new Cliente();
                c.setId(rs.getInt("id"));
                c.setNome(rs.getString("nome"));
                c.setDataNascimento(rs.getDate("data_nascimento"));
                c.setEmail(rs.getString("email"));
                c.setCpf(rs.getString("cpf"));
                c.setEndereco(rs.getString("endereco"));
                c.setTelefone(rs.getString("telefone"));
                lista.add(c);
            }           
        } catch (SQLException e) {
            System.err.println("Erro ao listar produtos: " + e.getMessage());
        } finally {
            conexao.desconectar();
        }
        
        return lista;
    }
    
    public List<Cliente> pesquisar(String pesquisa) {
        List<Cliente> lista = new ArrayList<>();
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        
        if (conn == null) {
            System.err.println("Erro de conexão ao listar fornecedores.");
            return lista;
        }
        
        String sql = "select * from clientes where nome like ?";
        
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + pesquisa + "%");            
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Cliente c = new Cliente();
                c.setId(rs.getInt("id"));
                c.setNome(rs.getString("nome"));
                c.setDataNascimento(rs.getDate("data_nascimento"));
                c.setEmail(rs.getString("email"));
                c.setCpf(rs.getString("cpf"));
                c.setEndereco(rs.getString("endereco"));
                c.setTelefone(rs.getString("telefone"));
                lista.add(c);
            }         
        } catch (SQLException e) {
            System.err.println("Erro ao listar clientes: " + e.getMessage());
        } finally {
            conexao.desconectar();
        }
        
        return lista;
    }
}
