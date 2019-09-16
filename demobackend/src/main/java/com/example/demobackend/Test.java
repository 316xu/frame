package com.example.demobackend;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Test {

    public static void main(String args[]) {

        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            list.add(scanner.nextInt());
        }

        for (int i = 0; i < n; i++) {

            int cnt = 0;

            int max = 0;
            for (int j = i; j >= 0; j--) {
                if (list.get(j) > max) {
                    cnt++;
                    max = list.get(j);
                }
            }

            max = 0;
            for (int j = i; j < n; j++) {
                if (list.get(j) > max) {
                    cnt++;
                    max = list.get(j);
                }
            }

            System.out.print(cnt);
            System.out.print(" ");
        }
    }
}
