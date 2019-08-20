/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mirangolo.sales.relatorios;

import java.io.OutputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;

/**
 *
 * @author PENA
 */
public class GerenciadorImpressao implements Serializable{
     private static final long serialVersionUID = 1L;

    private String nomeArquivo;
    private Map<String, Object> parametro;
    private Connection connection;

    public GerenciadorImpressao() {
    }

    public GerenciadorImpressao(String nomeArquivo, Map<String, Object> parametro, Connection connection) {
        this.nomeArquivo = nomeArquivo;
        this.parametro = parametro;
        this.connection = connection;
    }

    public void geraPDF(OutputStream outputStream) {
        try {
            JasperPrint jasperPrint = JasperFillManager.fillReport(this.nomeArquivo, this.parametro, this.connection);
            JRExporter exporter = new JRPdfExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
            exporter.exportReport();
        } catch (JRException e) {
            System.err.println("Ocorreu um erro com o JASPER ao gerar PDF..." + e.getMessage());
        }
    }

    public void geraDocx(OutputStream outputStream) {
        try {
            JasperPrint jasperPrint = JasperFillManager.fillReport(this.nomeArquivo, this.parametro, this.connection);
            JRExporter exporter = new JRDocxExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
            exporter.exportReport();
        } catch (JRException e) {
            System.err.println("Ocorreu um erro com o JASPER ao Gerar Docx..." + e.getMessage());
        }
    }

    public void geraExcel(OutputStream outputStream) {
        try {
            JasperPrint jasperPrint = JasperFillManager.fillReport(this.nomeArquivo, this.parametro, this.connection);
            JRExporter exporter = new JRXlsxExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
            exporter.exportReport();
        } catch (JRException e) {
            System.err.println("Ocorreu um erro com o JASPER ao Gerar Excel..." + e.getMessage());
        }
    }
}
