package Servlets.com.databases;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Servlets.com.pojo.Alumno;

/**
 *
 * @author Jordan
 */
public class AlumnoDB {

    static final String JDBC_DRIVER_CLASS = "com.mysql.jdbc.Driver";
    static final String JDBC_URL = "jdbc:mysql://localhost:3306/aed";
    static final String JDBC_USER = "root";
    static final String JDBC_PASS = "root";

    private Connection con;

    public AlumnoDB() {
        con = null;

        try {
            Class.forName(JDBC_DRIVER_CLASS);
            con = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);

        } catch (ClassNotFoundException ex) {
            System.err.println("Error al cargar el driver");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public AlumnoDB(String JDBC_DRIVER_CLASS, String JDBC_URL, String JDBC_USER, String JDBC_PASS) {
        try {
            Class.forName(JDBC_DRIVER_CLASS);
            con = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);

        } catch (ClassNotFoundException ex) {
            System.err.println("Error al cargar el driver");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public Connection getCon() {
        return con;
    }

    public boolean insertAlumn(String nombre, String apellidos, String curso, int nota) throws SQLException {
        PreparedStatement statement = null;

        statement = con.prepareStatement("insert into alumnos (nombre, apellidos, curso, nota) values(?, ?, ?, ?)");

        statement.setString(1, nombre);
        statement.setString(2, apellidos);
        statement.setString(3, curso);
        statement.setInt(4, nota);

        statement.executeUpdate();

        return true;
    }

    public boolean insertAlumnOneValue(String column, String value) throws SQLException {
        PreparedStatement statement = null;

        statement = con
                .prepareStatement("insert into alumnos (" + column + ") values(?)");

        if (column == "nota")
            statement.setInt(1, Integer.parseInt(value));
        else
            statement.setString(1, value);

        statement.executeUpdate();

        return true;

    }

    public ArrayList<Alumno> getAlumnos() {
        ArrayList<Alumno> listaAlumnos = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = con.prepareStatement("select * from alumnos");
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                listaAlumnos.add(new Alumno(resultSet.getInt("id"),
                        resultSet.getString("nombre"),
                        resultSet.getString("apellidos"),
                        resultSet.getString("curso"),
                        resultSet.getInt("nota")));
            }

            return listaAlumnos;

        } catch (SQLException e) {
            System.err.println("Error al modificar un dato en la base de datos");
            return null;
        }
    }
}
