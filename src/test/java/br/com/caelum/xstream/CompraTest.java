package br.com.caelum.xstream;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import com.thoughtworks.xstream.XStream;

public class CompraTest {

    @Test
    public void deveSerializarCadaUmDosProdutosDeUmaCompra() {
        String resultadoEsperado = "<compra>\n"+
                "  <id>15</id>\n"+
                "  <produtos>\n"+
                "    <produto codigo=\"1587\">\n"+
                "      <nome>geladeira</nome>\n"+
                "      <preco>1000.0</preco>\n"+
                "      <descrição>geladeira duas portas</descrição>\n"+
                "    </produto>\n"+
                "    <produto codigo=\"1588\">\n"+
                "      <nome>ferro de passar</nome>\n"+
                "      <preco>100.0</preco>\n"+
                "      <descrição>ferro com vaporizador</descrição>\n"+
                "    </produto>\n"+
                "  </produtos>\n"+
                "</compra>";
        Compra compra = compraComGeladeiraEFerro();
        
        XStream xStream = xStreamParaCompraEProduto();
        
        String xmlGerado = xStream.toXML(compra);
        assertEquals(resultadoEsperado, xmlGerado);
    }

    @Test
    public void deveGerarUmaCompraComOsProdutosAdequados() {
        String xmlDeOrigem = "<compra>\n"+
                "  <id>15</id>\n"+
                "  <produtos>\n"+
                "    <produto codigo=\"1587\">\n"+
                "      <nome>geladeira</nome>\n"+
                "      <preco>1000.0</preco>\n"+
                "      <descrição>geladeira duas portas</descrição>\n"+
                "    </produto>\n"+
                "    <produto codigo=\"1588\">\n"+
                "      <nome>ferro de passar</nome>\n"+
                "      <preco>100.0</preco>\n"+
                "      <descrição>ferro com vaporizador</descrição>\n"+
                "    </produto>\n"+
                "  </produtos>\n"+
                "</compra>";

        Compra compraEsperada = compraComGeladeiraEFerro();
        
        XStream xStream = xStreamParaCompraEProduto();
        
        Compra compraResultado = (Compra) xStream.fromXML(xmlDeOrigem);
        assertEquals(compraEsperada, compraResultado);
    }
    
    @Test
    public void deveSerializarDuasGeladeirasIguais() {
        String resultadoEsperado = "<compra>\n" 
                + "  <id>15</id>\n"
                + "  <produtos>\n" 
                + "    <produto codigo=\"1587\">\n"
                + "      <nome>geladeira</nome>\n"
                + "      <preco>1000.0</preco>\n"
                + "      <descrição>geladeira duas portas</descrição>\n"
                + "    </produto>\n"
                + "    <produto codigo=\"1587\">\n"
                + "      <nome>geladeira</nome>\n"
                + "      <preco>1000.0</preco>\n"
                + "      <descrição>geladeira duas portas</descrição>\n"
                + "    </produto>\n"
                + "  </produtos>\n" 
                + "</compra>";

        Compra compra = this.compraDuasGeladeirasIguais();
        XStream xStream = this.xStreamParaCompraEProduto();
        xStream.setMode(XStream.NO_REFERENCES);
        assertEquals(resultadoEsperado, xStream.toXML(compra));
    }
    
    @Test
    public void deveSerializarColecaoImplicita () {
        String resultadoEsperado = "<compra>\n"+
                "  <id>15</id>\n"+
                "  <produto codigo=\"1587\">\n"+
                "    <nome>geladeira</nome>\n"+
                "    <preco>1000.0</preco>\n"+
                "    <descrição>geladeira duas portas</descrição>\n"+
                "  </produto>\n"+
                "  <produto codigo=\"1588\">\n"+
                "    <nome>ferro de passar</nome>\n"+
                "    <preco>100.0</preco>\n"+
                "    <descrição>ferro com vaporizador</descrição>\n"+
                "  </produto>\n"+
                "</compra>";
        
        Compra compra = compraComGeladeiraEFerro();
        
        XStream xStream = xStreamParaCompraEProduto();
        xStream.addImplicitCollection(Compra.class, "produtos");
        
        String xmlGerado = xStream.toXML(compra);
        assertEquals(resultadoEsperado, xmlGerado);
    }
    
    @Test
    public void deveSerializarLivroEMusica() {
        String resultadoEsperado = "<compra>\n" 
                + "  <id>15</id>\n"
                + "  <produtos class=\"linked-list\">\n" 
                + "    <livro codigo=\"1589\">\n"
                + "      <nome>O Pássaro Raro</nome>\n"
                + "      <preco>100.0</preco>\n"
                + "      <descrição>dez histórias sobre a existência</descrição>\n"
                + "    </livro>\n" 
                + "    <musica codigo=\"1590\">\n"
                + "      <nome>Meu Passeio</nome>\n"
                + "      <preco>100.0</preco>\n"
                + "      <descrição>música livre</descrição>\n"
                + "    </musica>\n"
                + "  </produtos>\n" 
                + "</compra>";

        Compra compra = this.compraComLivroEMusica();

        XStream xstream = this.xStreamParaCompraEProduto();
        String xmlGerado = xstream.toXML(compra);

        assertEquals(resultadoEsperado, xmlGerado);
    }
    
    @Test
    public void deveUsarUmConversorDiferente() {
        String xmlEsperado = "<compra estilo=\"novo\">\n" 
                + "  <id>15</id>\n"
                + "  <fornecedor>guilherme.silveira@caelum.com.br</fornecedor>\n"
                + "  <endereco>\n"
                + "    <linha1>Rua Vergueiro 3185</linha1>\n"
                + "    <linha2>8 andar - Sao Paulo - SP</linha2>\n"
                + "  </endereco>\n"
                + "  <produtos>\n" 
                + "    <produto codigo=\"1587\">\n"
                + "      <nome>geladeira</nome>\n"
                + "      <preco>1000.0</preco>\n"
                + "      <descrição>geladeira duas portas</descrição>\n"
                + "    </produto>\n"
                + "    <produto codigo=\"1587\">\n"
                + "      <nome>geladeira</nome>\n"
                + "      <preco>1000.0</preco>\n"
                + "      <descrição>geladeira duas portas</descrição>\n"
                + "    </produto>\n"
                + "  </produtos>\n" 
                + "</compra>";
        
        Compra compra = compraDuasGeladeirasIguais();

        XStream xstream = xStreamParaCompraEProduto();
        xstream.setMode(XStream.NO_REFERENCES);
        xstream.registerConverter(new CompraDiferenteConverter());
        String xmlGerado = xstream.toXML(compra);

        assertEquals(xmlEsperado, xmlGerado);
        
        Compra deserializada = (Compra) xstream.fromXML(xmlGerado);
        assertEquals(compra, deserializada);
    }
    
    private Compra compraComGeladeiraEFerro() {
        Produto geladeira = geladeira();
        Produto ferro = ferro();
        List<Produto> produtos = new ArrayList<>();
        produtos.add(geladeira);
        produtos.add(ferro);
        
        Compra compra = new Compra(15, produtos);
        return compra;
    }
    
    private Compra compraDuasGeladeirasIguais() {
        Produto geladeira = geladeira();

        List<Produto> produtos = new ArrayList<Produto>();
        produtos.add(geladeira);
        produtos.add(geladeira);

        Compra compra = new Compra(15, produtos);
        return compra;
    }
    
    private Compra compraComLivroEMusica() {
        Produto passaro = new Livro("O Pássaro Raro", 100.0, "dez histórias sobre a existência", 1589);
        Produto passeio = new Musica("Meu Passeio", 100.0, "música livre", 1590);
        List<Produto> produtos = new LinkedList<>();
        produtos.add(passaro);
        produtos.add(passeio);

        return new Compra(15, produtos);
    }

    private XStream xStreamParaCompraEProduto() {
        XStream xStream = new XStream();
        xStream.alias("compra", Compra.class);
        xStream.alias("produto", Produto.class);
        xStream.alias("livro", Livro.class);
        xStream.alias("musica", Musica.class);
        xStream.aliasField("descrição", Produto.class, "descricao");
        xStream.useAttributeFor(Produto.class, "codigo");
        return xStream;
    }

    private Produto geladeira() {
        return new Produto("geladeira", 1000, "geladeira duas portas", 1587);
    }
    
    private Produto ferro() {
        return new Produto("ferro de passar", 100, "ferro com vaporizador", 1588);
    }
}