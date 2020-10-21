package org.example.controller;

import org.example.db.MySQLConnection;
import org.example.model.Actor;
import org.example.model.Genre;
import org.example.model.Movie;
import org.example.view.WindowMovies;

import java.util.ArrayList;

public class WindowController {


    private WindowMovies view;

    private MySQLConnection connection;

    public WindowController(WindowMovies view) {
        this.view = view;
        this.connection = MySQLConnection.getInstance();
        init();
    }

    public void init() {

        view.getGenero().setOnAction(
                (e) -> {
                    addGenero();
                }
        );
        view.getActor().setOnAction(

                (e) -> {
                    addActor();
                }

        );
        view.getPelicula().setOnAction(

                (e) -> {
                    addPelicula();
                }

        );
        view.getVinculePA().setOnAction(

                (e) -> {
                    vincularPa();
                }
        );
        view.getdPelicula().setOnAction(

                (e) -> {
                    deleteMovie();
                }

        );
        view.getdActor().setOnAction(

                (e) -> {
                    deleteActor();
                }
        );
        view.getdGenero().setOnAction(

                (e) -> {
                    deleteGenero();
                }
        );
        view.getListxGenero().setOnAction(

                (e) -> {
                    listxGenero();
                }
        );
        view.getListActorPe().setOnAction(

                (e) -> {
                    listPxActor();
                }
        );
        view.getListMoviesActor().setOnAction(

                (e) -> {
                    listAxP();
                }
        );
        view.getListAll().setOnAction(

                (e) -> {
                    listAll();
                }
        );

    }


    public void addGenero() {
        view.windowInsertGenre();
        view.getAdd().setOnAction(
                (e) -> {
                    String type = view.getTfType().getText();
                    Genre genero = new Genre(-1, type);
                    view.getRespuest().setText("Agregado: " + connection.insertGenero(genero));
                    view.getTfType().setText("Escribe el nuevo tipo..");
                }
        );
    }

    public void addActor() {
        view.windowInsertActor();
        view.getAdd().setOnAction(
                (e) -> {
                    String nombre = view.getTfType().getText();
                    Actor actorr = new Actor(-1, nombre);
                    view.getRespuest().setText("Agregado: " + connection.insertActor(actorr));
                    view.getTfType().setText("Escribe el nuevo tipo..");
                }
        );
    }


    private void addPelicula() {
        view.windowInesertMovie();
        showGeneros();
        view.getAdd().setOnAction(

                (e) -> {
                    String nameMovie = view.getTfType().getText();
                    String generoID = view.getIdGenero().getText();
                    int genreID = Integer.parseInt(generoID);
                    Movie pelicula = new Movie(-1, nameMovie,genreID);
                    view.getRespuest().setText("Agregada: " + connection.insertPelicula(pelicula, genreID));
                    view.getTfType().setText(" Escribe nuevo nombre...");
                    view.getIdGenero().setText("Escribe nuevo ID del genero...");
                }

        );

    }

    public void vincularPa() {
        view.showVincularPA();
        showMovies();
        showActors();
        view.getAdd().setOnAction(

                (e) -> {
                    int idMovie = Integer.parseInt(view.getTfType().getText());
                    int idActor = Integer.parseInt(view.getIdGenero().getText());
                    view.getRespuest().setText("Se vinculo: " + connection.vincularPA(idMovie, idActor));
                    view.getTfType().setText("Escribe nuevo ID pelicula");
                    view.getIdGenero().setText("Escribe nuevo ID actor");
                }
        );
    }

    public void deleteMovie() {
        view.showDelete();
        view.getTittleDelete().setText("--------- Eliminar Pelicula ----------");
        showMovies();
        view.getAdd().setOnAction(
                (e) -> {
                    int idMovie = Integer.parseInt(view.getTfType().getText());
                    view.getRespuest().setText("Eliminado: " + connection.deleteMovie(idMovie));
                    view.getTfType().setText("Escribe el ID de la pelicula ...");
                    view.getGetGeneros().setText("");
                    showMovies();
                }
        );
    }

    public void deleteActor() {

        view.showDelete();
        view.getTittleDelete().setText("--------- Eliminar Actor ----------");
        view.getLabe1().setText("Ingresa el ID del actor");
        view.getTfType().setText("Escribe el ID del actor...");
        showActors();
        view.getAdd().setOnAction(
                (e) -> {
                    int idActor = Integer.parseInt(view.getTfType().getText());
                    view.getRespuest().setText("Eliminado: " + connection.deleteActor(idActor));
                    view.getTfType().setText("Escribe el ID del actor...");
                    view.getGetGeneros().setText("");
                    showActors();
                }

        );
    }

    public void deleteGenero() {
        view.showDelete();
        view.getTittleDelete().setText("--------- Eliminar Genero ----------");
        view.getLabe1().setText("Ingresa el ID del Genero");
        view.getTfType().setText("Escribe el ID del Genero...");
        showGeneros();
        view.getAdd().setOnAction(
                (e) -> {
                    int idGenre = Integer.parseInt(view.getTfType().getText());
                    view.getRespuest().setText("Eliminado: " + connection.deleteGenre(idGenre));
                    view.getTfType().setText("Escribe el ID del Genero...");
                    view.getGetGeneros().setText("");
                    showGeneros();
                }

        );
    }

    public void listxGenero() {
        view.showInfo();
        view.getTittleDelete().setText("------ Listas de peliculas por genero -------");
        showGeneros();
        view.getAdd().setOnAction(
                (e) -> {
                    view.getTexArea1().setText("");
                    int idGenre = Integer.parseInt(view.getTfType().getText());
                    ArrayList<Movie> moviesxgenero = connection.listxGenero(idGenre);
                    for (int i = 0; i < moviesxgenero.size(); i++) {
                        view.getTexArea1().appendText("Nombre pelicula: " + moviesxgenero.get(i).getName() + "\n");
                    }
                    view.getTfType().setText("");

                }
        );

    }

    public void listPxActor() {
        view.showInfo();
        view.getTittleDelete().setText("------ Listas de peliculas por Actor -------");
        view.getLabe1().setText("Ingresa el ID del actor para ver las peliculas en el cual actua");
        view.getTfType().setText("Escribe el Id del actor...");
        showActors();
        view.getAdd().setOnAction(

                (e) -> {
                    view.getTexArea1().setText("");
                    int idActor = Integer.parseInt(view.getTfType().getText());
                    ArrayList<Movie> moviesxgenero = connection.listPxA(idActor);
                    for (int i = 0; i < moviesxgenero.size(); i++) {
                        view.getTexArea1().appendText("Nombre pelicula: " + moviesxgenero.get(i).getName() + "\n");
                    }
                    view.getTfType().setText("");

                }

        );
    }

    public void listAxP() {
        view.showInfo();
        view.getTittleDelete().setText("------ Listas de Actores por peliculas -------");
        view.getLabe1().setText("Ingresa el ID de la  pelicula  para ver los actores ");
        view.getTfType().setText("Escribe el Id de la pelicula...");
        showMovies();
        view.getAdd().setOnAction(

                (e) -> {
                    view.getTexArea1().setText("");
                    int idMovie = Integer.parseInt(view.getTfType().getText());
                    ArrayList<Actor> moviesxgenero = connection.listAxP(idMovie);
                    for (int i = 0; i < moviesxgenero.size(); i++) {
                        view.getTexArea1().appendText("Nombre Actor: " + moviesxgenero.get(i).getName() + "\n");
                    }
                    view.getTfType().setText("");

                }

        );
    }

    public void listAll() {
        view.showinfoAll();
        view.getTittleDelete().setText("---------- Cartelera -----------");
        view.getTexArea1().appendText("---------Cinema Hollywood---------" + "\n");
      /* ArrayList<String> infos = connection.infoALl();
        for (int i = 0;i<infos.size();i++){
            view.getTexArea1().appendText(""+ infos.get(i).toString()+"\n");
        }*/
        ArrayList<Movie> movies = connection.getAllMovies();
        int genero = -1;
        String genre = "";
        String nameMovie = null;
        for (int i = 0; i < movies.size(); i++) {
            nameMovie = movies.get(i).getName();
            genero = movies.get(i).getGeneroID();
            ArrayList<Actor> actors = connection.listAxP(movies.get(i).getId());
            genre = connection.getGenre(genero);
            String msg = "\nTitulo: " + nameMovie + "\n Genero: " + genre + "\n Actores: ";
            for (int k = 0; k < actors.size(); k++) {
                msg += actors.get(k).getName() + ", ";
            }
            msg += "\n------------------------";
            view.getTexArea1().appendText(msg);
        }

    }


    public void showMovies() {
        ArrayList<Movie> movies = connection.getAllMovies();
        view.getGetGeneros().appendText("---------- Peliculas ---------------" + "\n");
        for (int i = 0; i < movies.size(); i++) {
            int id = movies.get(i).getId();
            String nombre = movies.get(i).getName();
            view.getGetGeneros().appendText("ID: " + id + " Pelicula: " + nombre + "\n");
        }
    }


    public void showActors() {
        view.getGetGeneros().appendText("----------   Actores  ---------------" + "\n");
        ArrayList<Actor> actors = connection.getAllActors();
        for (int i = 0; i < actors.size(); i++) {
            int id = actors.get(i).getId();
            String nombre = actors.get(i).getName();
            view.getGetGeneros().appendText("ID: " + id + " Actor: " + nombre + "\n");
        }
    }

    public void showGeneros() {
        view.getGetGeneros().appendText("----------   Generos  ---------------" + "\n");
        ArrayList<Genre> generos = connection.getAllGenere();
        for (int i = 0; i < generos.size(); i++) {
            int id = generos.get(i).getId();
            String tipo = generos.get(i).getType();
            view.getGetGeneros().appendText("ID: " + id + " Genero: " + tipo + "\n");
        }
    }


}
