package io.github.omerturkkan.javapractice.fileio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileIoPractice {
    public static void main(String[] args) throws IOException {
        // Everything happens in a temp directory, so the repo stays clean
        Path workspace = Files.createTempDirectory("java-practice-fileio");
        System.out.println("workspace    : " + workspace);

        try {
            Path notes = workspace.resolve("notes.txt");
            Path csv = workspace.resolve("data/orders.csv");

            // Files.writeString: whole file in one call (Java 11+)
            Files.writeString(notes, "first line\nsecond line\n", StandardCharsets.UTF_8);
            System.out.printf("%nexists       : %b, size: %d bytes%n", Files.exists(notes), Files.size(notes));

            // APPEND adds to the end instead of replacing the content
            Files.writeString(notes, "third line\n", StandardCharsets.UTF_8, StandardOpenOption.APPEND);

            System.out.println("\nreadString   :");
            System.out.print(Files.readString(notes).indent(2));

            List<String> lines = Files.readAllLines(notes);
            System.out.printf("readAllLines : %d lines -> %s%n", lines.size(), lines);

            // Files.lines streams lazily: the file is not held in memory at once
            try (Stream<String> stream = Files.lines(notes)) {
                String longest = stream.max(Comparator.comparingInt(String::length)).orElse("");
                System.out.printf("longest line : '%s'%n", longest);
            }

            // Nested directories must exist before writing into them
            Files.createDirectories(csv.getParent());

            // BufferedWriter for line-by-line writing, closed automatically
            try (BufferedWriter writer = Files.newBufferedWriter(csv, StandardCharsets.UTF_8)) {
                writer.write("city,amount");
                writer.newLine();
                for (String row : List.of("Istanbul,4200", "Ankara,1250", "Istanbul,899", "Izmir,640")) {
                    writer.write(row);
                    writer.newLine();
                }
            }
            System.out.printf("%nwrote csv    : %s (%d bytes)%n", csv.getFileName(), Files.size(csv));

            // BufferedReader for line-by-line reading
            try (BufferedReader reader = Files.newBufferedReader(csv, StandardCharsets.UTF_8)) {
                System.out.printf("header       : %s%n", reader.readLine());
                String line;
                int count = 0;
                while ((line = reader.readLine()) != null) count++;
                System.out.printf("data rows    : %d%n", count);
            }

            // A small report: sum the amounts per city
            try (Stream<String> rows = Files.lines(csv)) {
                Map<String, Integer> totals = rows
                        .skip(1)
                        .map(row -> row.split(","))
                        .collect(Collectors.groupingBy(parts -> parts[0],
                                Collectors.summingInt(parts -> Integer.parseInt(parts[1]))));
                System.out.println("totals       : " + totals);
            }

            // Copy, move, delete
            Path backup = workspace.resolve("notes.bak");
            Files.copy(notes, backup, StandardCopyOption.REPLACE_EXISTING);
            Path archived = Files.move(backup, workspace.resolve("data/notes-archived.txt"));
            System.out.printf("%ncopied+moved : %s%n", workspace.relativize(archived));

            // Path operations are pure string math, no disk access
            System.out.printf("fileName     : %s%n", csv.getFileName());
            System.out.printf("parent       : %s%n", workspace.relativize(csv.getParent()));
            System.out.printf("absolute     : %b%n", csv.isAbsolute());

            // Walking the tree
            System.out.println("\ntree:");
            try (Stream<Path> tree = Files.walk(workspace)) {
                tree.sorted()
                        .filter(path -> !path.equals(workspace))
                        .forEach(path -> System.out.printf("  %-28s %s%n",
                                workspace.relativize(path),
                                Files.isDirectory(path) ? "<dir>" : Files.isRegularFile(path) ? "file" : "?"));
            }

            // Reading a file that does not exist is a checked IOException
            try {
                Files.readString(workspace.resolve("missing.txt"));
            } catch (IOException e) {
                System.out.printf("%nmissing file : %s%n", e.getClass().getSimpleName());
            }
        } finally {
            // Delete children before parents, so walk the tree in reverse order
            try (Stream<Path> tree = Files.walk(workspace)) {
                List<Path> toDelete = tree.sorted(Comparator.reverseOrder()).toList();
                for (Path path : toDelete) Files.delete(path);
            }
            System.out.printf("%ncleaned up   : %b (workspace deleted)%n", !Files.exists(workspace));
        }
    }
}
