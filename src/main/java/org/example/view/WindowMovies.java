package org.example.view;

import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.controller.WindowController;

import java.awt.*;

public class WindowMovies extends Stage {

    private WindowController controller;

    private Scene scene;

    private HBox hbScene;

    private VBox vBoxOptions;

    private AnchorPane aPoperations;

    private AnchorPane aPOptios;

    private Label labelInsert;

    private Label labelDelete;

    private Label labelSelect;

    private  Label labe1;

    private Label tittleDelete;

    private MenuButton mbInsert;

    private MenuButton mbDelete;

    private MenuButton mbList;

    private MenuItem genero;
    private MenuItem pelicula;
    private MenuItem actor;
    private MenuItem vinculePA;

    private MenuItem dGenero;
    private MenuItem dPelicula;
    private MenuItem dActor;

    private MenuItem listxGenero;
    private MenuItem listMoviesActor;
    private MenuItem listActorPe;
    private MenuItem listAll;

    private Separator separator;

    private Separator separator2;

    private TextField tfType;

    private TextField idGenero;

    private Button add;

    private VBox operations;

    private Label respuest;

    private TextArea getGeneros;

    private TextArea texArea1;


    public WindowMovies() {

        vBoxOptions = new VBox();
        aPoperations = new AnchorPane();
        separator = new Separator();
        separator2 = new Separator();
        separator.setOrientation(Orientation.VERTICAL);
        separator.setDisable(true);
        separator.setPrefHeight(60);
        separator2.setOrientation(Orientation.VERTICAL);
        separator2.setDisable(true);
        separator2.setPrefHeight(60);
        labelInsert = new Label("Seleccione Tipo de Usuario");
        labelDelete = new Label("Seleccione el tipo que quiere \n eliminar");
        labelSelect = new Label("Seleccione el tipo a buscar");
        mbInsert = new MenuButton("Insert");
        mbDelete = new MenuButton("Delete");
        mbList = new MenuButton("Search");
        genero = new MenuItem("Genero");
        pelicula = new MenuItem("Pelicula");
        actor = new MenuItem("Actor");
        vinculePA = new MenuItem("Vincular Pelicula Actor");
        mbInsert.getItems().clear();
        mbInsert.getItems().addAll(genero, pelicula, actor,vinculePA);
        vBoxOptions.getChildren().addAll(labelInsert, mbInsert, separator, labelDelete);

        dGenero = new MenuItem("Eliminar Genero");
        dPelicula = new MenuItem("Eliminar Pelicula");
        dActor = new MenuItem("Eliminar Actor");
        mbDelete.getItems().clear();
        mbDelete.getItems().addAll(dGenero, dPelicula, dActor);
        vBoxOptions.getChildren().addAll(mbDelete, separator2, labelSelect);

        listxGenero = new MenuItem("Lista de peliculas por genero");
        listActorPe = new MenuItem("Lista de peliculas de un actor");
        listMoviesActor = new MenuItem("Lista de Actores de una pelicula");
        listAll = new MenuItem("Mostrar toda la información");
        mbList.getItems().clear();
        mbList.getItems().addAll(listxGenero,listActorPe,listMoviesActor,listAll);
        vBoxOptions.getChildren().add(mbList);

        operations = new VBox();
        aPOptios = new AnchorPane();
        aPOptios.getChildren().add(operations);
        hbScene = new HBox();
        hbScene.getChildren().addAll(vBoxOptions, aPoperations);
        scene = new Scene(hbScene, 490, 500);
        this.setTitle("~~~~~~~ Data Base Cinema ~~~~~~~");
        this.setScene(scene);


        controller = new WindowController(this);

    }


    public MenuItem getGenero() {
        return genero;
    }

    public MenuItem getPelicula() {
        return pelicula;
    }

    public MenuItem getActor() {
        return actor;
    }

    public MenuItem getdGenero() {
        return dGenero;
    }

    public MenuItem getdPelicula() {
        return dPelicula;
    }

    public MenuItem getdActor() {
        return dActor;
    }

    public TextField getTfType() {
        return tfType;
    }

    public Button getAdd() {
        return add;
    }

    public Label getRespuest() {
        return respuest;
    }

    public TextArea getGetGeneros() {
        return getGeneros;
    }

    public AnchorPane getaPoperations() {
        return aPoperations;
    }

    public TextField getIdGenero() {
        return idGenero;
    }

    public MenuItem getVinculePA() {
        return vinculePA;
    }

    public Label getLabe1() {
        return labe1;
    }

    public MenuItem getListxGenero() {
        return listxGenero;
    }

    public MenuItem getListMoviesActor() {
        return listMoviesActor;
    }

    public MenuItem getListActorPe() {
        return listActorPe;
    }

    public MenuItem getListAll() {
        return listAll;
    }

    public TextArea getTexArea1() {
        return texArea1;
    }

    public Label getLabelDelete() {
        return labelDelete;
    }

    public Label getTittleDelete() {
        return tittleDelete;
    }

    public void windowInsertGenre() {
        Label title = new Label("----------- Registrar Genero -----------");
        aPoperations.getChildren().clear();
        operations.getChildren().clear();
        //operations = new VBox();
        Label tipo = new Label("Escribe el tipo de genero");
        tfType = new TextField("Escribe el tipo...");
        add = new Button("Agregar");
        respuest = new Label();
        operations.getChildren().addAll(title,tipo, tfType, add, respuest);
        aPoperations.getChildren().addAll(operations);
    }

    public void windowInsertActor() {
        aPoperations.getChildren().clear();
        operations.getChildren().clear();
        Label title = new Label("---------- Registrar Actor -----------");
        Label tipo = new Label("Escribe el nombre del Actor");
        tfType = new TextField("Escribe el nombre...");
        add = new Button("Agregar");
        respuest = new Label();
        operations.getChildren().addAll(title,tipo, tfType, add, respuest);
        aPoperations.getChildren().addAll(operations);
    }

    public void windowInesertMovie() {
        aPoperations.getChildren().clear();
        operations.getChildren().clear();
        Label title = new Label("----------- Registrar Pelicula --------");
        Label nameMovie = new Label("Ingresa el nombre de la pelicula");
        Label typeMovie = new Label("Ingresa el Numero (ID) del genero de la pelicula");
        getGeneros = new TextArea();
        getGeneros.setPrefSize(300,300);
        respuest = new Label();
        add = new Button("Agregar");
        tfType = new TextField("Escribe el nombre...");
        idGenero = new TextField("Escribe el numero...");
        operations.getChildren().addAll(title,nameMovie, tfType, getGeneros, typeMovie, idGenero, add, respuest);
        aPoperations.getChildren().add(operations);

    }

    public void showVincularPA() {
        aPoperations.getChildren().clear();
        operations.getChildren().clear();
        getGeneros = new TextArea();
        getGeneros.setPrefSize(300,300);
        Label title = new Label("----------- Vinvular Pelicula Actor --------");
        Label idP = new Label("Ingresa el Id de la pelicula");
        Label idA = new Label("Ingresa el Id del Actor");
        tfType = new TextField("Escribe el ID pelicula...");
        idGenero = new TextField("Escribe el ID Actor...");
        add = new Button("Agregar");
        respuest = new Label();
        operations.getChildren().addAll(title,getGeneros, idP, tfType, idA, idGenero, add, respuest);
        aPoperations.getChildren().add(operations);
    }
    public  void showDelete(){
        aPoperations.getChildren().clear();
        operations.getChildren().clear();
        tittleDelete = new Label();
        getGeneros = new TextArea();//cambiar nombre por textArea1
        getGeneros.setMaxSize(300,300);
        labe1 = new Label("Ingresa el Id de la pelicula a eliminar");
        tfType = new TextField("Escribe el ID pelicula...");
        tfType.setMaxSize(300,300);
        add = new Button("Eliminar");
        respuest = new Label();
        operations.getChildren().addAll(tittleDelete,getGeneros,labe1,tfType,add,respuest);
        aPoperations.getChildren().add(operations);
    }
    public  void showInfo(){

        aPoperations.getChildren().clear();
        operations.getChildren().clear();
        getGeneros = new TextArea();
        getGeneros.setMaxSize(300,300);
        tittleDelete = new Label();
        labe1 = new Label("Ingresa el Id del genero para ver la lista de peliculas");
        tfType = new TextField("Escribe el ID del genero...");
        add = new Button("Buscar");
        texArea1 = new TextArea();
        texArea1.setMaxSize(300,300);
        operations.getChildren().addAll(tittleDelete,getGeneros,labe1,tfType,add,texArea1);
        aPoperations.getChildren().add(operations);

    }

    public void showinfoAll() {
        aPoperations.getChildren().clear();
        operations.getChildren().clear();
        tittleDelete = new Label();
        texArea1 = new TextArea();
        texArea1.setMaxSize(300,300);
        operations.getChildren().addAll(tittleDelete,texArea1);
        aPoperations.getChildren().add(operations);
    }
}
