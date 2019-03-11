package venda.view;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import venda.negocio.Compra;
import venda.negocio.ItemCompra;
import venda.negocio.Pagamento;
import venda.negocio.PagamentoCartao;
import venda.negocio.PagamentoDinheiro;

public class PontoDeVenda extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1229423932061582763L;
	private JLabel lNome, lCpf, lNumerocartao, lValidadeCartao, lValorPago, lValorDaCompra; // Rótulos utilizados para
																							// pagamento
	private JTextField tNome, tCpf, tNumerocartao, tValidadeCartao, tValorPago, tValorDaCompra;// Caixas de textos
																								// utilizados para
																								// pagamento
	private JButton bOk1, bOk2, bFechar; // Botões para as telas de pagamento
	Pagamento idPagamento;
	Compra idCompra;

	ItemCompra ic = new ItemCompra();

	public PontoDeVenda() {

	}

	// Retorna a data para o aplicativo arquivar os dados no banco de dados
	public String getDateTime() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		return dateFormat.format(date);
	}

	// Banco de dados
	public void criaCompra() {
		idCompra = new Compra();
		idCompra.criaCompra();
	}

	public int id_compra() {
		return idCompra.id_compra();
	}

	public int id_pagamento() {
		return idPagamento.id_pagamento();
	}

	// Funções de alguns botoes.
	// Cria uma nova compra
	public void novaCompra() {
		criaCompra();// Cria um nova compra
		id_compra();// Recupera código da comprar
	}

	public void criaPagamento2() {
		idPagamento = new Pagamento();
		idPagamento.criaPagamento(precoTotal(), id_compra());
	}

	// Confirma se o usuário vai mesmo cancelar a compra.
	public boolean cancelarComprar() {
		int opc = JOptionPane.showConfirmDialog(null,
				"Tem certeza que deseja cancelar a comprar, todos os dados serão perdidos.", "PDV - MENSAGEM",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (opc == JOptionPane.OK_OPTION) {
			JOptionPane.showMessageDialog(null, "Compra cancelado com sucesso", "PDV - MENSAGEM", 1);
			return true;
		}
		return false;
	}

	// Remove uma mercadoria
	public boolean removeMercadoria(int id_mercadoria) {
		boolean resultado = false;
		if (ic.removeMercadoria(id_mercadoria, id_compra())) {
			JOptionPane.showMessageDialog(null, "Mercadoria excluída com sucesso!", "PDV - MENSAGEM", 1);
			resultado = true;
		} else {
			JOptionPane.showMessageDialog(null, "Não conseguimos excluir a mercadoria.", "PDV - MENSAGEM", 2);
		}
		return resultado;
	}

	// Finaliza a aplicação.
	public void sairPDV() {
		try {
			int opc = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja Fechar o PDV ? .", "PDV - MENSAGEM",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (opc == JOptionPane.OK_OPTION) {
				ic.removeMercadoria(id_compra());
				JOptionPane.showMessageDialog(null, "O PDV será encerrado, Obigado por comprar conosco.",
						"PDV - MENSAGEM", 1);
				System.exit(0);
			} else {
				JOptionPane.showMessageDialog(null, "Operação cancelada pelo usuário", "PDV - ALERTA", 2);
			}
		} catch (java.lang.NullPointerException e) {
			JOptionPane.showMessageDialog(null, "O PDV será encerrado, Obigado por comprar conosco.", "PDV - MENSAGEM",
					1);
			System.exit(0);
		}
	}

	// Adiciona os produtos na lista.
	public void adicionarProdutos(int id_mercadoria, int quantidadeItens) {
		ic.adicionarItens(id_mercadoria, quantidadeItens, id_compra());
	}

	public String getMercadoria() {
		return ic.listaDeCompras(id_compra());
	}

	// retorna o preço subtraido
	public double precoTotal() {
		return ic.getPrecoCompra(id_compra());
	}

	// Pega os 10 produtos mais vendidos
	public String getTopDez() {
		return ic.getTopDez();
	}

	public int getQuantidadeMercadoria() {
		return ic.getQuantidadeMercadoria(id_compra());
	}

	/*
	 * O método opcPagamento recebe o opção de pagamento escolhida pelo usuário e o
	 * preço da venda, então ele cria a tela conforme a opção escolhida.
	 */
	public void opcPagamento(int operacao, double valorDaCompra)// *
	{
		if (operacao == 0) {
			setTitle("PDV - PAGAMENTO CARTÃO");
			setSize(300, 200);
			setLocation(50, 250);
			setResizable(false);

			Container janela = getContentPane();
			janela.setLayout(new GridLayout(0, 2));
			janela.removeAll();

			lNome = new JLabel("Nome:");
			lCpf = new JLabel("CPF:");
			lNumerocartao = new JLabel("Número do cartão:");
			lValidadeCartao = new JLabel("Validade do cartão:");
			lValorDaCompra = new JLabel("Valor da compra: R$");
			tNome = new JTextField(20);
			tCpf = new JTextField(20);
			tNumerocartao = new JTextField(20);
			tValidadeCartao = new JTextField(20);
			tValorDaCompra = new JTextField(20);
			bOk1 = new JButton("OK");
			bOk1.addActionListener(this);
			bFechar = new JButton("Fechar PDV");
			bFechar.addActionListener(this);

			setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

			janela.add(lNome);
			janela.add(tNome);
			janela.add(lCpf);
			janela.add(tCpf);
			janela.add(lNumerocartao);
			janela.add(tNumerocartao);
			janela.add(lValidadeCartao);
			janela.add(tValidadeCartao);
			janela.add(lValorDaCompra);
			janela.add(tValorDaCompra);
			janela.add(bOk1);
			janela.add(bFechar);

			tValorDaCompra.setEnabled(false);
			bFechar.setEnabled(false);
			tValorDaCompra.setText("" + valorDaCompra);
			setVisible(true);
		} else {
			setTitle("PDV - PAGAMENTO DINHEIRO");
			setSize(300, 200);
			setLocation(50, 250);
			setResizable(false);

			Container janela = getContentPane();
			janela.setLayout(new GridLayout(0, 2));
			janela.removeAll();

			lNome = new JLabel("Nome:");
			lCpf = new JLabel("CPF:");
			lValorPago = new JLabel("Valor pago:");
			lValorDaCompra = new JLabel("Valor da compra: R$");
			bOk2 = new JButton("OK");
			bOk2.addActionListener(this);
			tNome = new JTextField(20);
			tCpf = new JTextField(20);
			tValorPago = new JTextField(20);
			tValorDaCompra = new JTextField(20);
			bFechar = new JButton("Fechar PDV");
			bFechar.addActionListener(this);

			setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

			janela.add(lNome);
			janela.add(tNome);
			janela.add(lCpf);
			janela.add(tCpf);
			janela.add(lValorPago);
			janela.add(tValorPago);
			janela.add(lValorDaCompra);
			janela.add(tValorDaCompra);
			janela.add(bOk2);
			janela.add(bFechar);

			tValorDaCompra.setEnabled(false);
			bFechar.setEnabled(false);
			tValorDaCompra.setText("" + valorDaCompra);
			setVisible(true);
		}
	}

	// Ação dos botões

	public void actionPerformed(ActionEvent evento) {

		if (evento.getSource() == bOk1) {
			try {
				int numCar = Integer.parseInt(tNumerocartao.getText());
				int valCar = Integer.parseInt(tValidadeCartao.getText());
				double valCom = Double.parseDouble(tValorDaCompra.getText());

				// Envia os dados para a classe PagamentoCartao.
				PagamentoCartao c = new PagamentoCartao(tNome.getText(), tCpf.getText(), numCar, valCar, valCom);

				if (c.autorizacao() == true) {
					// Desativa alguns botões.
					JOptionPane.showMessageDialog(null, c.getDados(), "PDV - INFORMAÇÃO SOBRE PAGAMENTO", 1);
					JOptionPane.showMessageDialog(null, "Compra finalizada com sucesso", "PDV - MENSAGEM", 1);
					bOk1.setEnabled(false);
					tNome.setEnabled(false);
					tCpf.setEnabled(false);
					tNumerocartao.setEnabled(false);
					tValidadeCartao.setEnabled(false);
					bFechar.setEnabled(true);
					this.setVisible(false);
				} else {
					JOptionPane.showMessageDialog(null,
							"Pagamento NÃO AUTORIZADO pela operadora.\nVerifique os dados e tente novamente",
							"PDV - ALERTA", 2);
				}

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Erro, verifique os valores digitados sobre o cartão", "PDV - ERRO",
						0);
			}
		}
		if (evento.getSource() == bOk2) {
			try {
				double va = Double.parseDouble(tValorPago.getText()); // Recupera o valor pago pelo cliente.
				double valCom = Double.parseDouble(tValorDaCompra.getText());// Recupera o valor da comprar

				// Envia os dados para a classe PagamentoDinheiro.
				PagamentoDinheiro p = new PagamentoDinheiro(tNome.getText(), tCpf.getText(), va, valCom);

				if (p.dados().equals("O valor pago pelo cliente não cobre todos os custos.")) {
					JOptionPane.showMessageDialog(null, p.dados(), "PDV - INFORMAÇÃO SOBRE PAGAMENTO", 1);
					JOptionPane.showMessageDialog(null, "Pagamento não concluido", "PDV - ALERTA", 2);
				} else {
					JOptionPane.showMessageDialog(null, p.dados(), "PDV - INFORMAÇÃO SOBRE PAGAMENTO", 1);
					JOptionPane.showMessageDialog(null, "Compra finalizada com sucesso", "PDV - MENSAGEM", 1);
					bOk2.setEnabled(false);
					tNome.setEnabled(false);
					tCpf.setEnabled(false);
					tValorPago.setEnabled(false);
					bFechar.setEnabled(true);
					this.setVisible(false);
				}

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Erro, verifique os valores digitados", "PDV - ERRO", 0);
			}
		}
		if (evento.getSource() == bFechar) {
			sairPDV();
		}
	}
}