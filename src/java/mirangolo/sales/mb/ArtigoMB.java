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
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import mirangolo.sales.ejbs.ArtigoFacade;
import mirangolo.sales.ejbs.EstoqueFacade;
import mirangolo.sales.entities.Artigo;
import mirangolo.sales.entities.CategoriaArtigo;
import mirangolosales.util.JSFUtil;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author informatica
 */
@Named(value = "artigoMB")
///@RequestScoped
@SessionScoped
public class ArtigoMB implements Serializable {

    @Inject
    private ArtigoFacade artigoFacade;

    @Inject
    private CarrinhoBean carrinhoBean;

    @Inject
    CategoriaArtigo categoriaArtigo;
    
      @Inject
    private EstoqueMB estoqueMB;
      
    @Inject
    private EstoqueFacade estoqueFacade;

    private Artigo artigo;
    private List<Artigo> artigos;

    private List<Artigo> artigosCategorias;
    private List<Artigo> artigosPorNomes;
    
      private List<Artigo> artigosPorCodigoBarras;

    public ArtigoMB() {
    }

    @PostConstruct
    public void init() {
        artigo = new Artigo();
        artigos = artigoFacade.queryArtigosAll();
    }

    public Artigo getArtigo() {
        return artigo;
    }

    public void setArtigo(Artigo artigo) {
        this.artigo = artigo;
    }

    public List<Artigo> getArtigos() {
        return artigos;
    }

    public void setArtigos(List<Artigo> artigos) {
        this.artigos = artigos;
    }

    public CategoriaArtigo getCategoriaArtigo() {
        return categoriaArtigo;
    }

    public void setCategoriaArtigo(CategoriaArtigo categoriaArtigo) {
        this.categoriaArtigo = categoriaArtigo;
    }

    public List<Artigo> getArtigosCategorias() {
        return artigosCategorias;
    }

    public void setArtigosCategorias(List<Artigo> artigosCategorias) {
        this.artigosCategorias = artigosCategorias;
    }

    public List<Artigo> getArtigosPorNomes() {
        return artigosPorNomes;
    }

    public void setArtigosPorNomes(List<Artigo> artigosPorNomes) {
        this.artigosPorNomes = artigosPorNomes;
    }

    public List<Artigo> getArtigosPorCodigoBarras() {
        return artigosPorCodigoBarras;
    }

    public void setArtigosPorCodigoBarras(List<Artigo> artigosPorCodigoBarras) {
        this.artigosPorCodigoBarras = artigosPorCodigoBarras;
    }
    
    
    
    
    

    public List<SelectItem> getCarregarCategoriasArtigo() {
        List<SelectItem> selectItems = new ArrayList<>();
        List<Artigo> artigoList = artigoFacade.findAll();
        for (Artigo artigoActual : artigoList) {
            SelectItem item = new SelectItem(artigoActual, artigoActual.getNomeArtigo() + "(" + artigoActual.getCodigoComercialArtigo() + ")");
            selectItems.add(item);
        }
        return selectItems;
    }

    public void carregarArtigosDaCategoria() {

        artigosCategorias = artigoFacade.findArtigoByCategoria(categoriaArtigo);

    }

    public void save(ActionEvent actionEvent) {

        artigoFacade.create(artigo);
        artigo = new Artigo();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " Artigo registado com sucesso", "Guardar"));
        JSFUtil.refresh();
    }

    public void edit(ActionEvent actionEvent) {
        estoqueMB.pegaArtigo(artigo);
        
        estoqueMB.save();
        artigoFacade.edit(artigo);
        artigo = new Artigo();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " Artigo alterado com sucesso", "Editar"));
        JSFUtil.refresh();
    }

    public void delete(ActionEvent actionEvent) {
        artigoFacade.remove(artigo);
        artigo = new Artigo();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " Artigo alterado com sucesso", "Editar"));
        JSFUtil.refresh();
    }

    public List<Artigo> queryArtigos(String name) {
        List<Artigo> lista = new ArrayList<>();
        System.out.println(">>>>>>>>>>>>>>>>>>> Passou >>>>>>>>>>>>>>>>>>>>>>>");
        for (Artigo a : artigoFacade.queryArtigosAll()) {

            if (a.getNomeArtigo().toLowerCase().startsWith(name.toLowerCase())) {
                lista.add(a);
            }
        }
        return lista;
    }
    
    
    
     public List<Artigo> queryArtigosPorCodigoBarras(String codigoBarras) {
        List<Artigo> lista = new ArrayList<>();
        
        for (Artigo a : artigoFacade.queryArtigosAll()) {

            if (a.getCodigoBarraArtigo().toLowerCase().startsWith(codigoBarras.toLowerCase())) {
                lista.add(a);
            }
        }
        return lista;
    }

    public void selectListenerNomeArtgioAutoComplete(SelectEvent event) {
        Artigo a = (Artigo) event.getObject();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Artigo:\t" + a.getNomeArtigo() + "\t" + a.getPrecoArtigo()));
        carrinhoBean.addicionarProdutoCarrinho(a);

    }

    public void selectListenerArtgiosPorNomeAutoComplete(SelectEvent event) {
        Artigo a = (Artigo) event.getObject();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Artigo:\t" + a.getNomeArtigo() + "\t" + a.getPrecoArtigo()));
        artigosPorNomes = artigoFacade.findArtigosByNome(a.getNomeArtigo());

    }
    
    
    public void selectListenerArtgiosPorCodigoBarrasAutoComplete(SelectEvent event) {
        Artigo a = (Artigo) event.getObject();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Artigo:\t" + a.getNomeArtigo() + "\t" + a.getPrecoArtigo()));
        artigosPorCodigoBarras = artigoFacade.findArtigosByNome(a.getNomeArtigo());

    }

}
