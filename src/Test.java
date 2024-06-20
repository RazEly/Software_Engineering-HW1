import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Test {
    public static String nameGenerator() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(5); // 5 characters

        for (int i = 0; i < 5; i++) {
            char randomChar = (char) ('a' + random.nextInt(26)); // ASCII 'a' to 'z'
            sb.append(randomChar);
        }
        return sb.toString();
    }
    public static String gradesGenerator() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        // Generate a random number of decimals (between 1 and 10)
        int count = random.nextInt(10) + 1;

        for (int i = 0; i < count; i++) {
            // Generate a random decimal number between 0 and 100
            double randomNumber = random.nextDouble() * 100;

            // Append the random number to the StringBuilder
            sb.append(String.format("%.2f", randomNumber)); // Adjust the format as needed
            sb.append(" ");
        }

        // Remove the last space if there is any
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        // Specify the file name and path, for example: "/Desktop/Software_Engineering/HW1/tester.txt
        String fileName = "";

        // Using FileWriter and BufferedWriter to write to the file
        try (FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            String[] names = new String[100];
            bufferedWriter.write("1\n");


            // TEST 1: try adding students with bad information
            bufferedWriter.write("1\nMoshe\n5740394752039\n"); // not a valid grades string
            bufferedWriter.write("1\nMoshe\n-4 9 1 19 45 93\n"); // negative grades
            bufferedWriter.write("1\nMoshe\n10, 20, 30, 50, 85\n"); // wrong format
            bufferedWriter.write("1\nMoshe\n1b 76 ty 15 01001\n"); // letters are invalid
            bufferedWriter.write("6\n"); // unacceptable input
            bufferedWriter.write("yy\n"); // unacceptable input

            // TEST 2: add 50 random students
            for (int i = 0; i < 50; i++) {
                bufferedWriter.write("1\n");
                names[i] = nameGenerator();
                bufferedWriter.write(names[i]+"\n");
                bufferedWriter.write(gradesGenerator()+"\n");
            }
            bufferedWriter.write("2\n");

            // TEST 3: print the names and grades of 10 random students
            for (int i = 0; i < 10; i++) {
                bufferedWriter.write("3\n");
                bufferedWriter.write(names[(int)(Math.random()*50)]+"\n");
            }
            bufferedWriter.write("4\n");

            // TEST 4: add 50 more students
            for (int i = 50; i < 100; i++) {
                bufferedWriter.write("1\n");
                names[i] = nameGenerator();
                bufferedWriter.write(names[i]+"\n");
                bufferedWriter.write(gradesGenerator()+"\n");
            }
            bufferedWriter.write("4\n");

            //TEST5: try adding a student when there are already 100 students
            bufferedWriter.write("1\nRivka\n10 20 30");

            bufferedWriter.write("5\n");
        } catch (IOException e) {
            System.err.println("An IOException occurred: " + e.getMessage());
        }
    }
}