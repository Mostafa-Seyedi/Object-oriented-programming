package it.polito.po.utility;

import java.util.Optional;

public class Met implements Meter{
    private String id; 
    private String sn;
    private String brand; 
    private String model; 
    private String unit;
    private Optional<Point> servicePoint = Optional.empty();
    public void setServicePoint(Optional<Point> servicePoint) {
        this.servicePoint = servicePoint;
    }
    private Contract c;

    public Contract getC() {
        return c;
    }

    public Met(String id, String sn, String brand, String model, String unit) {
        this.id = id;
        this.sn = sn;
        this.brand = brand;
        this.model = model;
        this.unit = unit;
    }

    @Override
    public String getId() {
        // TODO Auto-generated method stub
        return id;
    }

    @Override
    public String getSN() {
        // TODO Auto-generated method stub
       return sn;
    }

    @Override
    public String getBrand() {
        // TODO Auto-generated method stub
       return brand;
    }

    @Override
    public String getModel() {
        // TODO Auto-generated method stub
       return model;
    }

    @Override
    public String getUnit() {
        // TODO Auto-generated method stub
       return unit;
    }

    @Override
    public Optional<ServicePoint> getServicePoint() {
        // TODO Auto-generated method stub
        return servicePoint.map(sp -> (ServicePoint) sp);
    }

}
