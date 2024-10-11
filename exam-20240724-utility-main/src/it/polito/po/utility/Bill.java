package it.polito.po.utility;

public class Bill {
    private String contractCode; 
    private String start; 
    private String end;
    
    public Bill(String contractCode, String start, String end) {
        this.contractCode = contractCode;
        this.start = start;
        this.end = end;
    }
    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }
    public void setStart(String start) {
        this.start = start;
    }
    public void setEnd(String end) {
        this.end = end;
    }
    public String getContractCode() {
        return contractCode;
    }
    public String getStart() {
        return start;
    }
    public String getEnd() {
        return end;
    }

}
