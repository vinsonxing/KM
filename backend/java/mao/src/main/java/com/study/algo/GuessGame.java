package com.study.algo;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class GuessGame {
    public static void writeToFile(String filePath, String content) {

        try (FileWriter writer = new FileWriter(filePath, true)) {
            writer.append(content);
            writer.append(System.getProperty("line.separator"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String fileName = "D:\\newFile.txt";

        writeToFile(fileName, "创建文件");
        Random r = new Random();
        int num = r.nextInt(101);
        Scanner in = new Scanner(System.in);
        System.out.println("请问要猜几次");
        int max = in.nextInt();
        System.out.println("随机数已生成，猜猜看，你一共有" + max + "次机会");
        int flag = 0;
        for (int i = 0; i < max; i++) {
            System.out.println("请输入你第" + (i + 1) + "次结果");
            int n = in.nextInt();
            if (n < 0) {
                System.out.println("Game over");
                return;
            } else if (n > num) {
                System.out.println("too big");
            } else if (n < num) {
                System.out.println("too small");
            } else {
                flag = 1;
                if (i <= 1) {
                    System.out.println("Bingo");
                } else if (i <= 3) {
                    System.out.println("lucky you");
                } else {
                    System.out.println("good guess");
                }
                break;
            }
        }
        if (flag == 0) {
            System.out.println("game over");
        }
        writeToFile(fileName, "bye");
    }
}
