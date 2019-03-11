package venda.negocio;

import java.text.DecimalFormat;

public class PagamentoDinheiro {
	private String nome;
	private String cpf;
	private double valor; // Valor pago pelo cliente.
	public double valorDaCompra; // Ser� utilizado para fazer a conta do troco se houver.

	public PagamentoDinheiro(String nome, String cpf, double valor, double valorDaCompra) {
		this.nome = nome;
		this.cpf = cpf;
		this.valor = valor;
		this.valorDaCompra = valorDaCompra;
	}

	// M�todo construtor e modificador
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCpf() {
		return cpf;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public double getValor() {
		return valor;
	}

	// Outros m�todos
	public String dados() // Exibe os dados da combrar j� com o troco
	{
		String x;
		DecimalFormat duas = new DecimalFormat("00.00");

		if (getValor() >= valorDaCompra) {
			x = "\nNome do Cliente: " + getNome() + "\nCPF do cliente: " + getCpf() + "\nTroco do cliente: "
					+ duas.format((getValor() - valorDaCompra));
		} else {
			x = "O valor pago pelo cliente n�o cobre todos os custos.";
		}
		return x;
	}
	// Fim da classe
}