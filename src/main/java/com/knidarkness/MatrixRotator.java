package com.knidarkness;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MatrixRotator {
    public List<List<Integer>> rotateMatrix(List<List<Integer>> matrix, int r) {
        List<List<Integer>> rings = getMatrixRings(matrix);
        for (List<Integer> ring : rings) {
            Collections.rotate(ring, - (r % ring.size()));
        }
        return assembleMatrix(rings, matrix.get(0).size(), matrix.size());
    }

    public List<Integer> getMatrixRing(List<List<Integer>> matrix, int ring) {
        List<Integer> result = new ArrayList<>();
        int matrixHeight = matrix.size();
        int matrixWidth = matrix.get(0).size();

        // Top row:
        for (int j = ring; j < matrixWidth - ring; j++) {
            result.add(matrix.get(ring).get(j));
        }
        // Right column: for rows from 'ring+1' to 'rows - ring - 2'
        for (int i = ring + 1; i < matrixHeight - 1 - ring; i++) {
            result.add(matrix.get(i).get(matrixWidth - 1 - ring));
        }
        // Bottom row, reversed order
        for (int j = matrixWidth - 1 - ring; j >= ring; j--) {
            result.add(matrix.get(matrixHeight - 1 - ring).get(j));
        }
        // Left column
        for (int i = matrixHeight - 2 - ring; i > ring; i--) {
            result.add(matrix.get(i).get(ring));
        }
        return result;
    }

    public List<List<Integer>> getMatrixRings(List<List<Integer>> matrix) {
        int rows = matrix.size();
        int cols = matrix.get(0).size();
        int numRings = Math.min(rows, cols) / 2;
        List<List<Integer>> rings = new ArrayList<>();
        for (int i = 0; i < numRings; i++) {
            rings.add(getMatrixRing(matrix, i));
        }
        return rings;
    }

    public List<List<Integer>> assembleMatrix(List<List<Integer>> rings, int x, int y) {
        List<List<Integer>> matrix = IntStream
            .range(0, y)
            .<List<Integer>>mapToObj(
                rindIndex -> new ArrayList<>(Collections.nCopies(x, 0))
            ).collect(Collectors.toList());

        for (int ring = 0; ring < rings.size(); ring++) {
            assembleRing(matrix, rings.get(ring), ring);
        }
        return matrix;
    }

    public void assembleRing(List<List<Integer>> matrix, List<Integer> ring, int i) {
        int matrixWidth = matrix.get(0).size();
        int matrixHeight = matrix.size();

        int ringWidth = matrixWidth - 2 * i;
        int ringHeight = matrixHeight - 2 * i;

        // Top row
        int pos = 0;
        for (int j = 0; j < ringWidth; j++) {
            matrix.get(i).set(i + j, ring.get(pos++));
        }
        // Right column
        for (int j = 1; j < ringHeight - 1; j++) {
            matrix.get(i + j).set(matrixWidth - 1 - i, ring.get((ringWidth - 1) + j));
        }
        // Bottom row
        int bottomRowStart = ringWidth + ringHeight - 2;
        for (int j = 0; j < ringWidth; j++) {
            matrix.get(matrixHeight - 1 - i).set(i + j, ring.get(bottomRowStart + (ringWidth - 1 - j)));
        }
        // Left column
        for (int j = 1; j < ringHeight - 1; j++) {
            int leftColStart = (ringWidth + ringHeight - 2) + ringWidth - 1;
            matrix.get(matrixHeight - 1 - i - j).set(i, ring.get(leftColStart + j));
        }
    }

    public void logMatrix(List<List<Integer>> matrix) {
        for (List<Integer> row : matrix) {
            String rowStr = row.stream().map(Object::toString).collect(Collectors.joining(" "));
            System.out.println(rowStr);
        }
    }
}
