/**
 * Solves the given maze using DFS or BFS
 * @author Ms. Namasivayam
 * @version 03/10/2023
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class MazeSolver {
    private Maze maze;

    public MazeSolver() {
        this.maze = null;
    }

    public MazeSolver(Maze maze) {
        this.maze = maze;
    }

    public void setMaze(Maze maze) {
        this.maze = maze;
    }

    /**
     * Starting from the end cell, backtracks through
     * the parents to determine the solution
     * @return An arraylist of MazeCells to visit in order
     */
    public ArrayList<MazeCell> getSolution() {
        // Initialize stack
        Stack<MazeCell> st = new Stack<MazeCell>();
        // Start from back and push parents until beginning
        MazeCell cell = maze.getEndCell();
        while(cell != null) {
            st.push(cell);
            cell = cell.getParent();
        }
        // Pop all cells into arraylist
        ArrayList<MazeCell> solution = new ArrayList<MazeCell>();
        while(!st.isEmpty()){
            solution.add(st.pop());
        }
        return solution;
    }

    /**
     * Performs a Depth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeDFS() {
        // TODO: Use DFS to solve the maze
        // Explore the cells in the order: NORTH, EAST, SOUTH, WEST
        Stack<MazeCell> st = new Stack<MazeCell>();
        ArrayList<MazeCell> solution = new ArrayList<MazeCell>();
        st.push(maze.getStartCell());
        while(st.peek() != maze.getEndCell()) {
            MazeCell current = st.peek();
            int row = current.getRow();
            int col = current.getCol();
            if(maze.isValidCell(row - 1, col)) {
                MazeCell neighbor = maze.getCell(row - 1, col);
                neighbor.setExplored(true);
                neighbor.setParent(current);
                st.push(neighbor);
            }
            if(maze.isValidCell(row, col + 1)) {
                MazeCell neighbor = maze.getCell(row, col + 1);
                neighbor.setExplored(true);
                neighbor.setParent(current);
                st.push(neighbor);
            }
            if(maze.isValidCell(row + 1, col)) {
                MazeCell neighbor = maze.getCell(row + 1, col);
                neighbor.setExplored(true);
                neighbor.setParent(current);
                st.push(neighbor);
            }
            if(maze.isValidCell(row, col - 1)) {
                MazeCell neighbor = maze.getCell(row, col - 1);
                neighbor.setExplored(true);
                neighbor.setParent(current);
                st.push(neighbor);
            }
        }
        return getSolution();
    }

    /**
     * Performs a Breadth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeBFS() {
        // TODO: Use BFS to solve the maze
        // Explore the cells in the order: NORTH, EAST, SOUTH, WEST
        Queue<MazeCell> q = new LinkedList<MazeCell>();
        ArrayList<MazeCell> solution = new ArrayList<MazeCell>();
        q.add(maze.getStartCell());
        while(!q.isEmpty()) {
            MazeCell current = q.peek();
            int row = current.getRow();
            int col = current.getCol();
            if(maze.isValidCell(row - 1, col)) {
                MazeCell neighbor = maze.getCell(row - 1, col);
                neighbor.setExplored(true);
                neighbor.setParent(current);
                q.add(neighbor);
            }
            if(maze.isValidCell(row, col + 1)) {
                MazeCell neighbor = maze.getCell(row, col + 1);
                neighbor.setExplored(true);
                neighbor.setParent(current);
                q.add(neighbor);
            }
            if(maze.isValidCell(row + 1, col)) {
                MazeCell neighbor = maze.getCell(row + 1, col);
                neighbor.setExplored(true);
                neighbor.setParent(current);
                q.add(neighbor);
            }
            if(maze.isValidCell(row, col - 1)) {
                MazeCell neighbor = maze.getCell(row, col - 1);
                neighbor.setExplored(true);
                neighbor.setParent(current);
                q.add(neighbor);
            }
        }
        return getSolution();
    }

    public static void main(String[] args) {
        // Create the Maze to be solved
        Maze maze = new Maze("Resources/maze3.txt");

        // Create the MazeSolver object and give it the maze
        MazeSolver ms = new MazeSolver();
        ms.setMaze(maze);

        // Solve the maze using DFS and print the solution
        ArrayList<MazeCell> sol = ms.solveMazeDFS();
        maze.printSolution(sol);

        // Reset the maze
        maze.reset();

        // Solve the maze using BFS and print the solution
        sol = ms.solveMazeBFS();
        maze.printSolution(sol);
    }
}
