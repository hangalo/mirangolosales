/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mirangolo.sales.relatorios;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;

import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import mirangolo.sales.ejbs.ArtigoFacade;
import mirangolo.sales.ejbs.VendaFacade;
import mirangolo.sales.entities.Artigo;
import mirangolo.sales.entities.Venda;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author informatica
 */
@Named(value = "gestorRelatoriosMB")

//@SessionScoped
@RequestScoped
public class GestorRelatoriosMB implements Serializable {

    @EJB
    private ArtigoFacade artigoFacade;

    @EJB
    private VendaFacade vendaFacade;

    JasperPrint jasperPrint;
    Connection conn;
    private int idFrate;

    private List<Artigo> artigos;

    private List<Venda> vendas;

    /**
     * Creates a new instance of FrateRapporti
     */
    public GestorRelatoriosMB() {
    }

    public int getIdFrate() {
        return idFrate;
    }

    public void setIdFrate(int idFrate) {
        this.idFrate = idFrate;
    }

    public void imprimirCatalogoArtigos(ActionEvent event) {

        artigos = artigoFacade.queryArtigosAll();

        try {

            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(artigos);

            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/WEB-INF/relatorios/catalogo.jasper");
            jasperPrint = JasperFillManager.fillReport(reportPath, new HashMap(), beanCollectionDataSource);

            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            // httpServletResponse.addHeader("Content-disposition", "attachment; filename=relatorio.pdf");
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            //JasperPrintManager.printPage(jasperPrint, 0, false);

            servletOutputStream.flush();
            servletOutputStream.close();
        } catch (IOException | JRException ioe) {

            ioe.printStackTrace();
        }
        vendas = null;
        FacesContext.getCurrentInstance().responseComplete();
        FacesContext.getCurrentInstance().responseComplete();
    }

    public void imprimirFacturaTest(ActionEvent event) {

        //  vendas = vendaFacade.findAll();
        vendas = vendaFacade.findVendaTodas();

        try {

            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(vendas);

            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/WEB-INF/relatorios/factura.jasper");

            jasperPrint = JasperFillManager.fillReport(reportPath, new HashMap(), beanCollectionDataSource);

            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            // httpServletResponse.addHeader("Content-disposition", "attachment; filename=relatorio.pdf");
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            //JasperPrintManager.printPage(jasperPrint, 0, false);

            servletOutputStream.flush();
            servletOutputStream.close();
        } catch (IOException | JRException ioe) {

            ioe.printStackTrace();
        }
        vendas = null;
        FacesContext.getCurrentInstance().responseComplete();
        FacesContext.getCurrentInstance().responseComplete();
    }

    public void imprimirFactura(Integer paramentro) {
        conn = Conexao.getConnection();
     //   vendas = vendaFacade.findVendaById(paramentro);

        try {

            //     JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(vendas);
            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/WEB-INF/relatorios/factura.jasper");

            //    JasperPrint jasperPrint = JasperFillManager.fillReport(this.nomeArquivo, this.parametro, this.connection);
            HashMap parametros = new HashMap();
            parametros.put("id_venda", paramentro);
            jasperPrint = JasperFillManager.fillReport(reportPath, parametros, conn);

            //  jasperPrint = JasperFillManager.fillReport(reportPath, new HashMap(), beanCollectionDataSource);
            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            // httpServletResponse.addHeader("Content-disposition", "attachment; filename=relatorio.pdf");
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            //JasperPrintManager.printPage(jasperPrint, 0, false);

            servletOutputStream.flush();
            servletOutputStream.close();
        } catch (IOException | JRException ioe) {

            ioe.printStackTrace();
        }
     //   vendas = null;
        FacesContext.getCurrentInstance().responseComplete();
        FacesContext.getCurrentInstance().responseComplete();
    }

}
