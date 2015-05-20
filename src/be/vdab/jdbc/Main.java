package be.vdab.jdbc;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Evelyne is cool");
        FilmDao dao = new JDBCFilmDao(); // instantiate a new JdbcFilmDao

        // Example usage:
        List<Film> films = dao.findAllFilms();
        for (Film film : films) {
            System.out.println(film.getTitle());
        }

        // Example usage:
        Film film = dao.findFilmById(223);
        film.setReleaseYear("2014");
        System.out.println(dao.updateFilm(film));

        // Example usage:
        System.out.println(dao.deleteFilm(232));

        // ... play around with this.
    }
}
