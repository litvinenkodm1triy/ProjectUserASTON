package Client.service.filler;

import Client.model.User;
import Client.service.file.FileReaderService;

import java.util.List;
import java.util.stream.Collectors;

public class FileFiller implements DataFiller {

    private final FileReaderService fileReaderService = new FileReaderService();

    @Override
    public List<User> fill(int size) {

        List<String> lines = fileReaderService.readLines();

        return lines.stream()
                .limit(size)
                .map(this::parseUser)
                .collect(Collectors.toList());
    }

    private User parseUser(String line) {

        String[] parts = line.split(",");

        return User.builder()
                .name(parts[0])
                .password(parts[1])
                .mail(parts[2])
                .build();
    }
}