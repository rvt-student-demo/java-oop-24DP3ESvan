package rvt;

public class Student extends Person {

    private int credits;

    public Student(String name, String adress) {
        super(name, adress);
    }

    public int Study() {
        return credits += 1;
    }

    public String toString() {
        return super.toString() + "\n" + "Study credits: " + this.credits;
    }

    public static void main(String[] args) {
        Student ollie = new Student("Ollie", "6381 Hollywood Blvd. Los Angeles 90028");
        System.out.println(ollie);
        ollie.Study();
        System.out.println(ollie);
    }
}
