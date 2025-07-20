package code.own.growingio;

/**
 * @Description:
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/9/19 19:31
 */
public class Main2 {
    public int numIslands (char[][] grid) {
        // write code here
        int rlen = grid.length;
        int clen = grid[0].length;
        int sum = 0;

        for (int i = 0; i < rlen; i++) {
            for (int j = 0; j < clen; j++) {
                if (grid[i][j] == '1') {
                    sum++;
                    dfs(grid, i, j);
                }
            }
        }

        return sum;
    }

    private void dfs(char[][] grid, int i, int j) {
        int rlen = grid.length;
        int clen = grid[0].length;
        if (i >= rlen || j >= clen || i < 0 || j < 0 || grid[i][j] == '0') {
            return;
        }
        grid[i][j] = '0';
        dfs(grid, i - 1, j);
        dfs(grid, i + 1, j);
        dfs(grid, i, j - 1);
        dfs(grid, i, j + 1);
    }
}
