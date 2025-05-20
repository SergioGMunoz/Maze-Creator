package model;

public class Disposition {
    public Maze maze;
    // 0 Free, 1 Block, 2 Crocodile, 3 MedKit
    public int[][] cells;

    public Disposition(Maze maze, int[][] cells) {
        this.maze = maze;
        this.cells = cells;
    }

    public void printDisposition() {
        System.out.println("Disposición:");
        for (int row = 0; row < maze.numRow; row++) {
            for (int col = 0; col < maze.numCol; col++) {
                System.out.println(cells[col][row]);
            }
            System.out.println();
        }
    }

	public int[][] getCells() {
		return cells;
	}
    
    
    
    
}
