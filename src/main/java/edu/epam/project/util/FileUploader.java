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

    public static String getPicturePath(Part part, String image_path, String directory_path) {
        String savePath = getSavePath(part, directory_path);
        String pictureName = savePath.substring(savePath.lastIndexOf("/"));
        return image_path + pictureName;
    }

    public static void processUploadedFile(Part part, String directory_path) throws IOException {
        String savePath = getSavePath(part, directory_path);
        File file = new File(savePath);
        part.write(file + File.separator);
    }
}
