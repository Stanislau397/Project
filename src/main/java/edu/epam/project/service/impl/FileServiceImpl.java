package edu.epam.project.service.impl;

import edu.epam.project.service.FileService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

public class FileServiceImpl implements FileService {

    private static final Logger logger = LogManager.getLogger(FileServiceImpl.class);

    @Override
    public void uploadImageFile(Part part, String storagePath) {
        String pathForServer = getFilePathForServer(part, storagePath);
        try {
            File file = new File(pathForServer);
            part.write(file + File.separator);
        } catch (IOException e) {
            logger.log(Level.ERROR, e);
        }
    }

    @Override
    public String changeFileName(String fileName) {
        return null;
    }

    @Override
    public boolean deleteFileByName(String fileName) {
        return false;
    }

    @Override
    public String getFilePathForDataBase(Part part, String databasePath) {
        String fileName = part.getSubmittedFileName();
        return databasePath.concat(fileName);
    }

    @Override
    public String getFilePathForServer(Part part, String storagePath) {
        String fileName = part.getSubmittedFileName();
        return storagePath.concat(fileName);
    }
}
