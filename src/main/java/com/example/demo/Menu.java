package com.example.demo;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.net.URL;
import java.net.HttpURLConnection;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.io.IOException;


public class Menu extends Application {

    public void start(Stage primaryStage) throws Exception {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setVgap(10);
        grid.setHgap(10);

        Button consulterBtn = new Button("Consulter les données");
        Button telechargerBtn = new Button("Télécharger le fichier");
        telechargerBtn.setOnAction(e -> {
            try {
                URL url = new URL("https://download.data.grandlyon.com/ws/grandlyon/pvo_patrimoine_voirie.pvostationvelov/all.json?maxfeatures=-1");
                HttpURLConnection http = (HttpURLConnection)url.openConnection();
                http.setRequestMethod("GET");
                InputStream in = http.getInputStream();
                Files.copy(in, Paths.get("data-velov.json"), StandardCopyOption.REPLACE_EXISTING);
                in.close();
                http.disconnect();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        Button proposerBtn = new Button("Proposer une station");
        Button supprimerBtn = new Button("Supprimer une proposition");

        grid.add(consulterBtn, 0, 0);
        grid.add(telechargerBtn, 1, 0);
        grid.add(proposerBtn, 0, 1);
        grid.add(supprimerBtn, 1, 1);

        Scene scene = new Scene(grid, 400, 200);
        primaryStage.setScene(scene);
        primaryStage.show();

        consulterBtn.setOnAction(e -> {
            GridPane dataGrid = new GridPane();
            dataGrid.setPadding(new Insets(10));
            dataGrid.setVgap(10);
            dataGrid.setHgap(10);

            Label addressLabel = new Label("Adresse:");
            dataGrid.add(addressLabel, 0, 0);
            Label districtLabel = new Label("Arrondissement:");
            dataGrid.add(districtLabel, 0, 1);
            Label stationNameLabel = new Label("Nom de la station:");
            dataGrid.add(stationNameLabel, 0, 2);
            Label terminalsLabel = new Label("Nombre de bornettes:");
            dataGrid.add(terminalsLabel, 0, 3);

            TextField addressField = new TextField();
            dataGrid.add(addressField, 1, 0);
            TextField districtField = new TextField();
            dataGrid.add(districtField, 1, 1);
            TextField stationNameField = new TextField();
            dataGrid.add(stationNameField, 1, 2);
            TextField terminalsField = new TextField();
            dataGrid.add(terminalsField, 1, 3);

            Button filterBtn = new Button("Filtrer");
            filterBtn.setOnAction(filterEvent -> {

            });
            dataGrid.add(filterBtn, 0, 4);

            Scene dataScene = new Scene(dataGrid, 400, 200);
            primaryStage.setScene(dataScene);
            primaryStage.show();
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}