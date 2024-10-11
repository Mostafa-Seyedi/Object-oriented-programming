package it.polito.po.utility;

public class Reading {
    private String contractId;
    private String meterId;
    private String date;
    private double value;
   
    public Reading(String contractId, String meterId, String date, double value) {
        this.contractId = contractId;
        this.meterId = meterId;
        this.date = date;
        this.value = value;
    }

     
    public String getContractId() {
        return contractId;
    }
    public String getMeterId() {
        return meterId;
    }
    public String getDate() {
        return date;
    }
    public double getValue() {
        return value;
    };
}
