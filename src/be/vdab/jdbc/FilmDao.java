package be.vdab.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

/**
 * Represents a strategy for managing Films in an underlying store.
 */
public interface FilmDao {
    /**
     * Retrieves a Film by its primary key.
     * @param id The unique id of the film to retrieve.
     * @return The film associated with the specified id, or null if no such film exists.
     */
    Film findFilmById(int id);

    /**
     * Retrieves a List of all films.
     * @return A {@link java.util.List} of films. When there are no films, returns an empty list. Never null.
     */
    List<Film> findAllFilms();

    /**
     * Updates the specified film from the underlying store.
     * @param film The film to update field by field. This instance must have its ID set.
     * @throws IllegalArgumentException when the film is null or its id is not set.
     * @return true in case of success.
     */
    boolean updateFilm(Film film) throws IllegalArgumentException;

    /**
     * Removes the film from the underlying store.
     * @param id The primary key of the film to delete.
     * @return true in case of success.
     */
    boolean deleteFilm(int id);



}
