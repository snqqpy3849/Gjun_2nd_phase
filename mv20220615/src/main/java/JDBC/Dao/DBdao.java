package JDBC.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBdao {
	// 修改資料
	public static boolean updateCoffeeSales(String coffee,String sale,String total) throws SQLException {
		    Connection con=null;
		    PreparedStatement updateSales = null;
		    PreparedStatement updateTotal = null;

		    String updateString =
		        "update COFFEES " +
		        "set SALES = ? where COF_NAME = ?";

		    String updateStatement =
		        "update COFFEES " +
		        "set TOTAL = TOTAL + ? " +
		        "where COF_NAME = ?";

		    try {
		    	Class.forName("com.mysql.cj.jdbc.Driver");
		    	con =  DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels?serverTimezone=Asia/Taipei","root","1234");
		      
		        con.setAutoCommit(false);
		        updateSales = con.prepareStatement(updateString);
		        updateTotal = con.prepareStatement(updateStatement);

		        //for (Map.Entry<String, Integer> e : salesForWeek.entrySet()) { }
		        updateSales.setInt(1, Integer.parseInt(sale));
		        updateSales.setString(2, coffee);
		        int r1 = updateSales.executeUpdate();
		        
		        updateTotal.setInt(1, Integer.parseInt(total));
		        updateTotal.setString(2, coffee);
		        int r2 = updateTotal.executeUpdate();
		        
		        if(r1>0 && r2>0) {
		        	con.commit();
		        	return true;
		        }else {
		        	con.rollback();
		        	return false;
		        }
		        
		    } catch (Exception e ) {
		        System.out.println(e.getMessage());
		        if (con != null) {
		            try {
		                System.err.print("Transaction is being rolled back");
		                con.rollback();
		            } catch(SQLException excep) {
		                System.out.println(e.getMessage());
		            }
		        }
		    } finally {
		        if (updateSales != null) {
		            updateSales.close();
		        }
		        if (updateTotal != null) {
		            updateTotal.close();
		        }
		        con.setAutoCommit(true);
		    }
		    return false;
		}

	// 刪除資料
	public static boolean DeleteCoffee(String coffee)  throws SQLException {
	    Connection con=null;
	    PreparedStatement  delete= null;
	   

	    String insertStatement =
	        "delete from COFFEES " +
	        "where COF_NAME=? ";

	    try {
	         
	      	 Class.forName("com.mysql.cj.jdbc.Driver");
	      	 con =  DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Taipei","root","1234");
		  
	      	 con.setAutoCommit(false);    
	      	 delete = con.prepareStatement(insertStatement);

	      	 //for (Map.Entry<String, Integer> e : salesForWeek.entrySet()) { }
	      	 delete.setString(1, coffee);          
	      	 int d = delete.executeUpdate();
	      	 if(d>0) {
	      		 con.commit();
	      		 return true;
	      	 }else {
	      		 con.rollback();
	      	 }
	      	 return false;
	      	 
	        
	    } catch (Exception e ) {
	        System.out.println(e.getMessage());
	        if (con != null) {
	            try {
	                System.err.print("Transaction is being rolled back");
	                con.rollback();
	            } catch(SQLException ex) {
	                System.out.println(ex.getMessage());
	            }
	        }
	    } finally {
	        if (delete != null) {
	            delete.close();
	        }
	        if (delete != null) {
	            delete.close();
	        }
	        con.setAutoCommit(true);
	    }
	    return false;
	}   

}
