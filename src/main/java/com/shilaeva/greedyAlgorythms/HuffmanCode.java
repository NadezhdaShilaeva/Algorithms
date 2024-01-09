package com.shilaeva.greedyAlgorythms;

import java.util.*;
import java.util.stream.Collectors;

public class HuffmanCode {
    private static class Node implements Comparable<Node> {
        public final int frequency;
        public String code;

        public Node(int frequency) {
            this.frequency = frequency;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(frequency, o.frequency);
        }

        public void buildCode(String code) {
            this.code = code;
        }
    }
    private static class InternalNode extends Node {
        public Node leftChild;
        public Node rightChild;

        public InternalNode(Node leftChild, Node rightChild) {
            super(leftChild.frequency + rightChild.frequency);
            this.leftChild = leftChild;
            this.rightChild = rightChild;
        }

        @Override
        public void buildCode(String code) {
            super.buildCode(code);
            leftChild.buildCode(code + "0");
            rightChild.buildCode(code + "1");
        }
    }

    private static class LeafNode extends Node {
        public char letter;
        public LeafNode(char letter, int frequency) {
            super(frequency);
            this.letter = letter;
        }

        @Override
        public void buildCode(String code) {
            super.buildCode(code);
            System.out.println(letter + ": " + code);
        }
    }

    private void codingText(String text, Map<Character, Node> symbolsCode) {
        text.chars().mapToObj(c -> (char) c).forEach(symbol -> System.out.print(symbolsCode.get(symbol).code));
    }

    private Map<Character, Node> getSymbolsCode(Map<Character, Long> symbolsFrequency) {
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        Map<Character, Node> symbolsCode = new HashMap<>();

        symbolsFrequency.forEach((symbol, frequency) -> {
            Node newLeaf = new LeafNode(symbol, frequency.intValue());
            priorityQueue.add(newLeaf);
            symbolsCode.put(symbol, newLeaf);
        });

        int sum = 0;
        while (priorityQueue.size() > 1) {
            Node leftNode = priorityQueue.poll();
            Node rightNode = priorityQueue.poll();
            Node node = new InternalNode(leftNode, rightNode);
            sum += node.frequency;
            priorityQueue.add(node);
        }

        Node rootNode = priorityQueue.poll();
        if (symbolsFrequency.size() == 1) {
            sum = rootNode.frequency;
        }
        System.out.println(symbolsFrequency.size() + " " + sum);

        if (symbolsFrequency.size() == 1) {
            rootNode.buildCode("0");
        } else {
            rootNode.buildCode("");
        }

        return symbolsCode;
    }
    private Map<Character, Long> getSymbolsFrequency(String text) {
        return text.chars().mapToObj(c -> (char) c).collect(Collectors.groupingBy(c -> c, Collectors.counting()));
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();

        codingText(text, getSymbolsCode(getSymbolsFrequency(text)));
    }

    public static void main(String[] args) {
        new HuffmanCode().run();
    }
}
