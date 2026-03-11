package Client.service.filler;

import Client.model.User;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomFiller implements DataFiller {

    private static final String[] NAMES = {
            "Alex", "John", "Anna", "Kate", "Mike", "Leo", "Max"
    };

    private final Random random = new Random();

    @Override
    public List<User> fill(int size) {

        return IntStream.range(0, size)
                .mapToObj(i -> generateUser())
                .collect(Collectors.toList());
    }

    private User generateUser() {

        String name = NAMES[random.nextInt(NAMES.length)];
        String password = "pass" + (1000 + random.nextInt(9000));
        String mail = name.toLowerCase() + random.nextInt(100) + "@mail.com";

        return User.builder()
                .name(name)
                .password(password)
                .mail(mail)
                .build();
    }
}