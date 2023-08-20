package com.study.algo;

import java.util.Arrays;
import java.util.Random;

enum Decor {
    Spade,
    Heart,
    Dimand,
    Club,
    BIGKING,
    SMALLKING
}

public class ShuffleSolution {
    static class Poker {
        int val;
        Decor decor;

        public Poker(int val, Decor decor) {
            this.val = val;
            this.decor = decor;
        }

        public Poker() {
        }

        public void setVal(int val) {
            this.val = val;
        }

        public void setDecor(Decor decor) {
            this.decor = decor;
        }

        public int getVal() {
            return val;
        }

        public Decor getDecor() {
            return decor;
        }
    }

    static private Poker[] pokers = new Poker[54];

    static Poker[] init() {
        for (int i = 0; i < 13; i++) {
            for (int j = 4 * i; j < 4 * i + 4; j++) {
                pokers[j] = new Poker();
                pokers[j].setVal(i + 1);
                if (j == 4 * i) {
                    pokers[j].setDecor(Decor.Club);
                } else if (j == 4 * i + 1) {
                    pokers[j].setDecor(Decor.Dimand);
                } else if (j == 4 * i + 2) {
                    pokers[j].setDecor(Decor.Heart);
                } else {
                    pokers[j].setDecor(Decor.Spade);
                }
            }
        }
        pokers[52] = new Poker();
        pokers[52].setVal(52);
        pokers[52].setDecor(Decor.BIGKING);
        pokers[53] = new Poker();
        pokers[53].setVal(53);
        pokers[53].setDecor(Decor.SMALLKING);
        return pokers;
    }

    static void show() {
        Arrays.stream(pokers).forEach(p -> {
            System.out.println(p.getDecor() + " " + p.getVal());
        });
    }

    static Poker[] shuffle() {
        Random rand = new Random();
        for (int i = 53; i > 0; i--) {
            int pos = rand.nextInt(i);
            swap(pokers, i, pos);
        }
        return pokers;
    }

    static void swap(Poker[] pokers, int i, int j) {
        if (i == j) {
            return;
        }
        Poker temp;
        temp = pokers[i];
        pokers[i] = pokers[j];
        pokers[j] = temp;
    }

    public static void main(String[] args) {
        init();
        shuffle();
        show();
    }
}
