package example;

import java.util.Scanner;

public class Splitter {

    public static void main(String[] args){
        System.out.println("Enter a sentence specified by spaces only: ");
        // Add your code
        Scanner sc = new Scanner(System.in);
        while(sc.hasNextLine()) {
        	String str = sc.next();
        	// str.split(" ");
        	System.out.println(str);
        }
        sc.close();
    }
}
