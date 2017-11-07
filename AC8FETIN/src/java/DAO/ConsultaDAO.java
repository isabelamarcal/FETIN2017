/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import POCO.Clinica;
import POCO.Consulta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Isabela
 */
public class ConsultaDAO {
     public Consulta cadastro(Consulta data) throws SQLException {
        try {
            Connection conn = ConnectionManager.Conectar();
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String sql = "INSERT INTO consulta (data, Clinica_idClinica, Medico_idMedico, Paciente_idPaciente) VALUES('" + format.format(data.getData())+ "'," + data.getClinica().getId()+ "," + data.getMedico().getId()+ "," + data.getPaciente().getId()+ ");";

            PreparedStatement preparedStmt = conn.prepareStatement(sql);

            preparedStmt.execute();

            conn.close();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return data;

    }


    public List<Consulta> consultaMedico(int medicoId) {

        try {
            Connection conn = ConnectionManager.Conectar();
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            String sql = "SELECT * FROM consulta where Medico_idMedico="+ medicoId +" ;";

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(sql);
            List<Consulta> datas = new ArrayList<Consulta>();
            while (rs.next()) {
                Consulta data = new Consulta();

                data.setClinica(new ClinicaDAO().getClinica(rs.getInt("Clinica_idClinica")));
                data.setMedico(new MedicoDAO().getMedico(rs.getInt("Medico_idMedico")));
                data.setPaciente(new PacienteDAO().getPaciente(rs.getInt("Paciente_idPaciente")));
                data.setData(new java.sql.Date(format.parse(rs.getString("data")).getTime()));

                datas.add(data);
            }
            st.close();
            conn.close();
                    return datas;

        } catch (Exception ex) {
            return null;
        }
    }
    
    
     public List<Consulta> consultaPaciente(int pacienteId) {

        try {
            Connection conn = ConnectionManager.Conectar();
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            String sql = "SELECT * FROM consulta where Paciente_idPaciente="+ pacienteId +" ;";

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(sql);
            List<Consulta> datas = new ArrayList<Consulta>();
            while (rs.next()) {
                Consulta data = new Consulta();

                data.setClinica(new ClinicaDAO().getClinica(rs.getInt("Clinica_idClinica")));
                data.setMedico(new MedicoDAO().getMedico(rs.getInt("Medico_idMedico")));
                data.setPaciente(new PacienteDAO().getPaciente(rs.getInt("Paciente_idPaciente")));
                data.setData(new java.sql.Date(format.parse(rs.getString("data")).getTime()));

                datas.add(data);
            }
            st.close();
            conn.close();
                    return datas;

        } catch (Exception ex) {
            return null;
        }
    }

   

    
}
