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
    
    public void logar(Usuario usuario){
        if("Médico".equals(usuario.getTipo())){
            mdao.login((Medico) usuario);
        }
        else{
            pdao.login((Paciente) usuario);
        }
    }
    
    
}
