/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BEAN;

import DAO.MedicoDAO;
import POCO.Medico;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Bianca
 */
@ManagedBean
public class MedicoBean {

    private MedicoDAO mdao;
    private Medico selecionado;

    public MedicoBean() {
        mdao = new MedicoDAO();
        selecionado = new Medico();
    }

    public void inserir(Medico m){
        try {
            selecionado = mdao.cadastro(m);
        } catch (SQLException ex) {
            Logger.getLogger(MedicoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


}
