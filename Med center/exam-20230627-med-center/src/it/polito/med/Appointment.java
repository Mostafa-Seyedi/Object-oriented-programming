package it.polito.med;

public class Appointment {
  private String ID; 
  private String patientSSN; 
  private String patientName; 
  private String patientSurname; 
  private String doctorID; 
  private String date; 
  private String slot;
  private boolean patientSituation = false; 
  private boolean appointmentComplete = false; 

  public Appointment(String iD, String patientSSN, String patientName, String patientSurname, String doctorID,
      String date, String slot) {
    ID = iD;
    this.patientSSN = patientSSN;
    this.patientName = patientName;
    this.patientSurname = patientSurname;
    this.doctorID = doctorID;
    this.date = date;
    this.slot = slot;
  }

  public String startSlot(){ 
    String[] parts = slot.split("-"); 
    return parts[0];
  }
  public String slotSSN(){ 
    return startSlot() + "=" + getPatientSSN();  
  }
  public boolean patientAccepted(){ 
    return patientSituation;
  }

  public boolean appointmentCompleted(){ 
    return appointmentComplete; 
  }

  public String getID() {
    return ID;
  }
  public String getPatientSSN() {
    return patientSSN;
  }
  public String getPatientName() {
    return patientName;
  }
  public String getPatientSurname() {
    return patientSurname;
  }
  public String getDoctorID() {
    return doctorID;
  }
  public String getDate() {
    return date;
  }
  public String getSlot() {
    return slot;
  }

  public void setPatientAccepted() {
    this.patientSituation = true;
  }
  
  public void setAppointmentComplete() {
    this.appointmentComplete = true;
  }


}
