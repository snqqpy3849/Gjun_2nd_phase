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

@WebServlet("/UpdateCoffeeServletJSON2")
public class UpdateCoffeeServletJSON2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UpdateCoffeeServletJSON2() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String js = request.getParameter("jsCoffee");
		Gson gson = new Gson();
		coffees[] cfs = gson.fromJson(js, coffees[].class);
		
		boolean b = updateTable(cfs);
		if(b) response.getWriter().append("Update Success");
		else response.getWriter().append("Update Failed");
	}

	private boolean updateTable(coffees[] cfs) {
		if(cfs.length == 0) {
			return false;
		}
		String url = "jdbc:mysql://localhost:3306/classicmodels?serverTimezone=Asia/Taipei";
		String user = "root";
		String password = "1234";
		String sql = "update coffees set sales=? , total=? where cof_name=?";
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			getClass().forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
			for(coffees c:cfs) {
				ps = conn.prepareStatement(sql);
				ps.setInt(1, c.getSales());
				ps.setInt(2, c.getTotal());
				ps.setString(3, c.getCofName());
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
