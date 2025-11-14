/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package lexical.analyzer;

import java.util.*;

public class lexicalanalyzer{
    private static final Set<String> KEYWORDS = new HashSet<>(Arrays.asList("if", "then", "else"));
    private static final Set<String> OPERATORS = new HashSet<>(Arrays.asList("+", "-", "*", "/", "=", ">", "<"));

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter your code (type 'end' to stop):");

        int line = 1;
        while (true) {
            System.out.print("Line " + line + ": ");
            String text = input.nextLine();
            if (text.equalsIgnoreCase("end")) break;
            analyzeLine(text, line);
            line++;
        }
    }

    private static void analyzeLine(String text, int lineNumber) {
        StringTokenizer tokenizer = new StringTokenizer(text, " +-*/=()><;", true);

        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken().trim();
            if (token.isEmpty()) continue;

            if (KEYWORDS.contains(token)) {
                printToken(lineNumber, "KEYWORD", token);
            } else if (OPERATORS.contains(token)) {
                printToken(lineNumber, "OPERATOR", token);
            } else if (isInteger(token)) {
                printToken(lineNumber, "INTEGER", token);
            } else if (isIdentifier(token)) {
                printToken(lineNumber, "IDENTIFIER", token);
            } else {
                System.out.println("[Line " + lineNumber + "] ERROR: Invalid token -> " + token);
            }
        }
    }

    private static boolean isIdentifier(String s) {
        if (!Character.isLetter(s.charAt(0))) return false;
        for (char c : s.toCharArray()) {
            if (!Character.isLetterOrDigit(c) && c != '_') return false;
        }
        return true;
    }

    private static boolean isInteger(String s) {
        for (char c : s.toCharArray()) {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }

    private static void printToken(int line, String type, String lexeme) {
        System.out.printf("[Line %d] %-12s -> %s%n", line, type, lexeme);
    }
}

