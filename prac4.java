import java.util.Scanner;

public class prac4
 {
    static int min_dist(int dist[], boolean visited[]) {
        int minimum = Integer.MAX_VALUE;
        int ind = -1;
        for (int k = 0; k < dist.length; k++) {
            if (!visited[k] && dist[k] <= minimum) {
                minimum = dist[k];
                ind = k;
            }
        }
        return ind;
    }

    static void printPath(int parent[], int j) {
        if (j == -1) return;
        printPath(parent, parent[j]);
        System.out.print((j + 1) + " "); // Convert to 1-based indexing
    }

    static void greedy_dijkstra(int graph[][], int src) {
        int numVertices = graph.length;
        int dist[] = new int[numVertices];
        boolean visited[] = new boolean[numVertices];
        int parent[] = new int[numVertices]; // To store the shortest path tree

        // Initialize all distances as infinity, visited[] as false, and parent[] as -1
        for (int k = 0; k < numVertices; k++) {
            dist[k] = Integer.MAX_VALUE;
            visited[k] = false;
            parent[k] = -1;
        }

        // Distance to source itself is always 0
        dist[src] = 0;

        // Find shortest path for all vertices
        for (int k = 0; k < numVertices - 1; k++) {
            int m = min_dist(dist, visited);
            visited[m] = true;

            for (int j = 0; j < numVertices; j++) {
                if (!visited[j] && graph[m][j] != 0 && dist[m] != Integer.MAX_VALUE
                        && dist[m] + graph[m][j] < dist[j]) {
                    dist[j] = dist[m] + graph[m][j];
                    parent[j] = m; // Update parent of the current node
                }
            }
        }

        // Print the result
        System.out.println("Vertex\tDistance\tPath");
        for (int k = 0; k < numVertices; k++) {
            System.out.print((k + 1) + "\t\t" + dist[k] + "\t\t");
            printPath(parent, k);
            System.out.println();
        }

        // Total tour cost
        int totalCost = 0;
        for (int d : dist) {
            if (d != Integer.MAX_VALUE) {
                totalCost += d;
            }
        }
        System.out.println("Total Tour Cost: " + totalCost);
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of vertices: ");
        int numVertices = sc.nextInt();

        int[][] graph = new int[numVertices][numVertices];

        System.out.println("Enter the adjacency matrix:");
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                graph[i][j] = sc.nextInt();
            }
        }

        // Take source vertex input from the user (convert to 0-based indexing internally)
        System.out.print("Enter the source vertex (1 to " + numVertices + "): ");
        int src = sc.nextInt() - 1;

        greedy_dijkstra(graph, src);

        sc.close();
    }
}



/*
Enter the number of vertices: 6
Enter the adjacency matrix:
0 7 9 0 0 14
7 0 10 0 0 0
9 10 0 11 0 2
0 0 11 0 6 0
0 0 0 6 0 9
14 0 2 0 9 0
Enter the source vertex (1 to 6): 1
Vertex	Distance	Path
1		0		1 
2		7		1 2 
3		9		1 3 
4		20		1 3 4 
5		20		1 3 6 5 
6		11		1 3 6 
Total Tour Cost: 67
*/
