package com.intern.cipherbyte;

import java.util.Random;
import java.util.Scanner;

public class GuessTheNumber {
    public static void main(String[] args) {
        Scanner scan=new Scanner(System.in);
        Random ran=new Random();

        int minNum=1;
        int maxNum=100;
        int targetNumber= ran.nextInt(maxNum-minNum+1)+minNum; //Generating a random number
        int attempts=0;
        int maxAttempts=10; //No. of attempts
        int score=100; //Initial Score

        System.out.println("=============================================");
        System.out.println("|         WELCOME TO GUESS THE NUMBER       |");
        System.out.println("|                                           |");
        System.out.println("|    I've selected a number between         |");
        System.out.println("|            " + minNum + " and " + maxNum + " for you to guess!            |");
        System.out.println("|                                           |");
        System.out.println("=============================================");

        while (attempts<maxAttempts)
        {
            System.out.println("Enter your guess: ");
            int guess=scan.nextInt();
            attempts++;

            if (guess==targetNumber){
                System.out.println("=============================================");
                System.out.println("|  Congratulations! You guessed the number  |");
                System.out.println("|      " + targetNumber + " correctly in " + attempts + " attempts!        |");
                System.out.println("|             Your final score is: " + score + "             |");
                System.out.println("=============================================");
                break;
            } else if (guess<targetNumber) {
                System.out.println("|             Too low! Try again.            |");
            }
            else {
                System.out.println("|             Too high! Try again.            |");
            }
            score-=10; //Decreasing the score.
        }

        if (attempts==maxAttempts){
            System.out.println("=============================================");
            System.out.println("|  Sorry, you've run out of attempts.       |");
            System.out.println("|       The correct number was: " + targetNumber + "          |");
            System.out.println("|             Your final score is: 0          |");
            System.out.println("=============================================");
        }
        scan.close();
    }
}
