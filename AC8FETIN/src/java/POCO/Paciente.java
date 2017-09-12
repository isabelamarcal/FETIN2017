/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package POCO;

import javax.faces.bean.ManagedBean;

/**
 *
 * @author Isabela
 */
@ManagedBean
public class Paciente extends Usuario{
    
    private String tipoSanguineo;
private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getTipoSanguineo() {
        return tipoSanguineo;
    }

    public void setTipoSanguineo(String tipoSanguineo) {
        this.tipoSanguineo = tipoSanguineo;
    }
    
}
