package com.vardanian.travelsalesproblem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TravelSalesProblem {

    private final int WEIGHT_PEAKS = 10001;
    //an array that contains the value of the distance
    private int[][] cost;

    TravelSalesProblem(){}

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
            return WEIGHT_PEAKS;
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
        Arrays.fill(result, WEIGHT_PEAKS);
        result[inputPeak] = inputPeak;
        // visit the city
        boolean[] visited = new boolean[cost.length];
        for (int i = 0; i < cost.length; i++) {
            // selected the top with minimum mark
            int city = cheapest(result, visited);
            visited[city] = true;
            for (int j = 0; j < cost.length; j++) {
                // the shortest distance from one peak to another
                result[j] = Math.min(result[j], result[city] + getCost(city, j));
            }
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
       TravelSalesProblem travelSalesProblem = new TravelSalesProblem();
        ClassLoader classLoader = travelSalesProblem.getClass().getClassLoader();
        File file = new File(travelSalesProblem.getClass().getClassLoader().getResource("file/inputText.txt").getFile());
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String input = reader.readLine();
            int source = Integer.parseInt(input);
            for (int testIndex = 0; testIndex < source; testIndex++) {
                String[] numberCities = new String[10000];
                input = reader.readLine();
                int countCities = Integer.parseInt(input);
                int tspSize = countCities + 1;
                TravelSalesProblem travelSalesProblem2 = new TravelSalesProblem(tspSize);
                for (int cityIndex = 0; cityIndex < countCities; cityIndex++) {
                    // reads name cities
                    input = reader.readLine();
                    String cityName = input;
                    //write cities name
                    numberCities[cityIndex] = cityName;
                    //reads the number of neighbours
                    input = reader.readLine();

                    int numberNeighbours = Integer.parseInt(input);
                    for (int neighborIndex = 0; neighborIndex < numberNeighbours; neighborIndex++) {
                        input = reader.readLine();
                        String[] splitInputText = input.split(" ");
                        // code neighbour
                        int cityNeighbor = Integer.parseInt(splitInputText[0]);
                        //weight neighbour of cost
                        int weightOfNeighbor = Integer.parseInt(splitInputText[1]);
                        travelSalesProblem2.setCost(cityIndex, cityNeighbor, weightOfNeighbor);
                    }
                }
                input = reader.readLine();
                // route number
                int route = Integer.parseInt(input);
                for (int routeIndex = 0; routeIndex < route; routeIndex++) {
                    input = reader.readLine();
                    String[] cityNames = input.split(" ");
                    String start = cityNames[0];
                    String destination = cityNames[1];
                    List<String> list = new ArrayList<String>();
                    for (String numberTest : numberCities) {
                        if (numberTest != null) {
                            list.add(numberTest);
                        }
                    }
                    numberCities = list.toArray(new String[list.size()]);
                    int startIndex = 0;
                    int destinationIndex = 0;
                    for (int i = 0; i < numberCities.length; i++) {
                        // find index of initial city
                        if (start.equals(numberCities[i])) {
                            startIndex = i;
                            break;
                        }
                    }
                    for (int i = 0; i < numberCities.length; i++) {
                        if (destination.equals(numberCities[i])) {
                            destinationIndex = i;
                            break;
                        }
                    }
                    Integer[] distanceSource = travelSalesProblem2.distanceFrom(startIndex);
                    int destinationDistance = distanceSource[destinationIndex];
                    System.out.println("Destination distance: " + destinationDistance);
                }
            }
        }
    }
}
