package edu.epam.project.util;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.Part;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileUploader {

    private static final Logger logger = LogManager.getLogger(FileUploader.class);

    public boolean uploadFile(InputStream is, String path) {
        boolean uploaded = false;
        try(FileOutputStream fops = new FileOutputStream(path)) {
            byte[] bytes = new byte[is.available()];
            is.read();
            fops.write(bytes);
            uploaded = true;
        } catch (IOException e) {
            logger.log(Level.ERROR, e);
        }
        return uploaded;
    }

    public String extractFileName(Part part) {//This method will print the file name.
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
    }
}
