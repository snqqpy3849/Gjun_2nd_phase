package JDBC;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SPCallParaServlet")
public class SPCallParaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SPCallParaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "jdbc:mysql://localhost:3306/classicmodels?serverTimezone=Asia/Taipei";
		String user = "root";
		String password = "1234";
		String sql = "call GetEmpCountInOffice(?,?)";
		String city = request.getParameter("city");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, user, password);
			CallableStatement cs = conn.prepareCall(sql);
			cs.setString(1, city);
			
			// 注意有回傳值的參數要怎麼寫
			cs.registerOutParameter(2, Types.INTEGER);
			
			cs.execute();
			// 因為只有一個結果，所以不用執行 cs.next()，也找不到
			// 因為只有一個結果，沒有用到 select，所以不用 ResultSet
			response.setContentType("text/html;charset=utf8");
			response.getWriter().append("<h2>The Param is "+cs.getInt(2)+"</h2>");
			conn.close();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
