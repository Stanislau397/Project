package edu.epam.project.controller.command;

import edu.epam.project.controller.command.impl.admin.FindAllUsersCommand;
import edu.epam.project.controller.command.impl.admin.BlockUserCommand;
import edu.epam.project.controller.command.impl.common.RegisterCommand;
import edu.epam.project.controller.command.impl.common.SignInCommand;
import edu.epam.project.controller.command.impl.common.SignOutCommand;
import edu.epam.project.controller.command.impl.user.ChangePasswordCommand;

public enum CommandType {

    SIGN_IN(new SignInCommand()),
    REGISTER(new RegisterCommand()),
    SIGN_OUT(new SignOutCommand()),
    FIND_ALL_USERS(new FindAllUsersCommand()),
    BLOCK_USER(new BlockUserCommand()),

    CHANGE_PASSWORD(new ChangePasswordCommand());

    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
