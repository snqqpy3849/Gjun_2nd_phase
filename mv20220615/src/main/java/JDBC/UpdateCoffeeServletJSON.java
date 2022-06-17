package JDBC;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import JDBC.Dao.DBdao;
import JDBC.model.coffees;

@WebServlet("/UpdateCoffeeServletJSON")
public class UpdateCoffeeServletJSON extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public UpdateCoffeeServletJSON() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String data=request.getParameter("jsCoffee");
		Gson g=new Gson();
		coffees c=g.fromJson(data, coffees.class);		
		boolean b=false;
		try {
		     b = DBdao.updateCoffeeSales(c.getCofName(), ""+c.getSales(), ""+c.getTotal());		     
		}catch(SQLException ex) {
			System.out.println("Json Servlet SQL Error:"+ex.getMessage());
		}
		response.setContentType("text/html;charset=utf8");
		if(b)
			response.getWriter().append("<h2>修改成功</h2>");
		else
			response.getWriter().append("<h2>修改失敗</h2>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
