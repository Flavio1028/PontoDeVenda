package venda.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class PontoDeVendaGUI extends JFrame implements ActionListener {
	private static final long serialVersionUID = 3710915419023091837L;
	JButton bNovaComprar, bCancelaComprar, bFechaComprar, bFechaPDV; // Bot�es relacionados ao lado esquerdo
	JButton bAdicionarMercadoria, bCancelaMercadoria, bExibeMercadorias, bExibeTop10; // Bot�es lado direiro
	JLabel lTotalComprar, lCodigoMercadoria, lValorComprar, lQuantidadeMercadoria; // R�tulos
	JTextField tCodigoMercadoria, tQuantidadeMercadoria; // Caixas de textos
	JScrollPane barra_rolagem; // Barra de rolagem
	String areaPrincipalMensagem = ""; // Dados escritos na �rea principal
	JPanel painelEsquerdo, painelDireito, painelCentro; // Paineis
	Container janela; // Conteiner;
	@SuppressWarnings("unused")
	private double x, y;
	private JTextArea areaPrincipal = new JTextArea(100, 30);
	private String area = "", fechamento = "";

	// Cria liga��o com a classe PontoDeVenda.
	PontoDeVenda p = new PontoDeVenda();

	public PontoDeVendaGUI() {
		principal();
	}

	// Janela central do aplicativo.
	public void principal() {
		// Itens b�sicos da janela
		setTitle("Mercado USJT - Ponto de Venda (PDV)");
		setSize(800, 450);
		setLocation(420, 100);
		setResizable(false);

		// Conteiner e layout da janela
		janela = getContentPane();
		janela.setLayout(new BorderLayout());

		// Personaliza��o do tema da janela.
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			// e.printStackTrace();
		}

		// painelEsquerda
		bNovaComprar = new JButton("Nova Comprar");
		bNovaComprar.addActionListener(this);
		bCancelaComprar = new JButton("Cancelar Comprar");
		bCancelaComprar.addActionListener(this);
		bFechaComprar = new JButton("Fechar Comprar");
		bFechaComprar.addActionListener(this);
		bFechaPDV = new JButton("Fechar PDV");
		bFechaPDV.addActionListener(this);
		lTotalComprar = new JLabel("Total Comprar");
		lValorComprar = new JLabel("00.00");

		// Criando e definindo o painel
		painelEsquerdo = new JPanel();
		painelEsquerdo.setLayout(new GridLayout(0, 1));
		painelEsquerdo.setPreferredSize(new Dimension(150, 100));
		painelEsquerdo.add(bNovaComprar);
		painelEsquerdo.add(bCancelaComprar);
		painelEsquerdo.add(bFechaComprar);
		painelEsquerdo.add(lTotalComprar);
		painelEsquerdo.add(lValorComprar);
		painelEsquerdo.add(bFechaPDV);

		// painelDireito
		bAdicionarMercadoria = new JButton("Adicionar Mercadoria");
		bAdicionarMercadoria.addActionListener(this);
		bCancelaMercadoria = new JButton("Remover Mercadoria");
		bCancelaMercadoria.addActionListener(this);
		bExibeMercadorias = new JButton("Exibe Mercadoria");
		bExibeMercadorias.addActionListener(this);
		bExibeTop10 = new JButton("Exibe TOP 10");
		bExibeTop10.addActionListener(this);
		lCodigoMercadoria = new JLabel("C�digo da Mercadoria");
		lQuantidadeMercadoria = new JLabel("Quantidade da Mercadoria");
		tCodigoMercadoria = new JTextField(20);
		tQuantidadeMercadoria = new JTextField(20);

		// Criando e definindo o painel
		painelDireito = new JPanel();
		painelDireito.setPreferredSize(new Dimension(150, 100));
		painelDireito.setLayout(new GridLayout(0, 1));
		painelDireito.add(lCodigoMercadoria);
		painelDireito.add(tCodigoMercadoria);
		painelDireito.add(lQuantidadeMercadoria);
		painelDireito.add(tQuantidadeMercadoria);
		painelDireito.add(bAdicionarMercadoria);
		painelDireito.add(bCancelaMercadoria);
		painelDireito.add(bExibeMercadorias);
		painelDireito.add(bExibeTop10);

		// Desativa alguns botoes.
		bCancelaComprar.setEnabled(false);
		bFechaComprar.setEnabled(false);
		bAdicionarMercadoria.setEnabled(false);
		bCancelaMercadoria.setEnabled(false);
		tCodigoMercadoria.setEnabled(false);
		tQuantidadeMercadoria.setEnabled(false);
		tCodigoMercadoria.setText("      ");
		tQuantidadeMercadoria.setText("     ");

		// Criando o painel centro
		painelCentro = new JPanel();
		painelCentro.setLayout(new BorderLayout());
		areaPrincipal.setEditable(false);

		barra_rolagem = new JScrollPane(areaPrincipal);
		this.add(barra_rolagem);
		painelCentro.add(barra_rolagem);

		janela.add(painelCentro, BorderLayout.CENTER);
		janela.add(painelEsquerdo, BorderLayout.WEST);
		janela.add(painelDireito, BorderLayout.EAST);

		tCodigoMercadoria.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if (tCodigoMercadoria.getText().equals("Digitar c�digo")) {
					tCodigoMercadoria.setText("");
				} else if (tCodigoMercadoria.getText().equals("")) {
					tCodigoMercadoria.setText("Digitar c�digo");
				}
			}
		});

		tQuantidadeMercadoria.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if (tQuantidadeMercadoria.getText().equals("Digitar quantidade")) {
					tQuantidadeMercadoria.setText("");
				} else if (tQuantidadeMercadoria.getText().equals("")) {
					tQuantidadeMercadoria.setText("Digitar quantidade");
				}
			}
		});

		this.setVisible(true);

	}

	// M�todos para escrever na tela principal.

	// Exibe mensagens de boas vindas e exibe a data da compra.
	private String cabecalho() {
		return areaPrincipalMensagem + "Bem vindo, comprar iniciada em " + p.getDateTime()
				+ "\n---------------------------------------------Lista de Compras----------------------------------------------\n";
	}

	int b;
	double d;

	// Exibe as informa��es sobre o fechamento da compra.
	private String fechamentoCompra(int unidades, double total) {
		DecimalFormat duas = new DecimalFormat("00.00");
		b = unidades;
		d = total;
		String c = "\n---------------------------------------------------------------------------------------------------------------------"
				+ "\nFECHAMENTO COMPRA"
				+ "\n---------------------------------------------------------------------------------------------------------------------"
				+ "\nUNIDADES: " + b + " Total R$ : " + duas.format(d)
				+ "\n---------------------------------------------------------------------------------------------------------------------";

		return c;
	}

	public void mudaAreaTexto() {
		areaPrincipal.setText(area + fechamento);
	}

	// Da a��es aos bot�es;

	public void actionPerformed(ActionEvent evento) {

		// Inicia uma nova compra.
		if (evento.getSource() == bNovaComprar) {
			areaPrincipal.setText(cabecalho());
			p.novaCompra();

			// muda os bot�es
			bNovaComprar.setEnabled(false);
			bCancelaComprar.setEnabled(true);
			bAdicionarMercadoria.setEnabled(true);
			tCodigoMercadoria.setEnabled(true);
			tQuantidadeMercadoria.setEnabled(true);
			tCodigoMercadoria.setText("Digitar c�digo");
			tQuantidadeMercadoria.setText("Digitar quantidade");
		}

		// Respons�vel por cancelar a comprar
		if (evento.getSource() == bCancelaComprar) {
			if (p.cancelarComprar() == true) {
				areaPrincipal.setText("Comprar cancelada com sucesso em: " + p.getDateTime()
						+ ", obrigado por compra conosco."
						+ "\n---------------------------------------------------------------------------------------------------------------------");
				lValorComprar.setText("Cancelado");
				tCodigoMercadoria.setText("Cancelado");
				tQuantidadeMercadoria.setText("Cancelado");

				// Desativa os bot�es
				bNovaComprar.setEnabled(true);
				bCancelaComprar.setEnabled(false);
				bAdicionarMercadoria.setEnabled(false);
				bCancelaMercadoria.setEnabled(false);
				tCodigoMercadoria.setEnabled(false);
				tQuantidadeMercadoria.setEnabled(false);
				bFechaComprar.setEnabled(false);
			} else {
				JOptionPane.showMessageDialog(null, "Opera��o cancelada pelo usu�rio", "PDV - ALERTA", 2);
			}
		}

		// Finaliza a aplica��o Ponto de Venda(PDV).
		if (evento.getSource() == bFechaPDV) {
			// Envia os dados para a classe PontoDeVenda.
			p.sairPDV();
		}

		// Adiciona produtos a lista de compras.
		if (evento.getSource() == bAdicionarMercadoria) {
			try {
				if (tCodigoMercadoria.getText().equals("") || tQuantidadeMercadoria.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Erro, os campos n�o podem estar vazios.", "PDV - ERRO", 0);
				} else {
					DecimalFormat duas = new DecimalFormat("00.00", new DecimalFormatSymbols(Locale.US));
					// Converte os campos de String para int.
					int codigoMercadoria = Integer.parseInt(tCodigoMercadoria.getText());
					int quantidadeMercadoria = Integer.parseInt(tQuantidadeMercadoria.getText());

					p.adicionarProdutos(codigoMercadoria, quantidadeMercadoria);

					areaPrincipal.setText(cabecalho() + p.getMercadoria());
					area = areaPrincipal.getText();

					fechamento = fechamentoCompra(p.getQuantidadeMercadoria(), p.precoTotal());

					String x = "" + duas.format(p.precoTotal());
					lValorComprar.setText(x);

					bCancelaComprar.setEnabled(true);
					bCancelaMercadoria.setEnabled(true);
					bFechaComprar.setEnabled(true);
					tCodigoMercadoria.setText("Digitar c�digo");
					tQuantidadeMercadoria.setText("Digitar quantidade");
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Erro, caracter n�o permitido.", "PDV - ERRO", 0);
				tCodigoMercadoria.setText("Digitar c�digo");
				tQuantidadeMercadoria.setText("Digitar quantidade");
			}
		}

		// Remover mercadoria do carrinho da lista de compras e subtrai o pre�o dela da
		// compra.
		if (evento.getSource() == bCancelaMercadoria) {
			int ativo = 1;
			do {
				try {
					String x = JOptionPane.showInputDialog(null, "Digite o c�digo do produto que deseja remover.",
							"PDV - MENSAGEM", 1);
					if (null == x) {
						JOptionPane.showMessageDialog(null, "Opera��o cancelada pelo usu�rio", "PDV - ALERTA", 2);
						ativo = 0;
					} else {
						int codigoMercadoria = Integer.parseInt(x);
						if (codigoMercadoria >= 1 && codigoMercadoria <= 88) {
							p.removeMercadoria(codigoMercadoria);
							areaPrincipal.setText(cabecalho() + p.getMercadoria());
							area = areaPrincipal.getText();

							fechamento = fechamentoCompra(p.getQuantidadeMercadoria(), p.precoTotal());

							lValorComprar.setText("" + (p.precoTotal()));
							ativo = 0;
						} else {
							JOptionPane.showMessageDialog(null, "Digite um c�digo v�lido.", "PDV - ALERTA", 2);
						}
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Digite um c�digo v�lido.", "PDV - ALERTA", 2);
				}
			} while (ativo == 1);
		}

		// Respons�vel por fechar a comprar e exibir na tela principal informa�oes sobre
		// o pagamento.
		if (evento.getSource() == bFechaComprar) {
			int opc = JOptionPane.showConfirmDialog(this,
					"Tem certeza que deseja fechar a compra ?\nVoc� n�o poder� adicionar mais itens ao carrinho",
					"PDV - MENSAGEM", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

			double ab = Double.parseDouble(lValorComprar.getText());
			if (opc == JOptionPane.OK_OPTION && ab > 0.0) {
				p.criaPagamento2();
				mudaAreaTexto();

				// Verifica a forma de pagamento.
				Object[] opcoes = { "Cat�o", "Dinheiro" };
				int opcPG = JOptionPane.showOptionDialog(null, "Escolha uma forma de pagamento", "PDV - MENSAGEM",
						JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcoes, opcoes[0]);

				double pg = Double.parseDouble(lValorComprar.getText());
				p.opcPagamento(opcPG, pg);

				bFechaComprar.setEnabled(false);
				bCancelaComprar.setEnabled(false);
				bAdicionarMercadoria.setEnabled(false);
				bCancelaMercadoria.setEnabled(false);
				tCodigoMercadoria.setEnabled(false);
				tQuantidadeMercadoria.setEnabled(false);
				bNovaComprar.setEnabled(true);
			} else if (ab <= 0) {
				JOptionPane.showMessageDialog(null,
						"Alerta - Opera��o sem sentido, para fechar uma compra voc� deve compra alguma coisa.",
						"PDV - ALERTA", 2);
			} else {
				JOptionPane.showMessageDialog(null, "Opera��o cancelada pelo usu�rio", "PDV - ALERTA", 2);
			}
		}

		// Exibe a lista de compras dispon�vel no banco de dados.
		if (evento.getSource() == bExibeMercadorias) {
			@SuppressWarnings("unused")
			MercodoriaGUI m = new MercodoriaGUI();
		}

		// Exibe o TOP 10 das mercadorias.
		if (evento.getSource() == bExibeTop10) {
			if (p.getTopDez().equals("")) {
				JOptionPane.showMessageDialog(null,
						"Essa informa��o n�o est� dispon�vel no momento, experimente comprar alguma coisa.",
						"PDV - TOP 10", 1);
			} else {
				JOptionPane.showMessageDialog(null, p.getTopDez(), "PDV - TOP 10", 1);
			}
		}
	}
}