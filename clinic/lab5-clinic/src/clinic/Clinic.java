package clinic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;



/**
 * Represents a clinic with patients and doctors.
 * 
 */
public class Clinic {

	/**
	 * Add a new clinic patient.
	 * 
	 * @param first first name of the patient
	 * @param last last name of the patient
	 * @param ssn SSN number of the patient
	 */
	Map<String, Patient> patients = new TreeMap<>();

	Map<Integer, Doctor> doctors = new TreeMap<>();

	Map<String, Doctor> doctorsBySSN = new TreeMap<>();


	public void addPatient(String first, String last, String ssn) {
   		// TODO to be implemented
			var patient = new Patient(first, last, ssn);
			patients.put(ssn, patient); 
 	}


	/**
	 * Retrieves a patient information
	 * 
	 * @param ssn SSN of the patient
	 * @return the object representing the patient
	 * @throws NoSuchPatient in case of no patient with matching SSN
	 */
	public String getPatient(String ssn) throws NoSuchPatient {
   		// TODO to be implemented
			if(!(patients.containsKey(ssn))){ 
				throw new NoSuchPatient(); 
			}

			return patients.get(ssn).toString();
	}

	/**
	 * Add a new doctor working at the clinic
	 * 
	 * @param first first name of the doctor
	 * @param last last name of the doctor
	 * @param ssn SSN number of the doctor
	 * @param docID unique ID of the doctor
	 * @param specialization doctor's specialization
	 */
	public void addDoctor(String first, String last, String ssn, int docID, String specialization) {
   		// TODO to be implemented
			var doctor = new Doctor(first, last, ssn, docID, specialization); 

			doctors.put(docID, doctor); 

			doctorsBySSN.put(ssn, doctor); 
			

			if(!(patients.containsKey(ssn))){ 
				patients.put(ssn, new Patient(first, last, ssn)); 
			}
	}

	/**
	 * Retrieves information about a doctor
	 * 
	 * @param docID ID of the doctor
	 * @return object with information about the doctor
	 * @throws NoSuchDoctor in case no doctor exists with a matching ID
	 */
	public String getDoctor(int docID) throws NoSuchDoctor {
   		// TODO to be implemented
			if (!(doctors.containsKey(docID))){ 
				throw new NoSuchDoctor();
			}	
			return doctors.get(docID).toString();
	}
	
	/**
	 * Assign a given doctor to a patient
	 * 
	 * @param ssn SSN of the patient
	 * @param docID ID of the doctor
	 * @throws NoSuchPatient in case of not patient with matching SSN
	 * @throws NoSuchDoctor in case no doctor exists with a matching ID
	 */
	public void assignPatientToDoctor(String ssn, int docID) throws NoSuchPatient, NoSuchDoctor {
   		// TODO to be implemented
			var patient = patients.get(ssn); 
			var doctor = doctors.get(docID); 

			if(!(patients.containsKey(ssn))){ 
				throw new NoSuchPatient(); 
			}
			if(!(doctors.containsKey(docID))){ 
				throw new NoSuchDoctor();
			}

			patient.doctor = doctor; 
			doctor.patients.put(ssn, patient); 
			
	}

	/**
	 * Retrieves the id of the doctor assigned to a given patient.
	 * 
	 * @param ssn SSN of the patient
	 * @return id of the doctor
	 * @throws NoSuchPatient in case of not patient with matching SSN
	 * @throws NoSuchDoctor in case no doctor has been assigned to the patient
	 */
	public int getAssignedDoctor(String ssn) throws NoSuchPatient, NoSuchDoctor {
   		// TODO to be implemente
			if(!(patients.containsKey(ssn))){ 
				throw new NoSuchPatient();
			}
			if(patients.get(ssn).doctor == null){ 
				throw new NoSuchDoctor(); 
			} 
		   return patients.get(ssn).doctor.getID();
	}
	
	/**
	 * Retrieves the patients assigned to a doctor
	 * 
	 * @param id ID of the doctor
	 * @return collection of patient SSNs
	 * @throws NoSuchDoctor in case the {@code id} does not match any doctor 
	 */
	public Collection<String> getAssignedPatients(int id) throws NoSuchDoctor {
   		// TODO to be implemented
			if(doctors.get(id) == null){
				throw new NoSuchDoctor();  
			}
		   return doctors.get(id).patients.keySet();
	}
	
	/**
	 * Loads data about doctors and patients from the given stream.
	 * <p>
	 * The text file is organized by rows, each row contains info about
	 * either a patient or a doctor.</p>
	 * <p>
	 * Rows containing a patient's info begin with letter {@code "P"} followed by first name,
	 * last name, and SSN. Rows containing doctor's info start with letter {@code "M"},
	 * followed by badge ID, first name, last name, SSN, and speciality.<br>
	 * The elements on a line are separated by the {@code ';'} character possibly
	 * surrounded by spaces that should be ignored.</p>
	 * <p>
	 * In case of error in the data present on a given row, the method should be able
	 * to ignore the row and skip to the next one.<br>

	 * 
	 * @param reader reader linked to the file to be read
	 * @throws IOException in case of IO error
	 */
	public int loadData(Reader reader) throws IOException {
   		// TODO to be implemented
			 int processedLines = 0; 
			 try(BufferedReader br = new BufferedReader(reader)){ 
				 String line; 
				 while ((line = br.readLine()) != null) {
					 if(processLine(line)){ 
						 processedLines++; 
					 }
				 }
			 }
				return processedLines;
	 }
	 private boolean processLine(String line){ 
		 line = line.trim(); 
		 if(line.isEmpty()){ 
			 return false; 
		 }
		 try{
			 String[] parts = line.split(";"); 
			 if(parts.length < 2){ 
				 System.out.println("Invalid line format: " + line);
				 return false; 
			 }
			 String type = parts[0].trim(); 
 
			 if(type.equalsIgnoreCase("P")){ 
				 if(parts.length < 4){ 
					 System.out.println("Invalid patient line format: " + line);
					 return false; 
				 }
				 String firstName = parts[1];
				 String lastName = parts[2].trim();
				 String SSN = parts[3].trim();
				 this.addPatient(firstName, lastName, SSN); 
 
 
			 } else if (type.equalsIgnoreCase("M")){ 
				 if(parts.length < 6){ 
					 System.out.println("Invalid patient line format: " + line);
					 return false; 
				 }
 
				 int badgeID = Integer.parseInt(parts[1].trim()); 
				 String firstName = parts[2].trim();
				 String lastName = parts[3].trim();
				 String SSN = parts[4].trim();
				 String specialization = parts[5].trim(); 
				 this.addDoctor(firstName, lastName, SSN, badgeID, specialization); 
			 } else { 
				 System.out.println("Unknown type: " + type);
				 return false; 
			 }
			 return true; 
	 } catch(Exception e){ 
		 e.getStackTrace(); 
		 return false; 
	 }
	 

	}


	/**
	 * Loads data about doctors and patients from the given stream.
	 * <p>
	 * The text file is organized by rows, each row contains info about
	 * either a patient or a doctor.</p>
	 * <p>
	 * Rows containing a patient's info begin with letter {@code "P"} followed by first name,
	 * last name, and SSN. Rows containing doctor's info start with letter {@code "M"},
	 * followed by badge ID, first name, last name, SSN, and speciality.<br>
	 * The elements on a line are separated by the {@code ';'} character possibly
	 * surrounded by spaces that should be ignored.</p>
	 * <p>
	 * In case of error in the data present on a given row, the method calls the
	 * {@link ErrorListener#offending} method passing the line itself,
	 * ignores the row, and skip to the next one.<br>
	 * 
	 * @param reader reader linked to the file to be read
	 * @param listener listener used for wrong line notifications
	 * @throws IOException in case of IO error
	 */
	public int loadData(Reader reader, ErrorListener listener) throws IOException {
   		// TODO to be implemented
			int processedLines = 0;
			int lineNumber = 1; // Start line number at 1
			try (BufferedReader br = new BufferedReader(reader)) {
				String line;
				while ((line = br.readLine()) != null) {
					if (processLine(line, lineNumber, listener)) {
						processedLines++;
						}
						lineNumber++;
				 }
			 }
			 return processedLines;
	 }

	private boolean processLine(String line, int lineNumber, ErrorListener errorListener) {
		line = line.trim();
			if (line.isEmpty()) {
				errorListener.offending(lineNumber, line);
				return false; // Skip empty lines
			}

			try {
				String[] parts = line.split(";");

					if (parts.length < 2) {
						errorListener.offending(lineNumber, line);
						return false;
					 }

					String type = parts[0].trim();

					if (type.equalsIgnoreCase("P")) {
							 // Process as patient
							if (parts.length < 4) {
								errorListener.offending(lineNumber, line);
								return false;
							 }
							 String firstName = parts[1].trim();
							 String lastName = parts[2].trim();
							 String ssn = parts[3].trim();
							 addPatient(firstName, lastName, ssn);
					 } else if (type.equalsIgnoreCase("M")) {
							 // Process as doctor
							 if (parts.length < 6) {
									 errorListener.offending(lineNumber, line);
									 return false;
							 }
							 int badgeID = Integer.parseInt(parts[1].trim());
							 String firstName = parts[2].trim();
							 String lastName = parts[3].trim();
							 String ssn = parts[4].trim();
							 String specialization = parts[5].trim();
							 addDoctor(firstName, lastName, ssn, badgeID, specialization);
					 } else {
							 errorListener.offending(lineNumber, line);
							 return false;
					 }
					 return true;

			 } catch (Exception e) {
					 errorListener.offending(lineNumber, line);
					 return false;
			 }
	 }
		
	/**
	 * Retrieves the collection of doctors that have no patient at all.
	 * The doctors are returned sorted in alphabetical order
	 * 
	 * @return the collection of doctors' ids
	 */

	// As we can see, the return type of this method is an Integer collection, so we understand that we need 
	// to return a list of badgeId of doctors

	public Collection<Integer> idleDoctors(){
   		// TODO to be implemented
		   return doctors.values().stream().filter(doctor -> doctor.patients.isEmpty())
			 .sorted(Comparator.comparing(Doctor::getLastName).thenComparing(Doctor::getFirstName)).map(Doctor::getID)
			 .toList();
	}

	/**
	 * Retrieves the collection of doctors having a number of patients larger than the average.
	 * 
	 * @return  the collection of doctors' ids
	 */
	public Collection<Integer> busyDoctors(){
   		// TODO to be implemented
			int totalPatients = 0;
			int NumberOfDoctors = doctors.size(); 
			for (var doctor : doctors.values()) {
				totalPatients += doctor.patients.size(); 
			}
			double average = (NumberOfDoctors == 0) ? 0 : (double) totalPatients / NumberOfDoctors; 

		   return doctors.values().stream().filter(doctor -> doctor.patients.size() > average)
			 .sorted(Comparator.comparing(Doctor::getLastName).thenComparing(Doctor::getFirstName)).map(Doctor::getID)
			 .toList();
	}

	/**
	 * Retrieves the information about doctors and relative number of assigned patients.
	 * <p>
	 * The method returns list of strings formatted as "{@code ### : ID SURNAME NAME}" where {@code ###}
	 * represent the number of patients (printed on three characters).
	 * <p>
	 * The list is sorted by decreasing number of patients.
	 * 
	 * @return the collection of strings with information about doctors and patients count
	 */
	public Collection<String> doctorsByNumPatients(){
   		// TODO to be implemented
			List<Doctor> sortedDoctors = doctors.values().stream().sorted(Comparator
			.comparingInt((Doctor doctor) -> doctor.patients
			.size()).reversed()).toList(); // or --> .collect(Collectors.toList())

			List<String> doctorInfoList = new ArrayList<>(); 
			for(var doctor : sortedDoctors){ 
				String doctorInfo = String.format("%03d : %d %s %s"
				, doctor.patients.size(), doctor.getID(), doctor.getLastName(), doctor.getFirstName()); 

				doctorInfoList.add(doctorInfo);
			}
		   return doctorInfoList;
	}
	
	/**
	 * Retrieves the number of patients per (their doctor's)  speciality
	 * <p>
	 * The information is a collections of strings structured as {@code ### - SPECIALITY}
	 * where {@code SPECIALITY} is the name of the speciality and 
	 * {@code ###} is the number of patients cured by doctors with such speciality (printed on three characters).
	 * <p>
	 * The elements are sorted first by decreasing count and then by alphabetic speciality.
	 * 
	 * @return the collection of strings with speciality and patient count information.
	 */
	public Collection<String> countPatientsPerSpecialization(){
   		// TODO to be implemented
			Map<String, Integer> specializationCount = new TreeMap<>(); 
			for(var doctor : doctors.values()){ 
				String specialization = doctor.getSpecialization(); 
				int patientCount = doctor.patients.size(); 

				specializationCount.put(specialization, specializationCount
				.getOrDefault(specialization, 0) + patientCount);
			}

			// filter the specializations with no patients
			specializationCount.entrySet().removeIf(entry -> entry.getValue() == 0); 

			// Sorting
		   return specializationCount.entrySet().stream()
			 .sorted(Map.Entry.<String, Integer>comparingByValue(Comparator.reverseOrder())
			 .thenComparing(Map.Entry.comparingByKey()))
			 .map(entry -> String.format("%03d - %s", entry.getValue(), entry.getKey()))
			 .collect(Collectors.toList());
	}

}
