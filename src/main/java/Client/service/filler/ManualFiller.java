package Client.service.filler;

import Client.model.User;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ManualFiller implements DataFiller {

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public List<User> fill(int size) {

        return IntStream.range(0, size)
                .mapToObj(i -> createUser())
                .collect(Collectors.toList());
    }

    private User createUser() {

        while (true) {
            try {
                System.out.print("Введите имя: ");
                String name = scanner.nextLine();

                System.out.print("Введите пароль: ");
                String password = scanner.nextLine();

                System.out.print("Введите email: ");
                String mail = scanner.nextLine();

                return User.builder()
                        .name(name)
                        .password(password)
                        .mail(mail)
                        .build();

            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка: " + e.getMessage());
                System.out.println("Попробуйте снова.");
            }
        }
    }
}