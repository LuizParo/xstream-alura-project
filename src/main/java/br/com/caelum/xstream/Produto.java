package br.com.caelum.xstream;

public class Produto {
    private String nome;
    private double preco;
    private String descricao;
    private int codigo;
    
    public Produto(String nome, double preco, String descricao, int codigo) {
        this.nome = nome;
        this.preco = preco;
        this.descricao = descricao;
        this.codigo = codigo;
    }
    
    public String getNome() {
        return nome;
    }
    public double getPreco() {
        return preco;
    }
    public String getDescricao() {
        return descricao;
    }
    public int getCodigo() {
        return codigo;
    }
}