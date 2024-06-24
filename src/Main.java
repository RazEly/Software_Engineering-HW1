import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static Scanner scanner; // Note: Do not change this line.

    /**
     * initiallize arrays to store student information
     */
    static String[] students = new String[100];
    static double[][] grades = new double[100][];
    static double[] gradesMean = new double[100];
    static int studentCount = 0;

    /**
     * adds a student if the student data is correct and there are less than 100 students in the database
     * @return true if a student was added
     */
    static boolean addStudent() {
        System.out.println("Enter student name:");
        String name = scanner.nextLine();
        System.out.println("Enter grades:");
        String gradesString = scanner.nextLine();
        if (studentCount >= 100) {
            System.out.println("Student limit reached.");
            return false;
        }
        String[] gradesArray = gradesString.split(" ");
        double[] gradesArrayDouble = new double[gradesArray.length];
        for (int i = 0; i < gradesArray.length; i++) {
            try {
                gradesArrayDouble[i] = Double.parseDouble(gradesArray[i]);
                if(gradesArrayDouble[i] < 0 || gradesArrayDouble[i] > 100) {
                    System.out.println("Invalid grades.");
                    return false;
                }
            } catch(Exception e) {
                System.out.println("Invalid grades.");
                return false;
            }
        }
        for (int i = 0; i < studentCount; i++) {
            if(name.equals(students[i])) {
                grades[i] = gradesArrayDouble.clone();
                gradesMean[i] = calcMean(grades[i]);
                System.out.println("Student " + name + " added successfully!");
                return true;
            }
        }
        // check if the clone method is the best option to pass by value
        students[studentCount] = name;
        grades[studentCount] = gradesArrayDouble.clone();
        gradesMean[studentCount] = calcMean(grades[studentCount]);
        studentCount++;
        // change to %reference
        System.out.println("Student " + name + " added successfully!");
        return true;
    }

    /**
     * helper function to calculate mean
     * @param grades an array of grades from a single student
     * @return the mean of the students grades, with 2 decimal point percision
     */
    static double calcMean(double[] grades) {
        double sum = 0;
        for (double grade : grades) {
            sum += grade;
        }
        return Math.round((sum/grades.length) * 100.00) / 100.00;
    }
    /**
     * prints all of the students in the database
     */
    static void displayStudents() {
        if(studentCount == 0) {
            System.out.println("No student records available.");
        }
        else {
            for (int i = 0; i < studentCount; i++) {
                //check if the commas are added manually
                System.out.println("Name: " + students[i] + ", Grades: " + Arrays.toString(grades[i]).replace("[", "").replace("]",""));
            }
        }
    }
    /**
     * asks for a name as an input and shows the average grade of the named student
     * @return true if a student was found with the input name
     */
    static boolean displayMean() {
        System.out.println("Enter student name:");
        String name = scanner.nextLine();
        // check that it's not an empty string?
        for (int i = 0; i < studentCount ; i++) {
            if(students[i].equals(name)) {
                System.out.println("Average grade for " + name + ": " + gradesMean[i]);
                return true;
            }
        }
        System.out.println("No student found with name" + name + ".");
        return false;
    }
    /**
     * prints the student with the best grade average in the database
     * @return true if a top student exists
     */
    static boolean topStudent() {
        if(studentCount == 0) {
            System.out.println("No student records available.");
            return false;
        }
        double topMean = 0;
        int topID = 0;
        for (int i = 0; i < studentCount; i++) {
            if(gradesMean[i] > topMean) {
                topMean = gradesMean[i];
                topID = i;
            }
        }
        System.out.println("Top performing student: " + students[topID] + " with an average grade of " + topMean +". ");
        return true;
    }



    /**
     * a CLI for the dean to manage and pull information from the database.
     * accepts one out of 5 possible actions, exits the program and deletes the database when 5 is entered
     * 
     */
    public static void manageGrades() {
        System.out.println("Welcome to the student management system!"); //DEFAULT TEXT
        System.out.println("1. Add student\n\n2. Display all students\n\n3. Calculate a student's average grade\n\n4.Find the top performing student\n\n5.Exit");
        System.out.println("Please enter your choice:");
        char deanInput;
        do {
            deanInput = scanner.nextLine().charAt(0);
            switch (deanInput) {
                case '1':
                    addStudent();
                    break;
                case '2':
                    displayStudents();
                    break;
                case '3':
                    displayMean();
                    break;
                case '4':
                    topStudent();
                    break;
                case '5':
                    students = new String[100];
                    grades = new double[100][];
                    gradesMean = new double[100];
                    studentCount = 0;
                    System.out.println("Exiting the program. Goodbye!");

                    break;
                default:
                    System.out.println(deanInput);
                    System.out.println("Invalid choice. Please try again");
                    break;
            }

        } while(deanInput != '5');
    }
    public static void main(String[] args) throws IOException {
        String path = args[0];
        scanner = new Scanner(new File(path));
        int numberOfTests = scanner.nextInt();
        scanner.nextLine();

        for (int i = 1; i <= numberOfTests; i++) {
            System.out.println("Test number " + i + " starts.");
            try {
                manageGrades();
            } catch(Exception e){
                System.out.println("Exception " + e);
            }
            System.out.println("Test number " + i + " ended.");
            System.out.println("-----------------------------------------------");
        }
        System.out.println("All tests have ended.");
    }
}
