/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import POCO.Medico;
import POCO.Paciente;
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
public class PacienteDAO {
        public Paciente cadastro(Paciente data) throws SQLException {
        try {
            Connection conn = ConnectionManager.Conectar();

            String sql = "INSERT INTO paciente (login, senha, nomeCompleto, tipoSanguineo) VALUES('" + data.getLogin() + "','" + data.getSenha() + "','" + data.getNomeCompleto() + "','" + data.getTipoSanguineo()+ "');";

            PreparedStatement preparedStmt = conn.prepareStatement(sql);

            preparedStmt.execute();

            sql = "SELECT * FROM paciente WHERE login='" + data.getLogin() + "' AND senha='" + data.getSenha() + "';";

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                data.setId(rs.getInt("idPaciente"));

            }
            st.close();
            conn.close();

        } catch (Exception ex) {
        }
        return data;

    }

    public Paciente login(Paciente data) {

        try {
            Connection conn = ConnectionManager.Conectar();

            String sql = "SELECT * FROM paciente WHERE login='" + data.getLogin() + "' AND senha='" + data.getSenha() + "';";

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                data.setId(rs.getInt("idPaciente"));
                data.setNomeCompleto(rs.getString("nomeCompleto"));
                data.setTipoSanguineo(rs.getString("tipoSanguineo"));

            }
            st.close();
            conn.close();
        } catch (Exception ex) {
        }
        return data;
    }

    public Paciente getPaciente(int id) {
        Paciente data = new Paciente();
        try {
            Connection conn = ConnectionManager.Conectar();

            String sql = "SELECT * FROM paciente WHERE idPaciente=" + id + " ;";

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                data.setId(rs.getInt("idPaciente"));
                data.setNomeCompleto(rs.getString("nomeCompleto"));
                data.setTipoSanguineo(rs.getString("tipoSanguineo"));
                data.setLogin(rs.getString("login"));
                data.setSenha(rs.getString("senha"));
            }
            st.close();
            conn.close();
        } catch (Exception ex) {
        }
        return data;
    }

    public List<Paciente> readAll() {

        try {
            Connection conn = ConnectionManager.Conectar();

            String sql = "SELECT * FROM paciente  ;";

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(sql);
            List<Paciente> datas = new ArrayList<Paciente>();
            while (rs.next()) {
                Paciente data = new Paciente();

                data.setId(rs.getInt("idPaciente"));
                data.setNomeCompleto(rs.getString("nomeCompleto"));
                data.setTipoSanguineo(rs.getString("tipoSanguineo"));
                data.setLogin(rs.getString("login"));
                data.setSenha(rs.getString("senha"));
                datas.add(data);
            }
            st.close();
            conn.close();
                    return datas;

        } catch (Exception ex) {
            return null;
        }
    }

    public void editar(Paciente data) {
        try {
            Connection conn = ConnectionManager.Conectar();

            String sql = "UPDATE paciente set login='"
                    + data.getLogin() + "', senha='" + data.getSenha() + "', nomeCompleto='" + data.getNomeCompleto() +
                    "', tipoSanguineo='" + data.getTipoSanguineo()+ "' where idPaciente=" + data.getId() + ";";

            PreparedStatement preparedStmt = conn.prepareStatement(sql);

            preparedStmt.execute();

            conn.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void deleta(Paciente data) {
        try {
            Connection conn = ConnectionManager.Conectar();

            String sql = "DELETE from paciente where idPaciente=" + data.getId() + ";";

            PreparedStatement preparedStmt = conn.prepareStatement(sql);

            preparedStmt.execute();

            conn.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }
}


