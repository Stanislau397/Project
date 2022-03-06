package edu.epam.project.service.impl;

import edu.epam.project.service.FileService;

import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class FileServiceImpl implements FileService {

    private static final String DOT = ".";

    @Override
    public String save(Part part, String directoryPath) throws IOException {
        String filename = part.getSubmittedFileName();
        String filePath = directoryPath.concat(filename);
        File file = new File(filePath);
        if (file.exists()) {
            String randomFileName = changeFileName(filename);
            filePath = directoryPath.concat(randomFileName);
        }
        part.write(filePath);
        return filePath;
    }

    @Override
    public String changeFileName(String fileName) {
        int lastIndexOfDot = fileName.lastIndexOf(DOT);
        String randomFileName = UUID.randomUUID().toString();
        String extension = fileName.substring(lastIndexOfDot);

        return randomFileName.concat(extension);
    }

    @Override
    public boolean remove(String fileName) {
        return false;
    }

    @Override
    public String getFilePathForDataBase(Part file, String databasePath) {
        String filename = file.getSubmittedFileName();
        return databasePath.concat(filename);
    }

    @Override
    public String getFilePathForServer(Part part, String storagePath) {
        String filename = part.getSubmittedFileName();
        return storagePath.concat(filename);
    }
}
