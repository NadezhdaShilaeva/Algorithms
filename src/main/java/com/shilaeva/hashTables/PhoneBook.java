package com.shilaeva.hashTables;

import java.util.Scanner;

public class PhoneBook {
    private void run() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        String[] phoneBook = new String[10000000];

        for (int i = 0; i < n; ++i) {
            String operation = scanner.next();
            if (operation.equals("add")) {
                int number = scanner.nextInt();
                String name = scanner.next();
                phoneBook[number] = name;
            } else if (operation.equals("del")) {
                int number = scanner.nextInt();
                phoneBook[number] = null;
            } else {
                int number = scanner.nextInt();
                if (phoneBook[number] == null) {
                    System.out.println("not found");
                } else {
                    System.out.println(phoneBook[number]);
                }
            }
        }
    }

    public static void main(String[] args) {
        new PhoneBook().run();
    }
}
