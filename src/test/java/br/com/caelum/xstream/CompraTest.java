package br.com.caelum.xstream;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
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
    
    private Compra compraComGeladeiraEFerro() {
        Produto geladeira = geladeira();
        Produto ferro = ferro();
        List<Produto> produtos = new ArrayList<>();
        produtos.add(geladeira);
        produtos.add(ferro);
        
        Compra compra = new Compra(15, produtos);
        return compra;
    }
    
    private XStream xStreamParaCompraEProduto() {
        XStream xStream = new XStream();
        xStream.alias("compra", Compra.class);
        xStream.alias("produto", Produto.class);
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