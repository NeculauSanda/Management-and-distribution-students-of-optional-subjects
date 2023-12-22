package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Secretariat {

    private final ArrayList<Student> studentiFacultate = new ArrayList<>();
    private final ArrayList<Curs<?>> cursuriOptionale = new ArrayList<>();

    public void adaugaStudent (String tipStudiu, String numeStudent) {
        Student student = null;
        //verificam numele studentului
        if(verificaStudent(numeStudent)) {
            throw new StudentDuplicat("Student duplicat: " + numeStudent);
        }

        // generam studentul
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

    // asaugam curs
    public <E extends Student> void adaugaCurs(String nume, int capacitate, Class<E> tipCurs) {
        Curs<E> curs = new Curs<>(nume, capacitate);
        cursuriOptionale.add(curs);
    }

    public String[] extragereCursuri (String[] date) {
        int index = 2;
        String[] datenew = new String[date.length - 2];
        while (index >= 2 && index < date.length) {
            datenew[index-2] = date[index];
            index++;
        }
        return datenew;
    }

    //se adauga preferintele de curs optional in listele studentilor
    public  void adaugaPreferinte(String[] date) {
        //extragem preferintele studentului
        String[] cursuri = extragereCursuri(date);
        for (Student studenti : studentiFacultate) {
            // daca s-a gasit studentul in baza de date
            if (studenti.getNume().equals(date[1])) {
                // pentru fiecare curs preferat se cauta existenta lui in lista cu cursuri si se adauga la preferintele studentului
                for (String numeCurs : cursuri) {
                    for (Curs<?> curs : cursuriOptionale) {
                        if (curs.getNumeCurs().equals(numeCurs)) {
                            studenti.adaugaPreferintaCurs(curs);
                            break;
                        }
                    }
                }
            }
        }
    }

    public void repartizeaza() {
        //sortam descrescator dupa medie
        sortareDescrescatoare();
        for(Student student : studentiFacultate) {
            ArrayList<Curs> cursuriStudent  = student.getPreferinte();
            for(Curs cursuristud : cursuriStudent) {
                if(cursuristud.getCapacitateMaxima() > cursuristud.getStudentiParticipanti().size()) {
                    cursuristud.adaugaStudent(student, 1);
                    break;
                } else {
                    int index = cursuristud.getStudentiParticipanti().size();
                    Student lastStudent = (Student) cursuristud.getStudentiParticipanti().get(index - 1); //ultimul student de optionalul acela
                    if(student.getMedie() == lastStudent.getMedie()) {
                        cursuristud.adaugaStudent(student, 0);
                        break;
                    }
                }
            }
        }
    }

    public void posteazaCurs(String cursNume, String args) {
        int first = 0;
        for(Curs curs : cursuriOptionale) {
            if(curs.getNumeCurs().equals(cursNume)) {

                try (FileWriter fw = new FileWriter("src/main/resources/" + args + "/" + args + ".out", true);
                     BufferedWriter bw = new BufferedWriter(fw);
                     PrintWriter out = new PrintWriter(bw)) {
                    if (first == 0) {
                        out.println("***");
                        first++;
                    }
                    out.print(curs.getNumeCurs() + " (" + curs.getCapacitateMaxima() + ")\n");
                    curs.sortareAlfabetica();
                    ArrayList<Student> date = curs.getStudentiParticipanti();
                    for (Student stud : date) {
                        out.println(stud.getNume() + " - " + stud.getMedie());
                    }
                }catch (IOException e) {
                    e.getMessage();
                }
            }
        }
    }

    public void posteazaStudent(String studentNume, String args) {
        int first = 0;
        for(Student student : studentiFacultate) {
            if(student.getNume().equals(studentNume)) {

                try (FileWriter fw = new FileWriter("src/main/resources/" + args + "/" + args + ".out", true);
                     BufferedWriter bw = new BufferedWriter(fw);
                     PrintWriter out = new PrintWriter(bw)) {
                    if (first == 0) {
                        out.println("***");
                        first++;
                    }

                    String tipstudent = null;
                    if(student instanceof StudentLicenta) {
                        tipstudent = "Licenta";
                    } else if(student instanceof StudentMaster) {
                        tipstudent = "Master";
                    }

                    out.print("Student " + tipstudent + ": " + student.getNume() + " - " + student.getMedie() + " - ");

                    for (Curs curs : cursuriOptionale) {
                        ArrayList<Student> students = curs.getStudentiParticipanti();
                        for(Student stud : students) {
                            if(stud.getNume().equals(studentNume)) {
                                out.println(curs.getNumeCurs());
                            }
                        }
                    }
                }catch (IOException e) {
                    e.getMessage();
                }
            }
        }
    }

    public void sortareDescrescatoare() {
        Student intermediar;
        for (int i = 0; i < studentiFacultate.size(); i++) {
            for (int j = i + 1; j < studentiFacultate.size(); j++) {
                // comparam mediile si facem rocada intre studenti
                if (studentiFacultate.get(i).getMedie() < studentiFacultate.get(j).getMedie()) {
                    intermediar = studentiFacultate.get(j);
                    studentiFacultate.set(j, studentiFacultate.get(i));
                    studentiFacultate.set(i, intermediar);
                }
            }
        }
    }

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
        //sortare
        sortareAfisare();
        int first = 0;
        for (Student student : studentiFacultate) {
            boolean tip = student instanceof StudentLicenta;
            boolean tip2 = student instanceof StudentMaster;
             try (FileWriter fw = new FileWriter("src/main/resources/" + args + "/" + args + ".out", true);
                 BufferedWriter bw = new BufferedWriter(fw);
                 PrintWriter out = new PrintWriter(bw)) {

                if(first == 0) {
                    out.println("***");
                    first++;
                }
                out.println(student.getNume() + " - " + student.getMedie());
            } catch (IOException e) {
                e.getMessage();
            }
        }
    }

    public void citesteMedii(String[] date) {
        for(Student student : studentiFacultate) {
            if(student.getNume().equals(date[0])) {
                double medie = Double.parseDouble(date[1]);
                student.setMedie(medie);
            }
        }
    }

    public void contestatii(String[] date) {
        for (Student student : studentiFacultate) {
            if(student.getNume().equals(date[1])) {
                student.setMedie(Double.parseDouble(date[2]));
            }
        }
    }
}

class StudentDuplicat extends RuntimeException {
    public StudentDuplicat(String message) {
        super(message);
    }
}
