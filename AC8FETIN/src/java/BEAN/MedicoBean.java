/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BEAN;

import CONSTANTES.Constantes;
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
    private Medico medico;
    private String localizado;

    public MedicoBean() {
        mdao = new MedicoDAO();
        medico = new Medico();
    }

    public void inserir(Medico m){
        try {
            medico = mdao.cadastro(m);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successo!", "Medico cadastrado."));

        } catch (SQLException ex) {
            Logger.getLogger(MedicoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public List<Medico> listaMedicos(){
        return mdao.readAll();
    }
    
    public void buscarCRM(){
        String[] l = new String[100];
        l = localizado.split(" ");
        int t = l.length;
        medico =  mdao.getMedicoCRM(l[t-1]);
    }
    
    public String buscarView(){
        medico = mdao.getMedico(Constantes.USUARIO.TIPO.IDMEDICO);
        return "medicoView";
    }
    
    public String buscarUpdate(){
        medico = mdao.getMedico(Constantes.USUARIO.TIPO.IDMEDICO);
        return "medicoUpdate";
    }
    
    public void excluir(){
        mdao.deleta(medico);
    }
    
     public void atualizar(int id){
        medico.setId(id);
        mdao.editar(medico);
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico selecionado) {
        this.medico = medico;
    }

    public String getLocalizado() {
        return localizado;
    }

    public void setLocalizado(String localizado) {
        this.localizado = localizado;
    }

}
