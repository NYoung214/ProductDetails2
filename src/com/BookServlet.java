package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BookServlet
 */
public class BookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String query = "select name from book where id=?";
	Connection conn = null;
	
    public void connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("connecting...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore?createDatabaseIfNotExist=true", "root", "root");
            System.out.println("connected...");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver not found");
        } catch (SQLException e) {
            System.out.println("SQL exception");
            e.printStackTrace();
        }
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		connect();
		
		String bookId = request.getParameter("bookId");
		RequestDispatcher rd = null;
		PrintWriter out = response.getWriter();
		out = response.getWriter();
		
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, Integer.parseInt(bookId));
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				out.println("<span style='color:blue'>Book "+bookId+": "+rs.getString("name"));
			}else {
				out.println("<span style='color:red'> Could not find Book with ID of "+bookId+"!</span>");
			}
			rd = request.getRequestDispatcher("index.html");		
			rd.include(request, response);
		}catch(Exception e) {
			out.println("<span style='color:red'> Could not find Book with ID of "+bookId+"!</span>");
			rd = request.getRequestDispatcher("index.html");		
			rd.include(request, response);
		}

		
		
	}

}
