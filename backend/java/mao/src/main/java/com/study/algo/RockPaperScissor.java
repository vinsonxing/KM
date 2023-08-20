package com.study.algo;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class RockPaperScissor {
   private static String getComputerMove(){
       Random random = new Random();
       String value = Move.values()[random.nextInt(3)].getValue();
       System.out.println("Computer move : " + value);
       return value;
   }

   private static boolean isPlayWin(String playerMove, String computerMove){
       return (playerMove.equals(Move.ROCK.value) && computerMove.equals(Move.SCISSORS.value) ||
               (playerMove.equals(Move.SCISSORS.value) && computerMove.equals(Move.PAPER.value)) ||
               (playerMove.equals(Move.PAPER.value) && computerMove.equals(Move.ROCK.value)));
   }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int wins = 0, losses = 0;
        while(true){
            String playerMove = scanner.nextLine();
            if(playerMove.equals("quit")){
                System.out.println("You won " + wins + "times and lost " + losses + " times" );
                break;
            }
            if (Arrays.stream(Move.values()).noneMatch(x -> x.getValue().equals(playerMove))) {
                System.out.println("Your move is not valid");
                continue;
            }
            String computerMove = getComputerMove();
            if (playerMove.equals(computerMove)) {
                System.out.println("tie!");
            } else if (isPlayWin(playerMove, computerMove)) {
                System.out.println("you win");
                wins++;
            } else {
                System.out.println("you lose");
                losses++;
            }
        }
    }
}
enum Move{
    ROCK("rock"),
    PAPER("paper"),
    SCISSORS("scissors");

    String value;
    Move(String name) {
        this.value = name;
    }

    public String getValue() {
        return value;
    }
}
