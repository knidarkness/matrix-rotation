package com.knidarkness;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class MatrixRotatorTest {

    private void assertMatrixEquals(int[][] expected, int[][] actual) {
        assertEquals(expected.length, actual.length, "Matrix row count mismatch");
        for (int i = 0; i < expected.length; i++) {
            assertArrayEquals(expected[i], actual[i], "Row " + i + " mismatch");
        }
    }

    @Test
    void testShiftMatrixSquare1() {
        int[][] matrix = {
            {1, 2},
            {3, 4}
        };
        int[][] expected = {
            {2, 4},
            {1, 3}
        };

        MatrixRotator matrixRotator = new MatrixRotator();
        int[][] result = matrixRotator.rotateMatrix(matrix, 1);
        assertMatrixEquals(expected, result);
    }

    @Test
    void testRotateMatrix2x2Zeros() {
        int[][] matrix = {
            {0, 0},
            {0, 0}
        };
        int[][] expected = {
            {0, 0},
            {0, 0}
        };

        MatrixRotator matrixRotator = new MatrixRotator();
        int[][] result = matrixRotator.rotateMatrix(matrix, 2);
        assertMatrixEquals(expected, result);
    }

    @Test
    void testRotateMatrix4x6() {
        int[][] matrix = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 16},
            {17, 18, 19, 20},
            {21, 22, 23, 24}
        };
        int[][] expected = {
            {2, 3, 4, 8},
            {1, 7, 11, 12},
            {5, 6, 15, 16},
            {9, 10, 19, 20},
            {13, 14, 18, 24},
            {17, 21, 22, 23}
        };

        MatrixRotator matrixRotator = new MatrixRotator();
        int[][] result = matrixRotator.rotateMatrix(matrix, 1);
        assertMatrixEquals(expected, result);
    }

    @Test
    void testGetCoordsForRingPositionOuter4x4() {
        MatrixRotator matrixRotator = new MatrixRotator();
        int[][] expectedCoords = {
                {0, 0},
                {0, 1},
                {0, 2},
                {0, 3},
                {1, 3},
                {2, 3},
                {3, 3},
                {3, 2},
                {3, 1},
                {3, 0},
                {2, 0},
                {1, 0}
        };
        for (int i = 0; i < expectedCoords.length; i++) {
            int[] coords = matrixRotator.getCoordsForRingPosition(
                    4,
                    4,
                    0,
                    i);
            assertArrayEquals(expectedCoords[i], coords, "Mismatch at index " + i);
        }
    }

    @Test
    void testGetCoordsForRingPositionInner4x6() {
        MatrixRotator matrixRotator = new MatrixRotator();

        // ring 1 for a 4x6 matrix (see testRotateMatrix4x6)
        int[][] expectedCoords = {
                {1, 1},
                {1, 2},
                {2, 2},
                {3, 2},
                {4, 2},
                {4, 1},
                {3, 1},
                {2, 1},
        };
        for (int i = 0; i < expectedCoords.length; i++) {
            int[] coords = matrixRotator.getCoordsForRingPosition(4, 6, 1, i);
            assertArrayEquals(expectedCoords[i], coords, "Mismatch at index " + i);
        }
    }
}
