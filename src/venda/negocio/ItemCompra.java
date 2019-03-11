package venda.negocio;
import java.sql.*;
import javax.swing.JOptionPane;

import venda.connection.DatabaseConnect;

import java.text.DecimalFormat;

public class ItemCompra {
	private PreparedStatement st;
	Connection conn;
	DatabaseConnect db;
	@SuppressWarnings("unused")
	private DecimalFormat duas;
	Mercadoria m = new Mercadoria();

	public ItemCompra() {
		db = new DatabaseConnect();
		conn = db.getConnection();
		duas = new DecimalFormat("00.00");
	}

	public void adicionarItens(int id_mercadoria, int quantidadeItens, int id_compra) {
		try {
			String sql = "insert into itemmercadoria(id_mercadoria,quantidade,id_compra) values (?,?,?)";
			st = conn.prepareStatement(sql);
			st.setInt(1, id_mercadoria);
			st.setInt(2, quantidadeItens);
			st.setInt(3, id_compra);
			st.executeUpdate();
			st.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Código inválido.", "PDV - ERRO", 0);
			JOptionPane.showMessageDialog(null,
					"Experimente consultar a lista de mercadorias disponíveis clicando em EXIBIR MERCADORIAS.",
					"PDV - ALERTA", 2);
			// e.printStackTrace();
		}
	}

	public boolean removeMercadoria(int id_mercadoria, int id_compra) {
		boolean resultado = false;

		try {
			String sql = "delete from itemmercadoria where id_mercadoria=(?) and id_compra = (?)";
			st = conn.prepareStatement(sql);
			st.setInt(1, id_mercadoria);
			st.setInt(2, id_compra);
			st.executeUpdate();
			resultado = true;
			st.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao remover mercadoria", "PDV - ERRO", 0);
		}
		return resultado;
	}

	public boolean removeMercadoria(int id_compra) {
		boolean resultado = false;

		try {
			String sql = "delete from itemmercadoria where id_compra = (?)";
			st = conn.prepareStatement(sql);
			st.setInt(1, id_compra);
			st.executeUpdate();
			resultado = true;
			st.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao remover mercadoria", "PDV - ERRO", 0);
		}
		return resultado;
	}

	public String listaDeCompras(int id_compra) {
		String lista = "";
		try {
			String sql = "SELECT im.id_temmercadoria, im.id_compra, im.quantidade, m.id_mercadoria, m.descricao, m.preco FROM itemmercadoria im INNER JOIN   mercadoria m USING(id_mercadoria) where id_compra = (?)";
			st = conn.prepareStatement(sql);
			st.setInt(1, id_compra);
			ResultSet resultSet = st.executeQuery();

			while (resultSet.next()) {
				lista += "Código mercadoria: " + resultSet.getInt("m.id_mercadoria") + " Descrição: "
						+ resultSet.getString("m.Descricao") + " Preço: " + resultSet.getDouble("m.preco")
						+ " Quantidade: " + resultSet.getInt("im.quantidade") + " Total: "
						+ (resultSet.getDouble("m.preco") * resultSet.getInt("im.quantidade")) + "\n\n";
			}
			st.close();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao exibir lista", "PDV - ERRO", 0);
		}
		return lista;
	}

	public double getPrecoCompra(int id_compra) {
		double valor = 0.0;

		try {
			String sql = "select im.quantidade, m.preco from itemmercadoria im inner join  mercadoria m using(id_mercadoria) where id_compra = (?)";
			st = conn.prepareStatement(sql);
			st.setInt(1, id_compra);
			ResultSet resultSet = st.executeQuery();

			while (resultSet.next()) {
				valor = valor + resultSet.getInt("im.quantidade") * resultSet.getDouble("m.preco");
			}
			st.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao exibir lista", "PDV - ERRO", 0);
			e.printStackTrace();
		}
		return valor;
	}

	// retorna as dez mercadorias mais compradas
	@SuppressWarnings("unused")
	public String getTopDez() {
		String lista = "";
		int somaQ;
		int idMer;
		int desc;

		try {
			String sql = "select m.id_mercadoria, im.quantidade, m.descricao from itemmercadoria im inner join  mercadoria m using(id_mercadoria) order by quantidade desc limit 10";
			st = conn.prepareStatement(sql);
			ResultSet resultSet = st.executeQuery();

			while (resultSet.next()) {
				lista += "Código mercadoria: " + resultSet.getInt("m.id_mercadoria") + " Total comprado: "
						+ resultSet.getInt("im.quantidade") + " Descrição: " + resultSet.getString("m.descricao")
						+ "\n";
			}
			st.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao exibir lista", "PDV - ERRO", 0);
		}
		return lista;
	}

	// retorna quantidade de mercadoria
	public int getQuantidadeMercadoria(int id_compra) {
		int quantidade = 0;
		try {
			String sql = "select sum(im.quantidade), im.id_compra from itemmercadoria im inner join   mercadoria m using(id_mercadoria) where id_compra = (?)";
			st = conn.prepareStatement(sql);
			st.setInt(1, id_compra);

			ResultSet resultSet = st.executeQuery();

			while (resultSet.next()) {
				quantidade = resultSet.getInt("sum(im.quantidade)");
			}
			st.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao exibir lista", "PDV - ERRO", 0);
		}
		return quantidade;
	}
}