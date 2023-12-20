package org.example;

import java.util.ArrayList;
import java.util.Arrays;

public class Curs<E extends Student> {
    private String numeCurs;
    private int capacitateMaxima;

    private ArrayList<E> studentiParticipanti;

    public Curs(String numeCurs, int capacitateMaxima) {
        this.numeCurs = numeCurs;
        this.capacitateMaxima = capacitateMaxima;
        this.studentiParticipanti = new ArrayList<>();
    }

    public void adaugaStudent(E student){
        if(studentiParticipanti.size() < capacitateMaxima) {
            studentiParticipanti.add(student);
        } else {
            throw new LimitaDepasitaException(ANSI_RED + "LIMITA DEPASITA\n" + ANSI_RESET + "Numarul de studenti participanti la cursul de "
                  + ANSI_RED  + numeCurs + ANSI_RESET + " a fost atins");
        }
    }

    // ---------------- culori pentru output --------------
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";
}


//exceptii
class LimitaDepasitaException extends RuntimeException {
    public LimitaDepasitaException(String message) {
        super(message);
    }
}
