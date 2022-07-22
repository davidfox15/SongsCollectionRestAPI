package DavidLisitsyn;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        System.out.print("Input author url: ");
        String url = in.nextLine();
        Parse.parseAuthor(url);
    }
}
