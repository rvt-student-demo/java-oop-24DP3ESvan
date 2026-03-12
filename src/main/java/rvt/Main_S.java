package rvt;

import java.util.Scanner;
public class Main_S {

    public static void main(String[] args) throws Exception {

        try (Scanner scanner = new Scanner(System.in)) {
            StudentService service = new StudentService();

            while (true) {

                System.out.println("\n1. Register");
                System.out.println("2. Show");
                System.out.println("3. Remove");
                System.out.println("4. Exit");

                String choice = scanner.nextLine();

                switch (choice) {

                    case "1":
                        service.registerStudent();
                        break;

                    case "2":
                        service.showStudents();
                        break;

                    case "3":
                        service.removeStudent();
                        break;

                    case "4":
                        return;

                    default:
                        System.out.println("Invalid option");
                }
            }
        }
    }
}
