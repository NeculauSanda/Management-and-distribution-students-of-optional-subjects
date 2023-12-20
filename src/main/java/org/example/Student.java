package org.example;

public class Student {
    private String Nume;
    private double medie;

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

}
