/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mirangolo.sales.mb;

import java.io.IOException;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
/**
 *
 * @author informatica
 */
@Named(value = "loginMB")
@RequestScoped
public class LoginMB {

    private String usuario;
    private String senha;

    public LoginMB() {
    }

    @PostConstruct
    public void init() {

    }

    public String logar() throws IOException {
        String pagina = "/home-pages/admin-entrata-home_1?faces-redirect=true";
        //FacesContext context = FacesContext.getCurrentInstance();
        if (this.usuario.equals("Elisio") && this.senha.equals("1234")) {
            /*try {
                context.getExternalContext().redirect("/mirangolosales/home-pages/admin-entrata-home_1.jsf");

            } catch (Exception e) {
                System.out.println("Erro ao logar!  " + e.getMessage());
            }*/
          
            return pagina;
        } else {
            /*try {
                context.getExternalContext().redirect("/mirangolosales/index_1.jsf");
            } catch (Exception e) {
                 System.out.println("Erro ao logar 2!  " + e.getMessage());
            }*/
            
            
            pagina = "/index_1?faces-redirect=true";
            return pagina;
        }
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}
