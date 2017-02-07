/*
There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up (u), down (d), left (l) or right (r), but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction. There is also a hole in this maze. The ball will drop into the hole if it rolls on to the hole.

Given the ball position, the hole position and the maze, find out how the ball could drop into the hole by moving the shortest distance. The distance is defined by the number of empty spaces traveled by the ball from the start position (excluded) to the hole (included). Output the moving directions by using 'u', 'd', 'l' and 'r'. Since there could be several different shortest ways, you should output the lexicographically smallest way. If the ball cannot reach the hole, output "impossible".

The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the borders of the maze are all walls. The ball and the hole coordinates are represented by row and column indexes.

Example 1

Input 1: a maze represented by a 2D array

0 0 0 0 0
1 1 0 0 1
0 0 0 0 0
0 1 0 0 1
0 1 0 0 0

Input 2: ball coordinate (rowBall, colBall) = (4, 3)
Input 3: hole coordinate (rowHole, colHole) = (0, 1)

Output: "lul"
Explanation: There are two shortest ways for the ball to drop into the hole.
The first way is left -> up -> left, represented by "lul".
The second way is up -> left, represented by 'ul'.
Both ways have shortest distance 6, but the first way is lexicographically smaller because 'l' < 'u'. So the output is "lul".

Example 2

Input 1: a maze represented by a 2D array

0 0 0 0 0
1 1 0 0 1
0 0 0 0 0
0 1 0 0 1
0 1 0 0 0

Input 2: ball coordinate (rowBall, colBall) = (4, 3)
Input 3: hole coordinate (rowHole, colHole) = (3, 0)
Output: "impossible"
Explanation: The ball cannot reach the hole.

Note:
There is only one ball and one hole in the maze.
Both the ball and hole exist on an empty space, and they will not be at the same position initially.
The given maze does not contain border (like the red rectangle in the example pictures), but you could assume the border of the maze are all walls.
The maze contains at least 2 empty spaces, and the width and the height of the maze won't exceed 30.
*/
class Point implements Comparable<Point>{
	public int x;
	public int y;
	public int len;
	public String s;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
		this.len = Integer.MAX_VALUE;
		s = "";
	}

	public Point(int x, int y, int len, String s) {
		this.x = x;
		this.y = y;
		this.len = len;
		this.s = s;
	}

	public int compareTo(Point p) {
		return this.len == p.len ? this.s.compareTo(p.s) : this.len - p.len;
	}
}

public class Solution {

    public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
        if (maze == null || ball == null || hole == null || maze.length == 0) {
        	return null;
        }
        Point[][] points = new Point[maze.length][maze[0].length];
        for (int i = 0; i < maze.length; i++) {
        	for (int j = 0; j < maze[0].length; j++) {
        		points[i][j] = new Point(i, j);
        	}
        }
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(ball[0], ball[1], 0, ""));
        int[][] dir = {{1, 0}, {0, 1}, {0, -1}, {-1, 0}};
        char[] ds = {'d', 'r', 'l', 'u'};
        while (!queue.isEmpty()) {
        	Point p = queue.poll();
        	if (points[p.x][p.y].compareTo(p) <= 0) continue;
        	points[p.x][p.y] = p;
        	for (int i = 0; i < dir.length; i++) {
        		int xx = p.x;
        		int yy = p.y;
        		int l = p.len;
        		while (xx + dir[i][0] >= 0 && xx + dir[i][0] < maze.length && yy + dir[i][1] >= 0 && yy + dir[i][1] < maze[0].length && maze[xx + dir[i][0]][yy + dir[i][1]] == 0 && (hole[0] != xx || hole[1] != yy)) {
        			xx += dir[i][0];
        			yy += dir[i][1];
        			l++;
        		}
        		queue.add(new Point(xx, yy, l, p.s + ds[i]));
        		
        	}
        }
        return points[hole[0]][hole[1]].len == Integer.MAX_VALUE ? "impossible" : points[hole[0]][hole[1]].s;
    }
}
