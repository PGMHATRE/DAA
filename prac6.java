import java.util.Scanner;

public class prac6 {
    static int minCost = Integer.MAX_VALUE; // Global variable to store minimum cost
    static int[] bestAssignment; // Array to store the best assignment

    // Function to calculate the minimum cost and optimal assignment using Branch and Bound
    static void branchAndBound(int[][] costMatrix, boolean[] assigned, int[] assignment, int level, int currentCost) {
        int n = costMatrix.length;

        // Base case: All students are assigned
        if (level == n) {
            if (currentCost < minCost) {
                minCost = currentCost;
                System.arraycopy(assignment, 0, bestAssignment, 0, n); // Update best assignment
            }
            return;
        }

//assignment: The array that currently holds the assignment of clubs to students.
//0: Start copying from the beginning of the assignment array.
//bestAssignment: The array where the current best assignment will be stored.
//0: Start filling bestAssignment from the beginning.
//n: Copy n elements (length of the arrays).

        // Explore each club for the current student
        for (int club = 0; club < n; club++) {
            if (!assigned[club]) { // If the club is not already assigned
                assigned[club] = true; // Mark this club as assigned
                assignment[level] = club; // Assign the club to the current student

                // Recur to assign the next student
                branchAndBound(costMatrix, assigned, assignment, level + 1, currentCost + costMatrix[level][club]);

                // Backtrack
                assigned[club] = false; // Unmark the club
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input the size of the cost matrix
        System.out.print("Enter the number of students/clubs: ");
        int n = scanner.nextInt();

        // Input the cost matrix
        int[][] costMatrix = new int[n][n];
        System.out.println("Enter the cost matrix:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                costMatrix[i][j] = scanner.nextInt();
            }
        }

        // Initialize variables
        boolean[] assigned = new boolean[n]; // To keep track of assigned clubs
        int[] assignment = new int[n]; // To store the current assignment
        bestAssignment = new int[n]; // To store the best assignment

        // Solve the problem using Branch and Bound
        branchAndBound(costMatrix, assigned, assignment, 0, 0);

        // Output the results
        System.out.println("Minimum Cost: " + minCost);
        System.out.println("Optimal Assignment:");
        for (int i = 0; i < n; i++) {
            System.out.println("Student " + (i + 1) + " -> Club " + (bestAssignment[i] + 1));
        }
    }
}



/*
 Enter the number of students/clubs: 3
Enter the cost matrix:
9 8 7  
4 5 6
3 2 1
Minimum Cost: 13
Optimal Assignment:
Student 1 -> Club 2
Student 2 -> Club 1
Student 3 -> Club 3
*/