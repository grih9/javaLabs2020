package ru.spbstu.lab1;

import java.util.InputMismatchException;
import java.util.Scanner;

public class PrimeNumber {
    private static boolean isPrime(int number) {
        for (int i = 2; i <= Math.sqrt(number); ++i) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println("Enter valid range: ");
        Scanner sc = new Scanner(System.in);

        try {
            int begin = sc.nextInt();
            int end = sc.nextInt();


            if ((begin > end) || (end < 2)) {
                return;
            }

            begin = Math.max(2, begin);

            for (int i = begin; i <= end; ++i) {
                if (isPrime(i)) {
                    System.out.println(i);
                }
            }

        } catch (InputMismatchException e) {
            e.printStackTrace();
        }
    }
}





