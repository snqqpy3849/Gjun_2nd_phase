package JDBC;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import JDBC.model.SimpleEmp;

@WebServlet("/GetDataRowsServletSP")
public class GetDataRowsServletSP extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public GetDataRowsServletSP() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "jdbc:mysql://localhost:3306/classicmodels?serverTimezone=Asia/Taipei";
        String username = "root";
        String password = "1234";
        
        PrintWriter out = response.getWriter();
        String city=request.getParameter("city");
        
        ArrayList<SimpleEmp> list = new ArrayList();
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, username, password);
            
            // 使用 stored procedure 的 sql 語法
            CallableStatement cStmt = con.prepareCall("call GetEmpInOffice(?)");
            cStmt.setString(1, city);
            ResultSet rs=cStmt.executeQuery();
            
            // 印出資料
            //print(out, rs);
            
            // 另一種印出資料的方式
            fillList(rs, list);
            
            //request.getSession().setAttribute("emps", list);	// 也可以用 request.setAttribute()
            request.setAttribute("emps", list);
            
            // 用一般 jsp 語法呈現
            //request.getRequestDispatcher("JDBC/ShowEmpInCity.jsp").forward(request, response);
            
            // 用 jstl 語法呈現
            request.getRequestDispatcher("JDBC/ShowEmpInCity2.jsp").forward(request, response);
              
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

	}

	private void fillList(ResultSet rs, ArrayList<SimpleEmp> list) {
        try {
            while (rs.next()) {
            	SimpleEmp emp = new SimpleEmp(
            			rs.getInt("employeeNumber"),
            			rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getString("email")
                        );
                list.add(emp);
            }
        } catch (Exception ex) {
        }

	}

	private void print(PrintWriter out, ResultSet rs) {
        try {          
            out.println("<table border='1'>");
            while (rs.next()) {             
                out.println("<tr><td>");
                String no = rs.getString("employeeNumber");
                out.println(""+no);
                out.println("</td><td>");
                String firstname = rs.getString("firstname");
                out.println(""+firstname);
                out.println("</td><td>");
                String lastname = rs.getString("lastname");
                out.println(""+lastname);
                out.println("</td><td>");
                String email = rs.getString("email");
                out.println(""+email);
                out.println("</td>");                
            }
            out.println("</table>");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }     
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
