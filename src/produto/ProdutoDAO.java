package produto;

import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ProdutoDAO {
    public boolean cadastrar(Produto produto) {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        
        String sql = "insert into produtos(nome, marca, descricao, preco, qtd_estoque) values (?, ?, ?, ?, ?)";
        
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, produto.getNome());
            ps.setString(2, produto.getMarca());
            ps.setString(3, produto.getDescricao());            
            ps.setDouble(4, produto.getPreco());            
            ps.setInt(5, produto.getQtdEstoque());        
            ps.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Produto inserido com sucesso!");
            return true;
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Erro no acesso ao Banco de Dados : "+ sqle.getMessage());
        }
        return false;
    }
    
    public void editar(Produto produto) {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        
        String sql = "update produtos set nome = ?, marca = ?, descricao = ?, preco = ?, qtd_estoque = ? where id = ?";
        
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, produto.getNome());
            ps.setString(2, produto.getMarca());
            ps.setString(3, produto.getDescricao());    
            ps.setDouble(4, produto.getPreco());            
            ps.setInt(5, produto.getQtdEstoque());            
            ps.setInt(6, produto.getId());
            ps.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Produto editado com sucesso!");
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Erro no acesso ao Banco de Dados : "+ sqle.getMessage());
        }
    }
    
    public void excluir(Produto produto) {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        String sql = "delete from produtos where id = ?";
        
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, produto.getId());
            ps.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Produto deletado com sucesso!");    
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Erro no acesso ao Banco de Dados : "+ sqle.getMessage());
        }        
    }
    
    public List<Produto> listar() {
        List<Produto> lista = new ArrayList<>();
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        
        if (conn == null) {
            System.err.println("Erro de conexão ao listar produtos.");
            return lista;
        }
        
        String sql = "select * from produtos";
        
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Produto p = new Produto();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setMarca(rs.getString("marca"));
                p.setDescricao(rs.getString("descricao"));
                p.setPreco(rs.getDouble("preco"));
                p.setQtdEstoque(rs.getInt("qtd_estoque"));
                p.setFornecedorId(rs.getInt("fornecedor_id"));
                lista.add(p);
            }           
        } catch (SQLException e) {
            System.err.println("Erro ao listar produtos: " + e.getMessage());
        } finally {
            conexao.desconectar();
        }
        
        return lista;
    }
    
    public List<Produto> pesquisar(String pesquisa) {
        List<Produto> lista = new ArrayList<>();
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        
        if (conn == null) {
            System.err.println("Erro de conexão ao listar produtos.");
            return lista;
        }
        
        String sql = "select * from produtos where nome like ?";
        
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + pesquisa + "%");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Produto p = new Produto();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setMarca(rs.getString("marca"));
                p.setDescricao(rs.getString("descricao"));
                p.setPreco(rs.getDouble("preco"));
                p.setQtdEstoque(rs.getInt("qtd_estoque"));
                p.setFornecedorId(rs.getInt("fornecedor_id"));
                lista.add(p);
            }
            
        } catch (SQLException e) {
            System.err.println("Erro ao listar produtos: " + e.getMessage());
        } finally {
            conexao.desconectar();
        }
        
        return lista;
    }
}
