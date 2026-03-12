package Client.service.filler;

import Client.model.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomFiller {

    private final Random random;


    private static final String[] NAMES = {
            "Алексей", "Дмитрий", "Михаил", "Андрей", "Сергей", "Павел", "Николай",
            "Мария", "Анна", "Елена", "Ольга", "Наталья", "Татьяна", "Екатерина",
            "Иван", "Петр", "Максим", "Владимир", "Константин", "Юлия", "Светлана"
    };

    private static final String[] DOMAINS = {
            "gmail.com", "yandex.ru", "mail.ru", "yahoo.com", "outlook.com",
            "bk.ru", "inbox.ru", "list.ru", "rambler.ru", "hotmail.com"
    };

    public RandomFiller() {
        this.random = new Random();
    }

    public List<User> fill(int count) {
        if (count <= 0) {
            System.out.println("Количество пользователей должно быть положительным");
            return new ArrayList<>();
        }

        List<User> users = new ArrayList<>();
        System.out.println("\n=== Генерация случайных пользователей ===");
        System.out.println("Генерируется: " + count + " пользователей");

        int attempts = 0;
        int maxAttempts = count * 10;

        while (users.size() < count && attempts < maxAttempts) {
            try {
                User user = generateRandomUser();
                users.add(user);
                System.out.print(".");
            } catch (IllegalArgumentException e) {
                attempts++;
            }
        }

        System.out.println("\n✓ Сгенерировано пользователей: " + users.size());
        return users;
    }

    private User generateRandomUser() {
        String name = generateRandomName();
        String password = generateRandomPassword();
        String mail = generateRandomMail(name);

        return User.builder()
                .name(name)
                .password(password)
                .mail(mail)
                .build();
    }


    private String generateRandomName() {
        return NAMES[random.nextInt(NAMES.length)];
    }


    private String generateRandomPassword() {
        int length = random.nextInt(15) + 6; // от 6 до 20 символов
        StringBuilder password = new StringBuilder();

        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        for (int i = 0; i < length; i++) {
            password.append(chars.charAt(random.nextInt(chars.length())));
        }

        return password.toString();
    }

    private String generateRandomMail(String name) {

        String latinName = transliterate(name.toLowerCase());

        if (random.nextBoolean()) {
            latinName += random.nextInt(1000);
        }

        String domain = DOMAINS[random.nextInt(DOMAINS.length)];

        return latinName + "@" + domain;
    }

    private String transliterate(String text) {
        String[] cyrillic = {"а", "б", "в", "г", "д", "е", "ё", "ж", "з", "и", "й",
                "к", "л", "м", "н", "о", "п", "р", "с", "т", "у", "ф",
                "х", "ц", "ч", "ш", "щ", "ъ", "ы", "ь", "э", "ю", "я"};
        String[] latin = {"a", "b", "v", "g", "d", "e", "e", "zh", "z", "i", "y",
                "k", "l", "m", "n", "o", "p", "r", "s", "t", "u", "f",
                "kh", "ts", "ch", "sh", "sch", "", "y", "", "e", "yu", "ya"};

        String result = text.toLowerCase();
        for (int i = 0; i < cyrillic.length; i++) {
            result = result.replace(cyrillic[i], latin[i]);
        }
        return result;
    }
}
