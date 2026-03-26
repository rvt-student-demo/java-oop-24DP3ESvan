package rvt;

public class Student_S {

    private String name;
    private String surname; 
    private String email;
    private String personalCode;
    private String registrationDate;

    public Student_S(String name, String surname, String email, String personalCode, String registrationDate) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.personalCode = personalCode;
        this.registrationDate = registrationDate;
    }

    public String toCSV() {
        return name + "," + surname + "," + email + "," + personalCode + "," + registrationDate;
    }

    public static Student_S fromCSV(String line) {
        String[] parts = line.split(",");
        return new Student_S(parts[0], parts[1], parts[2], parts[3], parts[4]);
    }

    public String getPersonalCode() {
        return personalCode;
    }

    public String toString() {
        return String.format("%-12s %-12s %-25s %-15s %-20s",
                name, surname, email, personalCode, registrationDate);
    }
}
