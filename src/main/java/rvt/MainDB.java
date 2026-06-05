package rvt;

import java.util.Scanner;
public class MainDB { 

    public static void main(String[] args) throws Exception {

        try (Scanner scanner = new Scanner(System.in)) {
            DatabaseConection dbConnection = new DatabaseConection(scanner);

            while (true) {

                System.out.println("\n1. Pievienot kategoriju");
                System.out.println("2. Pievienot produktu");
                System.out.println("3. Paradit visas kategorijas");
                System.out.println("4. Paradit visus produktus");
                System.out.println("5. Meklet produktus pec kategorijas");
                System.out.println("0. Iziet");
                System.out.println("\nUzraksta numuru ka savu izvele: ");
                String choice = scanner.nextLine();
                System.out.println("\nJusu izvele: " + choice);

                switch (choice) {

                    case "1":
                        dbConnection.addCategory();
                        continue;

                    case "2":
                        dbConnection.addProduct();
                        continue;

                    case "3":
                        dbConnection.showCategory();
                        continue;

                    case "4":
                        dbConnection.showProduct(); 
                        continue;

                    case "5":
                        dbConnection.searchProductByCategory();
                        continue;
                    
                    case "0":
                        return;

                    default:
                        System.out.println("Invalid option");
                }
            }
        }
    }
}