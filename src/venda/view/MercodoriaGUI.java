package venda.view;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import venda.negocio.Mercadoria;

public class MercodoriaGUI extends JFrame {

	private static final long serialVersionUID = -8814244300734290722L;
	JTextArea areaLista; // Areas de texto
	Container janela; // Conteiner
	JPanel painel; // Painel
	JScrollPane barra_rolagem; // Barra de rolagem

	Mercadoria m = new Mercadoria();

	public MercodoriaGUI() {
		telaMercadoria();
	}

	public void telaMercadoria() {
		setSize(400, 600);
		setLocation(0, 50);
		setTitle("PDV - Lista de produtos");
		setResizable(false);
		janela = getContentPane();
		janela.setLayout(new BorderLayout());

		painel = new JPanel();
		areaLista = new JTextArea(100, 30);
		areaLista.setText(m.verProdutos());
		areaLista.setEditable(false);

		painel.add(areaLista);
		barra_rolagem = new JScrollPane(painel);
		this.add(barra_rolagem);
		this.setVisible(true);
	}
}