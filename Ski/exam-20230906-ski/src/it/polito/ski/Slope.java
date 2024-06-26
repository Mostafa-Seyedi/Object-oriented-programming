package it.polito.ski;

public class Slope {
  private String name;
  private String difficulty;
  private String startingLift;
  
  public Slope(String name, String difficulty, String startingLift) {
    this.name = name;
    this.difficulty = difficulty;
    this.startingLift = startingLift;
  }
  public String getName() {
    return name;
  }
  public String getDifficulty() {
    return difficulty;
  }
  public String getStartingLift() {
    return startingLift;
  } 



}
