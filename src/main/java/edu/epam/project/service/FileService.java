package edu.epam.project.service;

import javax.servlet.http.Part;

public interface FileService {

    void uploadImageFile(Part part, String storagePath);

    String changeFileName(String fileName);

    boolean deleteFileByName(String fileName);

    String getFilePathForDataBase(Part file, String databasePath);

    String getFilePathForServer(Part file, String storagePath);
}
