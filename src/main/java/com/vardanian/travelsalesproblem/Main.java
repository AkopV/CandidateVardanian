package com.vardanian.travelsalesproblem;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String fileName = "C:\\Users\\Vardanian\\IdeaProjects\\CandidateVardanian\\src\\main\\resources\\inputText.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String input = reader.readLine();
            int source = Integer.parseInt(input);
            for (int testIndex = 0; testIndex < source; testIndex++) {
                String[] numberCities = new String[10000];
                input = reader.readLine();
                int countCities = Integer.parseInt(input);
                int tspSize = countCities + 1;
                TravelSalesProblem travelSalesProblem = new TravelSalesProblem(tspSize);
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
                        travelSalesProblem.setCost(cityIndex, cityNeighbor, weightOfNeighbor);
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
                    Integer[] distanceSource = travelSalesProblem.distanceFrom(startIndex);
                    int destinationDistance = distanceSource[destinationIndex];
                    System.out.println("Destination distance: " + destinationDistance);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found");
        } catch (IOException e) {
            System.err.println("Stream not closed");
        }
    }
}
