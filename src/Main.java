import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;

public class Main extends Application {
    public static void main(String[] args) {launch(args);}
    public void start(Stage stage1){
        stage1.setFullScreen(true);

        Menu fichier = new Menu("Fichier");
        Menu action = new Menu("Action");
        Menu charger = new Menu("Charger l'image : ");
        MenuItem image1 = new MenuItem("Charger l'image 1");
        MenuItem image2 = new MenuItem("Charger l'image 2");
        MenuItem image3 = new MenuItem("Charger l'image 3");
        MenuItem réinitialiser = new MenuItem("Réinitialiser");
        Menu fichier2 = new Menu("Fichier");
        Menu action2 = new Menu("Action");
        Menu charger2 = new Menu("Charger l'image : ");
        MenuItem image12 = new MenuItem("Charger l'image 1");
        MenuItem image22 = new MenuItem("Charger l'image 2");
        MenuItem image32 = new MenuItem("Charger l'image 3");
        MenuItem réinitialiser2 = new MenuItem("Réinitialiser");
        Label info = new Label("initialisation du programme");

        final ImageView selectedImage0 = new ImageView();

        charger.getItems().addAll(image1,image2,image3);
        charger2.getItems().addAll(image12,image22,image32);
        action.getItems().addAll(réinitialiser);
        action2.getItems().addAll(réinitialiser2);
        fichier.getItems().addAll(charger);
        fichier2.getItems().addAll(charger2);

        ColorAdjust imageColors = new ColorAdjust();
        selectedImage0.setEffect(imageColors);

        Label nomLumiere = new Label("Luminosité");
        Slider luminosité = new Slider(-1,1,0);
         luminosité.valueProperty().addListener((observable, oldValue, newValue) -> {
             imageColors.setBrightness((double)newValue);
         });

        Label nomContraste = new Label("Contraste");
        Slider contraste = new Slider(-1,1,0);
        contraste.valueProperty().addListener((observable, oldValue, newValue) -> {
            imageColors.setContrast((double)newValue);
        });
        Label nomTeinte = new Label("Teinte");
        Slider teinte = new Slider(-1,1,0);
        teinte.valueProperty().addListener((observable, oldValue, newValue) -> {
            imageColors.setHue((double)newValue);
        });
        Label nomSaturation = new Label("Saturation");
        Slider saturation = new Slider(-1,1,0);
        saturation.valueProperty().addListener((observable, oldValue, newValue) -> {
            imageColors.setSaturation((double)newValue);
        });
        Tooltip aideLum = new Tooltip("•Luminosité : Rend l'image plus claire ou plus sombre");
        luminosité.setTooltip(aideLum);
        Tooltip aideCon = new Tooltip("•Contraste : Diminue ou augmente la différence entre les couleurs");
        contraste.setTooltip(aideCon);
        Tooltip aideTei = new Tooltip("•Teinte : Change la teinte (couleur) de l'image");
        teinte.setTooltip(aideTei);
        Tooltip aideSat = new Tooltip("•Saturation : Diminue ou augmente l'intensité des couleurs");
        saturation.setTooltip(aideSat);

        try{
            Image picture0 = new Image(new File("default.jpg").toURI().toURL().toExternalForm());
            selectedImage0.setImage(picture0);
        }catch(Exception exception1){
            System.out.println("image non trouvé");
        }


        VBox changement = new VBox(nomLumiere,luminosité,nomContraste,contraste,nomTeinte,teinte,nomSaturation,saturation);
        changement.setAlignment(Pos.CENTER);
        changement.setSpacing(7);
        HBox centre = new HBox(selectedImage0,changement);
        centre.setAlignment(Pos.CENTER);

        MenuBar menu = new MenuBar(fichier2,action2);
        ContextMenu clicD = new ContextMenu(fichier,action);
        BorderPane root = new BorderPane();
        root.setTop(menu);
        root.setCenter(centre);
        root.setBottom(info);
         root.setOnContextMenuRequested(event -> {
             clicD.show(root, event.getScreenX(),event.getScreenY());
         });

         image1.setOnAction(event -> {
            try{
                Image picture1 = new Image(new File("image1.jpg").toURI().toURL().toExternalForm());
                selectedImage0.setImage(picture1);
                selectedImage0.setFitHeight(332);
                selectedImage0.setFitWidth(500);
                info.setText("Image 1 chargé");
            }catch(Exception exception2){
                System.out.println("image1 nn charger");
            }
        });
        image2.setOnAction(event -> {
            try{
                Image picture2 = new Image(new File("image2.jpg").toURI().toURL().toExternalForm());
                selectedImage0.setImage(picture2);
                selectedImage0.setFitHeight(222);
                selectedImage0.setFitWidth(500);
                info.setText("Image 2 charger");
            }catch(Exception exception2){
                System.out.println("image2 nn chargé");
            }
        });
        image3.setOnAction(event -> {
            try{
                Image picture3 = new Image(new File("image3.jpg").toURI().toURL().toExternalForm());
                selectedImage0.setImage(picture3);
                selectedImage0.setFitWidth(500);
                selectedImage0.setFitHeight(333);
                info.setText("Image 3 chargé");
            }catch(Exception exception2){
                System.out.println("image3 nn charger");
            }
        });

        réinitialiser.setOnAction(event -> {
            luminosité.setValue(0);
            contraste.setValue(0);
            teinte.setValue(0);
            saturation.setValue(0);
            info.setText("Effects d'image réinitialisé");
        });
        réinitialiser2.setOnAction(event -> réinitialiser.fire());

        image12.setOnAction(event -> {image1.fire();});
        image22.setOnAction(event -> {image2.fire();});
        image32.setOnAction(event -> {image3.fire();});

        Scene scene1 = new Scene(root);
        stage1.setScene(scene1);
        stage1.show();
        stage1.setTitle("Laboratoire 6");

    }
}
