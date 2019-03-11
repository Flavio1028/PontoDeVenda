package venda.negocio;
import java.sql.*;
import javax.swing.JOptionPane;

import venda.connection.DatabaseConnect;
import venda.view.PontoDeVenda;

public class Pagamento
{
   private int id_pagamento;
   private PreparedStatement st;
   Connection conn;
   DatabaseConnect db;
   PontoDeVenda p = new PontoDeVenda();//Chamando a classe ponto de venda.
   Mercadoria m = new Mercadoria();
   
   public Pagamento()
   {
      db = new DatabaseConnect(); 
      conn = db.getConnection();
   }
   
   
   //Cria um pagamento
   public void criaPagamento(double valor, int id_compra)
   {
      try
      {
         String data = p.getDateTime();
         String sql = "insert into pagamento(date,valor,id_compra) values (?,?,?)";
         st = conn.prepareStatement(sql);
         st.setString(1,data);
         st.setDouble(2,valor);
         st.setInt(3,id_compra);
         st.executeUpdate();
         st.close();
      }catch(Exception e)
      {
         JOptionPane.showMessageDialog(null,"Erro ao criar pagamento","PDV - ERRO",0);
         e.printStackTrace();
      }   
   }
   
   public int id_pagamento()
   {
      try
      {
          
          String sql = "SELECT MAX(id_pagamento) FROM pagamento";
          st = conn.prepareStatement(sql);
          ResultSet resultSet = st.executeQuery();
          
          if(resultSet.next() == true)
          {
            id_pagamento = resultSet.getInt("MAX(id_pagamento)");
          } 
          st.close();
      }catch(Exception e)
      {
         JOptionPane.showMessageDialog(null,"Erro ao criar pagamento","PDV - ERRO",0);   
      }
      return id_pagamento;
   } 
         
}