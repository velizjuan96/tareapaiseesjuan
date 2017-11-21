package sample.controladores;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import sample.Main;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.Observable;


public class Controller {

    public TextField txtpais;
    public ListView lvciudad;
    public ListView lvpais;
    public Label lvinformacionn;
    public ListView lvinformacion;

    ResultSet registro;
    private ObservableList<String> paises = FXCollections.observableArrayList();
    private ObservableList<String> ciudades = FXCollections.observableArrayList();
    private ObservableList<String> informacion=FXCollections.observableArrayList();

    public void initialize() {
        lvpais.setItems(paises);
        lvinformacion.setItems(informacion);

    }


    public void buscarPais(KeyEvent keyevent) {


        String nombreBusqueda = txtpais.getText().trim();
        if (nombreBusqueda.length() >= 1) {
            Connection con = Main.getConexion();

            try {
                Statement stml = con.createStatement();
                String sql = "SELECT Name FROM country where Name like '" + nombreBusqueda + "%'";
                ResultSet resultado = stml.executeQuery(sql);
                while (resultado.next()) {

                    paises.add(resultado.getString("Name"));

                }


            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    }


    public void lvciudades(MouseEvent mouseEvent) throws SQLException {

        Connection con = Main.getConexion();
        Statement stml = con.createStatement();

        ciudades.clear();
        lvciudad.setItems(ciudades);
        try {
            String pais = (String) lvpais.getSelectionModel().getSelectedItem();
            String consulta = "SELECT Name FROM  city WHERE CountryCode=(SELECT Code FROM country WHERE Name='" + pais + "')";
            registro = stml.executeQuery(consulta);
            while (registro.next()) {
                ciudades.add(registro.getString("Name"));
            }
            lvciudad.setItems(ciudades);
            registro.close();
        } catch (SQLException e) {


        }


    }


    public void informacion(MouseEvent mouseEvent) throws SQLException {
        Connection con = Main.getConexion();
        Statement stml = con.createStatement();

        informacion.clear();
        lvinformacion.setItems(informacion);

        try {
            String ciudad = (String) lvciudad.getSelectionModel().getSelectedItem();
            String consulta = "SELECT District,Population FROM  city WHERE CountryCode=(SELECT CountryCode FROM city WHERE Name='" + ciudad + "')";
            registro = stml.executeQuery(consulta);
            while (registro.next()) {
                informacion.add(registro.getString("District"));
                informacion.add(registro.getString("Population"));
            }
            lvinformacion.setItems(informacion);
            registro.close();
        } catch (SQLException e) {


        }





    }
}

