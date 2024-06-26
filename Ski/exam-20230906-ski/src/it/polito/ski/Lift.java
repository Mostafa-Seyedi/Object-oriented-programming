package it.polito.ski;

public class Lift {
  private String name; 
  private LiftType type;
  
  public Lift(String name, LiftType type) {
    this.name = name;
    this.type = type;
  }
  
  public String getName() {
    return name;
  }
  public String getType() {
    return type.getCode();
  } 

  public int getCapacity(){ 
    return type.getCapacity();
  }


}
