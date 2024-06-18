package clinic;

import java.util.Map;
import java.util.TreeMap;

public class Doctor {
  private String firstName; 
  private String lastName; 
  private String SSN; 
  private int ID; 
  private String specialization;

  Map<String, Patient> patients = new TreeMap<>();

  public Doctor(String firstName, String lastName, String SSN, int ID, String specialization) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.SSN = SSN;
    this.ID = ID;
    this.specialization = specialization;
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

  public int getID() {
    return ID;
  }

  public String getSpecialization() {
    return specialization;
  }


  @Override
  public String toString() {
    // TODO Auto-generated method stub
    return lastName + " " + firstName + "(" + SSN + ")" + " [" + ID + "]: " + specialization;  
  }


}
