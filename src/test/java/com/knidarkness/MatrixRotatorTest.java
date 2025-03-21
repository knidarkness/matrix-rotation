package com.knidarkness;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

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
    void testGetOuterLine() {
        int[][] matrix = {
            {1, 2},
            {3, 4}
        };

        MatrixRotator matrixRotator = new MatrixRotator();
        List<Integer> result = matrixRotator.getMatrixRing(matrix, 0);
        List<Integer> expected = Arrays.asList(1, 2, 4, 3);
        assertEquals(expected, result);
    }

    @Test
    void testGetInnerLineSmall() {
        int[][] matrix = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 16}
        };

        MatrixRotator matrixRotator = new MatrixRotator();
        List<Integer> result = matrixRotator.getMatrixRing(matrix, 1);
        List<Integer> expected = Arrays.asList(6, 7, 11, 10);
        assertEquals(expected, result);
    }

    @Test
    void testGetOuterLine6x4() {
        int[][] matrix = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 16},
            {17, 18, 19, 20},
            {21, 22, 23, 24}
        };

        MatrixRotator matrixRotator = new MatrixRotator();
        List<Integer> result = matrixRotator.getMatrixRing(matrix, 0);
        List<Integer> expected = Arrays.asList(1, 2, 3, 4, 8, 12, 16, 20, 24, 23, 22, 21, 17, 13, 9, 5);
        assertEquals(expected, result);
    }

    @Test
    void testGetInnerLine() {
        int[][] matrix = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 16},
            {17, 18, 19, 20},
            {21, 22, 23, 24}
        };

        MatrixRotator matrixRotator = new MatrixRotator();
        List<Integer> result = matrixRotator.getMatrixRing(matrix, 1);
        List<Integer> expected = Arrays.asList(6, 7, 11, 15, 19, 18, 14, 10);
        assertEquals(expected, result);
    }

    @Test
    void testGetInnerLine2() {
        int[][] matrix = {
            {1, 2, 3, 4, 5, 6},
            {7, 8, 9, 10, 11, 12},
            {13, 14, 15, 16, 17, 18},
            {19, 20, 21, 22, 23, 24}
        };

        MatrixRotator matrixRotator = new MatrixRotator();
        List<Integer> result = matrixRotator.getMatrixRing(matrix, 1);
        List<Integer> expected = Arrays.asList(8, 9, 10, 11, 17, 16, 15, 14);
        assertEquals(expected, result);
    }

    @Test
    void testGetMatrixLines() {
        int[][] matrix = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 16},
            {17, 18, 19, 20},
            {21, 22, 23, 24}
        };

        MatrixRotator matrixRotator = new MatrixRotator();
        List<List<Integer>> rings = matrixRotator.getMatrixRings(matrix);
        List<List<Integer>> expectedRings = Arrays.asList(
            Arrays.asList(1, 2, 3, 4, 8, 12, 16, 20, 24, 23, 22, 21, 17, 13, 9, 5),
            Arrays.asList(6, 7, 11, 15, 19, 18, 14, 10)
        );
        assertEquals(expectedRings, rings);

        int[][] reassembled = matrixRotator.assembleMatrix(rings, 4, 6);
        assertMatrixEquals(matrix, reassembled);
    }

    @Test
    void testAssembleMatrixEmpty() {
        List<List<Integer>> rings = new ArrayList<>();
        MatrixRotator matrixRotator = new MatrixRotator();
        int[][] result = matrixRotator.assembleMatrix(rings, 0, 0);
        int[][] expected = new int[0][0];
        assertMatrixEquals(expected, result);
    }

    @Test
    void testAssembleMatrix2x2Zeros() {
        List<List<Integer>> rings = List.of(Arrays.asList(0, 0, 0, 0));

        MatrixRotator matrixRotator = new MatrixRotator();
        int[][] result = matrixRotator.assembleMatrix(rings, 2, 2);
        int[][] expected = {
            {0, 0},
            {0, 0}
        };
        assertMatrixEquals(expected, result);
    }

    @Test
    void testAssembleMatrix2x2_1234() {
        List<List<Integer>> rings = List.of(Arrays.asList(1, 2, 4, 3));

        MatrixRotator matrixRotator = new MatrixRotator();
        int[][] result = matrixRotator.assembleMatrix(rings, 2, 2);
        int[][] expected = {
            {1, 2},
            {3, 4}
        };
        assertMatrixEquals(expected, result);
    }

    @Test
    void testAssembleMatrix2x4_12345678() {
        List<List<Integer>> rings = List.of(
                Arrays.asList(1, 2, 4, 6, 8, 7, 5, 3)
        );

        MatrixRotator matrixRotator = new MatrixRotator();
        int[][] result = matrixRotator.assembleMatrix(rings, 2, 4);
        int[][] expected = {
            {1, 2},
            {3, 4},
            {5, 6},
            {7, 8}
        };
        assertMatrixEquals(expected, result);
    }
}
