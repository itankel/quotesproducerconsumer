package com.ifat.bdd.utiles;

import lombok.SneakyThrows;

import java.io.File;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Stream;

public class FilesUtils {
    public static final String WRITE_TO_DIR = "c:\\tmp\\quotes_objects\\";

    @SneakyThrows
    public static Optional<String> getLastWrittenFile() {
        return Stream.of(new File(WRITE_TO_DIR).listFiles())
                .filter(file -> !file.isDirectory())
                .filter(file-> file.getName().contains(".obj"))
                .map(File::getAbsolutePath)
                .sorted(Comparator.reverseOrder())
                .findFirst();
    }

}
