package org.example;

import java.security.SecureRandom;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main extends Application {

    public String geraSenha() {
        SecureRandom random = new SecureRandom();

        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~";

        StringBuilder senhaFinal = new StringBuilder();

        // Gera 12 carcateres aleatorios
        for (int i = 0; i < 12; i++) {
            int posicao = random.nextInt(senhaFinal.length() + 1);
            int index = random.nextInt(caracteres.length());//tamamho maximo para a geraçao aleatoria
            senhaFinal.insert(posicao, caracteres.charAt(index));//adciona na senha final o char na posição index
        }

        return senhaFinal.toString();
    }
    @Override
    public void start(Stage stage) throws Exception {
        Label senhaLabel = new Label("Senha gerada: ");
        senhaLabel.setStyle("-fx-font-size: 20pt; -fx-text-fill: red");

        Label gerarLabel = new Label("Clique aqui para gerar uma senha");
        gerarLabel.setStyle("-fx-font-size: 20pt; -fx-text-fill: red");

        Label senha = new Label();
        senha.setStyle("-fx-font-size: 15pt; -fx-text-fill: red");


        Button gerar = new Button("Gerar senha");
        gerar.setStyle("-fx-background-color: rgb(150,0,0); -fx-text-fill: black;-fx-background-radius: 10 10 10 10;");
        gerar.setOnAction(event -> {
            senha.setText(geraSenha());
        });

        VBox layout = new VBox(10, gerarLabel, gerar, senhaLabel, senha);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: black");

        Scene cena = new Scene(layout, 500, 500);
        stage.setScene(cena);
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
