package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.print.DocFlavor;
import javax.swing.*;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main extends Application {

    private static final String CLAVE = null;
    private static final String CONNETION_String = "jdbc:mysql://127.0.0.1:3306/world";
    private static final String Usuario="root";
    private static Connection conexion;


    @Override
    public void start(Stage primaryStage) throws Exception{
        abrirConexion();
        Parent root = FXMLLoader.load(getClass().getResource("vistas/sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root,400, 375));
        primaryStage.show();
    }

    public static void abrirConexion(){
        try {
            conexion= DriverManager.getConnection(CONNETION_String,Usuario,CLAVE);
            JOptionPane.showMessageDialog(null,"exito");
        } catch (SQLException e) {
            e.printStackTrace();

            JOptionPane.showMessageDialog(null,"la conexion fallo");

        }
    }

    public static Connection getConexion(){
        return  conexion;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
