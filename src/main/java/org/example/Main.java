package org.example;

import java.io.*;

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
                    } catch (StudentDuplicat e) {
                        int first = 0;
                        try (FileWriter fw = new FileWriter("src/main/resources/" + args[0] + "/" + args[0] + ".out", true);
                             BufferedWriter bw = new BufferedWriter(fw);
                             PrintWriter out = new PrintWriter(bw)) {
                            if (first == 0) {
                                out.println("***");
                                first = 1;
                            }
                            out.println(e.getMessage());
                        }catch (IOException b) {
                            b.printStackTrace();
                        }
                    }

                } else if (intrari[0].equals("citeste_mediile")) {
                    int nr = 1;
                    while (true) {
                        // deschidem fisierele cu note pana cand nu mai exista fisier pe care sa-l deschida
                        try (BufferedReader br2 = new BufferedReader(new FileReader("src/main/resources/" + args[0] + "/" + "note_" + nr + ".txt"))) {
                            String linetwo;
                            while ((linetwo = br2.readLine()) != null) {
                                String[] medii = linetwo.split(splitBy);
                                secretariat.citesteMedii(medii);
                            }
                        } catch (IOException e) {
                            break; //nu vreau sa apara mesajul
                        }
                        nr++;
                    }
                } else if (intrari[0].equals("posteaza_mediile")) {
                    secretariat.afiseazaMedii(args[0]);
                }else if (intrari[0].equals("contestatie")) {
                    secretariat.contestatii(intrari);
                }else if (intrari[0].equals("adauga_curs")) {
                    if(intrari[1].equals("licenta")) {
                        secretariat.adaugaCurs(intrari[2], Integer.parseInt(intrari[3]), StudentLicenta.class);
                    } else {
                        secretariat.adaugaCurs(intrari[2], Integer.parseInt(intrari[3]), StudentMaster.class);
                    }
                } else if (intrari[0].equals("adauga_preferinte")) {
                    secretariat.adaugaPreferinte(intrari);
                } else if (intrari[0].equals("repartizeaza")) {
                    secretariat.repartizeaza();
                    secretariat.afiseazaStudCurs();
                } else if (intrari[0].equals("posteaza_curs")) {
                    secretariat.posteazaCurs(intrari[1], args[0]);
                } else if (intrari[0].equals("posteaza_student")) {
                    secretariat.posteazaStudent(intrari[1], args[0]);
                }
            }

        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
