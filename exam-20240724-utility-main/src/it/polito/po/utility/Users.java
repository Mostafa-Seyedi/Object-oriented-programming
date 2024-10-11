package it.polito.po.utility;

public class Users implements User{
    private String id; 
    private String ssn; 
    private String name;
    private String surname; 
    private String address; 
    private String email;
    private Type type;

    public Users(String ssn, String name, String surname, String address, String email) {
        this.ssn = ssn;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.email = email;
        this.type = Type.RESIDENTIAL;
    }

    public Users(String ssn, String name,String address, String email) {
        this.ssn = ssn;
        this.name = name;
        this.address = address;
        this.email = email;
        this.type = Type.BUSINESS;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getCF() {
       return ssn;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getSurname() {
        return surname;
    }
    @Override
    public String getAddress() {
        return address;
    }
    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public Type getType() {
        // TODO Auto-generated method stub
        return type;
    }

}
