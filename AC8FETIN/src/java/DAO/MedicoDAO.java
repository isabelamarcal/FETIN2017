/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import POCO.Clinica;
import POCO.Medico;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Isabela
 */
public class MedicoDAO {

    public Medico cadastro(Medico data) throws SQLException {
        try {
            Connection conn = ConnectionManager.Conectar();

            String sql = "INSERT INTO medico (login, senha, nomeCompleto, especialidade, crm) VALUES('" + data.getLogin() + "','" + data.getSenha() + "','" + data.getNomeCompleto() + "','" + data.getEspecialidade() + "','" + data.getCrm() + "');";

            PreparedStatement preparedStmt = conn.prepareStatement(sql);

            preparedStmt.execute();

            sql = "SELECT * FROM medico WHERE login='" + data.getLogin() + "' AND senha='" + data.getSenha() + "';";

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                data.setId(rs.getInt("idMedico"));

            }
            st.close();
            conn.close();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return data;

    }

    public Medico login(Medico data) {

        try {
            Connection conn = ConnectionManager.Conectar();

            String sql = "SELECT * FROM medico WHERE login='" + data.getLogin() + "' AND senha='" + data.getSenha() + "';";

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                data.setId(rs.getInt("idMedico"));
                data.setNomeCompleto(rs.getString("nomeCompleto"));
                data.setEspecialidade(rs.getString("especialidade"));
                data.setCrm(rs.getString("crm"));
            }
            st.close();
            conn.close();
        } catch (Exception ex) {
        }
        return data;
    }

    public Medico getMedicoCRM(String crm) {
        Medico data = new Medico();
        try {
            Connection conn = ConnectionManager.Conectar();

            String sql = "SELECT * FROM medico WHERE crm='" + crm + "' ;";

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                data.setId(rs.getInt("idMedico"));
                data.setNomeCompleto(rs.getString("nomeCompleto"));
                data.setCrm(rs.getString("crm"));
                data.setLogin(rs.getString("login"));
                data.setSenha(rs.getString("senha"));
                data.setEspecialidade(rs.getString("especialidade"));
            }
            st.close();
            conn.close();
        } catch (Exception ex) {
        }
        return data;
    }
    
    public Medico getMedico(int id) {
        Medico data = new Medico();
        try {
            Connection conn = ConnectionManager.Conectar();

            String sql = "SELECT * FROM medico WHERE idMedico=" + id + " ;";

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                data.setId(rs.getInt("idMedico"));
                data.setNomeCompleto(rs.getString("nomeCompleto"));
                data.setCrm(rs.getString("crm"));
                data.setLogin(rs.getString("login"));
                data.setSenha(rs.getString("senha"));
                data.setEspecialidade(rs.getString("especialidade"));
                
                ArrayList<Clinica> clinicas = (ArrayList<Clinica>) new ClinicaDAO().clinicaByMedico(data.getId());
                data.setClinicas(clinicas);
            }
            st.close();
            conn.close();
        } catch (Exception ex) {
        }
        return data;
    }

    public List<Medico> readAll() {

        try {
            Connection conn = ConnectionManager.Conectar();

            String sql = "SELECT * FROM medico  ;";

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(sql);
            List<Medico> datas = new ArrayList<Medico>();
            while (rs.next()) {
                Medico data = new Medico();

                data.setId(rs.getInt("idMedico"));
                data.setNomeCompleto(rs.getString("nomeCompleto"));
                data.setCrm(rs.getString("crm"));
                data.setLogin(rs.getString("login"));
                data.setSenha(rs.getString("senha"));
                data.setEspecialidade(rs.getString("especialidade"));
                datas.add(data);
                
                ArrayList<Clinica> clinicas = (ArrayList<Clinica>) new ClinicaDAO().clinicaByMedico(data.getId());
                data.setClinicas(clinicas);
            }
            st.close();
            conn.close();
                    return datas;

        } catch (Exception ex) {
            return null;
        }
    }

    public void editar(Medico data) {
        try {
            Connection conn = ConnectionManager.Conectar();

            String sql = "UPDATE medico set login='"
                    + data.getLogin() + "', senha='" + data.getSenha() + "', nomeCompleto='" + data.getNomeCompleto() + "', especialidade='"
                    + data.getEspecialidade() + "', crm='" + data.getCrm() + "' where idMedico=" + data.getId() + ";";

            PreparedStatement preparedStmt = conn.prepareStatement(sql);

            preparedStmt.execute();

            conn.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }
    
    public void addClinica(int medicoId, String clinicaId) {
        try {
            Connection conn = ConnectionManager.Conectar();

            String sql = "insert into clinica_has_medico values("
                    + clinicaId + ", "+medicoId+");";

            PreparedStatement preparedStmt = conn.prepareStatement(sql);

            preparedStmt.execute();

            conn.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void deleta(Medico data) {
        try {
            Connection conn = ConnectionManager.Conectar();

            String sql = "DELETE from medico where idMedico=" + data.getId() + ";";

            PreparedStatement preparedStmt = conn.prepareStatement(sql);

            preparedStmt.execute();

            conn.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }
}
