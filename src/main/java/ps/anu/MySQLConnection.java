package ps.anu;

import java.sql.*;

class ConnectionFailedException extends Exception {
    public ConnectionFailedException(String msg) {
        super(msg);
    }
}

public class MySQLConnection {
    private static Connection conn;

    private static void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "Vnit@25015");
            if (conn == null) throw new ConnectionFailedException("Connection Failed 😖😒");
        } catch (ClassNotFoundException | SQLException | ConnectionFailedException c) {
            if (c instanceof ConnectionFailedException) {
                System.out.println(c.getMessage());
                System.exit(1);
            }
            c.printStackTrace();
        }
    }

    public static Connection getConnectionObject() {
        if (conn == null) {
            connect();
        }
        return conn;
    }

    public static Statement getStatement() {

        getConnectionObject();

        Statement st = null;
        try {
            st = conn.createStatement();
        } catch (SQLException s) {
            s.printStackTrace();
        }
        return st;
    }

    public static PreparedStatement getPreparedStatement(String query) {
        getConnectionObject();
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(query);
        } catch (SQLException s) {
            s.printStackTrace();
        }
        return pst;
    }
}
