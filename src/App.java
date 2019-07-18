import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class App extends Application {

    private TextArea cmd;
    private TextArea desc;
    private List<Image> images;
    private ImageView imgView;

    private Engine engine;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        engine = new Engine(this);

        // GERENCIADOR DE LAYOUT
        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setHgap(10); // espaçamento horizontal
        root.setVgap(10); // espaçamento vertical
        root.setPadding(new Insets(35, 35, 35, 35));
        root.setBackground(Background.EMPTY);

        images = new ArrayList<>();

        loadImages();

        imgView = new ImageView();
        imgView.setImage(images.get(0));
        imgView.setPreserveRatio(true);
        imgView.setSmooth(true);
        imgView.setCache(true);

        // IMAGEM SALA
        HBox sala = new HBox();
        sala.getChildren().add(imgView);
        sala.setAlignment(Pos.CENTER);

        // DESCRIÇÃO SALA
        desc = new TextArea();
        desc.setPadding(new Insets(10));
        desc.setEditable(false);
        desc.setMaxSize(350, 400);
        sala.getChildren().add(desc);

        root.add(sala, 0, 0);

        // ENTRADA DE COMANDO
        HBox comando = new HBox(10);
        comando.setAlignment(Pos.CENTER);

        Label lbCmd = new Label("Comando:");
        comando.getChildren().add(lbCmd);

        cmd = new TextArea();
        cmd.setMaxHeight(30);
        comando.getChildren().add(cmd);

        Button butOk = new Button("Ok");
        butOk.setOnAction(e -> engine.joga(cmd.getText()));
        comando.getChildren().add(butOk);

        root.add(comando, 0, 2);

        // JANELA

        Scene scene = new Scene(root);
        scene.getStylesheets().add("estilo.css");
        primaryStage.setTitle("Daciolo CyberPunk 2077");
        primaryStage.getIcons().add(new Image("salas/icone.png"));
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void exibeTexto(String texto) {
        desc.appendText(texto);
        desc.setScrollTop(Double.MAX_VALUE);
        cmd.setText("");
    }

    public void limpaTela() {
        desc.setText("");
    }

    private void loadImages() {
        images.add(new Image("salas/0monte_fundo.png"));
        images.add(new Image("salas/1monte_daci.png"));
        images.add(new Image("salas/2monte_lampiao.png"));
        images.add(new Image("salas/3monte_lampiao_foice.png"));
        images.add(new Image("salas/4casa.png"));
        images.add(new Image("salas/5havan.png"));
        images.add(new Image("salas/6havan_destruida.png"));
        images.add(new Image("salas/7igreja.png"));
        images.add(new Image("salas/8igreja_biblia.png"));
        images.add(new Image("salas/9foro.png"));
        images.add(new Image("salas/10cristo.png"));
        images.add(new Image("salas/11cristo.png"));
        images.add(new Image("salas/12debate.png"));
        images.add(new Image("salas/13debate_urso.png"));
        images.add(new Image("salas/14debate_megafone.png"));
        images.add(new Image("salas/15posse.png"));
        images.add(new Image("salas/16posse.png"));
        images.add(new Image("salas/17monte_semdinamite1.png"));
        images.add(new Image("salas/18monte_semdinamite2.png"));
        images.add(new Image("salas/19casa_semegafone.png"));
        images.add(new Image("salas/20foro_semursinho.png"));
    }

    public void setImagem(int i) {
        imgView.setImage(images.get(i));
    }

}
