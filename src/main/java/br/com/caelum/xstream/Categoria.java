package br.com.caelum.xstream;

public class Categoria {
    private Categoria pai;
    private final String nome;
    
    public Categoria(Categoria pai, String nome) {
        this.pai = pai;
        this.nome = nome;
    }

    public Categoria getPai() {
        return pai;
    }

    public void setPai(Categoria pai) {
        this.pai = pai;
    }
    
    public String getNome() {
        return nome;
    }
}