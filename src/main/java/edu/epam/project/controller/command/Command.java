package edu.epam.project.controller.command;

import edu.epam.project.controller.Router;
import org.apache.commons.fileupload.FileUploadException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface Command {

    Router execute(HttpServletRequest request) throws ServletException, IOException;
}
