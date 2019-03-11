package venda.negocio;
import java.sql.*;
import java.text.*;
import java.util.Locale;
import javax.swing.JOptionPane;

import venda.connection.DatabaseConnect;

public class Mercadoria {
	private String produtos;
	private PreparedStatement st;
	Connection conn;
	DatabaseConnect db;

	DecimalFormat duas = new DecimalFormat("00.00", new DecimalFormatSymbols(Locale.US));

	public Mercadoria() {
		db = new DatabaseConnect();
		conn = db.getConnection();
	}

	// Exibe toda a lista de produtos
	public String verProdutos() {
		produtos = "";
		try {
			String sql = "SELECT * FROM mercadoria ORDER BY id_mercadoria";
			st = conn.prepareStatement(sql);
			ResultSet resultSet = st.executeQuery();

			while (resultSet.next()) {
				produtos = produtos + "C�digo: " + resultSet.getInt("id_mercadoria") + " ,Produto: "
						+ resultSet.getString("descricao") + " ,Pre�o: " + duas.format(resultSet.getDouble("preco"))
						+ "\n\n";
			}
			st.close();
		} catch (Exception e) {
			produtos = "Fala ao consutar lista de produtos.";
		}

		return produtos;
	}

	// Procura um produto em espec�fico e adiciona ele na lista de compras.
	public String criaLista(int codigo, int quantidade) {
		String produto = "C�digo do produto n�o localizado.";
		try {
			String sql = "SELECT * FROM mercadoria WHERE id_mercadoria = (?)";
			st = conn.prepareStatement(sql);
			st.setInt(1, codigo);
			ResultSet resultSet = st.executeQuery();

			while (resultSet.next() == true) {
				produto = "C�digo: " + resultSet.getInt("id_mercadoria") + " ,Produto: "
						+ resultSet.getString("descricao") + " ,Pre�o da unidade: "
						+ duas.format(resultSet.getDouble("preco")) + "\nPre�o total:"
						+ (resultSet.getDouble("preco") * quantidade) + "\n\n";
			}
			st.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao adicionar produto.", "PDV - ERRO", 0);
		}
		return produto;
	}
}