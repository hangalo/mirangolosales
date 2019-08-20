/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mirangolo.sales.relatorios;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.util.HashMap;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author informatica
 */

@Named(value = "gestorImpressao")

@SessionScoped
public class GestorImpressao implements Serializable{

    FacesContext facesContext;
    ServletContext servletContext;
    JasperPrint jasperPrint;
    Connection conn;

    public GestorImpressao() {
    }

    public String imprimir(String relatorio, HashMap paramentos) {
        try {
            prepararRelatorio(relatorio, paramentos);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            JRPdfExporter pdfExporter = new JRPdfExporter();
            pdfExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            pdfExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);
            pdfExporter.exportReport();
            byte[] bytes = baos.toByteArray();
            if (bytes != null && bytes.length > 0) {
                HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
                response.setContentType("application/pdf");
                response.setHeader("Content-Disposition", "filename=\"" + relatorio + "\"");

                // response.setHeader( "Content-Disposition", "attachment;filename=" + relatorio+".pdf" );
                //response.setHeader("Content-Disposition", "attachment; filename=\"" + relatorio  + "\"");
                response.setContentLength(bytes.length);
                ServletOutputStream outputStream = response.getOutputStream();
                outputStream.write(bytes, 0, bytes.length);
                outputStream.flush();
                outputStream.close();

            }
        } catch (JRException | IOException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public String imprimirPDF(String relatorio, HashMap paramentos) {
        try {
            
            prepararRelatorio(relatorio, paramentos);
            HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
            ServletOutputStream outputStream = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
            FacesContext.getCurrentInstance().responseComplete();
            outputStream.flush();
            outputStream.close();
        } catch (JRException | IOException ex) {
            System.out.println("Erro - >>>>" + ex.getMessage());
        }
        FacesContext.getCurrentInstance().responseComplete();
        FacesContext.getCurrentInstance().responseComplete();
        return null;
    }

    public void imprimirPDF2(String relatorio, HashMap paramentos) {
        try {
            System.out.println("Loading...");
            JasperDesign jasDsgn = JRXmlLoader.load(getPathPastaAplicacaoJSF() + relatorio);
            JasperReport jasRpt = JasperCompileManager.compileReport(jasDsgn);
            JasperPrint jasPrnt = JasperFillManager.fillReport(jasRpt, paramentos);
            JasperViewer.viewReport(jasPrnt);
            System.out.println("Done");
        } catch (Exception ex) {
            System.out.println("Cannot Load");
            System.err.println("Exception: " + ex.getMessage());
        }
    }

    private void prepararRelatorio(String relatorio, HashMap hashMap) {
        conn = Conexao.getConnection();

        System.out.println(">>>>>>> Relatio>>>>>" + getPathPastaAplicacaoJSF() + relatorio);

        try {
            facesContext = FacesContext.getCurrentInstance();
            facesContext.responseComplete();
            jasperPrint = JasperFillManager.fillReport(getPathPastaAplicacaoJSF() + relatorio, hashMap, conn);
        } catch (JRException jRException) {
            System.out.printf(" Relatorios nao localizado " + jRException.getMessage());
        }
    }

    private String getPathPastaAplicacaoJSF() {
        String separador = System.getProperty("file.separator");
        String pasta = "WEB-INF" + separador + "relatorios" + separador;
        String raizAplicacao = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
        return raizAplicacao + pasta;
    }

}
