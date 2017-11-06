/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BEAN;

import DAO.ClinicaDAO;
import POCO.Clinica;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 *
 * @author Isabela
 */

@ManagedBean
public class ClinicaBean {
    private Clinica clinica;
    private ClinicaDAO clinicaDAO;
    private String selecionado;

    public Clinica getClinica() {
        return clinica;
    }

    public void setClinica(Clinica clinica) {
        this.clinica = clinica;
    }

    public ClinicaBean() {
        clinica = new Clinica();
    }
    
    public void criarClinica(Clinica c){
        try {
            c = new ClinicaDAO().cadastro(c);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successo!", "Clínica cadastrada."));

        } catch (SQLException ex) {
            Logger.getLogger(MedicoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Clinica> clinicaByMedico(int idMedico){
        if(idMedico == -1){
            return clinicaDAO.readAll();
        }
        return clinicaDAO.clinicaByMedico(idMedico);
    }
    
    public List<Clinica> listClinicas(){
        return clinicaDAO.readAll();
    }

    public String getSelecionado() {
        return selecionado;
    }

    public void setSelecionado(String selecionado) {
        this.selecionado = selecionado;
    }
    
    public Clinica buscarClinicabyEndereco(String cli){
        String endereco = new String();
        endereco = cli.split(",")[0];
        return new ClinicaDAO().getClinicaEndereco(endereco);
    }
    
}
