package org.tyrell.Classes;

import java.util.*;

public class EncryptionClass {
    //Access Modifiers
    private Scanner scanner;
    private Random random;
    private ArrayList<Character> list;
    private ArrayList<Character> shuffledList;
    private char character;
    private String line;
    private char[] letters;
//    private char[] secretLetters;

    //Constructors
    public EncryptionClass() {
        //Initialise the variables of this class
        scanner =  new Scanner(System.in);
        random = new Random();
        list = new ArrayList<>();
        shuffledList = new ArrayList<>();
        character = ' ';

        newKey();
        askQuestion();
    }

    //Methods

    private void askQuestion() {
        //Consistently prompts user to provide a valid input
        while (true) {
            System.out.println("*************************************************");
            System.out.println("What do you want to do? ");
            System.out.println("(N)ewKey, (G)etKey, (E)ncrypt, (D)ecrypt, (Q)uit");
            char response = Character.toUpperCase(scanner.nextLine().charAt(0)); //Takes the first letter of the response and makes it an uppercase

            switch (response) {
                case 'N' -> newKey();
                case 'G' -> getKey();
                case 'E' -> encrypt();
                case 'D' -> decrypt();
                case 'Q' -> quit();
                default -> System.out.println("Not a valid option!!!");
            }
        }
    }

    private void newKey() {
        //Resets the values when user wants to create a new key
        character = ' ';
        list.clear();
        shuffledList.clear();

        for (int i=32; i<127; i++) {
            list.add(Character.valueOf(character));
            character++;
        }

        //copy the list to the shuffledList
        shuffledList = new ArrayList<>(list);
        //Shuffle the shuffled list
        Collections.shuffle(shuffledList); //random
        System.out.println("A new key has been generated!");
    }

    private void getKey() {
        System.out.println("Key: ");
        for(Character x: list) {
            System.out.print(x);
        }
        System.out.println();
        for(Character sh: shuffledList) {
            System.out.print(sh);
        }
        System.out.println();
    }

    //Encrypt the text into cypher text
    private void encrypt() {
        System.out.println("Enter message to encrypt: ");
        String msg = scanner.nextLine();

        letters = msg.toCharArray(); //Splits all the characters into a Character Array

        //Check to see if there is a matching letter in the list to the current letter in the array
        //If there is a match it returns the letter with the same index as the list from the shuffledList
        for(int k=0; k<letters.length; k++) {
            for (int l=0; l<list.size(); l++) {
                if (letters[k] == list.get(l)) {
                    letters[k] =shuffledList.get(l);
                    break;
                }
            }
        }
        System.out.println("Encrypted message: ");
        for(char x : letters)
            System.out.print(x);

        System.out.println();
    }

    //This will take cypher text and convert it to plain text
    private void decrypt() {
        System.out.println("Enter encrypted message: ");
        String msg = scanner.nextLine();

        letters = msg.toCharArray(); //Splits all the characters into a Character Array

        //Check to see if there is a matching letter in the shuffledList to the current letter in the array
        //If there is a match it returns the letter with the same index as the shuffledList from the list
        for(int k=0; k<letters.length; k++) {
            for (int l=0; l<shuffledList.size(); l++) {
                if (letters[k] == shuffledList.get(l)) {
                    letters[k] =list.get(l);
                    break;
                }
            }
        }
        System.out.println("Decrypted message: ");
        for(char x : letters)
            System.out.print(x);

        System.out.println();
    }

    private void quit() {
        System.out.println("Exiting...");
        System.exit(0);
    }
}
