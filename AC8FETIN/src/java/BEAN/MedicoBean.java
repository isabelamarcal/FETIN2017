/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BEAN;

import DAO.MedicoDAO;
import POCO.Medico;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 *
 * @author Bianca
 */
@ManagedBean
public class MedicoBean {

    private MedicoDAO mdao;
    private Medico selecionado;
    private String localizado;

    public MedicoBean() {
        mdao = new MedicoDAO();
        selecionado = new Medico();
    }

    public void inserir(Medico m){
        try {
            selecionado = mdao.cadastro(m);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successo!", "Medico cadastrado."));

        } catch (SQLException ex) {
            Logger.getLogger(MedicoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public List<Medico> listaMedicos(){
        return mdao.readAll();
    }
    
    public void buscar(){
        String[] l = new String[100];
        l = localizado.split(" ");
        int t = l.length;
        selecionado =  mdao.getMedicoCRM(l[t-1]);
    }
    
    public void excluir(){
        mdao.deleta(selecionado);
    }
    
     public void atualizar(int id){
        selecionado.setId(id);
        mdao.editar(selecionado);
    }

    public Medico getSelecionado() {
        return selecionado;
    }

    public void setSelecionado(Medico selecionado) {
        this.selecionado = selecionado;
    }

    public String getLocalizado() {
        return localizado;
    }

    public void setLocalizado(String localizado) {
        this.localizado = localizado;
    }

}
