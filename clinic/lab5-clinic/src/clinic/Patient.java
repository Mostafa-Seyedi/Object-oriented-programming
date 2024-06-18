package clinic;


public class Patient {
  private String firstName; 
  private String lastName; 
  private String SSN;

  Doctor doctor;  

  public Patient(String firstName, String lastName, String SSN) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.SSN = SSN;
  } 

  
  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getSSN() {
    return SSN;
  }


  @Override
  public String toString() {
    // TODO Auto-generated method stub
    return lastName +" "+ firstName +" ("+ SSN +")";
  }
}
