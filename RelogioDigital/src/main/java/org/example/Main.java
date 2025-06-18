package org.example;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import static javafx.application.Application.launch;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main extends Application {

    public String getHora(){
        LocalTime horaAtual = LocalTime.now();
        //formatando horas no relogio digital
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        return horaAtual.format(formatter);
    }

    @Override
    public void start(Stage stage) throws Exception {

        Label titulo = new Label("Relogio Digital");
        titulo.setStyle("-fx-font-size: 24pt; -fx-text-fill: rgb(180, 0, 0)");
        Label hora = new Label();
        hora.setStyle("-fx-font-size: 22pt; -fx-text-fill: red");



        VBox layout = new VBox(10, titulo, hora);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: black");

        Scene cena = new Scene(layout, 300, 300);
        stage.setTitle("Relogio Digital");
        stage.setScene(cena);
        stage.show();


        hora.setText(getHora());
        Timeline relogio= new Timeline(
                //realiza a atualizaçao do evento no tempo determinado
                new KeyFrame(Duration.seconds(1), actionEvent -> hora.setText(getHora())));
        relogio.setCycleCount(Timeline.INDEFINITE);
        relogio.play();


    }


    public static void main(String[] args) {
        launch(args);

    }


}