package edu.epam.project.controller.command;

import edu.epam.project.controller.command.impl.*;
import edu.epam.project.controller.command.impl.admin.*;
import edu.epam.project.controller.command.impl.common.*;
import edu.epam.project.controller.command.impl.user.ChangePasswordCommand;
import edu.epam.project.controller.command.impl.user.ShowUserProfileCommand;

public enum CommandType {

    SIGN_IN(new SignInCommand()),
    REGISTER(new RegisterCommand()),
    SIGN_OUT(new SignOutCommand()),
    ALL_USERS(new FindAllUsersCommand()),
    ALL_MOVIES(new FindAllMoviesCommand()),
    LEAVE_COMMENT(new LeaveCommentCommand()),
    RATE_MOVIE(new RateMovieCommand()),
    REMOVE_RATING(new RemoveRatingCommand()),
    REMOVE_COMMENT(new RemoveCommentCommand()),

    BLOCK_USER(new BlockUserCommand()),
    UNBLOCK_USER(new UnblockUserCommand()),

    CHANGE_PASSWORD(new ChangePasswordCommand()),
    CHANGE_LOCALE(new ChangeLocaleCommand()),

    OPEN_HOME_PAGE(new OpenHomePageCommand()),
    OPEN_LOGIN_PAGE(new OpenLogInPageCommand()),
    TO_UPLOAD_MOVIE(new ToUploadMovieCommand()),
    TO_GENRES(new ToGenresCommand()),
    TO_EDIT_MOVIE(new ToEditMovieCommand()),

    UPLOAD_MOVIE(new UploadMovieCommand()),
    ADD_GENRE(new AddGenreCommand()),
    ADD_ACTOR(new AddActorCommand()),
    ADD_ACTOR_TO_MOVIE(new AddActorToMovieCommand()),
    REMOVE_GENRE(new RemoveCommentCommand()),
    REMOVE_ACTOR_FROM_MOVIE(new RemoveActorFromMovieCommand()),
    EDIT_MOVIE(new EditMovieCommand()),
    FIND_ACTOR(new FindActorCommand()),
    DISPLAY_MOVIE_ACTORS(new DisplayMovieActorsCommand()),

    SHOW_ALL_MOVIES(new ShowAllMoviesCommand()),
    CURRENT_YEAR_MOVIES(new CurrentYearMoviesCommand()),
    MOVIES_BY_YEAR(new MoviesByYearCommand()),
    MOVIES_BY_GENRE(new MoviesByGenreCommand()),
    NEWEST_MOVIES(new NewestMoviesCommand()),
    MOST_RATED_MOVIES(new MostRatedMoviesCommand()),
    SHOW_COMMENTS(new ShowCommentsCommand()),
    UP_VOTE_COMMENT(new UpVoteCommentCommand()),
    DOWN_VOTE_COMMENT(new DownVoteCommentCommand()),
    SHOW_USER_PROFILE(new ShowUserProfileCommand()),
    SEARCH_MOVIE(new SearchCommand()),
    SHOW_MOVIE_DETAILS(new ShowMovieDetailsCommand());

    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
