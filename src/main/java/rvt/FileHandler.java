package rvt;

import java.io.*;
import java.util.*;

public class FileHandler {

    private static final String FILE = "students.csv";

    public static void saveStudent_S(Student_S student) throws IOException {
        FileWriter fw = new FileWriter(FILE, true);
        fw.write(student.toCSV() + "\n");
        fw.close();
    }

    public static List<Student_S> readStudents() throws IOException {

        List<Student_S> list = new ArrayList<>();

        File file = new File(FILE);
        if (!file.exists()) return list;

        BufferedReader br = new BufferedReader(new FileReader(FILE));
        String line;

        while ((line = br.readLine()) != null) {
            list.add(Student_S.fromCSV(line));
        }

        br.close();
        return list;
    }

    public static void overwrite(List<Student_S> students) throws IOException {
        FileWriter fw = new FileWriter(FILE);

        for (Student_S s : students) {
            fw.write(s.toCSV() + "\n");
        }

        fw.close();
    }
}
