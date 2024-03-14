package efs.task.syntax;

import static java.lang.System.exit;
import java.util.Random;
import java.util.Scanner;

public class GuessNumberGame {
    int randomNumber;
    int correctAnswer;
    int maxNumber;
    //Do not modify main method
    public static void main(String[] args) {
        try {
            GuessNumberGame game = new GuessNumberGame(args.length > 0 ? args[0] : "");
            game.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public GuessNumberGame(String argument) {
        int number = 0;
        try{
            number = Integer.parseInt(argument);
            if(number < 1 || number > UsefulConstants.MAX_UPPER_BOUND)
                throw new IllegalArgumentException();
        }
        catch (Exception e)
        {
            System.out.println(UsefulConstants.WRONG_ARGUMENT);
            throw new IllegalArgumentException();
        }
        this.maxNumber = number;
        Random random = new Random();
        this.correctAnswer = Math.abs(random.nextInt()) % this.maxNumber + 1;
    }

    public void play() {
        int attempts = 1;
        int maxAttempts = (int)(Math.log(this.maxNumber) / Math.log(2)) + 1;
        Scanner scanner = new Scanner(System.in);
        int guessedNumber;
        while(attempts <= maxAttempts)
        {
            progressBar(attempts, maxAttempts);
            System.out.println(UsefulConstants.GIVE_ME);
            String guess = scanner.nextLine();
            try{
                guessedNumber = Integer.parseInt(guess);
                if(guessedNumber == this.correctAnswer)
                {
                    System.out.println(UsefulConstants.YES);
                    break;
                }
                else if(guessedNumber > this.correctAnswer)
                    System.out.println("TO " + UsefulConstants.TO_MUCH + " a");
                else
                    System.out.println("TO " + UsefulConstants.TO_LESS + " a");
                attempts++;
            }
            catch (Exception e)
            {
                System.out.println("Hmm, '" + guess + "' to " + UsefulConstants.NOT_A_NUMBER + " a");
                attempts++;
            }
        }
        scanner.close();
        if(attempts <= maxAttempts)
            System.out.println(UsefulConstants.CONGRATULATIONS + ", " + attempts + " - tyle prób zajęło Ci odgadnięcie liczby " + this.correctAnswer + " a");
        else
            System.out.println(UsefulConstants.UNFORTUNATELY + ", wyczerpałeś limit prób (" + maxAttempts + ") do odgadnięcia liczby " + this.correctAnswer + " a");
    }

    public void progressBar(int attempts, int maxAttempts){
        System.out.print("[");
        for(int i = 0; i < attempts; i++)
            System.out.print("*");
        for(int i = 0; i < (maxAttempts - attempts); i++)
            System.out.print(".");
            System.out.print("]\n");
    }
}
