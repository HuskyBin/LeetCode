/*
Design a Snake game that is played on a device with screen size = width x height. Play the game online if you are not familiar with the game.

The snake is initially positioned at the top left corner (0,0) with length = 1 unit.

You are given a list of food's positions in row-column order. When a snake eats the food, its length and the game's score both increase by 1.

Each food appears one by one on the screen. For example, the second food will not appear until the first food was eaten by the snake.

When a food does appear on the screen, it is guaranteed that it will not appear on a block occupied by the snake.

Example:
Given width = 3, height = 2, and food = [[1,2],[0,1]].

Snake snake = new Snake(width, height, food);

Initially the snake appears at position (0,0) and the food at (1,2).

|S| | |
| | |F|

snake.move("R"); -> Returns 0

| |S| |
| | |F|

snake.move("D"); -> Returns 0

| | | |
| |S|F|

snake.move("R"); -> Returns 1 (Snake eats the first food and right after that, the second food appears at (0,1) )

| |F| |
| |S|S|

snake.move("U"); -> Returns 1

| |F|S|
| | |S|

snake.move("L"); -> Returns 2 (Snake eats the second food)

| |S|S|
| | |S|

snake.move("U"); -> Returns -1 (Game over because snake collides with border)
*/
public class SnakeGame {

    /** Initialize your data structure here.
        @param width - screen width
        @param height - screen height 
        @param food - A list of food positions
        E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the second is at [1,0]. */
    private Set<Integer> isBody;
    private Deque<Integer> bodyQueue = new LinkedList<>();
    private Queue<Integer> food = new LinkedList<>();
    private int score = 0;
    // private int headX = -1;
    // private int headY = -1;
    private int width = -1;
    private int height = -1;
    public SnakeGame(int width, int height, int[][] food) {
        if (width < 0 || height < 0 || food == null) {
            throw new RuntimeException();
        }
        isBody = new HashSet<>();
        isBody.add(0);
        bodyQueue.add(0);
        this.width = width;
        this.height = height;
        for (int i = 0; i < food.length; i++) {
            this.food.add(food[i][0] * width + food[i][1]);
        }
    }
    
    /** Moves the snake.
        @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down 
        @return The game's score after the move. Return -1 if game over. 
        Game over when snake crosses the screen boundary or bites its body. */
    public int move(String direction) {
        int curPoint = bodyQueue.peekLast();
        int nextX = curPoint / width;
        int nextY = curPoint % width;
        if (direction.equals("U")) {
            nextX -= 1;
        }
        else if (direction.equals("L")) {
            nextY -= 1;
        }
        else if (direction.equals("R")) {
            nextY += 1;
        }
        else if (direction.equals("D")) {
            nextX += 1;
        }
        else {
            throw new RuntimeException();
        }
        return moveToNext(nextX, nextY);
    }
    
    
    private int moveToNext(int x, int y) {
        isBody.remove(bodyQueue.peekFirst());
        
        if (x < 0 || x >= this.height || y < 0 || y >= this.width || isBody.contains(x * width + y)) {
            return -1;
        }
        int curFood = -1;
        if(this.food.size() > 0) {
            curFood = food.peek();
        }
        if (curFood == (x * width + y)) {
            this.score += 1;
            this.food.poll();
            isBody.add(bodyQueue.peekFirst());
        }
        else {
             this.bodyQueue.pollFirst();    
        }
        isBody.add(x * width + y);
        bodyQueue.addLast(x * width + y);
        return this.score;
    }
}

/**
 * Your SnakeGame object will be instantiated and called as such:
 * SnakeGame obj = new SnakeGame(width, height, food);
 * int param_1 = obj.move(direction);
 */
