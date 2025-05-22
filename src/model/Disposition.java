package model;

public class Disposition {
    public Maze maze;
    // 0 Free, 1 Block, 2 Crocodile, 3 MedKit
    public int[][] cells;

    public Disposition(Maze maze, int[][] cells) {
        this.maze = maze;
        this.cells = cells;
    }

    public void añadirCocodrilosYMedkits(int numCocodrilos, int numMedkits) {
        int filas = maze.getNumRow();
        int columnas = maze.getNumCol();
        java.util.Random rand = new java.util.Random();

        for (int i = 0; i < numCocodrilos;) {
            int row = rand.nextInt(filas);
            int col = rand.nextInt(columnas);
            if (cells[row][col] == 0 && !(row == 0 && col == 0) && !(row == filas - 1 && col == columnas - 1)) {
                cells[row][col] = 2;
                i++;
            }
        }

        for (int i = 0; i < numMedkits;) {
            int row = rand.nextInt(filas);
            int col = rand.nextInt(columnas);
            if (cells[row][col] == 0 && !(row == 0 && col == 0) && !(row == filas - 1 && col == columnas - 1)) {
                cells[row][col] = 3;
                i++;
            }
        }
    }

    public int[][] getCells() {
        return cells;
    }
}