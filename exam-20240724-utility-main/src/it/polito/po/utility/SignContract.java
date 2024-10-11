package it.polito.po.utility;

public class SignContract implements Contract{
    private String id; 
    private User user; 
    private ServicePoint sPoint;
    private Meter meter;

    public Meter getMeter() {
        return meter;
    }

    public SignContract(User user, ServicePoint sPoint) {
        this.user = user;
        this.sPoint = sPoint;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public ServicePoint getServicePoint() {
        return sPoint;
    }

}
