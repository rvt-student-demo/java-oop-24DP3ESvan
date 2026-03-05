package rvt;

public class Teacher_1 extends Person {

    private String salary;

    public Teacher_1(String name, String adress, int salary) {
        super(name, adress);
    }

    public String toString() {
        return super.toString() + "\n" + "Salary: " + this.salary + " euro/month";
    }

    public static void main(String[] args) {
        Teacher_1 ada = new Teacher_1("Ada Lovelace", "24 Maddox St. London W1S 2QN", 1200);
        Teacher_1 esko = new Teacher_1("Esko Ukkonen", "Mannerheimintie 15 00100 Helsinki", 5400);
        System.out.println(ada);
        System.out.println(esko);

        Student ollie = new Student("Ollie", "6381 Hollywood Blvd. Los Angeles 90028");

        int i = 0;
        while (i < 25) {
            ollie.Study();
            i = i + 1;
        }
        System.out.println(ollie);
    }
}
