package org.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;

public class Main extends Application {

    private char[][] palavras = {
            {'b', 'a', 'n', 'a', 'n', 'a'},
            {'m', 'a', 'c', 'a', 'c', 'o'},
            {'m', 'a', 'c', 'a'},
            {'o', 'l', 'h', 'o'}
    };

    private char[][] resultados = {
            {'_', '_', '_', '_', '_', '_'},
            {'_', '_', '_', '_', '_', '_'},
            {'_', '_', '_', '_'},
            {'_', '_', '_', '_'}
    };

    //index aleatorio
    private int index = new SecureRandom().nextInt(palavras.length);
    //tentativas maximas do jogo
    private int tentativas = 7;
    //aramzenando letras usadas
    private ArrayList<Character> letrasUsadas = new ArrayList<>();

    //atributos da classe
    private Label labelPalavra = new Label();
    private Label labelTentativas = new Label("Tentativas restantes: 7");
    private Label labelUsadas = new Label("Letras já usadas: ");
    private Label labelMensagem = new Label();

    @Override
    public void start(Stage stage) {
        //pegando input do usauario
        TextField campoLetra = new TextField();
        campoLetra.setPromptText("Digite uma letra");

        Button botaoPalpite = new Button("Palpite");

        //logica do jogo
        botaoPalpite.setOnAction(e -> {
            //se tentativas for maior que zero e resultados na posiçao index for diferente a palavras na posiçao index
            if (tentativas > 0 && !Arrays.equals(resultados[index], palavras[index])) {
                //pega o texto do input retira os espaços vazios e deixa minusculo
                String texto = campoLetra.getText().trim().toLowerCase();
                //verifica se foi passado apenas um caractere
                if (texto.length() != 1) {
                    labelMensagem.setText("Digite apenas UMA letra.");
                    return;
                }
                //pega o char que foi passado pelo input e armazenado em texto
                char tentativa = texto.charAt(0);
                //limpa o input
                campoLetra.clear();

                //faz a verificaçao das letras usadas
                if (letrasUsadas.contains(tentativa)) {
                    labelMensagem.setText("Letra já usada.");
                    return;
                }else{
                    letrasUsadas.add(tentativa);
                    tentativas--;
                }

                boolean acertou = false;

                //colocando a letra chutada no array resultados estiver certa
                for (int i = 0; i < palavras[index].length; i++) {
                    if (tentativa == palavras[index][i]) {
                        resultados[index][i] = tentativa;

                    }
                }

                //atualiza os labels a cada ciclo de tentativa
                atualizarTela();

                //determina o fim e a vitoria do jogo
                if (Arrays.equals(resultados[index], palavras[index])) {
                    labelMensagem.setText("Parabéns! Você venceu!");
                } else if (tentativas == 0) {
                    labelMensagem.setText("Você perdeu! A palavra era: " + new String(palavras[index]));
                }
            }
        });//fim do action

        //inicia os valores na tela
        atualizarTela();

        //montando o layout na tela
        VBox layout = new VBox(10,
                new Label("Jogo da Forca"),
                labelPalavra,
                campoLetra,
                botaoPalpite,
                labelTentativas,
                labelUsadas,
                labelMensagem
        );

        layout.setStyle("-fx-padding: 20px;");
        stage.setScene(new Scene(layout, 400, 300));
        stage.setTitle("Forca em JavaFX");
        stage.show();
    }

    private void atualizarTela() {
        //mostrando resultado
        labelPalavra.setText(new String(resultados[index]));
        //mostrando as tentativas
        labelTentativas.setText("Tentativas restantes: " + tentativas);
        //mostrando as letras usadas
        labelUsadas.setText("Letras já usadas: " + letrasUsadas);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
