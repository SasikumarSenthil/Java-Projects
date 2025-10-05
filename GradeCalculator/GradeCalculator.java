import java.util.Scanner;

class Student {
    String name;
    int numSubjects;
    int[] marks;

    // Method to input student details
    void inputDetails() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Student Name: ");
        name = sc.nextLine();

        System.out.print("Enter number of subjects: ");
        numSubjects = sc.nextInt();

        marks = new int[numSubjects];

        for (int i = 0; i < numSubjects; i++) {
            System.out.print("Enter marks for subject " + (i + 1) + ": ");
            marks[i] = sc.nextInt();
        }
        sc.close();
    }

    // Method to calculate total marks
    int getTotal() {
        int total = 0;
        for (int mark : marks) {
            total += mark;
        }
        return total;
    }

    // Method to calculate average
    double getAverage() {
        return (double) getTotal() / numSubjects;
    }

    // Method to find grade
    char getGrade() {
        double avg = getAverage();
        if (avg >= 90) return 'A';
        else if (avg >= 75) return 'B';
        else if (avg >= 60) return 'C';
        else if (avg >= 50) return 'D';
        else return 'F';
    }

    // Method to display result
    void displayResult() {
        System.out.println("\n--- Student Result ---");
        System.out.println("Name: " + name);
        System.out.println("Total Marks: " + getTotal());
        System.out.println("Average: " + getAverage());
        System.out.println("Grade: " + getGrade());
    }
}

public class GradeCalculator {
    public static void main(String[] args) {
        Student s = new Student();
        s.inputDetails();
        s.displayResult();
    }
}
