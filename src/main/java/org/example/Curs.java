package org.example;

import java.util.ArrayList;

public class Curs <E extends Student> {
    private final String numeCurs;
    private final int capacitateMaxima;

    private final ArrayList<E> studentiParticipanti;

    public ArrayList<E> getStudentiParticipanti() {
        return studentiParticipanti;
    }

    public String getNumeCurs() {
        return numeCurs;
    }

    public int getCapacitateMaxima() {
        return capacitateMaxima;
    }

    public Curs(String numeCurs, int capacitateMaxima) {
        this.numeCurs = numeCurs;
        this.capacitateMaxima = capacitateMaxima;
        this.studentiParticipanti = new ArrayList<>();
    }

    // exceptia este 1 ----> atunci cand urmatorul student nu are media egala cu ultimul din lista
    // exceptia este 0 ----> atunci cand urmatorul are aceeasi medie cu ultimul din lista
    public void adaugaStudent(E student, int exceptie){
        if(studentiParticipanti.size() < capacitateMaxima && exceptie == 1) {
            studentiParticipanti.add(student);
        } else if(studentiParticipanti.size() >= capacitateMaxima && exceptie == 1){
            throw new LimitaDepasitaException(ANSI_RED + "LIMITA DEPASITA\n" + ANSI_RESET + "Numarul de studenti participanti la cursul de "
                  + ANSI_RED  + numeCurs + ANSI_RESET + " a fost atins");
        } else if(studentiParticipanti.size() >= capacitateMaxima && exceptie == 0) {
            studentiParticipanti.add(student);
        }
    }

    // va fi nevoie la afisarea cursului
    public void sortareAlfabetica() {
        E intermediar;
        for (int i = 0; i < studentiParticipanti.size(); i++) {
            for (int j = i + 1; j < studentiParticipanti.size(); j++) {
                if (studentiParticipanti.get(i).getNume().compareTo(studentiParticipanti.get(j).getNume()) > 0) {
                    intermediar = studentiParticipanti.get(j);
                    studentiParticipanti.set(j, studentiParticipanti.get(i));
                    studentiParticipanti.set(i, intermediar);
                }
            }
        }
    }

    // ---------------- culori pentru output --------------
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";
}


//exceptii
class LimitaDepasitaException extends RuntimeException {
    public LimitaDepasitaException(String message) {
        super(message);
    }
}
