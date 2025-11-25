package org.example;

import org.springframework.stereotype.Service; // <--- ESTO ES NUEVO

@Service // <--- ESTA ETIQUETA ES LA MAGIA
public class MutantDetector {

    private static final int SECUENCIA_MUTANTE = 4;

    public boolean esMutante(String[] adn) {
        if (adn == null || adn.length == 0) return false;

        int n = adn.length;
        int contadorSecuencias = 0;
        char[][] matriz = new char[n][n];

        for (int i = 0; i < n; i++) {
            if (adn[i].length() != n) return false;
            matriz[i] = adn[i].toCharArray();
        }

        for (int fila = 0; fila < n; fila++) {
            for (int col = 0; col < n; col++) {

                if (col <= n - SECUENCIA_MUTANTE) {
                    if (verificarHorizontal(matriz, fila, col)) {
                        contadorSecuencias++;
                        if (contadorSecuencias > 1) return true;
                    }
                }

                if (fila <= n - SECUENCIA_MUTANTE) {
                    if (verificarVertical(matriz, fila, col)) {
                        contadorSecuencias++;
                        if (contadorSecuencias > 1) return true;
                    }
                }

                if (fila <= n - SECUENCIA_MUTANTE && col <= n - SECUENCIA_MUTANTE) {
                    if (verificarDiagonal(matriz, fila, col)) {
                        contadorSecuencias++;
                        if (contadorSecuencias > 1) return true;
                    }
                }

                if (fila <= n - SECUENCIA_MUTANTE && col >= SECUENCIA_MUTANTE - 1) {
                    if (verificarDiagonalInversa(matriz, fila, col)) {
                        contadorSecuencias++;
                        if (contadorSecuencias > 1) return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean verificarHorizontal(char[][] m, int f, int c) {
        char letra = m[f][c];
        return letra == m[f][c+1] && letra == m[f][c+2] && letra == m[f][c+3];
    }

    private boolean verificarVertical(char[][] m, int f, int c) {
        char letra = m[f][c];
        return letra == m[f+1][c] && letra == m[f+2][c] && letra == m[f+3][c];
    }

    private boolean verificarDiagonal(char[][] m, int f, int c) {
        char letra = m[f][c];
        return letra == m[f+1][c+1] && letra == m[f+2][c+2] && letra == m[f+3][c+3];
    }

    private boolean verificarDiagonalInversa(char[][] m, int f, int c) {
        char letra = m[f][c];
        return letra == m[f+1][c-1] && letra == m[f+2][c-2] && letra == m[f+3][c-3];
    }
}