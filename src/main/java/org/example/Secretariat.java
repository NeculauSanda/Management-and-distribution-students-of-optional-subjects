package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Secretariat {

    private ArrayList<Student> studentiFacultate = new ArrayList<>();
    private ArrayList<Curs> cursuriOptionale = new ArrayList<>();

    public void adaugaStudent (String tipStudiu, String numeStudent) {
        Student student = null;
        //verificam numele studentului
        if(verificaStudent(numeStudent)) {
            throw new StudentDuplicat("Student duplicat: " + numeStudent);
        }

        if(tipStudiu.equals("master")) {
            student = new StudentMaster(numeStudent);
        } else if(tipStudiu.equals("licenta")) {
            student = new StudentLicenta(numeStudent);
        }
        //adaugam studentul
        studentiFacultate.add(student);
    }

    public boolean verificaStudent(String numeStudent) {
        for(Student stud : studentiFacultate) {
            if(stud.getNume().equalsIgnoreCase(numeStudent)) {
                return true;
            }
        }
        return false;
    }

    /// de sters
//    public void afiseazaStudent() {
//        for (Student student : studentiFacultate) {
//            boolean tip = student instanceof StudentLicenta;
//            boolean tip2 = student instanceof StudentMaster;
//            System.out.println(student.getNume() + " licenta: " + tip + ", master: " + tip2 + "  ____  " + student.getMedie());
//        }
//    }


    public void sortareAfisare(){
        Student intermediar;
        for (int i = 0; i < studentiFacultate.size(); i++) {
            for(int j = i + 1; j < studentiFacultate.size(); j++) {
                // comparam mediile si facem rocada intre studenti
                if(studentiFacultate.get(i).getMedie() < studentiFacultate.get(j).getMedie()) {
                    intermediar = studentiFacultate.get(j);
                    studentiFacultate.set(j, studentiFacultate.get(i));
                    studentiFacultate.set(i, intermediar);
                } else if(studentiFacultate.get(i).getMedie() == studentiFacultate.get(j).getMedie()) {
                    // daca mediile sunt egale sortam dupa nume
                    if (studentiFacultate.get(i).getNume().compareTo(studentiFacultate.get(j).getNume()) > 0) {
                        intermediar = studentiFacultate.get(j);
                        studentiFacultate.set(j, studentiFacultate.get(i));
                        studentiFacultate.set(i, intermediar);
                    }
                }
            }

        }
    }
    public void afiseazaMedii(String args) {
//        Collections.sort(studentiFacultate, Comparator.comparing(Student::getMedie).reversed().thenComparing(Student::getNume));
        //sortare
        sortareAfisare();
        int first = 0;
        for (Student student : studentiFacultate) {
//            boolean tip = student instanceof StudentLicenta;
//            boolean tip2 = student instanceof StudentMaster;
//            System.out.println(student.getNume() + " licenta: " + tip + ", master: " + tip2 + "  ____  " + student.getMedie());
            try (FileWriter fw = new FileWriter("src/main/resources/" + args + "/" + args + ".out", true);
                 BufferedWriter bw = new BufferedWriter(fw);
                 PrintWriter out = new PrintWriter(bw)) {

                if(first == 0) {
                    out.println("***");
                    first++;
                }
                out.println(student.getNume() + " - " + student.getMedie());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void citesteMedii(String[] date) {
        for(Student student : studentiFacultate) {
            if(student.getNume().equals(date[0])) {
                double medie = Double.parseDouble(date[1]);
                student.setMedie(medie);
                return;
            }
        }
    }
}

class StudentDuplicat extends RuntimeException {
    public StudentDuplicat(String message) {
        super(message);
    }
}
