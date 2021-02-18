package edu.epam.project.controller.command;

import edu.epam.project.controller.command.impl.*;
import edu.epam.project.controller.command.impl.admin.FindAllUsersCommand;
import edu.epam.project.controller.command.impl.admin.BlockUserCommand;
import edu.epam.project.controller.command.impl.common.ChangeLocaleCommand;
import edu.epam.project.controller.command.impl.common.RegisterCommand;
import edu.epam.project.controller.command.impl.common.SignInCommand;
import edu.epam.project.controller.command.impl.common.SignOutCommand;
import edu.epam.project.controller.command.impl.user.ChangePasswordCommand;
import edu.epam.project.controller.command.impl.user.ShowUserProfileCommand;

public enum CommandType {

    SIGN_IN(new SignInCommand()),
    REGISTER(new RegisterCommand()),
    SIGN_OUT(new SignOutCommand()),
    FIND_ALL_USERS(new FindAllUsersCommand()),
    BLOCK_USER(new BlockUserCommand()),
    LEAVE_COMMENT(new LeaveCommentCommand()),
    RATE_MOVIE(new RateMovieCommand()),
    REMOVE_COMMENT(new RemoveCommentCommand()),

    CHANGE_PASSWORD(new ChangePasswordCommand()),
    CHANGE_LOCALE(new ChangeLocaleCommand()),

    SHOW_ALL_MOVIES(new ShowAllMoviesCommand()),
    SHOW_COMMENTS(new ShowCommentsCommand()),
    SHOW_USER_PROFILE(new ShowUserProfileCommand()),
    SHOW_MOVIE_DETAILS(new ShowMovieDetailsCommand());


    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
