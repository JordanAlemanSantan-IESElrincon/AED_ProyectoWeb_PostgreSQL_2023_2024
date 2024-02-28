package Dates;

import Connection.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentManagement {
    private final Connection con;

    /**
     * Obtenemos la conexión con la base de datos y creamos un objeto de la clase
     * Connection llamado "con". Con este objeto se establecerá una conexión con la
     * base de datos "aed".
     */
    public StudentManagement() {
        MyConnection myConnection = new MyConnection();
        con = myConnection.getCon();
    }

    /**
     * Este método recibirá como argumento todas las columnas de la tabla Alumno de
     * nuestra base de datos "aed".
     * <p>
     * Se pasa por parámetro todas las columnas de la tabla Alumnos para poder
     * realizar una inserción en esa tabla en concreto.
     * </p>
     * <p>
     * No es necesario que se especifiquen todas las columnas si no se quisiera
     * insertar un nuevo registro con todos los elementos. Es decir, se podría no
     * incluir la "nota" de un nuevo alumno que se vaya a añadir. Desde la
     * invocación del método se controlará los elementos que se incluirán o no, ya
     * que esos controles no los realiza este método, aunque sí permita insertar un
     * nuevo Alumno con lo datos que se desee realizando un buen control
     * </p>
     * <p>
     * Por tanto, este método inserta un nuevo alumno con todos los datos que se les
     * pase por parámetro menos el ID, porque el ID está como "auto_increment".
     * También se podría haber incluído, pero en este caso no será necesario
     * </p>
     * 
     * {@code insert into alumnos (nombre, apellidos, curso, nota) values(?, ?, ?, ?)}
     * 
     * @param name        {@code String} Nombre del alumno
     * @param surname     {@code String} Apellidos del alumno
     * @param age         {@code int} Edad del alumno
     * @param address     {@code String} Dirección del alumno
     * @param course      {@code int} Curso del alumno
     * @param family_dates {@code String} Datos de la familia del alumno
     * @return True si se ha actualizado correctamente
     */
    public boolean insertStudent(String name, String surname, int age, String address, int course, String family_dates, double note) {
        PreparedStatement statement;

        try {
            statement = con
                    .prepareStatement(
                            "insert into student (name, surname, age, address, course, family_dates, note) values(?, ?, ?, ?, ?, ?, ?)");

            statementAllDate(name, surname, age, address, course, family_dates, note, statement);

            statement.executeUpdate();

            return true;
        } catch (SQLException e) {
            System.err.println("Error when inserting data into the database");
            return false;
        }
    }

    /**
     * Este método modifica todos los elementos del alumno. Desde un único método se
     * puede modificar cualquier elemento de un alumno cualquiera.
     * <p>
     * Este método modifica todos los elementos de un alumno con la ID que se le pase
     * por parámetro
     * </p>
     * <p>
     * Para conseguir modificar un único valor con este método, los controles se
     * deberán realizar a la hora de invocar este método. Es decir, si se usa este
     * método y sólo se modifica, por ejemplo, el "curso" del alumno, el resto de
     * elementos mantendrán la información que ya existía antes de empezar a ser
     * modificados. Estos controles no los realiza este método, sino que se hará a
     * la hora de invocarlo
     * </p>
     * <p>
     * Por tanto, este método modifica cualquier elemento de un alumno concreto.
     * También pasamos por parámetro el ID del alumno, ya que utilizamos su ID para
     * actualizar los datos del alumno
     * </p>
     * 
     * {@code update alumnos set nombre=?, apellidos=?, curso=?, nota=? where id = ?}
     * 
     * @param id          {@code int} ID del alumno
     * @param name        {@code String} Nombre del alumno
     * @param surname     {@code String} Apellidos del alumno
     * @param age         {@code int} Edad del alumno
     * @param address     {@code String} Dirección del alumno
     * @param course      {@code int} Curso del alumno
     * @param family_dates {@code String} Datos de la familia del alumno
     * @return True si se ha actualizado correctamente
     */
    public boolean updateStudent(int id, String name, String surname, int age, String address, int course,
            String family_dates, double note) {
        PreparedStatement statement;

        try {
            statement = con.prepareStatement(
                    "update student set name=?, surname=?, age=?, address=?, course=?, family_dates=?, note=? where id = ?");

            // El número indica la posición del interrogante dentro de values.
            statementAllDate(name, surname, age, address, course, family_dates, note, statement);

            statement.setInt(8, id);
            statement.executeUpdate();

            return true;
        } catch (SQLException e) {
            System.err.println("Error when updating data in the database");
            return false;
        }
    }

    private void statementAllDate(String name, String surname, int age, String address, int course, String family_dates, double note, PreparedStatement statement) throws SQLException {
        statement.setString(1, name);
        statement.setString(2, surname);
        statement.setInt(3, age);
        statement.setString(4, address);
        statement.setInt(5, course);
        statement.setString(6, family_dates);
        statement.setDouble(7, note);
    }

    /**
     * Elimina un alumno de la base de datos según la 'id' pasada por parámetro
     * <p>
     * {@code delete from alumnos where id=?}
     * 
     * @param id ID del alumno
     * @return True si se ha eliminado correctamente
     */
    public boolean deleteStudent(int id) {
        PreparedStatement statement;

        try {
            statement = con.prepareStatement("delete from student where id=?");

            statement.setInt(1, id);
            statement.executeUpdate();

            return true;
        } catch (SQLException e) {
            System.err.println("Error when deleting data in the database");
            return false;
        }
    }

    public ArrayList<Student> getStudents() {
        ArrayList<Student> listStudents = new ArrayList<>();
        PreparedStatement statement;

        try {
            statement = con.prepareStatement("select * from student");
            return getStudents(listStudents, statement);

        } catch (SQLException e) {
            System.err.println("Error when obtaining data from the database");
            return null;
        }
    }

    public ArrayList<Student> getStudentsOrdered(String orderField, String orderType) {
        ArrayList<Student> listStudents = new ArrayList<>();
        PreparedStatement statement;

        try {
            String query = "SELECT * FROM student ORDER BY " + orderField + " " + orderType;
            statement = con.prepareStatement(query);
            return getStudents(listStudents, statement);

        } catch (SQLException e) {
            System.err.println("Error when obtaining data from the database");
            return null;
        }
    }

    private ArrayList<Student> getStudents(ArrayList<Student> listStudents, PreparedStatement statement) throws SQLException {
        ResultSet resultSet;
        resultSet = statement.executeQuery();

        while (resultSet.next()) {
            listStudents.add(new Student(resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("surname"),
                    resultSet.getInt("age"),
                    resultSet.getString("address"),
                    resultSet.getInt("course"),
                    resultSet.getString("family_dates"),
                    resultSet.getDouble("note")));
        }

        return listStudents;
    }


}
