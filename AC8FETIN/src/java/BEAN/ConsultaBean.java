/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BEAN;

import DAO.ConsultaDAO;
import DAO.MedicoDAO;
import POCO.Clinica;
import POCO.Consulta;
import POCO.Medico;
import POCO.Paciente;
import static com.sun.xml.bind.util.CalendarConv.formatter;
import java.sql.SQLException;
import java.util.Date;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Bianca
 */
@ManagedBean
public class ConsultaBean {
    
    private String clinicaSelecionada;
    private String medicoSelecionado;
    private Date dataSelecionada;
    private String data;
    private MedicoBean mbean;
    private ClinicaBean cbean;
    private PacienteBean pbean;
    private Consulta consulta;
    private Clinica clinica;
    private Medico medico;
    private Paciente paciente;
    private ConsultaDAO cdao;

    public void inserir() throws SQLException{
        clinica = cbean.buscarClinicabyEndereco(clinicaSelecionada);
        //data = formatter.format(dataSelecionada);
        medico = mbean.buscarCRM(medicoSelecionado);
        paciente = pbean.getCurrentPaciente();
        consulta = new Consulta();
        consulta.setClinica(clinica);
        consulta.setMedico(medico);
        consulta.setData(dataSelecionada);
        consulta.setPaciente(paciente);
        cdao.cadastro(consulta);
    }
    
    public Date getDataSelecionada() {
        return dataSelecionada;
    }

    public void setDataSelecionada(Date dataSelecionada) {
        this.dataSelecionada = dataSelecionada;
    }

    public String getClinicaSelecionada() {
        return clinicaSelecionada;
    }

    public void setClinicaSelecionada(String clinicaSelecionada) {
        this.clinicaSelecionada = clinicaSelecionada;
    }

    public String getMedicoSelecionado() {
        return medicoSelecionado;
    }

    public void setMedicoSelecionado(String medicoSelecionado) {
        this.medicoSelecionado = medicoSelecionado;
    }
    
    
    
}
