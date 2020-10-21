package org.example.model;

public class Movie {


    private int id;
    private String name;
    private int generoID;

    public Movie() {
    }

    public Movie(int id, String name, int generoID) {
        this.id = id;
        this.name = name;
        this.generoID = generoID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGeneroID() {
        return generoID;
    }
}
