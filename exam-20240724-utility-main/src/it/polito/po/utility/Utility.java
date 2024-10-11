package it.polito.po.utility;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Represents the facade class for the utility company.
 */
public class Utility {

    /**
     * Defines a new service point.
     *
     * @param municipality the municipality of the service point
     * @param address      the address of the service point
     * @param lat          the latitude of the service point
     * @param lon          the longitude of the service point
     * @return the id of the service point
     */
    Map<String, Point> servicePoints = new HashMap<>();
    private static int pointId = 0;

    Map<String, Met> meters = new HashMap<>();
    private static int meterId = 0;


    Map<String, User> users = new HashMap<>();
    private static int userId = 0;

    Map<String, Contract> contracts = new HashMap<>();
    private static int contractId = 0;

    Map<String, Reading> readings = new HashMap<>();
    private static int readingId = 0;
    

    public String defineServicePoint(String municipality, String address, double lat, double lon) {
        pointId++; 
        String id = "SP" + pointId;
        var servicePoint = new Point(id, municipality, address, lat, lon);
        Point position = new Point(lon, lat); 
        servicePoint.setPosition(position);
        var p  = new Point(lon, lat);
        servicePoints.put(id, servicePoint);
        return id;
    }

    /**
     * Returns the list of service points.
     *
     * @return the list of service points
     */
    public Collection<String> getServicePoints() {
        return servicePoints.keySet();
    }

    /**
     * Returns the service point with the given id.
     *
     * @param spId the id of the service point
     * @return the service point with the given id
     */
    public ServicePoint getServicePoint(String spId) {
        return servicePoints.get(spId);
    }

    /**
     * Adds a new meter to the utility company.
     *
     * @param sn    serial number of the meter
     * @param brand brand of the meter
     * @param model model of the meter
     * @param unit  unit of measure
     * @return the assigned unique id of the meter
     */
    public String addMeter(String sn, String brand, String model, String unit) {
        meterId++;
        String id = "MT" + meterId;
        var meter = new Met(id, sn, brand, model, unit);
        meters.put(id, meter);
        return id;
    }

    /**
     * Connects a meter to a service point.
     *
     * @param spId    the id of the service point
     * @param meterId the id of the meter
     */
    public void installMeter(String spId, String meterId) {
        var s = servicePoints.get(spId);
        var m = meters.get(meterId); s.setMeter(Optional.of(m)); m.setServicePoint(Optional.of(s));
        // connect them
    }

    /**
     * Returns the meter with the given id.
     *
     * @param mid the id of the meter
     * @return the meter with the given id
     */
    public Meter getMeter(String mid) {
        return meters.get(mid);
    }

    //----
    // R2 User and contracts

    /**
     * Adds a new user to the utility company.
     *
     * @param ssn      the social security number of the user
     * @param name    the name of the user
     * @param surname the surname of the user
     * @param address the address of the user
     * @param email   the email of the user
     * @return the id of the user
     */
    public String addUser(String ssn, String name, String surname, String address, String email) {
        userId++;
        String id = "U" + userId;
        var user = new Users(ssn, name, surname, address, email);
        users.put(id, user);
        return id;
    }

    /**
     * Adds a new business user to the utility company.
     *
     * @param ssn           the social security number or tax code of the user
     * @param businessName the name of the business
     * @param address      the address of the business
     * @param email        the email of the business
     * @return the id of the user
     */
    public String addUser(String ssn, String businessName, String address, String email) {
        userId++;
        String id = "U" + userId;
        var user = new Users(ssn, businessName, address, email);
        users.put(id, user);

        return id;
    }

    /**
     * Returns the user with the given id.
     *
     * @param uid the id of the user
     * @return the user with the given id
     */
    public User getUser(String uid) {
        return users.get(uid);
    }

    /**
     * Returns all users
     *
     * @return a collection of users' id
     */
    public Collection<String> getUsers() {
        return users.keySet();
    }

    /**
     * Signs a new contract with a user that is provided through a service point.
     *
     * @param user the id of the user
     * @param pdp  the id of the service point
     * @return the id of the contract
     */
    public String signContract(String user, String pdp) throws UtilityException {
        if (!users.containsKey(user)) {
            throw new UtilityException("Invalid user ID: " + user);
        }
        if (!servicePoints.containsKey(pdp)) {throw new UtilityException(pdp);}ServicePoint servicePoint = servicePoints.get(pdp);if (!servicePoint.getMeter().isPresent()) {throw new UtilityException("Service point has no meter installed: " + pdp);}
        contractId++; 
        String id = "C" + contractId;
        var u = users.get(user);
        var s = servicePoints.get(pdp);
        var contract = new SignContract(u, s);
        contracts.put(id, contract);

        return id;
    }

    /**
     * Returns the contract with the given id.
     *
     * @param contractId the id of the contract
     * @return the contract with the given id
     */
    public Contract getContract(String contractId) {
        return contracts.get(contractId);
    }

    //----
    // R3 Reading

    /**
     * Adds a new reading for a given meter.
     *
     * @param contractId
     * @param meterId
     * @param date
     * @param value
     * @throws UtilityException if the contract and meter do not match
     */
    public void addReading(String contractId, String meterId, String date, double value) throws UtilityException {
        var contract = contracts.get(contractId); 
        var meter = meters.get(meterId);
        if(!contract.getServicePoint().getMeter().get().getId().equals(meterId)){throw new UtilityException("Not match");}
        var reading = new Reading(contractId, meterId, date, value);
        readings.put(String.valueOf(this.readingId++), reading);
        System.out.println(contractId);
        }
        // record reading

    /**
     * Adds a new reading for a given meter.
     *
     * @param contractId id of the contract
     * @return a map that links dates and metering values
     */      
    public Map<String,Double> getReadings(String contractId) {
        Map<String, Double> g = new HashMap<>();
        for (var reading : readings.values()) {
            if (reading.getContractId().equals(contractId)) {
                g.put(reading.getDate(), reading.getValue());
            } 
        }
        return g;
    }

    /**
     * Read latest reading
     * 
     * @param contractId id of the contract
     * @return a metering value 
     */
    public double getLatestReading(String contractId) {
        return this.readings.values().stream().filter(r->r.getContractId().equals(contractId)).sorted(Comparator.comparing(Reading::getDate).reversed()).mapToDouble(Reading::getValue).findFirst().orElse(Double.NaN);
    }

    //----
    // R4 Tariffe

    /**
     * Computes the estimated reading for a given contract and date.
     * The estimated reading is computed as the linear interpolation of the latest two readings.
     *
     * @param contractId the id of the contract
     * @param date       the date for which the reading is estimated
     * @return the estimated reading
     * @throws UtilityException if estimation cannot be computed
     */
    public double getEstimatedReading(String contractId, String date) throws UtilityException {
        List<Reading> r = readings.values().stream() .filter(re -> re.getContractId().equals(contractId)) .sorted(Comparator.comparing(Reading::getDate)) .collect(Collectors.toList()); if (r.size() < 2) throw new UtilityException("Not enough readings"); LocalDate queryDate = LocalDate.parse(date), t0, t1; double y0, y1; for (int i = 0; i < r.size(); i++) { t1 = LocalDate.parse(r.get(i).getDate()); if (queryDate.isEqual(t1)) return r.get(i).getValue(); if (queryDate.isBefore(t1)) { if (i == 0) throw new UtilityException("Date before first reading"); t0 = LocalDate.parse(r.get(i - 1).getDate()); y0 = r.get(i - 1).getValue(); y1 = r.get(i).getValue(); return y0 + ChronoUnit.DAYS.between(t0, queryDate) / (double) ChronoUnit.DAYS.between(t0, t1) * (y1 - y0); } } t0 = LocalDate.parse(r.get(r.size() - 2).getDate()); t1 = LocalDate.parse(r.get(r.size() - 1).getDate()); y0 = r.get(r.size() - 2).getValue(); y1 = r.get(r.size() - 1).getValue(); return y0 + ChronoUnit.DAYS.between(t0, queryDate) / (double) ChronoUnit.DAYS.between(t0, t1) * (y1 - y0);
    }

    /**
     * Computes the consumption between two dates
     * 
     * @param contractId    the id of the contract
     * @param dateInitial   the initial date
     * @param dateFinal     the final date
     * @return  the total consumption between the two dates
     * @throws UtilityException if the contract id is not valid or a reading cannot be estimated for the dates
     */
    public double getConsumption(String contractId, String dateInitial, String dateFinal) throws UtilityException {
        return getEstimatedReading(contractId, dateFinal) - getEstimatedReading(contractId, dateInitial);
    }

        /**
     * Returns the consumption breakdown (month by month) 
     * 
     * @param contractId    id of the contrac
     * @param monthStart    initial month
     * @param monthEnd      final month
     * @param year          year of reference
     * @return the breakdown
     * @throws UtilityException in case contract is not valid, or it is not possible to get reading estimates
     */
    public List<String> getBillBreakdown(String contractId, int monthStart, int monthEnd, int year) throws UtilityException {
        List<String> breakdown = new ArrayList<>(); DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); for (int month = monthStart; month <= monthEnd; month++) { LocalDate startDate = LocalDate.of(year, month, 1); LocalDate endDate = startDate.plusMonths(1); double startReading = getEstimatedReading(contractId, startDate.format(formatter)); double endReading = getEstimatedReading(contractId, endDate.format(formatter)); breakdown.add(startDate + ".." + endDate + ": " + startReading + " -> " + endReading + " = " + (endReading - startReading)); } return breakdown;
    }

}
