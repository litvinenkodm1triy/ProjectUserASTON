package Client.service.filler;

import Client.model.User;
import Client.service.file.FileReaderService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class FileFiller {

    private final FileReaderService fileReader;

    public FileFiller() {
        this.fileReader = new FileReaderService();
    }

    public List<User> fill(int count) {
        System.out.println("Ошибка: необходимо указать путь к файлу. Используйте fillFromPath(String filePath)");
        return new ArrayList<>();
    }

    public List<User> fillFromPath(String filePath) {
        if (filePath == null || filePath.trim().isEmpty()) {
            System.out.println("Не указан путь к файлу");
            return new ArrayList<>();
        }

        System.out.println("\n=== Заполнение из файла ===");
        System.out.println("Файл: " + filePath);

        try {
            if (!fileReader.fileExists(filePath)) {
                System.out.println("❌ Файл не найден: " + fileReader.getAbsolutePath(filePath));
                return new ArrayList<>();
            }

            if (!fileReader.isReadable(filePath)) {
                System.out.println("❌ Файл недоступен для чтения: " + filePath);
                return new ArrayList<>();
            }

            List<User> users = fileReader.readFromFile(filePath);

            if (users.isEmpty()) {
                System.out.println("❌ Не удалось загрузить пользователей из файла.");
                return new ArrayList<>();
            }

            System.out.println("✓ Успешно загружено пользователей: " + users.size());
            return users;

        } catch (IOException e) {
            System.out.println("❌ Ошибка чтения файла: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public boolean fileExists(String filePath) {
        return fileReader.fileExists(filePath);
    }

    public String getAbsolutePath(String filePath) {
        return fileReader.getAbsolutePath(filePath);
    }
}