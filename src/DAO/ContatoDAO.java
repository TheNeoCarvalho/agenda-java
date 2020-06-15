package DAO;

import Factory.ConnectionFactory;
import java.sql.*;
import javax.swing.*;
import Model.Contato;
import java.util.ArrayList;
import java.util.List;

public class ContatoDAO {
    private Connection connection;
    
    public ContatoDAO(){
        this.connection = new ConnectionFactory().getConnection();
    }
    
    
       public void AdicionaContato(Contato contato){
    
        String sql = "INSERT INTO usuario (nome, email, telefone, celular, foto, nasc) VALUES (?,?,?,?,?,?)";
        
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            
            stmt.setString(1, contato.getNome());
            stmt.setString(2, contato.getEmail());
            stmt.setString(3, contato.getTelefone());
            stmt.setString(4, contato.getCelular());
            stmt.setString(5, contato.getFoto());
            stmt.setString(6, contato.getNasc());

            stmt.execute();
            stmt.close();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        
    }
       
      public List<Contato> ListaContatos(){
          
          String sql = "SELECT * FROM usuario";
          List<Contato> lista = new ArrayList<>();
          
          try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
                if(rs != null){
                    while(rs.next()){
                        Contato contato = new Contato();
                        
                        contato.setId(rs.getInt(1));
                        contato.setNome(rs.getString(2));
                        contato.setEmail(rs.getString(3));
                        contato.setTelefone(rs.getString(4));
                        contato.setCelular(rs.getString(5));
                        contato.setFoto(rs.getString(6));                        
                        contato.setNasc(rs.getString(7));
                        
                        lista.add(contato);
                    }
                    return lista;
                }else{
                    return null;
                }
            
          }catch(SQLException ex){
             return null;
          }  
      } 
       
      
      public String ExcluirContato(Contato c){
        String sql = "DELETE FROM usuario WHERE id = ?";
        
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, c.getId());
            
            if(stmt.executeUpdate() > 0){
                return "Excluido com sucesso!";
            }else{
                return "Erro ao excluir!";
            }
            
        } catch (SQLException ex) {
            return ex.getMessage();
        }
    }
       
    public String AlteraContato(Contato c){
    
        String sql = "UPDATE usuario SET nome = ?, email = ?, telefone = ?, celular = ?, foto = ?, nasc = ? WHERE id = ?";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, c.getNome());
            stmt.setString(2, c.getEmail());
            stmt.setString(3, c.getTelefone());
            stmt.setString(4, c.getCelular());
            stmt.setString(5, c.getFoto());
            stmt.setString(6, c.getNasc());
            stmt.setInt(7, c.getId());
            
            if(stmt.executeUpdate() > 0){
                return "Atualizado!";
            }else{
                return "Erro";
            }
            
        }catch(SQLException ex){
            ex.getMessage();
        }
        
        
        return "Erro";
    
    }
    
    
    public List<Contato> capturaContato(int cod){
    
        String sql = "SELECT * FROM usuario WHERE id = "+cod;
        List<Contato> lista = new ArrayList<>();
        
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            if(rs != null){
                while(rs.next()){
                    Contato c = new Contato();
                    
                    c.setId(rs.getInt(1));
                    c.setNome(rs.getString(2));
                    c.setEmail(rs.getString(3));
                    c.setTelefone(rs.getString(4));
                    c.setCelular(rs.getString(5));
                    c.setFoto(rs.getString(6));                    
                    c.setNasc(rs.getString(7));
                    
                    lista.add(c);
                }
                return lista;
            }else{
                return null;
            }
            
        }catch(SQLException ex){
            ex.getMessage();
        }
        
        
        return null;
    
    }
    
    
    public boolean testaContato(int cod){
        boolean resultado = false;
        String sql = "SELECT * FROM usuario WHERE id = "+cod;
        try{
            
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            if(rs != null){
                while(rs.next()){
                    resultado = true;
                }
            }
            
        }catch(SQLException ex){
            ex.getMessage();
        }
        
        
        return resultado;
        
    }
        
}
