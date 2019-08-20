/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mirangolo.sales.dados;

import java.util.Date;
import mirangolo.sales.entities.Cliente;

/**
 *
 * @author informatica
 */
public class DadosVenda {
    private Integer idVenda;
    private Date dataVenda;
    private Double valorTotal;
    private Double descontoIvaTotal;
    private String nomeCliente;
    private String sobrenomeCliente;
    private Cliente cliente;
    private String formaDePagamento;
    private String nomeFuncionario;
    private String sobrenomeFuncionario;

    public DadosVenda() {
    }

    public DadosVenda(Integer idVenda, Date dataVenda, Double valorTotal, Double descontoIvaTotal, String nomeCliente, String sobrenomeCliente, String formaDePagamento, String nomeFuncionario, String sobrenomeFuncionario) {
        this.idVenda = idVenda;
        this.dataVenda = dataVenda;
        this.valorTotal = valorTotal;
        this.descontoIvaTotal = descontoIvaTotal;
        this.nomeCliente = nomeCliente;
        this.sobrenomeCliente = sobrenomeCliente;
        this.formaDePagamento = formaDePagamento;
        this.nomeFuncionario = nomeFuncionario;
        this.sobrenomeFuncionario = sobrenomeFuncionario;
    }

    public DadosVenda(Integer idVenda, Date dataVenda, Double valorTotal, Double descontoIvaTotal, Cliente cliente, String formaDePagamento, String nomeFuncionario, String sobrenomeFuncionario) {
        this.idVenda = idVenda;
        this.dataVenda = dataVenda;
        this.valorTotal = valorTotal;
        this.descontoIvaTotal = descontoIvaTotal;
        this.cliente = cliente;
        this.formaDePagamento = formaDePagamento;
        this.nomeFuncionario = nomeFuncionario;
        this.sobrenomeFuncionario = sobrenomeFuncionario;
    }
    
    

    public Integer getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(Integer idVenda) {
        this.idVenda = idVenda;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Double getDescontoIvaTotal() {
        return descontoIvaTotal;
    }

    public void setDescontoIvaTotal(Double descontoIvaTotal) {
        this.descontoIvaTotal = descontoIvaTotal;
    }

  

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getSobrenomeCliente() {
        return sobrenomeCliente;
    }

    public void setSobrenomeCliente(String sobrenomeCliente) {
        this.sobrenomeCliente = sobrenomeCliente;
    }

    public String getFormaDePagamento() {
        return formaDePagamento;
    }

    public void setFormaDePagamento(String formaDePagamento) {
        this.formaDePagamento = formaDePagamento;
    }

    public String getNomeFuncionario() {
        return nomeFuncionario;
    }

    public void setNomeFuncionario(String nomeFuncionario) {
        this.nomeFuncionario = nomeFuncionario;
    }

    public String getSobrenomeFuncionario() {
        return sobrenomeFuncionario;
    }

    public void setSobrenomeFuncionario(String sobrenomeFuncionario) {
        this.sobrenomeFuncionario = sobrenomeFuncionario;
    }
    
    
          
    
  
}
