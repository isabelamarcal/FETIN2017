/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BEAN;

import CONSTANTES.Constantes;
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

    public String logar(Usuario usuario) {
        try {
            if ("Médico".equals(usuario.getTipo())) {
                Medico m = new Medico();
                m.setLogin(usuario.getLogin());
                m.setSenha(usuario.getSenha());
                m = mdao.login(m);
                if (m.getId() != 0) {
                    Constantes.USUARIO.TIPO.IDMEDICO = m.getId();
                    return ("/medico/dashboard");

                }
                return "";
            } else {
                Paciente p = new Paciente();
                p.setLogin(usuario.getLogin());
                p.setSenha(usuario.getSenha());
                p = pdao.login(p);
                if (p.getId() != 0) {
                    Constantes.USUARIO.TIPO.IDPACIENTE = p.getId();
                    return ("/paciente/dashboard");

                }
                return "";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
                            return "";

        }
    }

}
