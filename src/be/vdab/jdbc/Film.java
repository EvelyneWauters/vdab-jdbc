package be.vdab.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by jeansmits on 20/05/15.
 */
public class Film  {
    int id;
    String title;
    String releaseYear;

    public Film(int id, String title, String realeaseYear)     {
        this.id = id;
        this.title = title;
        this.releaseYear = realeaseYear;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title)  {
        this.title = title;
    }

    public String getTitle()    {
        return title;
    }

    public void setReleaseYear(String releaseYear)    {
        this.releaseYear = releaseYear;
    }

    public String getReleaseYear ()       {
        return releaseYear;
    }

    public String toString()    {
        return " title : " + title + "  release year : " + releaseYear + "\n";
    }
}
