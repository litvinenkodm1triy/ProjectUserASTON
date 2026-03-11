package Client.service.file;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Сервис для чтения данных из файла
 */
public class FileReaderService {

    private static final String DEFAULT_FILE_PATH = "users.txt";

    /**
     * Чтение строк из файла
     */
    public List<String> readLines() {
        return readLines(DEFAULT_FILE_PATH);
    }

    /**
     * Чтение строк из указанного файла
     */
    public List<String> readLines(String filePath) {

        List<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            String line;

            while ((line = reader.readLine()) != null) {

                if (!line.trim().isEmpty()) {
                    lines.add(line);
                }

            }

        } catch (IOException e) {
            System.out.println("Ошибка чтения файла: " + e.getMessage());
        }

        return lines;
    }
}