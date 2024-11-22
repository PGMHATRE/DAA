import java.util.Scanner;

class prac3 {
  final static int INF = 9999; // Represents infinity (no path)

  // Implementing Floyd-Warshall algorithm
  void floydWarshall(int graph[][], int nV) {
    int matrix[][] = new int[nV][nV];4
    int i, j, k;

    // Initialize matrix with input graph
    for (i = 0; i < nV; i++) {
      for (j = 0; j < nV; j++) {
        matrix[i][j] = graph[i][j];
      }
    }

    // Adding vertices individually to check for shorter paths
    for (k = 0; k < nV; k++) {
      for (i = 0; i < nV; i++) {
        for (j = 0; j < nV; j++) {
          // If a shorter path is found, update the matrix
          if (matrix[i][k] + matrix[k][j] < matrix[i][j]) {
            matrix[i][j] = matrix[i][k] + matrix[k][j];
          }
        }
      }
    }

    // Print the shortest path matrix
    printMatrix(matrix, nV);

    // Print the minimum costs to connect all offices
    printMinimumCosts(matrix, nV);
  }

  // Print the result matrix for shortest paths
  void printMatrix(int matrix[][], int nV) {
    System.out.println("Shortest distances between every pair of vertices:");
    for (int i = 0; i < nV; ++i) {
      for (int j = 0; j < nV; ++j) {
        if (matrix[i][j] == INF)
          System.out.print("INF ");
        else
          System.out.print(matrix[i][j] + "  ");
      }
      System.out.println();
    }
  }

  // Print the minimum costs to connect all offices
  void printMinimumCosts(int matrix[][], int nV) {
    System.out.println("\nThe minimum costs to connect all offices:");
    for (int i = 0; i < nV; i++) {
      for (int j = 0; j < nV; j++) {
        if (matrix[i][j] != INF && i != j) {
          System.out.println("Cost from Office " + (i+1) + " to Office " + (j+1) + ": " + matrix[i][j]);
        }
      }
    }
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    // Get number of vertices from the user
    System.out.println("Enter the number of offices (vertices): ");
    int nV = sc.nextInt();

    // Create graph matrix and take edge weights as input from user
    int graph[][] = new int[nV][nV];

    System.out.println("Enter the adjacency matrix (enter 9999 for infinity): ");
    for (int i = 0; i < nV; i++) {
      for (int j = 0; j < nV; j++) {
        graph[i][j] = sc.nextInt();
      }
    }

    // Close the scanner after input is taken
    sc.close();

    // Run the Floyd-Warshall algorithm
    prac3 fw = new prac3();
    fw.floydWarshall(graph, nV);
  }
}


/*
 Enter the number of offices (vertices): 
4
Enter the adjacency matrix (enter 9999 for infinity):
0 3 9999 7
8 0 2 9999
5 9999 0 1
2 9999 9999 0
Shortest distances between every pair of vertices:
0  3  5  6  
5  0  2  3
3  6  0  1
2  5  7  0

The minimum costs to connect all offices:
Cost from Office 1 to Office 2: 3
Cost from Office 1 to Office 3: 5
Cost from Office 1 to Office 4: 6
Cost from Office 2 to Office 1: 5
Cost from Office 2 to Office 3: 2
Cost from Office 2 to Office 4: 3
Cost from Office 3 to Office 1: 3
Cost from Office 3 to Office 2: 6
Cost from Office 3 to Office 4: 1
Cost from Office 4 to Office 1: 2
Cost from Office 4 to Office 2: 5
Cost from Office 4 to Office 3: 7
*/