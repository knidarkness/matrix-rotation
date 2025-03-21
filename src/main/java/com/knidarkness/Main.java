package com.knidarkness;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String[] inputConfig = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int m = Integer.parseInt(inputConfig[0]);
        int n = Integer.parseInt(inputConfig[1]);
        int r = Integer.parseInt(inputConfig[2]);

        int[][] matrixArr = new int[m][n];

        IntStream.range(0, m).forEach(i -> {
            try {
                String row = bufferedReader.readLine();
                matrixArr[i] = Stream.of(row.split(" ")).mapToInt(Integer::parseInt).toArray();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        bufferedReader.close();

        MatrixRotator mr = new MatrixRotator();
        int[][] rotatedMatrix = mr.rotateMatrix(matrixArr, r);
        mr.logMatrix(rotatedMatrix);

    }
}