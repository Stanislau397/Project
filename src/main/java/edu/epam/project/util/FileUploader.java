package edu.epam.project.util;

import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

public class FileUploader {


    private FileUploader() {

    }

    public static String getSavePath(Part part, String directory_path) {
        String fileName = part.getSubmittedFileName();
        return (directory_path + fileName);
    }

    public static String getPicturePath(Part part, String imagePath, String directoryPath) {
        String savePath = getSavePath(part, directoryPath);
        String pictureName = savePath.substring(savePath.lastIndexOf("/"));
        return imagePath + pictureName;
    }

    public static void processUploadedFile(Part part, String directoryPath) throws IOException {
        String savePath = getSavePath(part, directoryPath);
        File file = new File(savePath);
        part.write(file + File.separator);
    }
}
