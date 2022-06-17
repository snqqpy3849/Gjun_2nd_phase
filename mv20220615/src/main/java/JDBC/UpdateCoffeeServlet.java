package JDBC;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import JDBC.Dao.DBdao;

@WebServlet("/UpdateCoffeeServlet")
public class UpdateCoffeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UpdateCoffeeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UpdateCoffee</title>");            
            out.println("</head>");
            out.println("<body>");
            String coffee=request.getParameter("coffee");
            String sale=request.getParameter("sale");
            String total=request.getParameter("total");         
            try {
                boolean f = DBdao.updateCoffeeSales(coffee,sale,total);
                if(f) out.println("修改完成");
                else out.println("修改失敗");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            out.println("</body>");
            out.println("</html>");
        } finally {            
            out.close();
        }

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
