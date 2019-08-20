/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mirangolo.sales.mb;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;

import javax.faces.application.FacesMessage;
import javax.faces.application.ViewHandler;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import mirangolo.sales.ejbs.ArtigoFacade;
import mirangolo.sales.ejbs.ItensVendaFacade;
import mirangolo.sales.ejbs.VendaFacade;
import mirangolo.sales.entities.Artigo;
import mirangolo.sales.entities.Cliente;
import mirangolo.sales.entities.ItensVenda;
import mirangolo.sales.entities.Venda;
import mirangolo.sales.relatorios.GestorImpressao;
import mirangolo.sales.relatorios.GestorRelatoriosMB;

import org.primefaces.event.CellEditEvent;

/**
 *
 * @author informatica
 */
@Named(value = "carrinhoBean")
@SessionScoped
public class CarrinhoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    VendaMB vendaMB;

    @Inject
    VendaFacade vendaFacade;

    @Inject
    ArtigoFacade artigoFacade;

    @Inject
    ItensVendaFacade itensVendaFacade;

    @Inject
    Artigo artigo;

    @Inject
    GestorRelatoriosMB gestorRelatoriosMB;

    @Inject
    private GestorImpressao gestorImpressao;

    private List<ItensVenda> carrinho = new ArrayList<>();
    private List<Cliente> clientes;

    private Cliente cliente;
    private Venda venda;
    private Integer facturaActual;
    private float total;

    @PostConstruct
    public void inti() {

        cliente = new Cliente();

        venda = new Venda();
        setFacturaActual(vendaFacade.lastFactura() + 1);
        clientes = new ArrayList<>();

    }

    public Integer getFacturaActual() {
        return facturaActual;
    }

    public void setFacturaActual(Integer facturaActual) {
        this.facturaActual = facturaActual;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public CarrinhoBean() {
    }

    private int isExisting(Artigo artigo) {
        System.out.println(">>>>>>>>>>>>>>>>>>>>Passou Teste 1");
        for (int i = 0; i < this.carrinho.size(); i++) {
            if (this.carrinho.get(i).getIdArtigo().getIdArtigo().equals(artigo.getIdArtigo())) {
                return i;
            }
        }
        return -1;

    }

    public void actualizarCarrinho01(Artigo artigo) {
        if (artigo != null) {
            this.carrinho.set(this.isExisting(artigo), new ItensVenda(artigo));
        }
    }

    public void actualizarCarrinho(Artigo artigo, double quantidade) {
        for (int i = 0; i < carrinho.size(); i++) {
            if (carrinho.get(i).getIdArtigo().equals(artigo.getIdArtigo())) {
                carrinho.get(i).setQuantidadeProduto(quantidade);
            }
        }
    }

    public void onCellEdit(CellEditEvent event) {
        System.err.println("Valor caputador" + event.getNewValue());
        double valor = (Double) event.getNewValue();
        actualizarCarrinho(artigo, valor);
        suma();
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Quantidade altarada", "");
        FacesContext.getCurrentInstance().addMessage(null, msg);

    }

    public double suma() {
        double s = 0.0;
        for (ItensVenda it : this.carrinho) {
            s += it.getQuantidadeProduto() * it.getIdArtigo().getPrecoArtigo();
        }
        return s;
    }

    public void delete(ItensVenda it) {
        System.out.println("Delelte >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        if (this.carrinho != null && !this.carrinho.isEmpty()) {
            if (it != null) {
                this.carrinho.remove(it);
            }
        }

    }

    public String addicionarProdutoCarrinho(Artigo artigo) {
        System.out.println(">>>>>>>>>>>>>>>>> A inserir produto");

        int index = isExisting(artigo);
        if (index == -1) {
            this.carrinho.add(new ItensVenda(artigo, artigo.getPrecoArtigo(), 1.0));
        } else {
            double quantidade = this.carrinho.get(index).getQuantidadeProduto() + 1;
            this.carrinho.get(index).setQuantidadeProduto(quantidade);
        }
        // return "carrinho?face-redirect=true";
        System.out.println("Produto Inserido com sucesso");
        return null;
    }

    public List<ItensVenda> getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(List<ItensVenda> carrinho) {
        this.carrinho = carrinho;
    }

    public void limparCarrinho() {

        carrinho.clear();
    }

    public void registarCompra(ActionEvent event) {
        // guarda a factura -> busca a ultima factura

        double totalFactura = suma();
        // define o total da factura actual

        vendaMB.getVenda().setValorTotal(totalFactura);
        

        //chamada do metodo que regista a factura actual
        vendaMB.registarFactura();

        //busca a ultima factura registada
        Integer numeroFatura = vendaFacade.lastFactura();
        //define o nunmeo da factura actual
        venda.setIdVenda(numeroFatura);

        //percorre o carrinho e regista cada item
        for (ItensVenda item : carrinho) {
           
            item.setIdVenda(venda);
            item.setTaxaIva(14.0);
            itensVendaFacade.create(item);
            artigoFacade.acutalizaQuantidade(item.getIdArtigo().getIdArtigo(), item.getQuantidadeProduto());
        }
        carrinho.clear();

        imprimirFactura2();
    }

    public String imprimirFactura() {

        Integer numeroFactura = vendaFacade.ultimaFactura();
        System.out.println(">>>>>>>>imprimirFactura()" + numeroFactura);
        String relatorio = "factura.jasper";
        HashMap parametros = new HashMap();
        parametros.put("id_venda", numeroFactura);
        gestorImpressao.imprimirPDF2(relatorio, parametros);

        return null;

    }

    public void imprimirFactura2() {
        Integer numeroFactura = vendaFacade.ultimaFactura();
        gestorRelatoriosMB.imprimirFactura(numeroFactura);

    }

    public void recarregarPagina() {
        FacesContext context = FacesContext.getCurrentInstance();
        String viewId = context.getViewRoot().getViewId();
        ViewHandler handler = context.getApplication().getViewHandler();
        UIViewRoot root = handler.createView(context, viewId);
        root.setViewId(viewId);
        context.setViewRoot(root);

    }

    public void actualizarTotal(AjaxBehaviorEvent event) {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>actualizar Soma");
        UIViewRoot view = FacesContext.getCurrentInstance().getViewRoot();
        UIComponent component = view.findComponent("total");
        System.out.println("mirangolosales.mb.CarrinhoBean.actualizarTotal()" + component);
        suma();
    }

}
