/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BEAN;

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
    private Paciente selecionado;
    private String localizado;

    public PacienteBean() {
        pdao = new PacienteDAO();
        selecionado = new Paciente();
    }

    public void inserir(Paciente m){
        try {
            selecionado = pdao.cadastro(m);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successo!", "Paciente cadastrado."));
        } catch (SQLException ex) {
            Logger.getLogger(MedicoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
   //     return "login";
    }
    
//    public List<Medico> listaMedicos(){
//        return mdao.readAll();
//    }
//    
//    public void buscar(){
//        String[] l = new String[100];
//        l = localizado.split(" ");
//        int t = l.length;
//        selecionado =  mdao.getMedicoCRM(l[t-1]);
//    }
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
//    public void setSelecionado(Medico selecionado) {
//        this.selecionado = selecionado;
//    }
//
//    public String getLocalizado() {
//        return localizado;
//    }
//
//    public void setLocalizado(String localizado) {
//        this.localizado = localizado;
//    }

}
