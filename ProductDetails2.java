import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductDetails2 {

	public static void main(String[] args) {
		fillDatabase();


	}
	
	public static void fillDatabase() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore?createDatabaseIfNotExist=true", "root", "root");
            String book = "insert into book (name) values(Bookstore Book ?)";
            Statement st = conn.prepareStatement(""
            		+ "drop database [IF EXIST] bookstore;"
            		+ "create database bookstore;"
            		+ "use bookstore;");
			PreparedStatement ps = conn.prepareStatement(book);
			for(int i=1; 1<=30;i++) {
				ps.setInt(1, i);
				ps.executeUpdate();
			}
        } catch (ClassNotFoundException e) {
            System.out.println("Driver not found");
        } catch (SQLException e) {
            System.out.println("SQL exception");
            e.printStackTrace();
        }
	}

}
