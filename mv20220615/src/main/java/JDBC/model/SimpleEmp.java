package JDBC.model;

public class SimpleEmp {
	   private int id;
	   private String fname;
	   private String lname;
	   private String email;
	   public SimpleEmp(){}
	   public SimpleEmp(int id,String fname,String lname,String email){
	       this.id=id;
	       this.fname=fname;
	       this.lname=lname;
	       this.email=email;
	   }
	    /**
	     * @return the id
	     */
	    public int getId() {
	        return id;
	    }

	    /**
	     * @param id the id to set
	     */
	    public void setId(int id) {
	        this.id = id;
	    }

	    /**
	     * @return the fname
	     */
	    public String getFname() {
	        return fname;
	    }

	    /**
	     * @param fname the fname to set
	     */
	    public void setFname(String fname) {
	        this.fname = fname;
	    }

	    /**
	     * @return the lname
	     */
	    public String getLname() {
	        return lname;
	    }

	    /**
	     * @param lname the lname to set
	     */
	    public void setLname(String lname) {
	        this.lname = lname;
	    }

	    /**
	     * @return the email
	     */
	    public String getEmail() {
	        return email;
	    }

	    /**
	     * @param email the email to set
	     */
	    public void setEmail(String email) {
	        this.email = email;
	    }

}
