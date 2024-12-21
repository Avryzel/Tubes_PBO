import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class database {
    private static final String URL = "jdbc:mysql://localhost:3306/kontak_apk";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection connect() {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Koneksi berhasil!");
            return connection;
        } catch (SQLException e) {
            System.err.println("Koneksi gagal: " + e.getMessage());
            return null;
        }
    }
}