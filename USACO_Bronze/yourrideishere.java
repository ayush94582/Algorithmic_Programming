/*
ID: ayush941
LANG: JAVA
TASK: ride
 */
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

class ride {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("ride.in"));
        PrintWriter pw = new PrintWriter(new File("ride.out"));
        if (toNumber(sc.nextLine()) % 47 == toNumber(sc.nextLine()) % 47) {
            pw.println("GO");
        } else {
            pw.println("STAY");
        }
        pw.close();
    }

    public static int toNumber(String name) {
        int product = 1;
        for (int i = 0; i < name.length(); i++) {
            int charVal = (name.charAt(i) - 'A') + 1;
            product *= charVal;
        }
        return product;
    }
}