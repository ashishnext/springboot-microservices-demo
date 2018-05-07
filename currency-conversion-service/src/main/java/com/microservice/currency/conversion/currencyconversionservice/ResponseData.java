package com.microservice.currency.conversion.currencyconversionservice;


public class ResponseData {

    private Long id;
    private String name;
    private String email;
    private String address;
    private int port;

    public ResponseData() {

    }

    public ResponseData(Long id, String name, String email, String address, int port) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.port = port;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

}
