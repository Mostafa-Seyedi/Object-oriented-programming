package it.polito.ski;

import java.util.ArrayList;
import java.util.List;

public class Parking {
  private String name; 
  private int slots;
  private List<Lift> lifts = new ArrayList<>(); 

  Lift lift; 


  public Parking(String name, int slots) {
    this.name = name;
    this.slots = slots;
  }

  public String getName() {
    return name;
  }
  public int getSlots() {
    return slots;
  } 
  
  public List<Lift> getLifts() {
    return lifts;
  }

  public void addLift(Lift lift){ 
    lifts.add(lift);
  }

  public boolean isProportionate(){ 
    int totalCapacity = lifts.stream().mapToInt(Lift::getCapacity).sum(); 
    return slots / totalCapacity < 30; 
  }
  

  

}
