package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        //declarare secretariat
        Secretariat secretariat = new Secretariat();

        //citire fisier
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/" + args[0] + "/" + args[0] + ".in"))) {
            String line;
            String splitBy = " - ";
            while ((line = br.readLine()) != null) {
                String[] intrari = line.split(splitBy);
                // adugam studentii
                if (intrari[0].equals("adauga_student")) {

                    try {
                        secretariat.adaugaStudent(intrari[1], intrari[2]);
//                        secretariat.afiseazaStudent(); // de sters
                    } catch (StudentDuplicat e) {
                        System.out.println(e.getMessage());
                    }

                } else if (intrari[0].equals("citeste_mediile")) {
                    int nr = 1;
                    while (true) {
                        // deschidem fisierele cu note pana cand nu mai exista fisier pe care sa-l deschida
                        try (BufferedReader br2 = new BufferedReader(new FileReader("src/main/resources/" + args[0] + "/" + "note_" + nr + ".txt"))) {
                            String linetwo;
                            while ((linetwo = br2.readLine()) != null) {
                                String[] medii = linetwo.split(splitBy);
//                                System.out.println("-------->" + medii[0] + "   " + medii[1]); // de sters
                                secretariat.citesteMedii(medii);
                            }
                        } catch (IOException e) {
                            break; //nu vreau sa apara mesajul
                        }
                        nr++;
                    }
                } else if (intrari[0].equals("posteaza_mediile")) {
                    secretariat.afiseazaMedii(args[0]);
                }
            }

        } catch(IOException e){
            e.printStackTrace();
        }

//	System.out.println("code");
    }
}
