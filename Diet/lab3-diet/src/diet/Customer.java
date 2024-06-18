package diet;


public class Customer {
	private String lName; 
	private String fName; 
	private String email; 
	private String phone; 

	

	public Customer(String fName, String lName, String email, String phone) {
		this.lName = lName;
		this.fName = fName;
		this.email = email;
		this.phone = phone;
	}



	public String getLastName() {
		return lName;
	}
	
	public String getFirstName() {
		return fName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void SetEmail(String email) {
		this.email = email;
	}
	
	public void setPhone(String phone) {
		this.phone = phone; 
	}


	public String sortField(){ 
		return lName + fName;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return fName + " " + lName;
	}
	
}
