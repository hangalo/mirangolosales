/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mirangolo.sales.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import mirangolo.sales.ejbs.ClienteFacade;
import mirangolo.sales.entities.Artigo;
import mirangolo.sales.entities.Cliente;
import mirangolosales.util.DateUtil;
import mirangolosales.util.JSFUtil;
import mirangolosales.util.ResourceFiles;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Francis
 */
@Named(value = "clienteMB")
@RequestScoped
public class ClienteMB implements Serializable {

    @Inject
    ClienteFacade clienteFacade;
    @Inject
    ResourceFiles resourceFiles;

    private Cliente cliente;
    private List<Cliente> clientes;
    private List<Cliente> clientesPorNumeroContribuite;
    private List<Cliente> clientesPorNomeSobrenome;
    private String nome, sobrenome;

    public ClienteMB() {
    }

    @PostConstruct
    public void init() {
        clientes = clienteFacade.findAll();
        cliente = new Cliente();
        cliente.setDataCadastro(DateUtil.getDataActual());
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public List<Cliente> getClientesPorNumeroContribuite() {
        return clientesPorNumeroContribuite;
    }

    public void setClientesPorNumeroContribuite(List<Cliente> clientesPorNumeroContribuite) {
        this.clientesPorNumeroContribuite = clientesPorNumeroContribuite;
    }

    public List<Cliente> getClientesPorNomeSobrenome() {
        return clientesPorNomeSobrenome;
    }

    public void setClientesPorNomeSobrenome(List<Cliente> clientesPorNomeSobrenome) {
        this.clientesPorNomeSobrenome = clientesPorNomeSobrenome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }
    
    
    
    

    public List<SelectItem> getCarregarClientes() {
        List<SelectItem> selectItems = new ArrayList<>();
        List<Cliente> clienteList = clienteFacade.findAll();
        for (Cliente clienteActual : clienteList) {
            SelectItem item = new SelectItem(clienteActual, clienteActual.getNomeCliente() + "\t" + clienteActual.getSobrenomeCliente());
            selectItems.add(item);
        }
        return selectItems;
    }

    public List<Cliente> queryClientesPorNumeroContribuinte(String numeroContribuinte) {
        List<Cliente> lista = new ArrayList<>();

        for (Cliente c : clienteFacade.queryClientesAll()) {

            if (c.getNumeroContribuinte().toLowerCase().startsWith(numeroContribuinte.toLowerCase())) {
                lista.add(c);
            }
        }
        return lista;
    }

    public void selectListenerNumeroContribuinteClienteAutoComplete(SelectEvent event) {
        Cliente c = (Cliente) event.getObject();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Artigo:\t" + c.getNomeCliente() + "\t" + c.getSobrenomeCliente()));
        clientesPorNumeroContribuite = clienteFacade.queryClienteByNumeroContribuinte(c.getNumeroContribuinte());
     
    }

      public void buscarClientesPorNomeSobrenome(ActionEvent actionEvent) {
          clientesPorNomeSobrenome = clienteFacade.queryClienteByNomeSobrenome(nome, sobrenome);
      }
    public void save(ActionEvent actionEvent) {

        clienteFacade.create(cliente);
        cliente = new Cliente();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " Cliente registado com sucesso", "Guardar"));
        JSFUtil.refresh();
    }

    public void edit(ActionEvent actionEvent) {

        clienteFacade.edit(cliente);
        cliente = new Cliente();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " Cliente alterado com sucesso", "Editar"));
        JSFUtil.refresh();
    }

    public void delete(ActionEvent actionEvent) {
        clienteFacade.remove(cliente);
        cliente = new Cliente();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " Cliente alterado com sucesso", "Editar"));
        JSFUtil.refresh();
    }

}
