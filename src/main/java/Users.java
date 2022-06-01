    /* TO DO:
     * Add vars, methods to store age/age group, gender ID, trigger categories
     */

public class Users {
    protected String prefName;
    protected String email;
    protected String password;
    
    public Users() {
    }
 
    public Users(String prefName, String password, String email) {
        this.prefName = prefName;
        this.password = password;
        this.email = email;
    }
 
    public void setPrefName (String prefName) {
    	this.prefName = prefName;
    }
    
    public String getPrefName() {
    	return prefName;
    }
    
    public void setPassword (String password) {
    	this.password = password;
    }
    
    public String getPassword() {
    	return password;
    }
    
    public void setEmail (String email) {
    	this.email = email;
    }
    
    public String getEmail () {
    	return email;
    }
}