package university;
import java.util.ArrayList;
import java.util.List;

public class Student {

  private String fName; 
  private String lName; 
  private int Id; 
  
  private List<Course> registered = new ArrayList<>(); 
   
  private List<Integer> studentGrade = new ArrayList<>(); 

  public Student(String fName, String lName, int Id) {
    this.fName = fName;
    this.lName = lName;
    this.Id = Id; 
  } 

  public int getId(){ 
    return Id; 
  }
  
  public String getFName(){ 
    return fName; 
  }

  public String getLName(){ 
    return lName; 
  }

  public List<Course> getRegistered() {
    return registered;
  }


  public void addCourse(Course course){ 
    if(registered.size() >= 25){
      throw new IllegalStateException("Not more course can be registered");
    }
    registered.add(course);
  }
  
  public List<Integer> getStudentGrade(){ 
    return studentGrade;
  }

  public void addStudentGrade(int grade){ 
    if(grade < 0 || grade > 30){ 
      throw new IllegalArgumentException("Grade must be between 0 and 30");
    } 
    studentGrade.add(grade); 
  }

  public double avg(){
    if(studentGrade.size() == 0){ 
      return 0; 
    }

    int sum = 0; 
    for (var grade : studentGrade) {
      sum += grade;
    }
    Double average = (double) (sum / studentGrade.size());
    Double bonus = (double) (studentGrade.size() / registered.size()) * 10;  

    return average + bonus; 
  }
  


  @Override
  public String toString() {
    return Id + " " + fName + " " + lName;
  }

}
