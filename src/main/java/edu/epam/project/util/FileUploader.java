package edu.epam.project.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

public class FileUploader {

    private static final Logger logger = LogManager.getLogger(FileUploader.class);
    private static final String DOT = ".";

    public static String save(Part part, String directoryPath, String filename) throws IOException {
        String filePath = directoryPath.concat(filename);
        Path path = Paths.get(filePath);
        if (Files.exists(path)) {
            String randomFileName = changeFileName(filename);
            filePath = directoryPath.concat(randomFileName);
        }
        part.write(filePath);
        return filePath;
    }

    public static String changeFileName(String fileName) {
        int lastIndexOfDot = fileName.lastIndexOf(DOT);
        String randomFileName = UUID.randomUUID().toString();
        String extension = fileName.substring(lastIndexOfDot);

        return randomFileName.concat(extension);
    }

    public static boolean remove(String filename, String directoryPath) {
        String fileToRemove = directoryPath.concat(filename);
        File file = new File(fileToRemove);
        return file.delete();
    }
}
