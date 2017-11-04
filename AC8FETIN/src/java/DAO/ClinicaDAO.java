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
public class ClinicaDAO {
     public Clinica cadastro(Clinica data) throws SQLException {
        try {
            Connection conn = ConnectionManager.Conectar();

            String sql = "INSERT INTO clinica (endereco, cidade, estado) VALUES('" + data.getEndereco()+ "','" + data.getCidade()+ "','" + data.getEstado()+ "');";

            PreparedStatement preparedStmt = conn.prepareStatement(sql);

            preparedStmt.execute();

            sql = "SELECT * FROM clinica WHERE endereco='" + data.getEndereco()+ "' AND cidade='" + data.getCidade()+ "';";

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                data.setId(rs.getInt("idClinica"));

            }
            st.close();
            conn.close();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return data;

    }
     
    
        public Clinica getClinicaEndereco(String endereco) {
        Clinica data = new Clinica();
        try {
            Connection conn = ConnectionManager.Conectar();

            String sql = "SELECT * FROM clinica WHERE endereco LIKE " + endereco + " ;";

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                data.setId(rs.getInt("idClinica"));
                data.setCidade(rs.getString("cidade"));
                data.setEndereco(rs.getString("endereco"));
                data.setEstado(rs.getString("estado"));
                
            }
            st.close();
            conn.close();
        } catch (Exception ex) {
        }
        return data;
    }
   

    public Clinica getClinica(int id) {
        Clinica data = new Clinica();
        try {
            Connection conn = ConnectionManager.Conectar();

            String sql = "SELECT * FROM clinica WHERE idClinica=" + id + " ;";

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                data.setId(rs.getInt("idClinica"));
                data.setCidade(rs.getString("cidade"));
                data.setEndereco(rs.getString("endereco"));
                data.setEstado(rs.getString("estado"));
                
            }
            st.close();
            conn.close();
        } catch (Exception ex) {
        }
        return data;
    }

    public List<Clinica> readAll() {

        try {
            Connection conn = ConnectionManager.Conectar();

            String sql = "SELECT * FROM clinica;";

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(sql);
            List<Clinica> datas = new ArrayList<Clinica>();
            while (rs.next()) {
                Clinica data = new Clinica();

                data.setId(rs.getInt("idClinica"));
                data.setCidade(rs.getString("cidade"));
                data.setEndereco(rs.getString("endereco"));
                data.setEstado(rs.getString("estado"));
                datas.add(data);
            }
            st.close();
            conn.close();
                    return datas;

        } catch (Exception ex) {
            return null;
        }
    }
    
    public List<Clinica> clinicaByMedico(int idMedico) {

        try {
            Connection conn = ConnectionManager.Conectar();

            String sql = "SELECT * FROM clinica inner join clinica_has_medico where  clinica.idClinica = clinica_has_medico.Clinica_idclinica and clinica_has_medico.Medico_idMedico = "+ idMedico +";";

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(sql);
            List<Clinica> datas = new ArrayList<Clinica>();
            while (rs.next()) {
                Clinica data = new Clinica();

                data.setId(rs.getInt("idClinica"));
                data.setCidade(rs.getString("cidade"));
                data.setEndereco(rs.getString("endereco"));
                data.setEstado(rs.getString("estado"));
                datas.add(data);
            }
            st.close();
            conn.close();
                    return datas;

        } catch (Exception ex) {
            return null;
        }
    }

    public void editar(Clinica data) {
        try {
            Connection conn = ConnectionManager.Conectar();

            String sql = "UPDATE clinica set cidade='"
                    + data.getCidade()+ "', endereco='" + data.getEndereco()+ "', estado='" + data.getEstado()+ "' where idClinica=" + data.getId() + ";";

            PreparedStatement preparedStmt = conn.prepareStatement(sql);

            preparedStmt.execute();

            conn.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void deleta(Clinica data) {
        try {
            Connection conn = ConnectionManager.Conectar();

            String sql = "DELETE from clinica where idClinica=" + data.getId() + ";";

            PreparedStatement preparedStmt = conn.prepareStatement(sql);

            preparedStmt.execute();

            conn.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }
}
