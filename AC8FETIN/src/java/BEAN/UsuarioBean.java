/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BEAN;

import DAO.MedicoDAO;
import DAO.PacienteDAO;
import POCO.Medico;
import POCO.Paciente;
import POCO.Usuario;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Bianca
 */
@ManagedBean
public class UsuarioBean {
    
    private MedicoDAO mdao;
    private PacienteDAO pdao;

    public UsuarioBean() {    
        mdao = new MedicoDAO();
        pdao = new PacienteDAO();
    }
    
    public String logar(Usuario usuario){
        if("Médico".equals(usuario.getTipo())){
            Medico m = new Medico();
            m.setLogin(usuario.getLogin());
            m.setSenha(usuario.getSenha());
            m = mdao.login(m);
            if(m.getId()!=0){
                        return("medicoDashboard");

            }
            return "";
        }
        else{
             Paciente m = new Paciente();
            m.setLogin(usuario.getLogin());
            m.setSenha(usuario.getSenha());
            m = pdao.login(m);
            if(m.getId()!=0){
                        return("pacienteDashboard");

            }
            return "";
        }
    }
    
    
}
