package com.shilaeva.hashTables;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class ChainHashing {
    private static final int p = 1000000007;
    private static final int x = 263;

    private int getHash(String string, int m) {
        int xPow = 1;
        int hash = 0;
        for (int symbol : string.chars().toArray()) {
            hash = (int) ((hash + (long) symbol * xPow % p) % p);
            xPow = (int) ((long) xPow * x % p);
        }

        return hash % m;
    }

    private void run() {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        int n = scanner.nextInt();

        List<LinkedList<String>> hashTable = new ArrayList<>();
        for (int i = 0; i < m; ++i) {
            hashTable.add(new LinkedList<>());
        }

        for (int i = 0; i < n; ++i) {
            String operation = scanner.next();
            if (operation.equals("add")) {
                String string = scanner.next();
                int hash = getHash(string, m);
                if (!hashTable.get(hash).contains(string)) {
                    hashTable.get(hash).addFirst(string);
                }
            } else if (operation.equals("del")) {
                String string = scanner.next();
                int hash = getHash(string, m);
                hashTable.get(hash).remove(string);
            } else if (operation.equals("find")) {
                String string = scanner.next();
                int hash = getHash(string, m);
                System.out.println(hashTable.get(hash).contains(string) ? "yes" : "no");
            } else {
                int index = scanner.nextInt();
                hashTable.get(index).forEach(s -> System.out.print(s + " "));
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        new ChainHashing().run();
    }
}
