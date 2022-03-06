package edu.epam.project.controller.command;

import edu.epam.project.controller.command.impl.*;
import edu.epam.project.controller.command.impl.admin.*;
import edu.epam.project.controller.command.impl.common.*;
import edu.epam.project.controller.command.impl.user.*;

public enum CommandType {

    TEST(new TestCommand()),

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
    CHANGE_USER_AVATAR(new ChangeAvatarCommand()),

    OPEN_HOME_PAGE(new OpenHomePageCommand()),
    TO_UPLOAD_MOVIE(new ToUploadMovieCommand()),
    TO_GENRES(new ToGenresCommand()),
    TO_EDIT_MOVIE(new ToEditMovieCommand()),
    TO_ADMIN_CABINET(new ToAdminCabinetCommand()),
    TO_EDIT_USER(new ToEditUserCommand()),
    TO_EDIT_ACTOR(new ToEditActorCommand()),
    TO_EDIT_DIRECTOR(new ToEditDirectorCommand()),
    EDIT_DIRECTOR_INFO(new EditDirectorInfoCommand()),
    TO_ALL_COUNTRIES(new DisplayAllCountriesCommand()),

    UPLOAD_MOVIE(new UploadMovieCommand()),
    ADD_GENRE(new AddGenreCommand()),
    ADD_COUNTRY(new AddCountryCommand()),
    ADD_GENRE_TO_MOVIE(new AddGenreToMovieCommand()),
    ADD_COUNTRY_TO_MOVIE(new AddCountryToMovieCommand()),
    ADD_ACTOR(new AddActorCommand()),
    EDIT_ACTOR(new EditActorCommand()),
    EDIT_DIRECTOR(new EditDirectorInfoCommand()),
    SEARCH_ACTOR(new SearchActorCommand()),
    SEARCH_DIRECTORS(new SearchDirectorCommand()),
    ADD_DIRECTOR(new AddDirectorCommand()),
    ADD_DIRECTOR_TO_MOVIE(new AddDirectorToMovieCommand()),
    ADD_ACTOR_TO_MOVIE(new AddActorToMovieCommand()),
    REMOVE_GENRE(new DeleteGenreCommand()),
    REMOVE_COUNTRY(new RemoveCountryCommand()),
    REMOVE_ACTOR(new DeleteActorCommand()),
    REMOVE_DIRECTOR(new DeleteDirectorCommand()),
    REMOVE_ACTOR_FROM_MOVIE(new RemoveActorFromMovieCommand()),
    REMOVE_DIRECTOR_FROM_MOVIE(new RemoveDirectorFromMovieCommand()),
    REMOVE_GENRE_FROM_MOVIE(new RemoveGenreFromMovieCommand()),
    REMOVE_COUNTRY_FROM_MOVIE(new RemoveCountryFromMovieCommand()),
    DISPLAY_ACTOR_INFO(new DisplayActorInfoCommand()),
    DISPLAY_DIRECTOR_INFO(new DisplayDirectorInfoCommand()),
    FIND_USER(new SearchUserCommand()),
    CHANGE_USER_ROLE(new ChangeUserRoleCommand()),
    CHANGE_MOVIE_POSTER(new ChangeMoviePosterCommand()),
    UPDATE_MOVIE(new UpdateMovieCommand()),
    DISPLAY_MOVIE_DIRECTORS(new DisplayMovieDirectorsCommand()),
    DISPLAY_MOVIE_ACTORS(new DisplayMovieActorsCommand()),
    DISPLAY_MOVIE_GENRES(new DisplayMovieGenresCommand()),
    DISPLAY_MOVIE_POSTER(new DisplayMoviePosterCommand()),
    DISPLAY_MOVIE_COUNTRIES(new DisplayMovieCountriesCommand()),
    DISPLAY_ALL_ACTORS(new DisplayAllActorsCommand()),
    DISPLAY_ALL_DIRECTORS(new DisplayAllDirectorsCommand()),
    DISPLAY_ALL_COUNTRIES(new DisplayAllCountriesCommand()),

    UPDATE_MOVIE_TRAILER(new UpdateMovieTrailerCommand()),
    SHOW_ALL_MOVIES(new ShowAllMoviesCommand()),
    CURRENT_YEAR_MOVIES(new CurrentYearMoviesCommand()),
    MOVIES_BY_YEAR(new MoviesByYearCommand()),
    MOVIES_BY_GENRE(new MoviesByGenreCommand()),
    NEWEST_MOVIES(new NewestMoviesCommand()),
    UPCOMING_MOVIES(new DisplayUpcomingMoviesCommand()),
    UPCOMING_MOVIES_BY_GENRE(new ShowUpcomingMoviesByGenreCommand()),
    CURRENT_YEAR_MOVIES_BY_GENRE(new ShowCurrentYearMoviesByGenre()),
    NEWEST_MOVIES_BY_GENRE(new ShowNewestMoviesByGenreCommand()),
    MOVIES_BY_GENRE_AND_YEAR(new ShowMoviesByGenreAndYearCommand()),
    MOST_RATED_MOVIES(new MostRatedMoviesCommand()),
    UP_VOTE_COMMENT(new UpVoteCommentCommand()),
    EDIT_COMMENT(new EditCommentCommand()),
    DOWN_VOTE_COMMENT(new DownVoteCommentCommand()),
    SHOW_USER_PROFILE(new ShowUserProfileCommand()),
    SEARCH_MOVIE_BY_KEY_WORD(new SearchMovieCommand()),
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
