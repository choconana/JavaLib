package Code.own.duxiaoman;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @Description:
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/9/13 20:47
 */
public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        scanner.nextLine();
        char[][] maze = new char[n][n];
        for (int i = 0; i < n; i++) {
            String str = scanner.nextLine();
            for (int j = 0; j < n; j++) {
                maze[i][j] = str.charAt(j);
            }
        }
        int res = leastTime(maze, k);
        System.out.println(res == -1 ? "No solution" : res);
    }

    private static int leastTime(char[][] maze, int k) {
        int rowlen = maze.length;
        int collen = maze[0].length;
        int[][] distance = new int[rowlen][collen];
        for (int[] row: distance)
            Arrays.fill(row, Integer.MAX_VALUE);
        distance[0][0] = 0;
        dfs(maze, new int[]{0,0}, distance, k);
        return distance[rowlen - 1][collen - 1] == Integer.MAX_VALUE ? -1 : distance[rowlen - 1][collen - 1];
    }

    public static void dfs(char[][] maze, int[] start, int[][] distance, int k) {
        int[][] dirs={{0,1}, {0,-1}, {-1,0}, {1,0}};
        for (int[] dir: dirs) {
            int x = start[0] + dir[0];
            int y = start[1] + dir[1];
            int count = 0;
            while (x >= 0 && y >= 0 && x < maze.length && y < maze[0].length && maze[x][y] != '1') {
                char c = maze[x][y];
                x += dir[0];
                y += dir[1];
                if (c == '0') {
                    count++;
                } else if (c == '#') {
                    count += k + 1;
                }
            }
            if (distance[start[0]][start[1]] + count < distance[x - dir[0]][y - dir[1]]) {
                distance[x - dir[0]][y - dir[1]] = distance[start[0]][start[1]] + count;
                dfs(maze, new int[]{x - dir[0],y - dir[1]}, distance, k);
            }
        }
    }
}
/*

3 2
0#0
0#1
0##

5 3
00100
00000
00#10
11011
00000
*/
