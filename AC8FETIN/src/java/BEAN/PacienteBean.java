/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BEAN;

import CONSTANTES.Constantes;
import DAO.PacienteDAO;
import POCO.Paciente;
import java.sql.SQLException;
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
public class PacienteBean {

    private PacienteDAO pdao;
    private Paciente paciente;
    private String localizado;

    public PacienteBean() {
        pdao = new PacienteDAO();
        paciente = new Paciente();
    }

    public String inserir(Paciente p) {
        try {
            paciente = pdao.cadastro(p);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successo!", "Paciente cadastrado."));
        } catch (SQLException ex) {
            Logger.getLogger(MedicoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "PacienteLogin";
    }

//    
//    public void excluir(){
//        mdao.deleta(selecionado);
//    }
//    
//     public void atualizar(int id){
//        selecionado.setId(id);
//        mdao.editar(selecionado);
//    }
//
//    public Medico getSelecionado() {
//        return selecionado;
//    }
//
//
//    public void setLocalizado(String localizado) {
//        this.localizado = localizado;
//    }
    public String buscarView() {
        paciente = pdao.getPaciente(Constantes.USUARIO.TIPO.IDPACIENTE);
        return "pacienteView";
    }

    public String buscarUpdate() {
        paciente = pdao.getPaciente(Constantes.USUARIO.TIPO.IDPACIENTE);
        return "pacienteUpdate";
    }

    public String excluir() {
        paciente = pdao.getPaciente(Constantes.USUARIO.TIPO.IDPACIENTE);
        pdao.deleta(paciente);
        return "UpdateToIndexP";
    }

    public String atualizar(int id) {
        paciente.setId(id);
        pdao.editar(paciente);
        return "UpdateToReadP";
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
    
    public Paciente getCurrentPaciente(){
        return pdao.getPaciente(Constantes.USUARIO.TIPO.IDPACIENTE);
    }
    
}
