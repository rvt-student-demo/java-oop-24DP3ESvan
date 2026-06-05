error id: file:///C:/Users/A240393ES/Documents/java-oop-24DP3ESvan/src/main/java/rvt/MainDB.java:_empty_/DatabaseConnection#
file:///C:/Users/A240393ES/Documents/java-oop-24DP3ESvan/src/main/java/rvt/MainDB.java
empty definition using pc, found symbol in pc: _empty_/DatabaseConnection#
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 308
uri: file:///C:/Users/A240393ES/Documents/java-oop-24DP3ESvan/src/main/java/rvt/MainDB.java
text:
```scala
package rvt;

import java.util.Scanner;
public class MainDB { 

    public static void main(String[] args) throws Exception {

        try (Scanner scanner = new Scanner(System.in)) {
            Product product = new Product();
            Category category = new Category();
            DatabaseC@@onnection dbConnection = new DatabaseConnection();

            while (true) {

                System.out.println("\n1. Pievienot kategoriju");
                System.out.println("2. Pievienot produktu");
                System.out.println("3. Paradit visas kategorijas");
                System.out.println("4. Paradit visus produktus");
                System.out.println("5. Meklet produktus pec kategorijas");
                System.out.println("0. Iziet");

                String choice = scanner.nextLine();

                switch (choice) {

                    case "1":
                        ...
                        break;

                    case "2":
                        ...
                        break;

                    case "3":
                        ...
                        break;

                    case "4":
                        ...
                        return;

                    case "5":
                        ...
                        return;
                    
                    case "0":
                        break;

                    default:
                        System.out.println("Invalid option");
                }
            }
        }
    }
}
```


#### Short summary: 

empty definition using pc, found symbol in pc: _empty_/DatabaseConnection#