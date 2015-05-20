package be.vdab.jdbc;

import java.util.List;

/**
 * Created by jeansmits on 20/05/15.
 */
public class TestClass {
    public static void main(String[] args) {
        FilmDao da = new JDBCFilmDao();
        System.out.println(da.findFilmById(5));

        FilmDao dao = new JDBCFilmDao();
        List<Film> test = dao.findAllFilms();
        System.out.println(test);


        Film filmtest = new Film(999,"Velvet Terminator ft Stijn", "2015");
        System.out.println(dao.updateFilm(filmtest));
    }
}
