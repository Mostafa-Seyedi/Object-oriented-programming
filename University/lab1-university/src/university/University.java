package university;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * This class represents a university education system.
 * 
 * It manages students and courses.
 *
 */
public class University {

	private String name; 
	private String rectorFirstName; 
	private String rectorLastName;

	private Map<Integer, Student> students; 
	private int nextId; 
	private final static int MAX_STUDENTS = 1000;

	HashMap<Integer, Course> courses; 
	int nextCourseId; 
	private final static int MAX_COURSES = 50; 
	
	// List<Exam> examsGrade;

// R1
	/**
	 * Constructor
	 * @param name name of the university
	 */
	public University(String name){
		// Example of logging
		// logger.info("Creating extended university object");
		//TODO: to be implemented

		this.name = name; 	
		students = new HashMap<>(); 
		nextId = 10000;

		courses = new HashMap<>();
		nextCourseId = 10; 

		// examsGrade = new ArrayList<>();
		 
	}
	
	/**
	 * Getter for the name of the university
	 * 
	 * @return name of university
	 */
	public String getName(){
		//TODO: to be implemented
		return name;
	}
	
	/**
	 * Defines the rector for the university
	 * 
	 * @param first first name of the rector
	 * @param last	last name of the rector
	 */
	public void setRector(String first, String last){
		//TODO: to be implemented
		this.rectorFirstName = first; 
		this.rectorLastName = last;
	}
	
	/**
	 * Retrieves the rector of the university with the format "First Last"
	 * 
	 * @return name of the rector
	 */
	public String getRector(){
		//TODO: to be implemented
		return rectorFirstName + " " + rectorLastName;
	}
	
// R2
	/**
	 * Enrol a student in the university
	 * The university assigns ID numbers 
	 * progressively from number 10000.
	 * 
	 * @param first first name of the student
	 * @param last last name of the student
	 * 
	 * @return unique ID of the newly enrolled student
	 */	

	
	public int enroll(String first, String last){
		//TODO: to be implemented
		if(students.size() >= MAX_STUDENTS){
			throw new IllegalStateException("Maximum number of students enrolled");
		}
		var newStudent = new Student(first, last, nextId);  
		students.put(nextId, newStudent); 
		logger.info("New student enrolled: " + nextId + " " + first + " " + last);
		return nextId++;
	}
	
	/**
	 * Retrieves the information for a given student.
	 * The university assigns IDs progressively starting from 10000
	 * 
	 * @param id the ID of the student
	 * 
	 * @return information about the student
	 */
	public String student(int id){
		//TODO: to be implemented
		// method 1
		String result = ""; 
		if(students.containsKey(id)){ 
			result = students.get(id).toString(); 
		} else {
			System.out.println("key not found.");
		}
		return result; 

		// method 2

		// return students.get(id).toString();
	}
	
// R3
	/**
	 * Activates a new course with the given teacher
	 * Course codes are assigned progressively starting from 10.
	 * 
	 * @param title title of the course
	 * @param teacher name of the teacher
	 * 
	 * @return the unique code assigned to the course
	 */
	public int activate(String title, String teacher){
		//TODO: to be implemented
		if(courses.size() >= MAX_COURSES){
			throw new IllegalStateException("maximum number of courses offered");
		}
		var newCourse = new Course(title, teacher, nextCourseId);
		courses.put(nextCourseId, newCourse);
		logger.info("New course activated: " + nextCourseId + " " + title + " " + teacher);
		return nextCourseId++;
	}
	
	/**
	 * Retrieve the information for a given course.
	 * 
	 * The course information is formatted as a string containing 
	 * code, title, and teacher separated by commas, 
	 * e.g., {@code "10,Object Oriented Programming,James Gosling"}.
	 * 
	 * @param code unique code of the course
	 * 
	 * @return information about the course
	 */
	public String course(int code){
		//TODO: to be implemented
		// method 1
		String result = "";
		if(courses.containsKey(code)){ 
			result = courses.get(code).toString(); 
		} else { 
			System.out.println("key not found.");
		}
		return result; 


		// method 2

		// return courses.get(code).toString();
	}
	


// R4
	/**
	 * Register a student to attend a course
	 * @param studentID id of the student
	 * @param courseCode id of the course
	 */
	public void register(int studentID, int courseCode){
		//TODO: to be implemented
		Student s = students.get(studentID);
		Course c = courses.get(courseCode); 
		s.addCourse(c); 
		c.addStudent(s);
		logger.info("Student " + studentID + " signed up for course " + courseCode);
	}
	
	/**
	 * Retrieve a list of attendees.
	 * 
	 * The students appear one per row (rows end with `'\n'`) 
	 * and each row is formatted as describe in in method {@link #student}
	 * 
	 * @param courseCode unique id of the course
	 * @return list of attendees separated by "\n"
	 */

	public String listAttendees(int courseCode){
		//TODO: to be implemented
		String result = "";
		var course = courses.get(courseCode); 
		List<Student> attendees = course.getAttended(); 
		for (var student : attendees) {
			result += student.toString() + "\n"; 
		}
		return result;
	}

	/**
	 * Retrieves the study plan for a student.
	 * 
	 * The study plan is reported as a string having
	 * one course per line (i.e. separated by '\n').
	 * The courses are formatted as describe in method {@link #course}
	 * 
	 * @param studentID id of the student
	 * 
	 * @return the list of courses the student is registered for
	 */
	public String studyPlan(int studentID){
		//TODO: to be implemented
		String result = "";
		var student = students.get(studentID);
		List<Course> registered = student.getRegistered();
		for (var course : registered) {
			result += course.toString() + "\n"; 
		}
		return result;
	}

// R5
	/**
	 * records the grade (integer 0-30) for an exam can 
	 * 
	 * @param studentId the ID of the student
	 * @param courseID	course code 
	 * @param grade		grade ( 0-30)
	 */


	public void exam(int studentId, int courseID, int grade){ 
		Student s = students.get(studentId); 
		Course c = courses.get(courseID); 
		
		s.addStudentGrade(grade);
		c.addCourseGrades(grade);

		// if(grade < 0 || grade > 30){ 
		// 	throw new IllegalArgumentException("Grade must be between 0 and 30");
		// }
		// examsGrade.add(new Exam(studentId, courseID, grade));

		logger.info("Student " + studentId + " took an exam in course " + courseID +  " with grade " + grade);
	}

	/**
	 * Computes the average grade for a student and formats it as a string
	 * using the following format 
	 * 
	 * {@code "Student STUDENT_ID : AVG_GRADE"}. 
	 * 
	 * If the student has no exam recorded the method
	 * returns {@code "Student STUDENT_ID hasn't taken any exams"}.
	 * 
	 * @param studentId the ID of the student
	 * @return the average grade formatted as a string.
	 */
	public String studentAvg(int studentId) {
		// int totalGrade = 0;
		// int numberOfExams = 0;
		// String result = "";

		// for (Exam exam : examsGrade) {
		// 	if(exam.getStudentId() == studentId){ 
		// 		totalGrade += exam.getGrade();
		// 		numberOfExams++;
		// 	}
		// }
		// if(numberOfExams == 0){ 
		// 	result = "Student " + studentId + " hasn't taken any exams"; 
		// }
		
		// double avgGrade = (double) totalGrade / numberOfExams;
		// result = "Student " + studentId + " : " + avgGrade;

		var student = students.get(studentId); 
		List<Integer> studentGrade = student.getStudentGrade();
		
		String result = ""; 
		int sum = 0;  
		int numberOfExams = 0; 
		
		for (Integer grade : studentGrade) {
			sum += grade; 
			numberOfExams++; 
		}

		if(numberOfExams == 0){ 
			result = "Student " + studentId + " hasn't taken any exams"; 
			return result; 
		}

		double avgGrade = (double) sum / numberOfExams;
		result = "Student " + studentId + " : " + avgGrade;

		return result	;
	}
	
	/**
	 * Computes the average grades of all students that took the exam for a given course.
	 * 
	 * The format is the following: 
	 * {@code "The average for the course COURSE_TITLE is: COURSE_AVG"}.
	 * 
	 * If no student took the exam for that course it returns {@code "No student has taken the exam in COURSE_TITLE"}.
	 * 
	 * @param courseId	course code 
	 * @return the course average formatted as a string
	 */
	public String courseAvg(int courseId) {
		// int totalGrade = 0; 
		// int numberOfStudents = 0; 
		// String result = "";
		// // The reason that we create an instance of "Course" class and save the result in a variable is that 
		// // we need to access the title of the course
		// var c = courses.get(courseId);

		// for (Exam exam : examsGrade) {
		// 	if(exam.getCourseCode() == courseId){ 
		// 		totalGrade += exam.getGrade(); 
		// 		numberOfStudents++;
		// 	}
		// }
		// if(numberOfStudents == 0){ 
		// 	result = "No student has taken the exam in " + c.getTitle();
		// }
		// double avgCourseGrade = (double) totalGrade / numberOfStudents; 
		// result = "The average for the course " + c.getTitle() + "is: " + avgCourseGrade;

		var course = courses.get(courseId); 
		List<Integer> courseGrades = course.getCourseGrades(); 

		int sum = 0; 
		int numberOfStudents = 0; 
		String result = "";
		
		for (Integer grade : courseGrades) {
			sum += grade; 
			numberOfStudents++; 
		}

		if(numberOfStudents == 0){ 
			result = "No student has taken the exam in " + course.getTitle();
			return result;
		}
		double avgCourseGrade = (double) sum / numberOfStudents; 
		result = "The average for the course " + course.getTitle() + "is: " + avgCourseGrade;

		
 
		return result;
	}

// R6
	/**
	 * Retrieve information for the best students to award a price.
	 * 
	 * The students' score is evaluated as the average grade of the exams they've taken. 
	 * To take into account the number of exams taken and not only the grades, 
	 * a special bonus is assigned on top of the average grade: 
	 * the number of taken exams divided by the number of courses the student is enrolled to, multiplied by 10.
	 * The bonus is added to the exam average to compute the student score.
	 * 
	 * The method returns a string with the information about the three students with the highest score. 
	 * The students appear one per row (rows are terminated by a new-line character {@code '\n'}) 
	 * and each one of them is formatted as: {@code "STUDENT_FIRSTNAME STUDENT_LASTNAME : SCORE"}.
	 * 
	 * @return info on the best three students.
	 */
	public String topThreeStudents() {
	
		// // A TreeMap contain "student Id" as key and list of grades as value
		// TreeMap<Integer, List<Double>> studentGrades = new TreeMap<>();

		// for(Exam exam : this.examsGrade){
		// 	if(studentGrades.containsKey(exam.getStudentId())){
		// 		studentGrades.get(exam.getStudentId()).add((double) exam.getGrade());
		// 	} else {
		// 		List<Double> grades = new ArrayList<>();
		// 		grades.add((double) exam.getGrade());
		// 		studentGrades.put(exam.getStudentId(), grades);
		// 	}
		// }

		// Map<Student, Double> topStudents = studentGrades.entrySet().stream()
		// 		.collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().stream()
		// 		.mapToDouble(Double::doubleValue).average().getAsDouble(), (a, b) -> a, TreeMap::new))
		// 		.entrySet().stream()
		// 		.sorted(Map.Entry.<Integer, Double>comparingByValue().reversed())
		// 		.limit(3)
		// 		.collect(Collectors.toMap(e -> students.get(e.getKey()), e -> e.getValue()));


		// String result = "";

		// for (Map.Entry<Student, Double> entry : topStudents.entrySet()) {
		// 	Student student = entry.getKey();
		// 	Double avg = entry.getValue();

		// 	result += student.getFName() + " " + student.getLName() + " : " + avg + "\n";
		// }

		List<Student> topStudents = students.values().stream().filter(s -> s.avg() > 0)
		.sorted(Comparator.comparing(Student::avg).reversed()).limit(3).toList(); 

		String result = ""; 

		for (var s : topStudents) {
			result += s.getFName() + " " + s.getLName() + 	" : " + s.avg() + "\n"; 
		}

		return result;
	}

// R7
    /**
     * This field points to the logger for the class that can be used
     * throughout the methods to log the activities.
     */
    public static final Logger logger = Logger.getLogger("University");

}