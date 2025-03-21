package com.knidarkness;

import java.util.Arrays;
import java.util.stream.Collectors;

public class MatrixRotator {

    public int[][] rotateMatrix(int[][] matrix, int r) {
        int height = matrix.length;
        int width = matrix[0].length;
        int[][] rotated = new int[height][width];

        int numRings = Math.min(height, width) / 2;
        for (int ring = 0; ring < numRings; ring++) {
            processRing(matrix, rotated, ring, r);
        }
        return rotated;
    }

    public void processRing(int[][] matrix, int[][] rotated, int ring, int shift) {
        int height = matrix.length;
        int width = matrix[0].length;
        int ringWidth = width - 2 * ring;
        int ringHeight = height - 2 * ring;

        int ringLength = 2 * ringWidth + 2 * (ringHeight - 2);
        int effectiveShift = shift % ringLength;

        for (int index = 0; index < ringLength; index++) {
            int newIndex = (index - effectiveShift + ringLength) % ringLength;

            int[] src = getCoordsForRingPosition(width, height, ring, index);
            int[] dst = getCoordsForRingPosition(width, height, ring, newIndex);

            rotated[dst[0]][dst[1]] = matrix[src[0]][src[1]];
        }
    }

    public int[] getCoordsForRingPosition(int fullWidth, int fullHeight, int ring, int index) {
        int ringWidth = fullWidth - 2 * ring;
        int ringHeight = fullHeight - 2 * ring;

        int columnElements = ringHeight - 2;  // column elements without top/bottom row
        int rightColumnX = fullWidth - 1 - ring;
        int bottomRowY = fullHeight - 1 - ring;

        if (index < ringWidth) {
            // Top row: no need to shift, can already return
            return new int[]{ring, ring + index};
        } else if (index < ringWidth + columnElements) {
            // Right column: "skip" the top row
            int localIndex = index - ringWidth;
            return new int[]{ring + localIndex + 1, rightColumnX};
        } else if (index < ringWidth + columnElements + ringWidth) {
            // Bottom row: skip top row & right column, then go left from the right
            int localIndex = index - (ringWidth + columnElements);
            return new int[]{ bottomRowY, rightColumnX - localIndex };
        } else {
            // Left column: Skip prev. rows & columns
            // then same as for bottom row - with index increasing -> go bottom to top
            int localIndex = index - (ringWidth + columnElements + ringWidth);
            return new int[]{ bottomRowY - localIndex - 1, ring };
        }
    }

    public void logMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            String rowStr = Arrays.stream(row)
                    .mapToObj(String::valueOf)
                    .collect(Collectors.joining(" "));
            System.out.println(rowStr);
        }
    }
}
