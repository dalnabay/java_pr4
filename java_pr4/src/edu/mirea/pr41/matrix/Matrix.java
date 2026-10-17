package edu.mirea.pr41.matrix;

import java.util.Arrays;

public class Matrix {
    private final double[][] values;
    private final int rowCount;
    private final int columnCount;

    public Matrix(double[][] values) {
        if (values.length == 0 || values[0].length == 0) {
            throw new IllegalArgumentException("Матрица не должна быть пустой");
        }
        rowCount = values.length;
        columnCount = values[0].length;
        this.values = new double[rowCount][columnCount];
        for (int i = 0; i < rowCount; i++) {
            if (values[i].length != columnCount) {
                throw new IllegalArgumentException("Строки должны иметь одинаковую длину");
            }
            this.values[i] = Arrays.copyOf(values[i], columnCount);
        }
    }

    public int getRows() {
        return rowCount;
    }
    public int getColumns() {
        return columnCount;
    }
    public double get(int row, int column) {
        return values[row][column];
    }

    public Matrix add(Matrix other) {
        if (rowCount != other.rowCount || columnCount != other.columnCount) {
            throw new IllegalArgumentException("Размеры матриц должны совпадать");
        }
        double[][] result = new double[rowCount][columnCount];
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                result[i][j] = values[i][j] + other.values[i][j];
            }
        }
        return new Matrix(result);
    }

    public Matrix multiply(double number) {
        double[][] result = new double[rowCount][columnCount];
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                result[i][j] = values[i][j] * number;
            }
        }
        return new Matrix(result);
    }

    public Matrix multiply(Matrix other) {
        if (columnCount != other.rowCount) {
            throw new IllegalArgumentException("Число столбцов первой матрицы должно совпадать с числом строк второй");
        }
        double[][] result = new double[rowCount][other.columnCount];
        for (int i = 0; i < rowCount; i++) {
            for (int k = 0; k < columnCount; k++) {
                double factor = values[i][k];
                for (int j = 0; j < other.columnCount; j++) {
                    result[i][j] += factor * other.values[k][j];
                }
            }
        }
        return new Matrix(result);
    }

    public void print() {
        for (double[] row : values) { System.out.println(Arrays.toString(row)); }
    }
}
