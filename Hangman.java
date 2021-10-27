package miniProject1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Hangman {

    public static void main(String[] args) {

        try {
            Scanner txt = new Scanner(new File("src/cities.txt"));

            // Create an empty list and store each line of the file in the new list
            ArrayList<String> list = new ArrayList<>();
            while (txt.hasNextLine()) {
                list.add(txt.nextLine());
            }

            // Select a random word
            Random word = new Random();
            String random_word = list.get(word.nextInt(list.size()));

            // Convert the word to char array
            char[] char_word = random_word.toCharArray();

            // Creating arrays
            int[] number_charword = new int[char_word.length];

            int wrong = 0; // variable that counts wrong letters
            System.out.println("Here's the question.");

            // printing the hidden word "________"
            for (int i = 0; i < char_word.length; i++) {
                if (number_charword[i] == 1) {
                    System.out.print(char_word[i]);
                }
                if (number_charword[i] == 0) {
                    System.out.print("_");
                }
            }

            // elements and variables
            String letter;
            Scanner keyboard = new Scanner(System.in);
            boolean right = !false;
            int guesses = 1;
            int display = 0;
            while(true) {

                if (display == 0) {
                    System.out.println();
                    System.out.print("Guess a letter: ");
                    letter = keyboard.nextLine();
                    display++;
                } else{
                    System.out.print("You are guessing: ");
                    // Printing the array with the correct letters
                    for (int i = 0; i < char_word.length; i++) {
                        if (number_charword[i] == 1) {
                            System.out.print(char_word[i]);
                        }
                        if (number_charword[i] == 0) {
                            System.out.print("_");
                        }
                    }
                    System.out.println();
                    System.out.print("Guess a letter: ");
                    letter = keyboard.nextLine();
                }

                char char_letter = letter.charAt(0); // Convert the String letter into char to compare

                // Check if the letter which was typing is in the hidden word
                for (int i = 0; i < char_word.length; i++) {
                    if (char_letter == char_word[i]) {
                        number_charword[i] = 1;
                        right = true;
                    }
                }

                // If a character is wrong
                // Still trying to keep the count of wrong guesses, right now working with overall guesses, good or bad
                if (!right){
                    wrong++;
                    System.out.print("You have guessed (" + wrong + ") wrong guesses: " + char_letter);
                    System.out.println();
                }

                // Player will lose the game after 10 errors with no success
                if(guesses == 10){
                    System.out.println("You have guessed (" + guesses + ") wrong guesses: " );
                    System.out.println("You lose!" );
                    System.out.println("The correct word was '" + random_word + "'" );
                    break;
                }

                // Check if the hidden word is totally revealed
                int check_word = 0;
                for (int i = 0; i < number_charword.length; i++) {
                    if (number_charword[i] == 1 || number_charword[i] == 3) {
                        check_word = check_word + 1;
                    }
                }

                if (check_word == number_charword.length) {
                    System.out.println();
                    System.out.println("You win!");
                    System.out.println("You have guessed '" + random_word + "' correctly!");
                    break;
                }

                guesses++;

                right = false;

            }


        }
        catch (FileNotFoundException fileNotFoundException) {
            System.out.println("File not found!");
        }


    }


}