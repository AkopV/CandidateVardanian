package vardanian.travelingSalesmanProblem;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("C:\\Users\\Vardanian\\IdeaProjects\\CandidateVardanian\\src\\main\\java\\com\\vardanian\\travelingSalesmanProblem\\input.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String line = reader.readLine();
        int source = Integer.parseInt(line);
        for (int i = 0; i < source; i++)

        {// the number of tests
            String[] citiesIds = new String[10000];// create an array of the condition of the job
            line = reader.readLine();
            int CountCities = Integer.parseInt(line);// read the number of cities
            int matrixSize = CountCities + 1; // The problem of indexing the array from 0
            TravelingSalesmanProblem salesmanProblem = new TravelingSalesmanProblem(matrixSize);
            for (int CityIndex = 0; CityIndex < CountCities; CityIndex++) {
                line = reader.readLine();// reads the name of the city
                String CityName = line;
                citiesIds[CityIndex] = CityName;// write the name of the city
                line = reader.readLine();// read the next value (the number of neighbors)
                int p = Integer.parseInt(line);
                for (int neighborIndex = 0; neighborIndex < p; neighborIndex++) {
                    line = reader.readLine();
                    String[] brokenLine = line.split(" ");
                    int cityToConnect = Integer.parseInt(brokenLine[0]);// Write the code neighbor
                    int weightOfConnection = Integer.parseInt(brokenLine[1]);// write the weight of the edge
                    salesmanProblem.setEdge(CityIndex, cityToConnect, weightOfConnection);
                }
            }
            line = reader.readLine();
            int routesToFind = Integer.parseInt(line);// number of calculated routes
            for (int routesIndex = 0; routesIndex < routesToFind; routesIndex++) {
                line = reader.readLine();// reads the path
                String[] cityNames = line.split(" ");
                String start = cityNames[0];
                String destination = cityNames[1];
                List<String> list = new ArrayList<String>();
                for (String s : citiesIds) {// remove the null elements from the array before writing to the list
                    if (s != null) {
                        list.add(s);
                    }
                }
                citiesIds = list.toArray(new String[list.size()]);
                int startIndex = 0;
                int destinationIndex = 0;
                for (int j = 0; j < citiesIds.length; j++) {  // find the index of the initial city
                    if (start.equals(citiesIds[j])) {
                        startIndex = j;
                        break;
                    }
                }
                for (int k = 0; k < citiesIds.length; k++) {  // find the index of the end of the city
                    if (destination.equals(citiesIds[k])) {
                        destinationIndex = k;
                        break;
                    }
                }
                Integer[] distancesFromSource = salesmanProblem.distancesFrom(startIndex);
                int destinationDistance = distancesFromSource[destinationIndex];
                System.out.println(destinationDistance);
            }
        }
        reader.close();
    }
}

