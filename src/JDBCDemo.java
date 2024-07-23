import java.sql.*;

public class JDBCDemo {

    public static final String COLUMN_DELIMITER = " | ";

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        /*
            import package - import java.sql.*
            load and register
            create connection
            create statement
            execute statement
            process results
            close connection
         */
        String url = "jdbc:postgresql://localhost:5432/Demo";
        String userName = "postgres";
        String password = "Root@1234";
        String query = "select * from student";
        Class.forName("org.postgresql.Driver"); /*Load and register driver*/
        Connection connection = DriverManager.getConnection(url, userName, password);
        System.out.println("Connection is established...");

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        while(resultSet.next()) {
            try {
                System.out.println(resultSet.getInt("id") + COLUMN_DELIMITER +
                        resultSet.getString("sname") + COLUMN_DELIMITER +
                        resultSet.getInt("marks"));
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }

        insert(connection);

        connection.close();
        System.out.println("Connection is closed...");
    }

    private static void insert(Connection connection) throws SQLException {
        String sql = "insert into student values(?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, 10);
        preparedStatement.setString(2, "Max");
        preparedStatement.setInt(3, 80);

        preparedStatement.execute();
    }
}
