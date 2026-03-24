package co.edu.eci.mathservice.controller;

public class Fibonacci {
    public static long calcularFibonacci(int n) {
        if (n == 0) return 2;
        if (n == 1) return 1;

        long a = 2;
        long b = 1;
        long resultado = 0;
        System.out.println(a);
        System.out.println(b);

        for (int i = 2; i <= n; i++) {
            resultado = a + b;
            a = b;
            b = resultado;
            System.out.println(resultado);
        }
        return resultado;
    }
}

