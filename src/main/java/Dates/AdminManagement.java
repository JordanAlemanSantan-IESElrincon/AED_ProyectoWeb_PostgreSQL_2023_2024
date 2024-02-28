package Dates;

import Connection.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminManagement {
    private MyConnection myConnection;
    private Connection con;

    /**
     * Obtenemos la conexión con la base de datos y creamos un objeto de la clase
     * Connection llamado "con". Con este objeto se establecerá una conexión con la
     * base de datos "aed".
     */
    public AdminManagement() {
        myConnection = new MyConnection();
        con = myConnection.getCon();
    }

    public boolean existsAdmin(String user, String password) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = con.prepareStatement("select * from admins where name=? and password=?");
            statement.setString(1, user);
            statement.setString(2, password);
            resultSet = statement.executeQuery();

            return resultSet.next();

        } catch (SQLException e) {
            System.err.println("Error when obtaining data from the database");
            return false;
        }
    }
}
