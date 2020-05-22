package com.dev.cinema;

import com.dev.cinema.lib.Injector;
import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.model.Movie;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.service.CinemaHallService;
import com.dev.cinema.service.MovieService;
import com.dev.cinema.service.MovieSessionService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

public class Main {
    private static final Injector injector = Injector.getInstance("com.dev.cinema");

    public static void main(String[] args) {
        var movieService = (MovieService) injector.getInstance(MovieService.class);
        var movie = new Movie();
        movie.setTitle("Flubber");
        movie.setDescription("Professor Philip Brainard (Robin Williams), "
                + "of Medfield College, is a mad scientist who is developing a "
                + "new energy source in an attempt to raise enough money to save "
                + "the college from closure.");
        movieService.add(movie);

        var blueHall = new CinemaHall();
        blueHall.setCapacity(40);
        blueHall.setDescription("Premium hall");
        var greenHall = new CinemaHall();
        greenHall.setCapacity(400);
        greenHall.setDescription("Just for students =)");
        var cinemaHallService =
                (CinemaHallService) injector.getInstance(CinemaHallService.class);
        cinemaHallService.add(blueHall);
        cinemaHallService.add(greenHall);

        var movieSession = new MovieSession();
        movieSession.setMovie(movie);
        movieSession.setCinemaHall(blueHall);
        movieSession.setShowtime(LocalDateTime.of(2020, Month.MAY, 29, 17, 20));
        var anotherOneMovieSession = new MovieSession();
        anotherOneMovieSession.setMovie(movie);
        anotherOneMovieSession.setCinemaHall(blueHall);
        anotherOneMovieSession.setShowtime(LocalDateTime.of(2020, Month.MAY, 30, 19, 20));
        var andAnotherOneMovieSession = new MovieSession();
        andAnotherOneMovieSession.setMovie(movie);
        andAnotherOneMovieSession.setCinemaHall(blueHall);
        andAnotherOneMovieSession.setShowtime(LocalDateTime.of(2020, Month.MAY, 31, 23, 20));
        var movieSessionService =
                (MovieSessionService) injector.getInstance(MovieSessionService.class);

        movieSessionService.add(movieSession);
        movieSessionService.add(anotherOneMovieSession);
        movieSessionService.add(andAnotherOneMovieSession);

        movieService.getAll().forEach(System.out::println);
        cinemaHallService.getAll().forEach(System.out::println);
        movieSessionService.getAll().forEach(System.out::println);
        LocalDate date = LocalDate.of(2020, Month.MAY, 3);
        movieSessionService.findAvailableSessions(movie.getId(), date).forEach(System.out::println);
    }
}
