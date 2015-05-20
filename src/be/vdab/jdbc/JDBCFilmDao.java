package be.vdab.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jeansmits on 20/05/15.
 */
public class JDBCFilmDao implements FilmDao{

    @Override
    public Film findFilmById(int id) {
        Connection c= null;
        Film film = null;
        try {
            c = createConnection();
            PreparedStatement ps = c.prepareStatement(
                    "select * from film where film_id = ?"
                            // kan ook met concatenatie : "select * from film where film_id = " + id -->> SQL INJECTION
                            // (tenzij de id bij het inlezen parsen naar int)
            );
            ps.setInt(1, id);   //geen SQL-injectie nodig, JDBC 'sanitized' de inkomende waarde door deze helemaal mee te geven als String
            ps.execute();
            ResultSet rs = ps.executeQuery();
            rs.next();
            film = new Film(rs.getInt("film_id"), rs.getString("title"), rs.getString("release_year"));

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (c != null)  {
                    c.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return film;
    }

    @Override
    public List<Film> findAllFilms() {
        Connection c= null;
        List <Film> lfilm = new ArrayList<>();

        try {
            c = createConnection();
            PreparedStatement ps = c.prepareStatement(
                    "select * from film"
            );
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                lfilm.add(new Film(rs.getInt("film_id"),rs.getString("title"), rs.getString("release_year")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (c != null)  {
                    c.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return lfilm;
    }
    @Override
    public boolean updateFilm(Film film) throws IllegalArgumentException {
        Connection c= null;
        try {
            c = createConnection();
            PreparedStatement ps = c.prepareStatement(
                    "UPDATE film SET  title=? , release_year=? " +
                            "WHERE film_id = ?"
            );
            ps.setString(1,film.getTitle());
            ps.setString(2, film.getReleaseYear());
            ps.setInt(3, film.getId());
            ps.execute();
            return ps.executeUpdate()==1;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (c != null)  {
                    c.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean deleteFilm(int id) {
        Connection c= null;
        try {
            c = createConnection();
            PreparedStatement ps = c.prepareStatement(
                    "DELETE from film WHERE film_id = ?"
            );
            ps.setInt(1, id);
            ps.execute();
            return ps.executeUpdate()==1;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (c != null)  {
                    c.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    static Connection createConnection()    {
        String url = "jdbc:mysql://localhost:3306/sakila";
        String username = "root";
        String password = "";
        String driverClassName = "com.mysql.jdbc.Driver";

        try {
            Class.forName(driverClassName);
            return DriverManager.getConnection(url, username, password);

        } catch (ClassNotFoundException | SQLException e)    {
            throw new RuntimeException("Unable to create connection due to underlying error", e);
        }

    }
}
