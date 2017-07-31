package com.company;

// Pavlo Khryshceniuk grupa nr 7
//package source;

import java.util.Scanner;

public class Main {

    public static Scanner inScan = new Scanner(System.in);

    static public boolean ifEmpty(long[][] tab, int n, int m) {
        boolean boo = true;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (tab[i][j] != 0) {
                    boo = false;
                }
            }
        }
        return boo;
    }

    static public class Res {

        int i, j;
        int k, l;
        long sum;
    }

    static public class Max {

        int k = 0, l = 0;
        long sum = 0;
    }

    public static void main(String[] args) {

        int numrep = inScan.nextInt();

        for (int y = 0; y < numrep; y++) {
            int n = inScan.nextInt();
            int m = inScan.nextInt();

            long[][] tab = new long[n][m];
            long[] tmp = new long[m];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    tab[i][j] = inScan.nextLong();
                }
            }

            Res result = new Res();

            for (int i = 0; i < n; i++) {
                Res maxsofar = new Res();
                for (int putinhuilo = 0; putinhuilo < m; putinhuilo++) {
                    tmp[putinhuilo] = 0;
                }

                for (int j = i; j < n; j++) {
                    Res maxcur = new Res();
                    maxcur.i = i;
                    maxcur.j = j;

                    for (int k = 0; k < m; k++) {

                        tmp[k] += tab[j][k];

                        if (maxcur.sum <= 0) {

                            maxcur.sum = tmp[k];
                            maxcur.k = k;
                            maxcur.l = k;

                        } else {

                            maxcur.sum += tmp[k];
                            maxcur.l = k;
                        }

                        if ((maxcur.sum == maxsofar.sum) && (maxsofar.l - maxsofar.k > maxcur.l - maxcur.k)) {

                            maxsofar.sum = maxcur.sum;
                            maxsofar.k = maxcur.k;
                            maxsofar.l = maxcur.l;
                            maxsofar.i = maxcur.i;
                            maxsofar.j = maxcur.j;

                        } else if (maxcur.sum > maxsofar.sum) {

                            maxsofar.sum = maxcur.sum;
                            maxsofar.k = maxcur.k;
                            maxsofar.l = maxcur.l;
                            maxsofar.i = maxcur.i;
                            maxsofar.j = maxcur.j;

                        }

                    }

                    if (result.sum == maxsofar.sum
                            && ((maxsofar.l + 1 - maxsofar.k + 1) * (maxsofar.j + 1 - maxsofar.i + 1))
                            < ((result.l + 1 - result.k + 1) * (result.j + 1 - result.i + 1))) {

                        result.sum = maxsofar.sum;
                        result.i = maxsofar.i;
                        result.j = maxsofar.j;
                        result.k = maxsofar.k;
                        result.l = maxsofar.l;

                    } else if (result.sum < maxsofar.sum) {
                        result.sum = maxsofar.sum;
                        result.i = maxsofar.i;
                        result.j = maxsofar.j;
                        result.k = maxsofar.k;
                        result.l = maxsofar.l;

                    }

                }

            }

            if (ifEmpty(tab, n, m)) {
                System.out.println("max_sum=0");
                System.out.println("[0..0, 0..0]");
            } else if (result.sum
                    == 0) {
                System.out.println("0");
            } else {
                System.out.println("max_sum=" + result.sum);
                System.out.println("[" + result.i + ".." + result.j + ", " + result.k + ".." + result.l + "]");
            }
        }

    }
}
