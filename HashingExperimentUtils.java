import java.util.ArrayList;
import java.util.Collections; // can be useful
import java.util.Random;

public class HashingExperimentUtils {
    final private static int k = 16;

    public static double[] measureInsertionsProbing() {
        double[] alpha = {0.5, 0.75, 0.875, 0.9375};
        double[] results = new double[alpha.length];
        Random rand = new Random();

        for (int j = 0; j < alpha.length; j++) {
            ProbingHashTable<Integer, Integer> probingHashTable = new ProbingHashTable<>(new ModularHash(), k, alpha[j]);
            int numInsertions = (int) (alpha[j] * (1 << k));
            long totalElapsed = 0;
            ArrayList<Integer> insertedKeys = new ArrayList<>();
            for (int i = 0; i < numInsertions; i++) {
                int key = rand.nextInt();
                while (insertedKeys.contains(key)) {
                    key = rand.nextInt();
                }
                long startTime = System.nanoTime();
                probingHashTable.insert(key, 0);
                long elapsedNanos = System.nanoTime() - startTime;
                totalElapsed += elapsedNanos;
                insertedKeys.add(key);
            }
            results[j] = ((double) totalElapsed) / numInsertions;
        }
        return results;
    }

    public static double[] measureSearchesProbing() {
        double[] alpha = {0.5, 0.75, 0.875, 0.9375};
        double[] results = new double[alpha.length];
        Random rand = new Random();

        for (int j = 0; j < alpha.length; j++) {
            ProbingHashTable<Integer, Integer> probingHashTable = new ProbingHashTable<>(new ModularHash(), k, alpha[j]);
            int numSearches = (int) (alpha[j] * (1 << k));
            ArrayList<Integer> insertedKeys = new ArrayList<>();
            for (int i = 0; i < numSearches; i++) {
                int key = rand.nextInt();
                while (insertedKeys.contains(key)) {
                    key = rand.nextInt();
                }
                probingHashTable.insert(key, 0);
                insertedKeys.add(key);
            }
            long totalElapsed = 0;
            for (int i = 0; i < numSearches; i++) {
                int key = 0;
                // the next lines going to promise as that 50% of all searches are successful and the rest are unsuccessful.
                if (i % 2 == 0) {
                    int idx = rand.nextInt(insertedKeys.size());
                    key = insertedKeys.get(idx);
                }
                else {
                    key = rand.nextInt();
                    while (insertedKeys.contains(key)) {
                        key = rand.nextInt();
                    }
                }
                long startTime = System.nanoTime();
                probingHashTable.search(key);
                long elapsedNanos = System.nanoTime() - startTime;
                totalElapsed += elapsedNanos;
            }
            results[j] = ((double) totalElapsed) / numSearches;
        }
        return results;
    }

    public static double[] measureInsertionsChaining() {
        double[] alpha = {0.5, 0.75, 1, 1.5, 2};
        double[] results = new double[alpha.length];
        Random rand = new Random();

        for (int j = 0; j < alpha.length; j++) {
            ChainedHashTable<Integer, Integer> chainedHashTable = new ChainedHashTable<>(new ModularHash(), k, alpha[j]);
            int numInsertions = (int) (alpha[j] * (1 << k));
            long totalElapsed = 0;
            ArrayList<Integer> insertedKeys = new ArrayList<>();
            for (int i = 0; i < numInsertions; i++) {
                int key = rand.nextInt();
                while (insertedKeys.contains(key)) {
                    key = rand.nextInt();
                }
                long startTime = System.nanoTime();
                chainedHashTable.insert(key, 0);
                long elapsedNanos = System.nanoTime() - startTime;
                totalElapsed += elapsedNanos;
                insertedKeys.add(key);
            }
            results[j] = ((double) totalElapsed) / numInsertions;
        }
        return results;
    }

    public static double[] measureSearchesChaining() {
        double[] alpha = {0.5, 0.75, 1, 1.5, 2};
        double[] results = new double[alpha.length];
        Random rand = new Random();

        for (int j = 0; j < alpha.length; j++) {
            ChainedHashTable<Integer, Integer> chainedHashTable = new ChainedHashTable<>(new ModularHash(), k, alpha[j]);
            int numSearches = (int) (alpha[j] * (1 << k));
            ArrayList<Integer> insertedKeys = new ArrayList<>();
            for (int i = 0; i < numSearches; i++) {
                int key = rand.nextInt();
                while (insertedKeys.contains(key)) {
                    key = rand.nextInt();
                }
                chainedHashTable.insert(key, 0);
                insertedKeys.add(key);
            }
            long totalElapsed = 0;
            for (int i = 0; i < numSearches; i++) {
                int key = 0;
                // the next lines going to promise as that 50% of all searches are successful and the rest are unsuccessful.
                if (i % 2 == 0) {
                    int idx = rand.nextInt(insertedKeys.size());
                    key = insertedKeys.get(idx);
                }
                else {
                    key = rand.nextInt();
                    while (insertedKeys.contains(key)) {
                        key = rand.nextInt();
                    }
                }
                long startTime = System.nanoTime();
                chainedHashTable.search(key);
                long elapsedNanos = System.nanoTime() - startTime;
                totalElapsed += elapsedNanos;
            }
            results[j] = ((double) totalElapsed) / numSearches;
        }
        return results;
    }
}
