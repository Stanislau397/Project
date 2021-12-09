package edu.epam.project.controller.filter;

import edu.epam.project.controller.command.CommandType;

import java.util.HashSet;
import java.util.Set;

public class SecurityConfig {

    private static final Set<CommandType> SECURITY_PAGE = new HashSet<>();

    static {
        SECURITY_PAGE.add(CommandType.TO_EDIT_MOVIE);
        SECURITY_PAGE.add(CommandType.TO_ADMIN_CABINET);
    }

    public static boolean isSecurityPage(CommandType commandType) {
        return SECURITY_PAGE.contains(commandType);
    }
}