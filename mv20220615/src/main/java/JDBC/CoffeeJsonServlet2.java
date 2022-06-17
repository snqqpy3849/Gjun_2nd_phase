package JDBC;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import JDBC.model.coffees;

@WebServlet("/CoffeeJsonServlet2")
public class CoffeeJsonServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CoffeeJsonServlet2() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String js = request.getParameter("coffee");
		Gson gson = new Gson();
		coffees[] cfs = gson.fromJson(js, coffees[].class);
		
		boolean b = insertTbale(cfs);
		if(b) response.getWriter().append("Success");
		else response.getWriter().append("Failed");
		
		//response.getWriter().append(""+cfs.length);
		
	}

	private boolean insertTbale(coffees[] cfs) {
		if(cfs.length == 0) {
			return false;
		}
		String url = "jdbc:mysql://localhost:3306/classicmodels?serverTimezone=Asia/Taipei";
		String user = "root";
		String password = "1234";
		String sql = "insert into coffees(cof_name,sup_id,price,sales,total) values(?,?,?,?,?)";
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
			conn.setAutoCommit(false);
			for(coffees c : cfs) {
				ps = conn.prepareStatement(sql);
				ps.setString(1, c.getCofName());
				ps.setInt(2, c.getSupId());
				ps.setDouble(3, c.getPrice());
				ps.setInt(4, c.getSales());
				ps.setInt(5, c.getTotal());
				int r = ps.executeUpdate();
				if(r==0) {
					conn.rollback();
					return false;
				}
			}
			conn.commit();
			
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				conn.rollback();
				return false;
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			try {
				conn.setAutoCommit(true);
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return true;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
