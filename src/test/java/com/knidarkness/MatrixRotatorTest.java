package com.knidarkness;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

class MatrixRotatorTest {

    private void assertMatrixEquals(List<List<Integer>> expected, List<List<Integer>> actual) {
        assertEquals(expected.size(), actual.size(), "Matrix row count mismatch");
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), actual.get(i), "Row " + i + " mismatch");
        }
    }

    @Test
    void testShiftMatrixSquare1() {
        List<List<Integer>> matrix = new ArrayList<>();
        matrix.add(Arrays.asList(1, 2));
        matrix.add(Arrays.asList(3, 4));

        List<List<Integer>> expected = new ArrayList<>();
        expected.add(Arrays.asList(2, 4));
        expected.add(Arrays.asList(1, 3));

        MatrixRotator matrixRotator = new MatrixRotator();
        List<List<Integer>> result = matrixRotator.rotateMatrix(matrix, 1);
        assertMatrixEquals(expected, result);
    }

    @Test
    void testRotateMatrix2x2Zeros() {
        List<List<Integer>> matrix = new ArrayList<>();
        matrix.add(Arrays.asList(0, 0));
        matrix.add(Arrays.asList(0, 0));

        List<List<Integer>> expected = new ArrayList<>();
        expected.add(Arrays.asList(0, 0));
        expected.add(Arrays.asList(0, 0));

        MatrixRotator matrixRotator = new MatrixRotator();
        List<List<Integer>> result = matrixRotator.rotateMatrix(matrix, 2);
        assertMatrixEquals(expected, result);
    }

    @Test
    void testRotateMatrix4x6() {
        List<List<Integer>> matrix = new ArrayList<>();
        matrix.add(Arrays.asList(1, 2, 3, 4));
        matrix.add(Arrays.asList(5, 6, 7, 8));
        matrix.add(Arrays.asList(9, 10, 11, 12));
        matrix.add(Arrays.asList(13, 14, 15, 16));
        matrix.add(Arrays.asList(17, 18, 19, 20));
        matrix.add(Arrays.asList(21, 22, 23, 24));

        List<List<Integer>> expected = new ArrayList<>();
        expected.add(Arrays.asList(2, 3, 4, 8));
        expected.add(Arrays.asList(1, 7, 11, 12));
        expected.add(Arrays.asList(5, 6, 15, 16));
        expected.add(Arrays.asList(9, 10, 19, 20));
        expected.add(Arrays.asList(13, 14, 18, 24));
        expected.add(Arrays.asList(17, 21, 22, 23));

        MatrixRotator matrixRotator = new MatrixRotator();
        List<List<Integer>> result = matrixRotator.rotateMatrix(matrix, 1);
        assertMatrixEquals(expected, result);
    }

    @Test
    void testGetOuterLine() {
        List<List<Integer>> matrix = new ArrayList<>();
        matrix.add(Arrays.asList(1, 2));
        matrix.add(Arrays.asList(3, 4));

        MatrixRotator matrixRotator = new MatrixRotator();
        List<Integer> result = matrixRotator.getMatrixRing(matrix, 0);
        List<Integer> expected = Arrays.asList(1, 2, 4, 3);
        assertEquals(expected, result);
    }

    @Test
    void testGetInnerLineSmall() {
        List<List<Integer>> matrix = new ArrayList<>();
        matrix.add(Arrays.asList(1, 2, 3, 4));
        matrix.add(Arrays.asList(5, 6, 7, 8));
        matrix.add(Arrays.asList(9, 10, 11, 12));
        matrix.add(Arrays.asList(13, 14, 15, 16));

        MatrixRotator matrixRotator = new MatrixRotator();
        List<Integer> result = matrixRotator.getMatrixRing(matrix, 1);
        List<Integer> expected = Arrays.asList(6, 7, 11, 10);
        assertEquals(expected, result);
    }

    @Test
    void testGetOuterLine6x4() {
        List<List<Integer>> matrix = new ArrayList<>();
        matrix.add(Arrays.asList(1, 2, 3, 4));
        matrix.add(Arrays.asList(5, 6, 7, 8));
        matrix.add(Arrays.asList(9, 10, 11, 12));
        matrix.add(Arrays.asList(13, 14, 15, 16));
        matrix.add(Arrays.asList(17, 18, 19, 20));
        matrix.add(Arrays.asList(21, 22, 23, 24));

        MatrixRotator matrixRotator = new MatrixRotator();
        List<Integer> result = matrixRotator.getMatrixRing(matrix, 0);
        List<Integer> expected = Arrays.asList(1, 2, 3, 4, 8, 12, 16, 20, 24, 23, 22, 21, 17, 13, 9, 5);
        assertEquals(expected, result);
    }

    @Test
    void testGetInnerLine() {
        List<List<Integer>> matrix = new ArrayList<>();
        matrix.add(Arrays.asList(1, 2, 3, 4));
        matrix.add(Arrays.asList(5, 6, 7, 8));
        matrix.add(Arrays.asList(9, 10, 11, 12));
        matrix.add(Arrays.asList(13, 14, 15, 16));
        matrix.add(Arrays.asList(17, 18, 19, 20));
        matrix.add(Arrays.asList(21, 22, 23, 24));

        MatrixRotator matrixRotator = new MatrixRotator();
        List<Integer> result = matrixRotator.getMatrixRing(matrix, 1);
        List<Integer> expected = Arrays.asList(6, 7, 11, 15, 19, 18, 14, 10);
        assertEquals(expected, result);
    }

    @Test
    void testGetInnerLine2() {
        List<List<Integer>> matrix = new ArrayList<>();
        matrix.add(Arrays.asList(1, 2, 3, 4, 5, 6));
        matrix.add(Arrays.asList(7, 8, 9, 10, 11, 12));
        matrix.add(Arrays.asList(13, 14, 15, 16, 17, 18));
        matrix.add(Arrays.asList(19, 20, 21, 22, 23, 24));

        MatrixRotator matrixRotator = new MatrixRotator();
        List<Integer> result = matrixRotator.getMatrixRing(matrix, 1);
        List<Integer> expected = Arrays.asList(8, 9, 10, 11, 17, 16, 15, 14);
        assertEquals(expected, result);
    }

    @Test
    void testGetMatrixLines() {
        List<List<Integer>> matrix = new ArrayList<>();
        matrix.add(Arrays.asList(1, 2, 3, 4));
        matrix.add(Arrays.asList(5, 6, 7, 8));
        matrix.add(Arrays.asList(9, 10, 11, 12));
        matrix.add(Arrays.asList(13, 14, 15, 16));
        matrix.add(Arrays.asList(17, 18, 19, 20));
        matrix.add(Arrays.asList(21, 22, 23, 24));

        MatrixRotator matrixRotator = new MatrixRotator();
        List<List<Integer>> rings = matrixRotator.getMatrixRings(matrix);
        List<List<Integer>> expectedRings = new ArrayList<>();
        expectedRings.add(Arrays.asList(1, 2, 3, 4, 8, 12, 16, 20, 24, 23, 22, 21, 17, 13, 9, 5));
        expectedRings.add(Arrays.asList(6, 7, 11, 15, 19, 18, 14, 10));
        assertEquals(expectedRings, rings);

        List<List<Integer>> reassembled = matrixRotator.assembleMatrix(rings, 4, 6);
        assertMatrixEquals(matrix, reassembled);
    }

    @Test
    void testAssembleMatrixEmpty() {
        List<List<Integer>> rings = new ArrayList<>();
        MatrixRotator matrixRotator = new MatrixRotator();
        List<List<Integer>> result = matrixRotator.assembleMatrix(rings, 0, 0);
        List<List<Integer>> expected = new ArrayList<>();
        assertMatrixEquals(expected, result);
    }

    @Test
    void testAssembleMatrix2x2Zeros() {
        List<List<Integer>> rings = new ArrayList<>();
        rings.add(Arrays.asList(0, 0, 0, 0));

        List<List<Integer>> expected = new ArrayList<>();
        expected.add(Arrays.asList(0, 0));
        expected.add(Arrays.asList(0, 0));

        MatrixRotator matrixRotator = new MatrixRotator();
        List<List<Integer>> result = matrixRotator.assembleMatrix(rings, 2, 2);
        assertMatrixEquals(expected, result);
    }

    @Test
    void testAssembleMatrix2x2_1234() {
        List<List<Integer>> rings = new ArrayList<>();
        rings.add(Arrays.asList(1, 2, 4, 3));

        List<List<Integer>> expected = new ArrayList<>();
        expected.add(Arrays.asList(1, 2));
        expected.add(Arrays.asList(3, 4));

        MatrixRotator matrixRotator = new MatrixRotator();
        List<List<Integer>> result = matrixRotator.assembleMatrix(rings, 2, 2);
        assertMatrixEquals(expected, result);
    }

    @Test
    void testAssembleMatrix2x4_12345678() {
        List<List<Integer>> rings = new ArrayList<>();
        rings.add(Arrays.asList(1, 2, 4, 6, 8, 7, 5, 3));

        List<List<Integer>> expected = new ArrayList<>();
        expected.add(Arrays.asList(1, 2));
        expected.add(Arrays.asList(3, 4));
        expected.add(Arrays.asList(5, 6));
        expected.add(Arrays.asList(7, 8));

        MatrixRotator matrixRotator = new MatrixRotator();
        List<List<Integer>> result = matrixRotator.assembleMatrix(rings, 2, 4);
        assertMatrixEquals(expected, result);
    }
}
