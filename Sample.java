// Time Complexity: O(m * n), where m = number of rows, n = number of columns
//   - In the worst case, all pixels in the image are the same color and need to be visited
// Space Complexity: O(m * n) for recursion stack in the worst case (e.g., all pixels same and visited recursively)
// Did this code successfully run on Leetcode :yes
// Any problem you faced while coding this :no


// Your code here along with comments explaining your approach
//We perform a DFS from the starting cell (sr, sc) and recursively paint all connected cells that have the same original color.
// At each step, we change the current cell’s color to the new one and then visit all four adjacent directions (up, down, left, right).
// The recursion stops when a cell is out of bounds or its color does not match the original.
class Solution {
    int[][] dirs; // directions: up, down, right, left
    int m, n;

    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        this.dirs = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        this.m = image.length;
        this.n = image[0].length;

        int oldColor = image[sr][sc];

        // If the starting pixel already has the target color, no changes needed
        if (oldColor == color) return image;

        // Begin DFS from the starting pixel
        dfs(image, sr, sc, oldColor, color);
        return image;
    }

    private void dfs(int[][] image, int r, int c, int oldColor, int newColor) {
        // Base case: if out of bounds or color doesn't match the oldColor, return
        if (r < 0 || r >= m || c < 0 || c >= n || image[r][c] != oldColor) return;

        // Repaint the current pixel
        image[r][c] = newColor;

        // Recurse in all 4 directions
        for (int[] dir : dirs) {
            dfs(image, r + dir[0], c + dir[1], oldColor, newColor);
        }
    }
}



// Time Complexity: O(m * n), where m is the number of rows and n is the number of columns
//   - Each cell is processed twice (once per pass)
// Space Complexity: O(m * n) for the result matrix

//We initialize a result matrix where cells with 0 remain 0 and cells with 1 are set to a large value (99999).
// In the first pass (top-left to bottom-right), we update each cell based on the minimum of its top and left neighbors.
// In the second pass (bottom-right to top-left), we update using bottom and right neighbors, ensuring all distances to the nearest 0 are correctly propagated.

class Solution {
    int m, n;
    int[][] result;

    public int[][] updateMatrix(int[][] mat) {
        this.m = mat.length;
        this.n = mat[0].length;
        this.result = new int[m][n];

        // Initialize result matrix: 0 stays 0, 1 becomes a large placeholder value
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 1) {
                    result[i][j] = 99999;
                }
            }
        }

        // First pass: top-left to bottom-right
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 1) {
                    if (i > 0) {
                        result[i][j] = Math.min(result[i][j], result[i - 1][j] + 1);
                    }
                    if (j > 0) {
                        result[i][j] = Math.min(result[i][j], result[i][j - 1] + 1);
                    }
                }
            }
        }

        // Second pass: bottom-right to top-left
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (mat[i][j] == 1) {
                    if (i < m - 1) {
                        result[i][j] = Math.min(result[i][j], result[i + 1][j] + 1);
                    }
                    if (j < n - 1) {
                        result[i][j] = Math.min(result[i][j], result[i][j + 1] + 1);
                    }
                }
            }
        }

        return result;
    }
}
