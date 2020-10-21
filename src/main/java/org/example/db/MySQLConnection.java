package org.example.db;

import com.sun.tools.javah.Gen;
import org.example.model.Actor;
import org.example.model.Genre;
import org.example.model.Movie;

import java.sql.*;
import java.util.ArrayList;

public class MySQLConnection {

    private static MySQLConnection instance = null;
    private Connection connection;

    private MySQLConnection() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbmovies", "root", "");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static synchronized MySQLConnection getInstance() {

        if (instance == null) {
            instance = new MySQLConnection();
        }
        return instance;
    }

    public void closeDB() {
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public boolean insertGenero(Genre genero) {
        boolean add = true;
        try {
            Statement statement = connection.createStatement();
            String sql = ("INSERT  INTO generos (tipo) VALUES ('$TYPE')")
                    .replace("$TYPE", genero.getType());
            statement.execute(sql);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            add = false;
        }
        return add;
    }

    public boolean insertActor(Actor actor) {
        boolean add = true;
        try {
            Statement statement = connection.createStatement();
            String sql = ("INSERT  INTO actores (nombre) VALUES ('$NOMBRE')")
                    .replace("$NOMBRE", actor.getName());
            statement.execute(sql);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            add = false;
        }
        return add;
    }

    public boolean insertPelicula(Movie movie, int generoID) {
        boolean add = true;
        try {
            Statement statement = connection.createStatement();
            String sql = ("INSERT  INTO peliculas (nombre,generoID) VALUES ('$NOMBRE','$GENEROID')")
                    .replace("$NOMBRE", movie.getName())
                    .replace("$GENEROID", "" + generoID);
            statement.execute(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            add = false;
        }
        return add;
    }

    public ArrayList<Genre> getAllGenere() {
        ArrayList<Genre> output = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String sql = ("SELECT * FROM generos ");
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                int id = result.getInt(1);
                String tipo = result.getString(2);
                Genre genero = new Genre(id, tipo);
                output.add(genero);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return output;
    }

    public boolean vincularPA(int idMovie, int idActor) {
        boolean add = true;
        try {
            Statement statement = connection.createStatement();
            String sql = ("INSERT INTO peliculas_actores (pelicuaID,actorID) VALUES ('$PELICULAID','$ACTORID')")
                    .replace("$PELICULAID", "" + idMovie)
                    .replace("$ACTORID", "" + idActor);
            statement.execute(sql);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            add = false;
        }
        return add;
    }

    public ArrayList<Movie> getAllMovies() {
        ArrayList<Movie> output = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String sql = ("SELECT * FROM peliculas ");
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                int id = result.getInt(1);
                String nombre = result.getString(2);
                int idGenero = result.getInt(3);
                Movie genero = new Movie(id, nombre,idGenero);
                output.add(genero);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return output;
    }

    public ArrayList<Actor> getAllActors() {
        ArrayList<Actor> output = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String sql = ("SELECT * FROM actores ");
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                int id = result.getInt(1);
                String nombre = result.getString(2);
                Actor actor = new Actor(id, nombre);
                output.add(actor);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return output;
    }


    public boolean deleteMovie(int idMovie) {
        boolean removed = true;
        try {
            Statement statement = connection.createStatement();
            String sql = ("DELETE  FROM peliculas_actores  WHERE peliculas_actores.pelicuaID=$idMovie")
                    .replace("$idMovie", "" + idMovie);
            String sql2 = ("DELETE  FROM peliculas  WHERE peliculas.id=$id ")
                    .replace("$id", "" + idMovie);
            statement.execute(sql);
            statement.execute(sql2);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            removed = false;
        }
        return removed;
    }

    public boolean deleteActor(int idActor) {
        boolean removed = true;
        try {
            Statement statement = connection.createStatement();
            String sql = ("DELETE  FROM peliculas_actores  WHERE peliculas_actores.actorID=$idActor")
                    .replace("$idActor", "" + idActor);
            String sql2 = ("DELETE  FROM actores  WHERE actores.id=$id ")
                    .replace("$id", "" + idActor);
            statement.execute(sql);
            statement.execute(sql2);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            removed = false;
        }
        return removed;
    }

    public boolean deleteGenre(int idGenre) {
        boolean removed = true;
        try {
            Statement statement = connection.createStatement();
            String sql = ("SELECT id FROM peliculas WHERE peliculas.generoID = $idGenre")
                    .replace("$idGenre", "" + idGenre);
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                int idMovieGenre = result.getInt(1);
                deleteMovie(idMovieGenre);
            }
            String sql2 = ("DELETE  FROM generos  WHERE generos.id=$id ")
                    .replace("$id", "" + idGenre);
            statement.execute(sql2);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            removed = false;
        }
        return removed;
    }


    public ArrayList<Movie> listxGenero(int idGenre) {

        ArrayList<Movie> output = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String sql = ("SELECT id , nombre ,generoID FROM peliculas WHERE peliculas.generoID=$idGenero")
                    .replace("$idGenero", "" + idGenre);
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                int id = result.getInt(1);
                String nombre = result.getString(2);
                int idGen = result.getInt(3);
                Movie movie = new Movie(id, nombre,idGen);
                output.add(movie);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return output;

    }

    public ArrayList<Movie> listPxA(int idActor) {
        ArrayList<Movie> output = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String sql = ("SELECT peliculas.id ,peliculas.nombre ,peliculas.generoID FROM (peliculas INNER JOIN peliculas_actores ON peliculas.id = peliculas_actores.pelicuaID) INNER JOIN actores ON  peliculas_actores.actorID = actores.id  WHERE actores.id = $idAct")
                    .replace("$idAct", "" + idActor);
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                int id = result.getInt(1);
                String nombre = result.getString(2);
                   int idGen =  result.getInt(3);
                Movie movie = new Movie(id, nombre,idGen);
                output.add(movie);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return output;
    }
    //SELECT actores.id , actores.nombre FROM ( actores INNER JOIN peliculas_actores ON actores.id= peliculas_actores.actorID) INNER JOIN peliculas ON peliculas.id= peliculas_actores.pelicuaID  WHERE  peliculas.id=5
    public ArrayList<Actor> listAxP(int idMovie) {
        ArrayList<Actor> output = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String sql = ("SELECT actores.id , actores.nombre FROM ( actores INNER JOIN peliculas_actores ON actores.id= peliculas_actores.actorID) INNER JOIN peliculas ON peliculas.id= peliculas_actores.pelicuaID  WHERE  peliculas.id=$idM")
                    .replace("$idM", "" + idMovie);
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                int id = result.getInt(1);
                String nombre = result.getString(2);
                Actor actor = new Actor(id, nombre);
                output.add(actor);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return output;
    }

   public ArrayList<String>  infoALl(){
        ArrayList<String> ouput = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String sql = ("SELECT peliculas.nombre, actores.nombre , generos.tipo FROM (peliculas LEFT JOIN peliculas_actores ON peliculas.id = peliculas_actores.pelicuaID) LEFT JOIN actores ON peliculas_actores.actorID = actores.id INNER JOIN generos ON generos.id=peliculas.generoID");
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                String nombreMovie ="TITULO: "+result.getString(1);
                String nombreActor ="ACTOR: "+result.getString(2);
                String tipo ="GENERO: "+result.getString(3);
                String obj =nombreMovie+". "+tipo+". "+nombreActor+".";
                ouput.add(obj);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return  ouput;
    }

    public String getGenre(int idGenre){
        String genero = "";
        try {
            Statement statement = connection.createStatement();
            String sql = ("SELECT generos.id, generos.tipo FROM generos WHERE generos.id = $generid")
                    .replace("$generid",""+idGenre);
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                genero = result.getString(2);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return genero;
    }

}
