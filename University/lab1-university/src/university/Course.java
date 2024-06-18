package university;
import java.util.ArrayList;
import java.util.List;

public class Course {

  private String title; 
  private String teacherName; 
  private int courseId;
  
  private List<Student> attended = new ArrayList<>(); 

  private List<Integer> courseGrades = new ArrayList<>(); 


  public Course(String title, String teacherName, int courseId) {
    this.title = title;
    this.teacherName = teacherName;
    this.courseId = courseId;
  } 
  
  
  public void addStudent(Student student){ 
    if(attended.size() >= 100){
      throw new IllegalStateException("Not more course can be registered");
    }
    attended.add(student);
  }

  public void addCourseGrades(int grade){ 
    if(grade < 0 || grade > 30){ 
      throw new IllegalArgumentException("grade must be between 0 and 30");
    } 
    courseGrades.add(grade); 
  }

  @Override
  public String toString() {
    // TODO Auto-generated method stub
    return courseId + ", " + title + ", " + teacherName;
  }

  public String getTitle() {
    return title;
  }


  public String getTeacherName() {
    return teacherName;
  }


  public int getCourseId() {
    return courseId;
  }

  public List<Student> getAttended() {
    return attended;
  }

  public List<Integer> getCourseGrades() {
    return courseGrades;
  }





}
