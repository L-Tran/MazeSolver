/**
 * Solves the given maze using DFS or BFS
 * @author Ms. Namasivayam finished by Logan Tran
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
        // Initialize stack and push start cell
        Stack<MazeCell> explored = new Stack<MazeCell>();
        explored.push(maze.getStartCell());
        // Keep looping until no elements left
        while(!explored.isEmpty()) {
            // pop element to check possible directions
            MazeCell current = explored.pop();
            int row = current.getRow();
            int col = current.getCol();
            // Explore the cells in the order: NORTH, EAST, SOUTH, WEST
            if(maze.isValidCell(row - 1, col)) {
                // Push if exploring
                explored.push(updateCell(row - 1, col, current));
            }
            if(maze.isValidCell(row, col + 1)) {
                // Push if exploring
                explored.push(updateCell(row, col + 1, current));
            }
            if(maze.isValidCell(row + 1, col)) {
                // Push if exploring
                explored.push(updateCell(row + 1, col, current));
            }
            if(maze.isValidCell(row, col - 1)) {
                // Push if exploring
                explored.push(updateCell(row, col - 1, current));
            }
        }
        return getSolution();
    }

    /**
     * Performs a Breadth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeBFS() {
        // Initialize queue
        Queue<MazeCell> explored = new LinkedList<MazeCell>();
        // Add start cell
        explored.add(maze.getStartCell());
        // Keep looping until no elements left
        while(!explored.isEmpty()) {
            // Remove element to check possible directions
            MazeCell current = explored.remove();
            int row = current.getRow();
            int col = current.getCol();
            // Explore the cells in the order: NORTH, EAST, SOUTH, WEST
            if(maze.isValidCell(row - 1, col)) {
                // Push if exploring
                explored.add(updateCell(row - 1, col, current));
            }
            if(maze.isValidCell(row, col + 1)) {
                // Push if exploring
                explored.add(updateCell(row, col + 1, current));
            }
            if(maze.isValidCell(row + 1, col)) {
                // Push if exploring
                explored.add(updateCell(row + 1, col, current));
            }
            if(maze.isValidCell(row, col - 1)) {
                // Push if exploring
                explored.add(updateCell(row, col - 1, current));
            }
        }
        return getSolution();
    }

    // Update cell when explored
    public MazeCell updateCell(int row, int col, MazeCell current) {
        // Create neighbor cell when valid
        MazeCell neighbor = maze.getCell(row, col);
        // Set cell to explored
        neighbor.setExplored(true);
        // Set parent to cell
        neighbor.setParent(current);
        return neighbor;
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
