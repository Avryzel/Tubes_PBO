import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class database {
    private static final String URL = "jdbc:sqlite:src/kontak_apk.db";
    private static Connection connection;

    public static Connection connect() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(URL);
                System.out.println("Koneksi berhasil ke database SQLite.");
            }
        } catch (SQLException e) {
            System.err.println("Gagal menghubungkan ke database: " + e.getMessage());
        }
        return connection;
    }

    public static void close() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Koneksi database ditutup.");
            } catch (SQLException e) {
                System.err.println("Gagal menutup koneksi database: " + e.getMessage());
            }
        }
    }
}
