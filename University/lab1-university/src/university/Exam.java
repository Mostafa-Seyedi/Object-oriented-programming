package university;
public class Exam {
  private int studentId; 
  private int courseCode; 
  private int grade;


  public Exam(int studentId, int courseCode, int grade) {
    this.studentId = studentId;
    this.courseCode = courseCode;
    this.grade = grade;
  } 
  
  public int getStudentId() {
    return studentId;
  }


  public int getCourseCode() {
    return courseCode;
  }


  public int getGrade() {
    return grade;
  }



}
