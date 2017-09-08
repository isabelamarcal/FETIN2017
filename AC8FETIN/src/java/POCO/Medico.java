/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package POCO;

import java.util.ArrayList;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Isabela
 */
@ManagedBean
public class Medico extends Usuario{
    private String especialidade;
    private String cmr;
    private ArrayList<Clinica> clinicas;
    private int id;

    public ArrayList<Clinica> getClinicas() {
        return clinicas;
    }

    public void setClinicas(ArrayList<Clinica> clinicas) {
        this.clinicas = clinicas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getCmr() {
        return cmr;
    }

    public void setCmr(String cmr) {
        this.cmr = cmr;
    }
    
    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }
}
