package rvt;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;


public class StudentService {

    private Scanner scanner = new Scanner(System.in);

    public void registerStudent() throws IOException {

        System.out.print("Name: ");
        String name = scanner.nextLine();

        if (validateName(name)) {
            System.out.println("Invalid name!");
            return;
        }

        System.out.print("Surname: ");
        String surname = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        if (validateEmail(email)) {
            System.out.println("Invalid email!");
            return;
        }

        System.out.print("Personal code: ");
        String code = scanner.nextLine();

        if (validatePersonalCode(code)) {
            System.out.println("Invalid personal code!");
            return;
        }

        String date = LocalDateTime.now().toString();

        Student_S s = new Student_S(name, surname, email, code, date);

        FileHandler.saveStudent_S(s);

        System.out.println("Student registered!");
    }

    private boolean validatePersonalCode(String code) {
        throw new UnsupportedOperationException("Unimplemented method 'validatePersonalCode'");
    }

    private boolean validateEmail(String email) {
        throw new UnsupportedOperationException("Unimplemented method 'validateEmail'");
    }

    private boolean validateName(String name) {
        throw new UnsupportedOperationException("Unimplemented method 'validateName'");
    }

    public void showStudents() throws IOException {

        List<Student_S> list = FileHandler.readStudents();

        System.out.printf("%-12s %-12s %-25s %-15s %-20s\n",
                "Name","Surname","Email","Code","Date");

        for (Student_S s : list) {
            System.out.println(s);
        }
    }

    public void removeStudent() throws IOException {

        System.out.print("Enter personal code: ");
        String code = scanner.nextLine();

        List<Student_S> list = FileHandler.readStudents();

        list.removeIf(s -> s.getPersonalCode().equals(code));

        FileHandler.overwrite(list);

        System.out.println("Removed!");
    }
}