package edu.epam.project.service;

import javax.servlet.http.Part;
import java.io.IOException;

public interface FileService {

    String save(Part part, String storagePath) throws IOException;

    String changeFileName(String fileName);

    boolean remove(String fileName);

    String getFilePathForDataBase(Part file, String databasePath);

    String getFilePathForServer(Part file, String storagePath);
}
