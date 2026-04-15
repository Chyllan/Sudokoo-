import java.util.Arrays;

public class Sudoku {

    public static void main(String[] args) {
        simulatePuzzle(printPuzzle());
    }

    public static void simulatePuzzle(int[][] puz) {
        int line = 0;
        for (int i = 0; i < 3; i++) {
            System.out.println("+----------+----------+");
            for (int j = 0; j < 3; j++) {
                System.out.print("|");
                for (int n : puz[i * 3 + j]) {
                    if (line == 3) {
                        System.out.print("|");
                        line = 0;
                    }
                    System.out.print(n + " ");
                    line++;
                }
                System.out.println("|");
                line = 0;
            }
        }
        System.out.println("+----------+----------+");
    }

    public static int[][] printPuzzle() {
        int[][] arr = randArr();
        while (!isValid(arr)) {
            arr = randArr();
        }
        return arr;
    }

    public static void scramble(int[] arr) {
        int[] temp = arr.clone();
        int tempSize = arr.length;
        int idx = 0;
        while (tempSize > 0) {
            for (int j = 0; j < 3; j++) {
                int randInd = (int) (Math.random() * (3 - j));
                arr[idx++] = temp[randInd];
                for (int k = randInd; k < tempSize - 1; k++) {
                    temp[k] = temp[k + 1];
                }
                tempSize--;
            }
        }
    }

    public static int[][] randArr() {
        int[][] rand = new int[9][9];
        int[] model = {1, 2, 3, 4, 5, 6, 7, 8, 9};

        int randStart = (int) (Math.random() * 9);

        for (int i = 0; i < randStart; i++) {
            rotateLeft(model);
        }
        for (int i = 0; i < 9; i++) {
            rand[i] = model.clone();
            rotateLeft(model);
            rotateLeft(model);
            rotateLeft(model);
            if (i % 3 == 2) rotateLeft(model);
        }
        scrambleRows(rand);
        scrambleCols(rand);

        return rand;
    }

    public static void rotateLeft(int[] arr) {
        int first = arr[0];
        for (int i = 0; i < arr.length - 1; i++) {
            arr[i] = arr[i + 1];
        }
        arr[arr.length - 1] = first;
    }

    public static void scrambleRows(int[][] arr) {
        int[][] temp = arr.clone();
        int tempSize = arr.length;
        int[][] result = new int[arr.length][];
        int idx = 0;
        while (tempSize > 0) {
            for (int j = 0; j < 3; j++) {
                int randInd = (int) (Math.random() * (3 - j));
                result[idx++] = temp[randInd];
                for (int k = randInd; k < tempSize - 1; k++) {
                    temp[k] = temp[k + 1];
                }
                tempSize--;
            }
        }
        System.arraycopy(result, 0, arr, 0, arr.length);
    }

    public static void scrambleCols(int[][] arr) {
        int[][] tilted = new int[arr[0].length][arr.length];
        for (int i = 0; i < arr[0].length; i++) {
            for (int j = 0; j < arr.length; j++) {
                tilted[i][j] = arr[j][i];
            }
        }
        scrambleRows(tilted);
        for (int i = 0; i < arr[0].length; i++) {
            for (int j = 0; j < arr.length; j++) {
                arr[j][i] = tilted[i][j];
            }
        }
    }

    public static boolean isValidGroup(int[] arr) {
        int[] temp = arr.clone();
        Arrays.sort(temp);
        int[] model = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        return Arrays.equals(temp, model);
    }

    public static boolean isValid(int[][] puz) {
        for (int[] row : puz) {
            if (!isValidGroup(row)) return false;
        }

        for (int i = 0; i < puz[0].length; i++) {
            int[] col = new int[puz.length];
            for (int j = 0; j < puz.length; j++) {
                col[j] = puz[j][i];
            }
            if (!isValidGroup(col)) return false;
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int[] square = {
                    puz[0 + i * 3][0 + j * 3], puz[0 + i * 3][1 + j * 3], puz[0 + i * 3][2 + j * 3],
                    puz[1 + i * 3][0 + j * 3], puz[1 + i * 3][1 + j * 3], puz[1 + i * 3][2 + j * 3],
                    puz[2 + i * 3][0 + j * 3], puz[2 + i * 3][1 + j * 3], puz[2 + i * 3][2 + j * 3]
                };
                if (!isValidGroup(square)) return false;
            }
        }
        return true;
    }
}
