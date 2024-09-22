import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class June2024 {
    private static final int[][] direction = {
        {-1, 0}, {1, 0}, {0, -1}, {0, 1},
        {-1, -1}, {-1, 1}, {1, -1}, {1, 1}
    };

    private static final Map<String, Integer> states = new HashMap<>();
    private static final int POPULATION_SIZE = 100;
    private static final int MAX_GENERATIONS = 1000;
    private static final double MUTATION_RATE = 0.5;
    private static final int GRID_SIZE = 5;
    private static final int TOURNAMENT_SIZE = 5; // Size of tournament for selection

    static {
        states.put("california", 39538223);
        states.put("texas", 29145505);
        states.put("florida", 21538187);
        states.put("newyork", 20201249);
        states.put("pennsylvania", 13002700);
        states.put("illinois", 12812508);
        states.put("ohio", 11799448);
        states.put("georgia", 10711908);
        states.put("northcarolina", 10439388);
        states.put("michigan", 10077331);
        states.put("newjersey", 9288994);
        states.put("virginia", 8631393);
        states.put("washington", 7705281);
        states.put("arizona", 7151502);
        states.put("massachusetts", 7029917);
        states.put("tennessee", 6910840);
        states.put("indiana", 6785528);
        states.put("maryland", 6177224);
        states.put("missouri", 6154913);
        states.put("wisconsin", 5893718);
        states.put("colorado", 5773714);
        states.put("minnesota", 5706494);
        states.put("southcarolina", 5118425);
        states.put("alabama", 5024279);
        states.put("louisiana", 4657757);
        states.put("kentucky", 4505836);
        states.put("oregon", 4237256);
        states.put("oklahoma", 3959353);
        states.put("connecticut", 3605944);
        states.put("utah", 3271616);
        states.put("iowa", 3190369);
        states.put("nevada", 3104614);
        states.put("arkansas", 3011524);
        states.put("mississippi", 2961279);
        states.put("kansas", 2937880);
        states.put("newmexico", 2117522);
        states.put("nebraska", 1961504);
        states.put("idaho", 1839106);
        states.put("westvirginia", 1793716);
        states.put("hawaii", 1455721);
        states.put("newhampshire", 1377529);
        states.put("maine", 1362359);
        states.put("rhodeisland", 1097379);
        states.put("montana", 1084225);
        states.put("delaware", 989948);
        states.put("southdakota", 886667);
        states.put("northdakota", 779094);
        states.put("alaska", 733391);
        states.put("districtcolumbia", 689545);
        states.put("vermont", 643077);
        states.put("wyoming", 576851);
    }

    public static void main(String[] args) {
        List<char[][]> population = generateInitialPopulation(POPULATION_SIZE, GRID_SIZE, GRID_SIZE);
        int generation = 0;

        while (generation < MAX_GENERATIONS) {
            // Calculate Fitness of Individuals
            Map<char[][], Integer> fitnessMap = calculateFitness(population);

            // Sort Population (by Fitness)
            population.sort((grid1, grid2) -> fitnessMap.get(grid2) - fitnessMap.get(grid1));

            // Track Generations
            if (generation % 100 == 0) {
                System.out.println("Generation: " + generation);
            }

            // Terminate if Min Score is Achieved
            if (fitnessMap.get(population.get(0)) >= 200000000) break;

            // Create New Population
            List<char[][]> newPopulation = new ArrayList<>();
            while (newPopulation.size() < POPULATION_SIZE) {
                // Select Parents using Tournament Selection
                char[][] parent1 = tournamentSelection(population, fitnessMap, TOURNAMENT_SIZE);
                char[][] parent2 = tournamentSelection(population, fitnessMap, TOURNAMENT_SIZE);

                // Crossover
                char[][] child = crossover(parent1, parent2);

                // Mutation
                if (Math.random() < MUTATION_RATE) {
                    mutate(child);
                }

                newPopulation.add(child);
            }

            population = newPopulation;
            generation++;
        }

        System.out.println("Final best grid:");
        printGrid(population.get(0));
        System.out.println(calculateScore(population.get(0))); 
    }

    // Generate the Initial Population w/ Random Grids
    public static List<char[][]> generateInitialPopulation(int populationSize, int rows, int cols) {
        List<char[][]> population = new ArrayList<>();
        for (int i = 0; i < populationSize; i++) {
            population.add(generateRandomGrid(rows, cols));
        }
        return population;
    }
    // Generate Random Grid, Can Be Improved By Counting The Number of Letter With Higest Recurrence and Don't Input Lower (or less probabiltiy)
    public static char[][] generateRandomGrid(int rows, int cols) {
        char[][] grid = new char[rows][cols];
        Random random = new Random();
        // Fill the grid with random values
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = (char) ('a' + random.nextInt(26));
            }
        }
        return grid;
    }

    // Generate the Fitness Map
    public static Map<char[][], Integer> calculateFitness(List<char[][]> population) {
        Map<char[][], Integer> fitnessMap = new HashMap<>();
        for (char[][] grid : population) {
            int score = calculateScore(grid);
            fitnessMap.put(grid, score);
        }
        return fitnessMap;
    }

    // Calculate Score 
    public static int calculateScore(char[][] grid) {
        int score = 0;
        Set<String> foundStates = new HashSet<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                for (String state : states.keySet()) {
                    if (!foundStates.contains(state) && dfs(grid, i, j, state, 0, false)) {
                        score += states.get(state);
                        foundStates.add(state);
                    }
                }
            }
        }
        return score;
    }

    // Use DFS to Check for States in the Grid
    public static boolean dfs(char[][] grid, int x, int y, String word, int index, boolean oneMismatch) {
        if (index == word.length()) {
            return true;
        }
        if (x < 0 || y < 0 || x >= grid.length || y >= grid[0].length) {
            return false;
        }
        if (grid[x][y] != word.charAt(index)) {
            if (oneMismatch) {
                return false;
            } else {
                oneMismatch = true;
            }
        }

        for (int[] dir : direction) {
            int newX = x + dir[0];
            int newY = y + dir[1];
            if (dfs(grid, newX, newY, word, index + 1, oneMismatch)) {
                return true;
            }
        }

        return false;
    }

    // Tournament Selection
    public static char[][] tournamentSelection(List<char[][]> population, Map<char[][], Integer> fitnessMap, int tournamentSize) {
        Random random = new Random();
        List<char[][]> tournament = new ArrayList<>();
        for (int i = 0; i < tournamentSize; i++) {
            char[][] selected = population.get(random.nextInt(population.size()));
            tournament.add(selected);
        }
        return tournament.stream().max((grid1, grid2) -> fitnessMap.get(grid1) - fitnessMap.get(grid2)).orElse(null);
    }

    // Crossover - Combine Two Grid Randomly
    public static char[][] crossover(char[][] parent1, char[][] parent2) {
        char[][] child = new char[parent1.length][parent1[0].length];
        Random random = new Random();
        for (int i = 0; i < parent1.length; i++) {
            for (int j = 0; j < parent1[0].length; j++) {
                child[i][j] = random.nextBoolean() ? parent1[i][j] : parent2[i][j];
            }
        }
        return child;
    }

    // Mutation - Randomly Change on Value in the Grid, can be improved with a more probabilistic approach
    public static void mutate(char[][] grid) {
        Random random = new Random();
        int row = random.nextInt(grid.length);
        int col = random.nextInt(grid[0].length);
        grid[row][col] = (char) ('a' + random.nextInt(26));
    }
    // Prints Grid
    public static void printGrid(char[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }
}
