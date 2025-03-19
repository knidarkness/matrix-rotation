# Matrix Rotation

## What

This is a Java-based solution for the [Matrix Layer Rotation](https://www.hackerrank.com/challenges/matrix-rotation-algo/problem) challenge from HackerRank. The goal of the task is to rotate a given matrix counter-clockwise. You can find the main implementation file in the [MatrixRotator.java](./src/main/java/com/knidarkness/MatrixRotator.java), while the test cases used during the TDD-based approach live in [MatrixRotatorTest.java](./src/test/java/com/knidarkness/MatrixRotatorTest.java).

### Constraints

Some of the important constraints are:

- `2 <= m, n <= 300`, where `m, n` are the matrix dimensions
- `1 <= r <= 10^9`, where `r` is the amount of rotation "steps"
- `min(m, n) % 2 = 0`
- Maximum value of each matrix element is `10^8`

## How & Why

The goal of the task is to rotate a given matrix counter-clockwise.

Some of the fundamental assumptions are:

- Maximal amount of steps a matrix can be rotated before it becomes the initial matrix equals `(m + (n - 1)) * 2`, which is always less than `10^9`
- Java integer can hold a value of up to 2^31, so we don't need to use any Long/BigInt datatypes
- The HackerRank implementation template suggests using `List<List<Integer>>` as a structure to store a matrix. While `int[][]` would be marginally faster, I believe its importance is negligible on our data volume of `<= 10 000` elements.
- It is guaranteed that we can rotate matrix and all elements will move because of `min(m, n) % 2 = 0` constraint -- there will not be a static part middle row or column which is not affected by rotation.

With these assumptions in mind, let's take a look at approach we can use to implement the rotation.

On one hand, we could implement some sort of "brute-force simulation" -- create a new matrix, and then for each element of the matrix try to simulate the final position by "rotating" shifting element and changing the direction of shift when we encounter a "wall" (i.e. outer boundary of matrix or row/column). However, implementing this approach would likely be quite cumbersome, hard to read, and computationally not very effective.

Instead, let's treat our matrix not as collection of rows (or columns), but rather as a set of nested rings (or layers), each defined by the shortest distance to the edge of matrix (see picture below). In this case, we rotate the matrix by rotating each individual ring. Extra (albeit small) benefit is that we can remove extra rotations (i.e. further than "full circle") on per-ring basis - hence further saving some time. And the rotation itself becomes a fairly trivial list/array shift. The more specific steps are:

- Split matrix into the nested rings
- For each ring define needed shift based on `r % len(ring)`
- Shift each individual ring left by the value of previous step
- Re-build a matrix from the shifted rings

Below is the picture illustrating this flow if we need to rotate a matrix by 1 step:

![Rotation example](matrix-rotation.jpg)

As for the algorithmic complexity of this approach, we are looking at O(n^2) if `n = m = 300` (or (n x m) otherwise) and the extra memory needed grows linearly with the amount of elements of the matrix being rotated. 

## Done

And with this, we successfully solved the challenge:
![Done](done.png)