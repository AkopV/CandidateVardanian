package travelsalesproblem;

import java.util.Arrays;

public class TravelSalesProblem {
    //an array that contains the value of the distance
    private int[][] cost;
    /*@param peak is a number of peak in the graph*/
    TravelSalesProblem(int peak) {
        cost = new int[peak][peak];
    }
    // set the weight between of neighbours
    public void setCost(int i, int j, int weight) {
        cost[i][j] = weight;
    }
    /**
     * @param i peak graph
     * @param j peak graph
     * @return 0 if i and j equal
     * @return 10001 if no connection between the top of the peaks or weight
     * @return cost[i][j]*/
    public int getCost(int i, int j) {
        if (i == j){
            return 0;
        }
        if (cost[i][j] == 0) {
            return 10001;
        }
        return cost[i][j];
    }
    /**
     * @param result array of the distance elements
     * @param visited array that check count elements, which visited
     * @return the shortest distance element, ignoring those in visited*/
    public int cheapest(Integer[] result, boolean[] visited) {
        int shortDistance = -1;
        for (int i = 0; i < cost.length; i++) {
            if (!visited[i] && ((shortDistance < 0) || (result[i] < result[shortDistance]))) {
                shortDistance = i;
            }
        }
        return shortDistance;
    }
    /**
     *@param inputPeak
     *@return array of the shortest distance from one peak to another*/
    public Integer[] distanceFrom(int inputPeak){
        Integer[] result = new Integer[cost.length];
        // set tops mark
        Arrays.fill(result, 10001);
        // assign the first top mark equal to inputPeak
        result[inputPeak] = inputPeak;
        // visit the city
        boolean[] visited = new boolean[cost.length];
        for (int i = 0; i < cost.length; i++) {
            // selected the top with minimum mark
            int city = cheapest(result, visited);
            // note selected peak visited
            visited[city] = true;
            for (int j = 0; j < cost.length; j++) {
                // the shortest distance from one peak to another
                result[j] = Math.min(result[j], result[city] + getCost(city, j));
            }
        }
        return result;
    }
}
