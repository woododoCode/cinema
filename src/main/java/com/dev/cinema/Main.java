package com.dev.cinema;

import com.dev.cinema.config.AppConfig;
import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.model.Movie;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.Ticket;
import com.dev.cinema.model.User;
import com.dev.cinema.security.AuthenticationService;
import com.dev.cinema.service.interfaces.CinemaHallService;
import com.dev.cinema.service.interfaces.MovieService;
import com.dev.cinema.service.interfaces.MovieSessionService;
import com.dev.cinema.service.interfaces.OrderService;
import com.dev.cinema.service.interfaces.ShoppingCartService;
import com.dev.cinema.service.interfaces.UserService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    private static final AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(AppConfig.class);

    public static void main(String[] args) {
        var movieService = context.getBean(MovieService.class);
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
        var cinemaHallService = context.getBean(CinemaHallService.class);
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
        var movieSessionService = context.getBean(MovieSessionService.class);

        movieSessionService.add(movieSession);
        movieSessionService.add(anotherOneMovieSession);
        movieSessionService.add(andAnotherOneMovieSession);

        movieService.getAll().forEach(System.out::println);
        cinemaHallService.getAll().forEach(System.out::println);
        movieSessionService.getAll().forEach(System.out::println);
        LocalDate date = LocalDate.of(2020, Month.MAY, 3);
        movieSessionService.findAvailableSessions(movie.getId(), date).forEach(System.out::println);
        var authenticationService = context.getBean(AuthenticationService.class);
        var userService = context.getBean(UserService.class);
        userService.getAll().forEach(System.out::println);
        var shoppingCartService = context.getBean(ShoppingCartService.class);
        User grabli2 = authenticationService.register("grabli2@mail.com", "grabli2");
        shoppingCartService.addSession(movieSession, grabli2);
        System.out.println(shoppingCartService.getByUser(grabli2));

        var orderService = context.getBean(OrderService.class);
        List<Ticket> tickets = shoppingCartService.getByUser(grabli2).getTickets();
        orderService.completeOrder(tickets, grabli2);
        System.out.println(orderService.getOrderHistory(grabli2));
    }
}
