package org.example.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.ToIntFunction;

public class CSVFileUtils {

    // init csv file
    public static void initCSVFile(String filePath, String csvHeader) {
        File file = new File("data");

        if (!file.exists()) {
            file.mkdir();
        }

        File csvFile = new File(filePath);
        if (!csvFile.exists()) {
            try (PrintWriter writer = new PrintWriter(filePath)) {
                writer.println(csvHeader);
            } catch (IOException e) {
                throw new RuntimeException("Error while creating CSV file " + e.getMessage());
            }
        }
    }

    // read entities from csv file
    public static <T> List<T> readAllFromCSVFile(String filePath, Function<String, T> function) {
        List<T> lists = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            reader.readLine();

            String line;

            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    lists.add(function.apply(line));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error while " +
                    "reading CSV file " + e.getMessage());
        }

        return lists;
    }

    public static <T> void saveToCSV(String filePath, String csvHeader, List<T> data, Function<T, String> convert) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            writer.println(csvHeader);

            for (T item:data) {
                writer.println(convert.apply(item));
            }
        } catch (IOException e) {
            throw new RuntimeException("Error while saving CSV file " + e.getMessage());
        }
    }

    public static <T> int getNextId(List<T> lists, ToIntFunction<T> idGetter) {
        return lists.stream()
                .mapToInt(idGetter)
                .max()
                .orElse(0) + 1;
    }
}
