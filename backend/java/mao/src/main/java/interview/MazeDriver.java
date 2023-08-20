package interview;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class MazeDriver {
    public static void main(String[] args) throws Exception {
        File maze1 = new File("src/resources/maze1.txt");
        //File maze2 = new File("src/resources/maze2.txt");

        execute(maze1);
        //execute(maze2);
    }

    private static void execute(File file) throws Exception {
        Maze maze = new Maze(file);
        //dfs(maze);
        bfs(maze);
    }

    private static void bfs(Maze maze) {
    	BFSMazeSolver bfs = new BFSMazeSolver();
        List<Coordinate> path = bfs.solve(maze);
        maze.printPath(path);
        maze.reset();
    }

//    private static void dfs(Maze maze) {
//        DFSMazeSolver dfs = new DFSMazeSolver();
//        List<Coordinate> path = dfs.solve(maze);
//        maze.printPath(path);
//        maze.reset();
//    }
}
class BFSMazeSolver {
	private static final int[][] DIRECTIONS = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

    private static void execute(File file) throws Exception {
        Maze maze = new Maze(file);
        //dfs(maze);
        bfs(maze);
    }

    private static void bfs(Maze maze) {
    	BFSMazeSolver bfs = new BFSMazeSolver();
        List<Coordinate> path = bfs.solve(maze);
        maze.printPath(path);
        maze.reset();
    }

//    private static void dfs(Maze maze) {
//        DFSMazeSolver dfs = new DFSMazeSolver();
//        List<Coordinate> path = dfs.solve(maze);
//        maze.printPath(path);
//        maze.reset();
//    }
	
    public List<Coordinate> solve(Maze maze) {
        Queue<Coordinate> nextToVisit = new LinkedList<>();
        Coordinate start = maze.getEntry();
        nextToVisit.add(start);

        while (!nextToVisit.isEmpty()) {
            Coordinate cur = nextToVisit.remove();

            if (!maze.isValidLocation(cur.getX(), cur.getY()) || maze.isExplored(cur.getX(), cur.getY())) {
                continue;
            }

            if (maze.isWall(cur.getX(), cur.getY())) {
                maze.setVisited(cur.getX(), cur.getY(), true);
                continue;
            }

            if (maze.isExit(cur.getX(), cur.getY())) {
                return backtrackPath(cur);
            }

            for (int[] direction : DIRECTIONS) {
                Coordinate coordinate = new Coordinate(cur.getX() + direction[0], cur.getY() + direction[1], cur);
                nextToVisit.add(coordinate);
                maze.setVisited(cur.getX(), cur.getY(), true);
            }
        }
        return Collections.emptyList();
    }

    private List<Coordinate> backtrackPath(Coordinate cur) {
        List<Coordinate> path = new ArrayList<>();
        Coordinate iter = cur;

        while (iter != null) {
            path.add(iter);
            iter = iter.parent;
        }

        return path;
    }
}

class Coordinate {
    int x;
    int y;
    Coordinate parent;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
        this.parent = null;
    }

    public Coordinate(int x, int y, Coordinate parent) {
        this.x = x;
        this.y = y;
        this.parent = parent;
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    Coordinate getParent() {
        return parent;
    }
}
class Maze {
    private static final int ROAD = 0;
    private static final int WALL = 1;
    private static final int START = 2;
    private static final int EXIT = 3;
    private static final int PATH = 4;

    private int[][] maze;
    private boolean[][] visited;
    private Coordinate start;
    private Coordinate end;

    public Maze(File maze) throws FileNotFoundException {
        String fileText = "";
        try (Scanner input = new Scanner(maze)) {
            while (input.hasNextLine()) {
                fileText += input.nextLine() + "\n";
            }
        }
        initializeMaze(fileText);
    }

    private void initializeMaze(String text) {
        if (text == null || (text = text.trim()).length() == 0) {
            throw new IllegalArgumentException("empty lines data");
        }

        String[] lines = text.split("[\r]?\n");
        maze = new int[lines.length][lines[0].length()];
        visited = new boolean[lines.length][lines[0].length()];

        for (int row = 0; row < getHeight(); row++) {
            if (lines[row].length() != getWidth()) {
                throw new IllegalArgumentException("line " + (row + 1) + " wrong length (was " + lines[row].length() + " but should be " + getWidth() + ")");
            }

            for (int col = 0; col < getWidth(); col++) {
                if (lines[row].charAt(col) == '#')
                    maze[row][col] = WALL;
                else if (lines[row].charAt(col) == 'S') {
                    maze[row][col] = START;
                    start = new Coordinate(row, col);
                } else if (lines[row].charAt(col) == 'E') {
                    maze[row][col] = EXIT;
                    end = new Coordinate(row, col);
                } else
                    maze[row][col] = ROAD;
            }
        }
    }

    public int getHeight() {
        return maze.length;
    }

    public int getWidth() {
        return maze[0].length;
    }

    public Coordinate getEntry() {
        return start;
    }

    public Coordinate getExit() {
        return end;
    }

    public boolean isExit(int x, int y) {
        return x == end.getX() && y == end.getY();
    }

    public boolean isStart(int x, int y) {
        return x == start.getX() && y == start.getY();
    }

    public boolean isExplored(int row, int col) {
        return visited[row][col];
    }

    public boolean isWall(int row, int col) {
        return maze[row][col] == WALL;
    }

    public void setVisited(int row, int col, boolean value) {
        visited[row][col] = value;
    }

    public boolean isValidLocation(int row, int col) {
        if (row < 0 || row >= getHeight() || col < 0 || col >= getWidth()) {
            return false;
        }
        return true;
    }

    public void printPath(List<Coordinate> path) {
        int[][] tempMaze = Arrays.stream(maze)
            .map(int[]::clone)
            .toArray(int[][]::new);
        for (Coordinate coordinate : path) {
            if (isStart(coordinate.getX(), coordinate.getY()) || isExit(coordinate.getX(), coordinate.getY())) {
                continue;
            }
            tempMaze[coordinate.getX()][coordinate.getY()] = PATH;
        }
        System.out.println(toString(tempMaze));
    }

    public String toString(int[][] maze) {
        StringBuilder result = new StringBuilder(getWidth() * (getHeight() + 1));
        for (int row = 0; row < getHeight(); row++) {
            for (int col = 0; col < getWidth(); col++) {
                if (maze[row][col] == ROAD) {
                    result.append(' ');
                } else if (maze[row][col] == WALL) {
                    result.append('#');
                } else if (maze[row][col] == START) {
                    result.append('S');
                } else if (maze[row][col] == EXIT) {
                    result.append('E');
                } else {
                    result.append('.');
                }
            }
            result.append('\n');
        }
        return result.toString();
    }

    public void reset() {
        for (int i = 0; i < visited.length; i++)
            Arrays.fill(visited[i], false);
    }
}
