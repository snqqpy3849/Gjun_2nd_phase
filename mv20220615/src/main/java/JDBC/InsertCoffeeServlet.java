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

@WebServlet("/InsertCoffeeServlet")
public class InsertCoffeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public InsertCoffeeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
		response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet InsertCoffee</title>");            
            out.println("</head>");
            out.println("<body>");
            String coffee=request.getParameter("coffee");
            String sale=request.getParameter("sale");
            String total=request.getParameter("total");         
            String supplier=request.getParameter("supplier");
            String price=request.getParameter("price");         
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

	private boolean InsertCoffee(String coffee, String sale, String total, String supplier, String price) throws SQLException {
	    Connection con=null;
	    PreparedStatement  insert= null;
	   

	    String insertStatement =
	        "insert into COFFEES( COF_NAME , SUP_ID , PRICE , SALES ,TOTAL)" +
	        "values ( ? , ? ,? ,? ,?)";

	    try {
	         Class.forName("com.mysql.cj.jdbc.Driver");
	         con =  DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Taipei","root","1234");
	         
	         // sql 若設定了 setAutoCommit(false)，則表示在最後面一定要加上 commit 才能新增成功
	         // 若有新增失敗時，可以用 rollback 回復，不新增任何資料到資料庫
	         con.setAutoCommit(false);    
	         insert = con.prepareStatement(insertStatement);

	         //for (Map.Entry<String, Integer> e : salesForWeek.entrySet()) { }
			   insert.setString(1, coffee); 
			   insert.setInt(2, Integer.parseInt(supplier));           
			   insert.setDouble(3, Double.parseDouble(price));
			   insert.setInt(4, Integer.parseInt(sale));
			   insert.setInt(5, Integer.parseInt(total));
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
