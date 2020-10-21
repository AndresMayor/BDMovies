package org.example.main;

import javafx.application.Application;
import javafx.stage.Stage;
import org.example.db.MySQLConnection;
import org.example.view.WindowMovies;

/**
 * Hello world!
 *
 */
public class App extends Application
{
    public static void main( String[] args )
    {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        WindowMovies windowMo = new WindowMovies();
        windowMo.show();
    }

    @Override
    public void stop(){
        MySQLConnection connection =MySQLConnection.getInstance();
        connection.closeDB();
        System.exit(0);

    }

}
