package venda.negocio;
import java.text.DecimalFormat;
import java.util.Random;

public class PagamentoCartao
{
   private String nome;
   private String cpf;
   private int numeroCartao;
   private int validadeDoCartao;
   private String saidaDodos; // retorna os dados do pagamento. 
   public double valorDaCompra; // Será utilizado para fazer a conta do troco se houver.
   
   public PagamentoCartao(String nome, String cpf, int numeroCartao ,int validadeDoCartao, double valorDaCompra)
   {
      this.nome = nome;
      this.cpf = cpf;
      this.numeroCartao = numeroCartao;
      this.validadeDoCartao = validadeDoCartao;
      this.valorDaCompra = valorDaCompra;
   }
   
   //Métodos modificadores e de acesso
   public void setNome(String nome)
   {
      this.nome = nome;
   }
   public String getNome()
   {
      return nome;
   }
   public void setCpf(String cpf)
   {
      this.cpf = cpf;
   }
   public String getCpf()
   {
      return cpf;
   }
   public void setValidadeDoCartao(int validadeDoCartao)
   {
      this.validadeDoCartao = validadeDoCartao;
   }
   public int getValidadeDoCartao()
   {
      return validadeDoCartao;
   }
   public void setNumeroCartao(int numeroCartao)
   {
      this.numeroCartao = numeroCartao;
   }
   public int getNumeroCartao()
   {
      return numeroCartao;
   }
   
   //Outros métodos
   public void setDados(String x)
   {
      saidaDodos = x;
   }
   public String getDados()
   {
      return saidaDodos;
   }
   public boolean autorizacao()
   {
      DecimalFormat duas = new DecimalFormat("00.00");
      
      Random aleatorio = new Random();
      boolean aprovacao = aleatorio.nextBoolean();
         
         if(aprovacao == true)
         {
            setDados("\nNome do Cliente: " + getNome() + "\nCPF do cliente: " + getCpf() 
            + "\nNúmero do cartão: " + getNumeroCartao() +"\nValidade do cartão: " + getValidadeDoCartao()
            +  "\nValor pago: " +  duas.format(valorDaCompra));
         }else
         {
            setDados("Pagamento NÃO AUTORIZADO pela operadora.");
         }
         
        return aprovacao;    
   }
   //Fim da classe    
}