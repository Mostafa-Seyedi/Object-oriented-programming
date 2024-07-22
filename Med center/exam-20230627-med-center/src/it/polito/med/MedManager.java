package it.polito.med;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class MedManager {

	/**
	 * add a set of medical specialities to the list of specialities
	 * offered by the med centre.
	 * Method can be invoked multiple times.
	 * Possible duplicates are ignored.
	 * 
	 * @param specialities the specialities
	 */
	private Set<String> specialitiesColl = new TreeSet<>(); 

	private Map<String, Doctor> doctors = new TreeMap<>();

	private Map<String, Appointment> appointments = new TreeMap<>(); 
	private static int appID = 0; 

	private String currentDate = ""; 

	public void addSpecialities(String... specialities) {
		for (var speciality : specialities) {
			specialitiesColl.add(speciality);
		}
		
	}

	/**
	 * retrieves the list of specialities offered in the med centre
	 * 
	 * @return list of specialities
	 */
	public Collection<String> getSpecialities() {
		return specialitiesColl;
	}
	
	
	/**
	 * adds a new doctor with the list of their specialitiesg
	 * 
	 * @param id		unique id of doctor
	 * @param name		name of doctor
	 * @param surname	surname of doctor
	 * @param speciality speciality of the doctor
	 * @throws MedException in case of duplicate id or non-existing speciality
	 */
	public void addDoctor(String id, String name, String surname, String speciality) throws MedException {
		if(!specialitiesColl.contains(speciality) || doctors.containsKey(id)){ 
			throw new MedException("Speciality does not exist or doctor ID already exist");
		}
		var doctor = new Doctor(id, name, surname, speciality); 
		doctors.put(id, doctor);

	}

	/**
	 * retrieves the list of doctors with the given speciality
	 * 
	 * @param speciality required speciality
	 * @return the list of doctor ids
	 */
	public Collection<String> getSpecialists(String speciality) {
		return doctors.values().stream().filter(d->d.getSpeciality().equals(speciality)).map(Doctor::getSpeciality).toList();
	}

	/**
	 * retrieves the name of the doctor with the given code
	 * 
	 * @param code code id of the doctor 
	 * @return the name
	 */
	public String getDocName(String code) {
		return doctors.get(code).getName();
	}

	/**
	 * retrieves the surname of the doctor with the given code
	 * 
	 * @param code code id of the doctor 
	 * @return the surname
	 */
	public String getDocSurname(String code) {
		return doctors.get(code).getSurname();
	}

	/**
	 * Define a schedule for a doctor on a given day.
	 * Slots are created between start and end hours with a 
	 * duration expressed in minutes.
	 * 
	 * @param code	doctor id code
	 * @param date	date of schedule
	 * @param start	start time
	 * @param end	end time
	 * @param duration duration in minutes
	 * @return the number of slots defined
	 */
	public int addDailySchedule(String code, String date, String start, String end, int duration) {
		int newStart = parsTimeString(start); 
		int newEnd = parsTimeString(end); 

		for (int i = newStart; i < newEnd ; i += duration) {
			doctors.get(code).addSchedule(date, parsTimeInt(i), parsTimeInt(i+duration));
		}
		
		return (newEnd - newStart) / duration;
	}

	private int parsTimeString(String time){ 
		String[] parts = time.split(":"); 
		int hour = Integer.parseInt(parts[0].trim()); 
		int min = Integer.parseInt(parts[1].trim()); 
		return (hour * 60) + min;  
	}

	private String parsTimeInt(int time){ 
		int hour = time / 60;
		int min = time%60;  
		
		return String.format("%02d:%02d", hour,min); 
	}

	

	/**
	 * retrieves the available slots available on a given date for a speciality.
	 * The returned map contains an entry for each doctor that has slots scheduled on the date.
	 * The map contains a list of slots described as strings with the format "hh:mm-hh:mm",
	 * e.g. "14:00-14:30" describes a slot starting at 14:00 and lasting 30 minutes.
	 * 
	 * @param date			date to look for
	 * @param speciality	required speciality
	 * @return a map doc-id -> list of slots in the schedule
	 */

	
	public Map<String, List<String>> findSlots(String date, String speciality) {
		Map<String, List<String>> sl = new TreeMap<>(); 
		List<String> specificDocIds = doctors.values().stream().filter(d -> d.getSpeciality().equals(speciality))
		.map(Doctor::getID).toList();
		
		for (var i : specificDocIds) {
			for (var schedule : doctors.get(i).getSchedule().values()) {
				sl.put(i, new ArrayList<>());
				sl.get(i).addAll(schedule.entrySet().stream().map(t -> t.getKey()  + "-"  + t.getValue()).toList());
			}		
			
		}

		return sl;
	}

	/**
	 * Define an appointment for a patient in an existing slot of a doctor's schedule
	 * 
	 * @param ssn		ssn of the patient
	 * @param name		name of the patient
	 * @param surname	surname of the patient
	 * @param code		code id of the doctor
	 * @param date		date of the appointment
	 * @param slot		slot to be booked
	 * @return a unique id for the appointment
	 * @throws MedException	in case of invalid code, date or slot
	 */
	public String setAppointment(String ssn, String name, String surname, String code, String date, String slot) throws MedException {
		appID++; 
		String appointmentID = String.valueOf(appID);
		if(!doctors.containsKey(code) || doctors.get(code).getSchedule() == null ||
			 doctors.get(code).getSchedule().values() == null){ 
				throw new MedException();
			 }

		var appointment = new Appointment(appointmentID, ssn, name, surname, code, date, slot);
		appointments.put(appointmentID, appointment);	 
		return appointments.get(appointmentID).getID();
	}

	/**
	 * retrieves the doctor for an appointment
	 * 
	 * @param idAppointment id of appointment
	 * @return doctor code id
	 */
	public String getAppointmentDoctor(String idAppointment) {
		return appointments.get(idAppointment).getDoctorID();
	}

	/**
	 * retrieves the patient for an appointment
	 * 
	 * @param idAppointment id of appointment
	 * @return doctor patient ssn
	 */
	public String getAppointmentPatient(String idAppointment) {
		return appointments.get(idAppointment).getPatientSSN();
	}

	/**
	 * retrieves the time for an appointment
	 * 
	 * @param idAppointment id of appointment
	 * @return time of appointment
	 */
	public String getAppointmentTime(String idAppointment) {
		String[] parts = appointments.get(idAppointment).getSlot().split("-");
		return parts[0];
	}

	/**
	 * retrieves the date for an appointment
	 * 
	 * @param idAppointment id of appointment
	 * @return date
	 */
	public String getAppointmentDate(String idAppointment) {
		return appointments.get(idAppointment).getDate();
	}

	/**
	 * retrieves the list of a doctor appointments for a given day.
	 * Appointments are reported as string with the format
	 * "hh:mm=SSN"
	 * 
	 * @param code doctor id
	 * @param date date required
	 * @return list of appointments
	 */
	public Collection<String> listAppointments(String code, String date) {
		return appointments.values().stream().filter(a -> a.getDoctorID().equals(code))
		.filter(a -> a.getDate().equals(date)).map(Appointment::slotSSN).toList();
		
	}

	/**
	 * Define the current date for the medical centre
	 * The date will be used to accept patients arriving at the centre.
	 * 
	 * @param date	current date
	 * @return the number of total appointments for the day
	 */
	public int setCurrentDate(String date) {
		this.currentDate = date;
		return appointments.values().stream().filter(a -> a.getDate().equals(date)).toList().size();
	}

	/**
	 * mark the patient as accepted by the med centre reception
	 * 
	 * @param ssn SSN of the patient
	 */
	public void accept(String ssn) {
		Appointment app = appointments.values().stream().filter(a -> a.getPatientSSN().equals(ssn)).findFirst().orElse(null);
		app.setPatientAccepted();

	}

	/**
	 * returns the next appointment of a patient that has been accepted.
	 * Returns the id of the earliest appointment whose patient has been
	 * accepted and the appointment not completed yet.
	 * Returns null if no such appointment is available.
	 * 
	 * @param code	code id of the doctor
	 * @return appointment id
	 */
	public String nextAppointment(String code) {
		Appointment app =  appointments.values().stream().filter(a -> a.getDoctorID().equals(code))
		.filter(a -> a.patientAccepted()).findFirst()
		.filter(a -> a.appointmentCompleted() == false)
		.orElse(null);

		if(app == null) return null; 

		return app.getID();
	}

	/**
	 * mark an appointment as complete.
	 * The appointment must be with the doctor with the given code
	 * the patient must have been accepted
	 * 
	 * @param code		doctor code id
	 * @param appId		appointment id
	 * @throws MedException in case code or appointment code not valid,
	 * 						or appointment with another doctor
	 * 						or patient not accepted
	 * 						or appointment not for the current day
	 */
	public void completeAppointment(String code, String appId)  throws MedException {
		if(!doctors.containsKey(code) || !appointments.containsKey(appId) || appointments.get(appId).getDoctorID() != code
		|| appointments.get(appId).patientAccepted() == false || appointments.get(appId).getDate() != currentDate){ 
			throw new MedException();
		}
		Appointment app = appointments.values().stream().filter(a -> a.getDoctorID().equals(code))
		.filter(a -> a.getID().equals(appId)).findFirst().orElse(null);
		
		app.setAppointmentComplete();
	}

	/**
	 * computes the show rate for the appointments of a doctor on a given date.
	 * The rate is the ratio of accepted patients over the number of appointments
	 *  
	 * @param code		doctor id
	 * @param date		reference date
	 * @return	no show rate
	 */
	public double showRate(String code, String date) {
		double acceptedPatients = appointments.values().stream().filter(a -> a.getDoctorID().equals(code))
		.filter(a -> a.getDate().equals(date)).filter(a -> a.patientAccepted()).toList().size();
		System.out.println(acceptedPatients);

		double appNum = appointments.values().stream().filter(a -> a.getDoctorID().equals(code))
		.filter(a -> a.getDate().equals(date)).toList().size();
		System.out.println(appNum);

		return acceptedPatients / appNum;
	}

	/**
	 * computes the schedule completeness for all doctors of the med centre.
	 * The completeness for a doctor is the ratio of the number of appointments
	 * over the number of slots in the schedule.
	 * The result is a map that associates to each doctor id the relative completeness
	 * 
	 * @return the map id : completeness
	 */
	public Map<String, Double> scheduleCompleteness() {
		Map<String, Double> completeness = new TreeMap<>(); 
		
		for (var d :doctors.values()) {
			double appNum = appointments.values().stream().filter(a -> a.getDoctorID() == d.getID()).toList().size();
			double slotsInSchecule = d.getSchedule().values().stream().mapToInt(s -> s.size()).sum();
			completeness.put(d.getID(), (double) appNum / slotsInSchecule); 
		}
		return completeness;
	}


	
}
