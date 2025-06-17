package org.example;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Calculadora de IMC");
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(10);
        gridPane.setHgap(10); // ajuda a dar espaçamento horizontal também

// Criação dos elementos
        Label Peso = new Label("Peso: ");
        TextField pesoInput = new TextField();
        pesoInput.setMaxWidth(200);
        pesoInput.setPromptText("Digite aqui o seu peso");

        Label Altura = new Label("Altura: ");
        TextField alturaInput = new TextField();
        alturaInput.setMaxWidth(200);
        alturaInput.setPromptText("Digite aqui sua altura");

        Label resultado = new Label();
        Label warning = new Label();

        Button GerarImc = new Button("Gerar IMC");

// Ação do botão
        GerarImc.setOnAction(actionEvent -> {
            if (!pesoInput.getText().isEmpty() && !alturaInput.getText().isEmpty()) {
                double peso = Double.parseDouble(pesoInput.getText());
                double altura = Double.parseDouble(alturaInput.getText());

                double imc = peso / (altura * altura);
                resultado.setText(String.format("Seu IMC é: %.2f", imc));
                warning.setText(" ");
            } else {
                warning.setText("Campos não podem estar vazios!");
            }
        });



// Tabela de IMC
        Label tituloTabela = new Label("Tabela de classificação de IMC");
        Text tabela = new Text("IMC\tClassificação\tObesidade (grau)\n" +
                "Menor que 18,5\tMagreza\t0\n" +
                "Entre 18,5 e 24,9\tNormal\t0\n" +
                "Entre 25,0 e 29,9\tSobrepeso\tI\n" +
                "Entre 30,0 e 39,9\tObesidade\tII\n" +
                "Maior que 40,0\tObesidade Grave\tIII");


        VBox layout = new VBox(10, Peso, pesoInput, Altura, alturaInput, GerarImc, resultado, warning, tituloTabela, tabela);
        layout.setAlignment(Pos.CENTER);

        Scene cena = new Scene(layout, 400, 450);
        stage.setScene(cena);
        stage.show();


    }

    public static void main(String[] args) {
        launch(args);
    }
}