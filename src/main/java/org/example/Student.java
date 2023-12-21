package org.example;

import java.util.ArrayList;

public class Student {
    private String Nume;
    private double medie;
    private ArrayList<Curs> preferinte = new ArrayList<>();

    public Student(String nume) {
        this.Nume = nume;
    }

    public Student(String nume, double medie) {
        this.Nume = nume;
        this.medie = medie;
    }

    public void setNume(String nume) {
        Nume = nume;
    }

    public void setMedie(double medie) {
        this.medie = medie;
    }

    public String getNume() {
        return Nume;
    }

    public double getMedie() {
        return medie;
    }

    public ArrayList<Curs> getPreferinte() {
        return preferinte;
    }

    public <E extends Student> void adaugaPreferintaCurs(Curs<E> curs) {
        preferinte.add(curs);
    }

}
