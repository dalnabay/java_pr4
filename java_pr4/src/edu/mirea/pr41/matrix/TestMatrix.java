package edu.mirea.pr41.matrix;

public class TestMatrix {
    public static void main(String[] args) {
        Matrix a = new Matrix(new double[][]{{2, 1}, {0, 3}});
        Matrix b = new Matrix(new double[][]{{4, 2}, {1, 5}});
        System.out.println("Сумма:");
        a.add(b).print();
        System.out.println("Умножение на 2:");
        a.multiply(2).print();
        System.out.println("Произведение:");
        a.multiply(b).print();
    }
}
