package it.polito.med;

import java.util.Map;
import java.util.TreeMap;

public class Doctor {
  private String ID; 
  private String name; 
  private String surname; 
  private String speciality;

  private Map<String, Map<String, String>> schedule = new TreeMap<>(); 
  

  public Doctor(String iD, String name, String surname, String speciality) {
    ID = iD;
    this.name = name;
    this.surname = surname;
    this.speciality = speciality;
  }

  public void addSchedule(String date, String s, String e){
    Map<String, String> workingHours = new TreeMap<>();
    if(schedule.containsKey(date)){ 
      schedule.get(date).put(s, e);
    } else{
      workingHours.put(s, e); 
      schedule.put(date, workingHours);
  }
  // System.out.println(schedule);
  }



  public String getID() {
    return ID;
  }
  public String getName() {
    return name;
  }
  public String getSurname() {
    return surname;
  }
  public String getSpeciality() {
    return speciality;
  } 


  public Map<String, Map<String, String>> getSchedule() {
    return schedule;
  }


  
}
