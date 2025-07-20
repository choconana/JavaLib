package code.other.jingdong;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = Integer.parseInt(scanner.nextLine());
        ArrayList<char[][]> mazeList = new ArrayList<>();
        ArrayList<int[][]> locations = new ArrayList<>();
        for (int i = 0; i < t; i++) {
            String[] strings = scanner.nextLine().split(" ");
            int n = Integer.parseInt(strings[0]);
            int m = Integer.parseInt(strings[1]);
            char[][] maze = new char[n][m];
            int[] start = new int[2];
            int[] end = new int[2];
            int[][] location = new int[2][2];
            for (int j = 0; j < n; j++) {
                String str = scanner.nextLine();
                for (int k = 0; k < m; k++) {
                    char c = str.charAt(k);
                    if (c == 'S') {
                        start[0] = j;
                        start[1] = k;
                        c = '.';
                    }
                    if (c == 'E') {
                        end[0] = j;
                        end[1] = k;
                        c = '.';
                    }
                    maze[j][k] = c;
                }
            }
            location[0] = start;
            location[1] = end;
            mazeList.add(maze);
            locations.add(location);
        }
        for (int i = 0; i < mazeList.size(); i++) {
            char[][] maze = mazeList.get(i);
            int[] start = locations.get(i)[0];
            int[] end = locations.get(i)[1];
            boolean res = canGet(maze, start, end);
            if (res) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }

    public static boolean canGet(char[][] maze, int[] start, int[] end) {
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        int[][] dirs={{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
        Queue<int[]> queue = new LinkedList<>();
        queue.add(start);
        visited[start[0]][start[1]] = true;
        while (!queue.isEmpty()) {
            int[] s = queue.remove();
            if (s[0] == end[0] && s[1] == end[1])
                return true;
            for (int[] dir: dirs) {
                int x = s[0] + dir[0];
                int y = s[1] + dir[1];
                while (x >= 0 && y >= 0 && x < maze.length && y < maze[0].length && maze[x][y] == '.') {
                    x += dir[0];
                    y += dir[1];
                }
                if (!visited[x - dir[0]][y - dir[1]]) {
                    queue.add(new int[] {x - dir[0], y - dir[1]});
                    visited[x - dir[0]][y - dir[1]] = true;
                }
            }
        }
        return false;
    }
}
/*
1
7 9
##...####
##.##....
....S####
#.##.#.#.
..#.###..
#...##E.#
###....##

[[1,1,0,0,0,1,1,1,1],
 [1,1,0,1,1,0,0,0,0],
 [0,0,0,0,S,1,1,1,1],
 [1,E,1,1,0,1,0,1,0],
 [0,0,1,0,1,1,1,0,0],
 [0,0,1,0,1,1,1,0,0],
 [0,0,1,0,1,1,1,0,0]]
[2,4]
[3,1]
 */