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
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import mirangolo.sales.dados.DadosVenda;
import mirangolo.sales.ejbs.ClienteFacade;
import mirangolo.sales.ejbs.VendaFacade;
import mirangolo.sales.entities.Cliente;
import mirangolo.sales.entities.Venda;
import mirangolo.sales.relatorios.GestorRelatoriosMB;
import mirangolosales.util.DateUtil;

/**
 *
 * @author Francis
 */
@Named(value = "vendaMB")
@SessionScoped
public class VendaMB implements Serializable {

    @Inject
    VendaFacade vendaFacade;

    @Inject
    ClienteFacade clienteFacade;
    
  

    private Venda venda;
    private List<Venda> vendas;
    
     private List<Venda> vendasAoCliente;
      private List<Venda> diarioVendas;

    private DadosVenda dadosVenda;
    private List<DadosVenda> dadosVendas;
    private Date inicio, fim;
    private Cliente cliente;

    public VendaMB() {
    }

    @PostConstruct
    public void init() {
        venda = new Venda();
        venda.setDataVenda(DateUtil.getDataActual());
        cliente = new Cliente();

    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getFim() {
        return fim;
    }

    public void setFim(Date fim) {
        this.fim = fim;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<DadosVenda> getDadosVendas() {
        return dadosVendas;
    }

    public void setDadosVendas(List<DadosVenda> dadosVendas) {
        this.dadosVendas = dadosVendas;
    }

    public List<Venda> getVendas() {
        return vendas;
    }

    public void setVendas(List<Venda> vendas) {
        this.vendas = vendas;
    }

    public List<Venda> getVendasAoCliente() {
        return vendasAoCliente;
    }

    public void setVendasAoCliente(List<Venda> vendasAoCliente) {
        this.vendasAoCliente = vendasAoCliente;
    }

    public List<Venda> getDiarioVendas() {
        return diarioVendas;
    }

    public void setDiarioVendas(List<Venda> diarioVendas) {
        this.diarioVendas = diarioVendas;
    }
    
    
    
    
    
    
    
    
    

    public void registarFactura() {
      
        vendaFacade.create(venda);
         
        
    }

    
    
    
    public void definirValorTotal(Double valor) {
        venda.setValorTotal(valor);
    }

    public void executarBuscarVendasEntreDatas(ActionEvent event) {

        System.out.println("Passou >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>00");
        buscarVendasEntreDatas();

        System.out.println("Passou >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>02");
    }

    public List<Venda> buscarVendasEntreDatas() {
        diarioVendas = vendaFacade.findBetweenDataNascita(inicio, fim);
        System.out.println("Passou >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>01");
        return diarioVendas;
    }

    public void loadCliente(ValueChangeEvent event) {
        cliente = (Cliente) event.getNewValue();

        System.out.println("Cliente" + cliente.getNomeCliente() + " Sobrenome" + cliente.getSobrenomeCliente());

        //   dadosVendas = vendaFacade.findClienteByCliente(cliente);
        vendasAoCliente = vendaFacade.findClienteByCliente(cliente);

      

    }

    public List<Venda> getVendasEntreDatas() {
        vendas = vendaFacade.findBetweenDataNascita(inicio, fim);

      

        return vendas;
    }

}
