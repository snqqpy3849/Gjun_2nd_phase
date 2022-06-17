package JDBC;

import java.io.IOException;
import java.io.PrintWriter;
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

@WebServlet("/CoffeeJsonServlet")
public class CoffeeJsonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CoffeeJsonServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String st = request.getParameter("coffee");
		Gson gson = new Gson();
		coffees co = gson.fromJson(st, coffees.class);
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		try {
			out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet InsertCoffee</title>");            
            out.println("</head>");
            out.println("<body>");
            
            String coffee=co.getCofName();
            int sale=co.getSales();
            int total=co.getTotal();         
            int supplier=co.getSupId();
            double price=co.getPrice(); 
            try {
                boolean f = InsertCoffee(coffee,sale,total,supplier,price);
                if(f)
                	out.println("新增完成");
                else
                	out.println("新增失敗");
                
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                out.println("新增失敗");
            }

            out.println("</body>");
            out.println("</html>");
        } finally {            
            out.close();
        }
            
	}

	private boolean InsertCoffee(String coffee, int sale, int total, int supplier, double price) throws SQLException {
		Connection con=null;
	    PreparedStatement  insert= null;	   

	    String insertStatement =
	        "insert into COFFEES( COF_NAME , SUP_ID , PRICE , SALES ,TOTAL)" +
	        "values ( ? , ? ,? ,? ,?)";

	    try {
	         Class.forName("com.mysql.cj.jdbc.Driver");
	         con =  DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Taipei","root","1234");

	         con.setAutoCommit(false);    
	         insert = con.prepareStatement(insertStatement);

	         //for (Map.Entry<String, Integer> e : salesForWeek.entrySet()) { }
			   insert.setString(1, coffee); 
			   insert.setInt(2, supplier);           
			   insert.setDouble(3, price);
			   insert.setInt(4, sale);
			   insert.setInt(5, total);
			   int row = insert.executeUpdate();
			   
			   // 資料無誤，執行 commit
			   con.commit();
			   if(row > 0) 
				   return true;
			   else 
				   return false;
	        
	    } catch (Exception e ) {
	        System.out.println(e.getMessage());
	        if (con != null) {
	            try {
	                System.err.print("Transaction is being rolled back");
	                
	                // sql 動作有問題，執行 rollback 不寫入資料庫
	                con.rollback();
	            } catch(SQLException ex) {
	                System.out.println(ex.getMessage());
	            }
	        }
	    } finally {
	        if (insert != null) {
	            insert.close();
	        }
	        if (insert != null) {
	            insert.close();
	        }
	        con.setAutoCommit(true);
	    }
	    return false;

		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
