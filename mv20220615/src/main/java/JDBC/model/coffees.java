package JDBC.model;

public class coffees {
	String cofName;
	Integer supId;
	double price;
	Integer sales;
	Integer total;
	public coffees() {}
	public coffees(String cofName, Integer supId, double price, Integer sales, Integer total) {
		this.cofName = cofName;
		this.supId = supId;
		this.price = price;
		this.sales = sales;
		this.total = total;
	}
	public String getCofName() {
		return cofName;
	}
	public void setCofName(String cofName) {
		this.cofName = cofName;
	}
	public int getSupId() {
		return supId;
	}
	public void setSupId(Integer supId) {
		this.supId = supId;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getSales() {
		return sales;
	}
	public void setSales(Integer sales) {
		this.sales = sales;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	
}
