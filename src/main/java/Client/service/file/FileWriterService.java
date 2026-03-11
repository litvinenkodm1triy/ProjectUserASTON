package Client.service.file;

import Client.model.User;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Сервис для записи данных в файл
 */
public class FileWriterService {

    private static final String DEFAULT_FILE_PATH = "result.txt";

    /**
     * Запись пользователей в файл (append режим)
     */
    public void writeUsers(List<User> users) {
        writeUsers(users, DEFAULT_FILE_PATH);
    }

    /**
     * Запись пользователей в указанный файл
     */
    public void writeUsers(List<User> users, String filePath) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {

            for (User user : users) {

                String line = String.format(
                        "%s,%s,%s",
                        user.getName(),
                        user.getPassword(),
                        user.getMail()
                );

                writer.write(line);
                writer.newLine();
            }

            writer.write("-----");
            writer.newLine();

        } catch (IOException e) {
            System.out.println("Ошибка записи в файл: " + e.getMessage());
        }
    }
}