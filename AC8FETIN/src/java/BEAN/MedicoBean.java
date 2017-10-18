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
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

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

     private LineChartModel lineConsultas;
     
    @PostConstruct
    public void init() {
        createLineModels();
    }
    
    private void createLineModels() {
        lineConsultas = initLinearModel();
        lineConsultas.setTitle("Ultimas consultas");
        lineConsultas.setLegendPosition("e");
        Axis yAxis = lineConsultas.getAxis(AxisType.Y);
        lineConsultas.setShowPointLabels(true);
        lineConsultas.getAxes().put(AxisType.X, new CategoryAxis("Months"));
        yAxis = lineConsultas.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(10);
         
        
    }
 
    public LineChartModel getLineConsultas() {
        return lineConsultas;
    }
     private LineChartModel initLinearModel() {
        LineChartModel model = new LineChartModel();
 
        LineChartSeries series1 = new LineChartSeries();
        series1.setLabel("Nº de consultas");
 
        series1.set(1, 2);
        series1.set(2, 1);
        series1.set(3, 3);
        series1.set(4, 6);
        series1.set(5, 8);
 
        model.addSeries(series1);
         
        return model;
    }
    
    public String inserir(Medico m){
        try {
            medico = mdao.cadastro(m);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successo!", "Medico cadastrado."));

        } catch (SQLException ex) {
            Logger.getLogger(MedicoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "MedicoLogin";
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
    
    public String excluir(){
        medico = mdao.getMedico(Constantes.USUARIO.TIPO.IDMEDICO);
        mdao.deleta(medico);
        return "UpdateToIndex";
    }
    
     public String atualizar(int id){
        medico.setId(id);
        mdao.editar(medico);
        return "UpdateToRead";
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
