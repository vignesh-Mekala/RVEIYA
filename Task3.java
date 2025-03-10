public class Task3 {
        public static int sumMatrix(int[][] matrix) {
            int sum = 0;
            for (int[] row : matrix) {
                for (int num : row) {
                    sum += num;
                }
            }
            return sum;
        }
    
        public static void main(String[] args) {
            int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
            };
            System.out.println("Sum of Matrix Elements: " + sumMatrix(matrix));
        }
    }
    


