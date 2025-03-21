package com.knidarkness;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MatrixRotator {

    public int[][] rotateMatrix(int[][] matrix, int r) {
        List<List<Integer>> rings = getMatrixRings(matrix);
        for (List<Integer> ring : rings) {
            Collections.rotate(ring, -(r % ring.size()));
        }
        return assembleMatrix(rings, matrix[0].length, matrix.length);
    }

    public List<Integer> getMatrixRing(int[][] matrix, int ring) {
        List<Integer> result = new ArrayList<>();
        int matrixHeight = matrix.length;
        int matrixWidth = matrix[0].length;

        // Top row
        for (int j = ring; j < matrixWidth - ring; j++) {
            result.add(matrix[ring][j]);
        }
        // Right column
        for (int i = ring + 1; i < matrixHeight - 1 - ring; i++) {
            result.add(matrix[i][matrixWidth - 1 - ring]);
        }
        // Bottom row, reversed!
        for (int j = matrixWidth - 1 - ring; j >= ring; j--) {
            result.add(matrix[matrixHeight - 1 - ring][j]);
        }
        // Left column
        for (int i = matrixHeight - 2 - ring; i > ring; i--) {
            result.add(matrix[i][ring]);
        }
        return result;
    }

    public List<List<Integer>> getMatrixRings(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int numRings = Math.min(rows, cols) / 2;
        List<List<Integer>> rings = new ArrayList<>();
        for (int i = 0; i < numRings; i++) {
            rings.add(getMatrixRing(matrix, i));
        }
        return rings;
    }

    public int[][] assembleMatrix(List<List<Integer>> rings, int width, int height) {
        int[][] matrix = new int[height][width];
        for (int ring = 0; ring < rings.size(); ring++) {
            assembleRing(matrix, rings.get(ring), ring);
        }
        return matrix;
    }

    public void assembleRing(int[][] matrix, List<Integer> ring, int i) {
        int matrixWidth = matrix[0].length;
        int matrixHeight = matrix.length;

        int ringWidth = matrixWidth - 2 * i;
        int ringHeight = matrixHeight - 2 * i;

        // Top row
        for (int j = 0; j < ringWidth; j++) {
            matrix[i][i + j] = ring.get(j);
        }
        // Right column
        for (int j = 1; j < ringHeight - 1; j++) {
            matrix[i + j][matrixWidth - 1 - i] = ring.get((ringWidth - 1) + j);
        }
        // Bottom row
        int bottomRowStart = ringWidth + ringHeight - 2;
        for (int j = 0; j < ringWidth; j++) {
            matrix[matrixHeight - 1 - i][i + j] = ring.get(bottomRowStart + (ringWidth - 1 - j));
        }
        for (int j = 1; j < ringHeight - 1; j++) {
            int leftColStart = (ringWidth + ringHeight - 2) + ringWidth - 1;
            matrix[matrixHeight - 1 - i - j][i] = ring.get(leftColStart + j);
        }
    }

    public void logMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            String rowStr = Arrays.stream(row).mapToObj(String::valueOf).collect(Collectors.joining(" "));
            System.out.println(rowStr);
        }
    }
}
