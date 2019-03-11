package venda.negocio;
import java.sql.*;
import javax.swing.JOptionPane;

import venda.connection.DatabaseConnect;
import venda.view.PontoDeVenda;

public class Compra
{
   private int id_compra;
   private PreparedStatement st;
   Connection conn;
   DatabaseConnect db;
   PontoDeVenda p = new PontoDeVenda();//Chamando a classe ponto de venda.
   
   public Compra()
   {
      db = new DatabaseConnect(); 
      conn = db.getConnection();
   }
   
   //Cria a compra
   public void criaCompra()
   {
      try
      {
         String data = p.getDateTime();
         String sql = "insert into compra(date) value (?)";
         st = conn.prepareStatement(sql);
         st.setString(1,data);
         st.executeUpdate();
         st.close();
      }catch(Exception e)
      {
         JOptionPane.showMessageDialog(null,"Erro ao criar comprar","PDV - ERRO",0);
      }
   }
   
   //Recupera o id_compra
   public int id_compra()
   {
      try
      {
          String sql = "SELECT MAX(id_compra) FROM compra";
          st = conn.prepareStatement(sql);
          ResultSet resultSet = st.executeQuery();
          
          if(resultSet.next() == true)
          {
            id_compra = resultSet.getInt("MAX(id_compra)");
          }  
          st.close();
      }catch(Exception e)
      {
         JOptionPane.showMessageDialog(null,"Erro ao criar comprar","PDV - ERRO",0);
          e.printStackTrace();
         return 0;
      }  
     return id_compra; 
   } 
}