 class ShortestPath
    {

        ShortestPath(int[][] graph){
            this.graphSize = graph.length;
        }
        // A utility function to find the vertex with minimum distance value,
        // from the set of vertices not yet included in shortest path tree
         int graphSize;


        // Driver method
        public static void main (String[] args) {
            /* Let us create the example graph discussed above */
            int graph[][] = new int[][]
                    {{0, 4, 0, 0, 0, 0, 0, 8, 0},
                    {4, 0, 8, 0, 0, 0, 0, 11, 0},
                    {0, 8, 0, 7, 0, 4, 0, 0, 2},
                    {0, 0, 7, 0, 9, 14, 0, 0, 0},
                    {0, 0, 0, 9, 0, 10, 0, 0, 0},
                    {0, 0, 4, 14, 10, 0, 2, 0, 0},
                    {0, 0, 0, 0, 0, 2, 0, 1, 6},
                    {8, 11, 0, 0, 0, 0, 1, 0, 7},
                    {0, 0, 2, 0, 0, 0, 6, 7, 0}
            };
            ShortestPath t = new ShortestPath(graph);
            t.dijkstra(graph, 0);
        }

        void dijkstra(int graph[][], int src) {
            int dist[] = new int[graphSize]; // The output array. dist[i] will hold
            // the shortest distance from src to i

            // visitedNode[i] will true if vertex i is included in shortest
            // path tree or shortest distance from src to i is finalized
            Boolean visitedNode[] = new Boolean[graphSize];

            // Initialize all distances as INFINITE and stpSet[] as false
            for (int i = 0; i < graphSize; i++) {
                dist[i] = Integer.MAX_VALUE;
                visitedNode[i] = false;
            }

            // Distance of source vertex from itself is always 0
            dist[src] = 0;

            // Find shortest path for all vertices
            for (int count = 0; count < graphSize-1; count++)
            {
                // Pick the minimum distance vertex from the set of vertices
                // not yet processed. minDistance is always equal to src in first
                // iteration.
                int minDistance = minDistance(dist, visitedNode);

                // Mark the picked vertex as processed
                visitedNode[minDistance] = true;

                // Update dist value of the adjacent vertices of the
                // picked vertex.
                for (int v = 0; v < graphSize; v++) {

                    // Update dist[v] only if is not in visitedNode, there is an
                    // edge from minDistance to v, and total weight of path from src to
                    // v through minDistance is smaller than current value of dist[v]
                    if (!visitedNode[v] && graph[minDistance][v] != 0
                        && dist[minDistance] != Integer.MAX_VALUE
                        && dist[minDistance] + graph[minDistance][v] < dist[v]) {

                            dist[v] = dist[minDistance] + graph[minDistance][v];
                    }
                }
            }

            // print the constructed distance array
            printSolution(dist, graphSize);
        }


        int minDistance(int dist[], Boolean visitedNode[])
        {
            // Initialize min value
            int min = Integer.MAX_VALUE, min_index=-1;

            for (int v = 0; v < graphSize; v++)
                if (visitedNode[v] == false && dist[v] <= min)
                {
                    min = dist[v];
                    min_index = v;
                }

            return min_index;
        }

        // A utility function to print the constructed distance array
        void printSolution(int dist[], int n)
        {
            System.out.println("Vertex   Distance from Source");
            for (int i = 0; i < graphSize; i++)
                System.out.println(i+" tt "+dist[i]);
        }
    }

