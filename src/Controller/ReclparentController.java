/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entities.Conseil;
import Entities.Contes;
import Entities.Cours;
import Entities.Enfant;
import Entities.Maitresse;
import Entities.Reclamation;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import Services.ServiceConseil;
import Services.ServiceContes;
import Services.ServiceCours;
import Services.ServiceDirecteur;
import Services.ServiceMaitresse;
import Services.ServiceParent;
import Services.ServiceReclamation;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FXML Controller class
 *
 * @author maiez
 */
public class ReclparentController implements Initializable {

    ObservableList<String> listRoles = FXCollections.observableArrayList("Cours", "Contes", "Tuteur", "Enfant", "Autre");
    String erreur;
    int selectedBonplanID;
    int selectedCompteID;
    java.sql.Timestamp timestamp = null;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField reclamation;
    @FXML
    private Label LAffiche;
    @FXML
    private ComboBox<String> typer;
    @FXML
    private ImageView prenomCheckMark;
    @FXML
    private ImageView nomCheckMark;
    @FXML
    private TextField email;
    @FXML
    private ImageView emailCheckMark;
    @FXML
    private DatePicker date;
    @FXML
    private ImageView dateCheckMark;
    @FXML
    private Button btnRet;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Stage window = new Stage();
       typer.setPromptText("Veuillez selectionner un type");
        typer.setItems(listRoles);
    }    

    @FXML
    private boolean testNom() {
        int nbNonChar = 0;
        for (int i = 1; i < nom.getText().trim().length(); i++) {
            char ch = nom.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }

        if (nbNonChar == 0 && nom.getText().trim().length() >= 3) {
            nomCheckMark.setImage(new Image("UI/checkMark.png"));
            return true;
        } else {
            nomCheckMark.setImage(new Image("UI/erreurCheckMark.png"));
               
            return false;

        }
    }

    @FXML
    private boolean testPrenom() {
        int nbNonChar = 0;
        for (int i = 1; i < prenom.getText().trim().length(); i++) {
            char ch = prenom.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }

        if (nbNonChar == 0 && prenom.getText().trim().length() >= 3) {
            prenomCheckMark.setImage(new Image("UI/checkMark.png"));
            return true;
        } else {
            prenomCheckMark.setImage(new Image("UI/erreurCheckMark.png"));
//                erreur = erreur + ("Pas de caractere permit dans le telephone\n");
            return false;

        }
    }


     private Boolean testSaisie() {
        erreur = "";
        if (!testNom()) {
            erreur = erreur + ("Veuillez verifier votre Nom: seulement des caractères et de nombre >= 3 \n");
        }
        if (!testPrenom()) {
            erreur = erreur + ("Veuillez verifier votre Prenom: seulement des caractères et de nombre >= 3");
        }
        if (!testemail()) {
            erreur = erreur + ("Veuillez verifier que votre adresse email est de la forme : ***@***.** \n");
        }
         if (!testdate()) {
            erreur = erreur + ("Veuillez verifier que vous avez choisi une date superieur a la date courante \n");
        }

        if ( (!testNom()) || (!testPrenom()) || !testemail() || !testdate() ) {
           
            
            
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Erreur de format");
        alert.setHeaderText("Vérifier les champs");
        alert.setContentText(erreur);
        
        
        }

        return  testNom() && testPrenom() && testemail() && testdate();
    }
    @FXML
    private void AjouterReclamation(ActionEvent event) {
        if (testSaisie()==true)
    {
       
        String x="Parent";
        ServiceReclamation sr= new ServiceReclamation();
        Reclamation r=new Reclamation();
      
        r.setNom(nom.getText());
        r.setPrenom(prenom.getText());
        r.setReclamation(reclamation.getText());
        r.setEmail(email.getText());
        r.setPp(x);
        r.setDate_creation(timestamp);
        if(typer.getValue().toString() =="Cours"){
        r.setType(typer.getValue()+ ": " +listeCours.getSelectionModel().getSelectedItem().getCours()+" "+listeCours.getSelectionModel().getSelectedItem().getType());
        }
        else if (typer.getValue().toString() =="Contes")
        {
        r.setType(typer.getValue()+ ": " +listeContes.getSelectionModel().getSelectedItem().getTitre()+" "+listeContes.getSelectionModel().getSelectedItem().getAuteur());    
        }
        else if (typer.getValue().toString() =="Tuteur")
        {
        r.setType(typer.getValue()+ ": " +listeMaitresse.getSelectionModel().getSelectedItem().getNom()+" "+listeMaitresse.getSelectionModel().getSelectedItem().getPrenom());      
        }
        else if (typer.getValue().toString() =="Enfant")
        {
        r.setType(typer.getValue()+ ": " +listeEnfant.getSelectionModel().getSelectedItem().getNomEnfant()+" "+listeEnfant.getSelectionModel().getSelectedItem().getPrenomEnfant());     
        }
        else 
        {
            
        }
        sr.AddReclamation(r);
      
      
            LAffiche.setText(r.toString());
            nom.clear();
            prenom.clear();
            reclamation.clear();
            email.clear();
            
            
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ajout à la base");
        alert.setHeaderText("Succées d'ajout");
        alert.setContentText("La reclamation choisi a été envoyer et va etre traité dans les plus brefs délais !");
        alert.showAndWait();  
    }
    }
    TableView<Cours> listeCours = new TableView<>();
    TextField rechercheReclamationn = new TextField();
     TableView<Contes> listeContes = new TableView<>();
      TextField rechercheCompteTFL = new TextField();
      TableView<Maitresse> listeMaitresse = new TableView<>();
      TextField rechercheMaitresse = new TextField();
      TableView<Enfant> listeEnfant = new TableView<>();
      TextField rechercheEnfant = new TextField();

    @FXML
    private void recherchetype(ActionEvent event) throws SQLException {
        if (typer.getValue().toString() =="Cours"){
                    rechercheReclamationn.setOnKeyReleased((KeyEvent a) -> {listReclamationn();});
                    
            Stage window = new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            window.setTitle("Veuillez choisir une reclamation");

       TableColumn<Cours, String> nomc = new TableColumn<>("Nom");
            nomc.setCellValueFactory(new PropertyValueFactory<>("nom"));
            
             TableColumn<Cours, String> type = new TableColumn<>("Type");
            type.setCellValueFactory(new PropertyValueFactory<>("type"));

            
            ServiceCours sc = new ServiceCours();
            
            listeCours.setEditable(true);
            listeCours.setItems((ObservableList<Cours>) sc.afficherCours());
            listeCours.getColumns().setAll(nomc,type);
            
         
            
            listeCours.setOnMouseClicked((MouseEvent event2)
                    -> {
                if (event2.getClickCount() >= 2) {
                    if (listeCours.getSelectionModel().getSelectedItem() != null) {
                      
                        selectedBonplanID = listeCours.getSelectionModel().getSelectedItem().getIdC();
                        selectedCompteID=0;
                        System.out.println("id du bon plan selectioné=" + selectedBonplanID);
                        window.close();
                     
                           Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Choix de la reclamation ");
        alert.setHeaderText("Succées d'ajout");
        alert.setContentText("La reclamation concernant ce cours est selectionnée avec succès!");
        alert.showAndWait(); 
                              
         }
                }
    });
        
            Button closeButton = new Button("Fermer");

            closeButton.setOnAction(e -> window.close());
            
 VBox layout = new VBox(10);
            layout.getChildren().setAll(rechercheReclamationn,listeCours,closeButton);
            layout.setAlignment(Pos.CENTER);
            Scene scene = new Scene(layout);
            window.setScene(scene);
            window.show();
        }
        //**************************************************************************************
         if (typer.getValue().toString() =="Contes"){
                    rechercheCompteTFL.setOnKeyReleased((KeyEvent a) -> {listContes();});
                    
            Stage window = new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            window.setTitle("Veuillez choisir une reclamation");

       TableColumn<Contes, String> titre = new TableColumn<>("Titre");
            titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
            
             TableColumn<Contes, String> auteur = new TableColumn<>("Auteur");
            auteur.setCellValueFactory(new PropertyValueFactory<>("auteur"));

            
            ServiceContes sc = new ServiceContes();
            
            listeContes.setEditable(true);
            listeContes.setItems((ObservableList<Contes>) sc.afficherContes());
            listeContes.getColumns().setAll(titre,auteur);
            
         
            
            listeContes.setOnMouseClicked((MouseEvent event2)
                    -> {
                if (event2.getClickCount() >= 2) {
                    if (listeContes.getSelectionModel().getSelectedItem() != null) {
                      
                        selectedBonplanID = listeContes.getSelectionModel().getSelectedItem().getId();
                        selectedCompteID=0;
                        System.out.println("id du bon plan selectioné=" + selectedBonplanID);
                        window.close();
                     
                           Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Choix de la reclamation ");
        alert.setHeaderText("Succées d'ajout");
        alert.setContentText("La reclamation concernant cette contes est selectionnée avec succès!");
        alert.showAndWait(); 
                              
         }
                }
    });
        
            Button closeButton = new Button("Fermer");

            closeButton.setOnAction(e -> window.close());
            
 VBox layout = new VBox(10);
            layout.getChildren().setAll(rechercheCompteTFL,listeContes,closeButton);
            layout.setAlignment(Pos.CENTER);
            Scene scene = new Scene(layout);
            window.setScene(scene);
            window.show();
        }
         //**************************************************************************************
         if (typer.getValue().toString() =="Enfant"){
                    rechercheEnfant.setOnKeyReleased((KeyEvent a) -> {listEnfant();});
                    
            Stage window = new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            window.setTitle("Veuillez choisir une reclamation");

       TableColumn<Enfant, String> nom = new TableColumn<>("Nom");
            nom.setCellValueFactory(new PropertyValueFactory<>("nomEnfant"));
            
             TableColumn<Enfant, String> prenom = new TableColumn<>("Prenom");
            prenom.setCellValueFactory(new PropertyValueFactory<>("prenomEnfant"));

            
            ServiceParent sp = new ServiceParent();
            
            listeEnfant.setEditable(true);
            listeEnfant.setItems((ObservableList<Enfant>) sp.AfficherEnfant());
            listeEnfant.getColumns().setAll(nom,prenom);
            
         
            
            listeEnfant.setOnMouseClicked((MouseEvent event2)
                    -> {
                if (event2.getClickCount() >= 2) {
                    if (listeEnfant.getSelectionModel().getSelectedItem() != null) {
                      
                        selectedBonplanID = listeEnfant.getSelectionModel().getSelectedItem().getIdEnfant();
                        selectedCompteID=0;
                        System.out.println("id du bon plan selectioné=" + selectedBonplanID);
                        window.close();
                     
                           Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Choix de la reclamation ");
        alert.setHeaderText("Succées d'ajout");
        alert.setContentText("La reclamation concernant cet enfant est selectionnée avec succès!");
        alert.showAndWait(); 
                              
         }
                }
    });
        
            Button closeButton = new Button("Fermer");

            closeButton.setOnAction(e -> window.close());
            
 VBox layout = new VBox(10);
            layout.getChildren().setAll(rechercheEnfant,listeEnfant,closeButton);
            layout.setAlignment(Pos.CENTER);
            Scene scene = new Scene(layout);
            window.setScene(scene);
            window.show();
        }
         //**************************************************************************************
         if (typer.getValue().toString() =="Tuteur"){
                    rechercheMaitresse.setOnKeyReleased((KeyEvent a) -> {listMaitresse();});
                    
            Stage window = new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            window.setTitle("Veuillez choisir une reclamation");

            TableColumn<Maitresse, String> nomM = new TableColumn<>("Nom");
            nomM.setCellValueFactory(new PropertyValueFactory<>("nomM"));
            
            TableColumn<Maitresse, String> prenomM = new TableColumn<>("Prenom");
            prenomM.setCellValueFactory(new PropertyValueFactory<>("prenomM"));

            
            ServiceMaitresse sp = new ServiceMaitresse();
            
            listeMaitresse.setEditable(true);
            listeMaitresse.setItems((ObservableList<Maitresse>) sp.afficherM());
            listeMaitresse.getColumns().setAll(nomM,prenomM);
            
         
            
            listeMaitresse.setOnMouseClicked((MouseEvent event2)
                    -> {
                if (event2.getClickCount() >= 2) {
                    if (listeMaitresse.getSelectionModel().getSelectedItem() != null) {
                      
                        selectedBonplanID = listeMaitresse.getSelectionModel().getSelectedItem().getIdM();
                        selectedCompteID=0;
                        System.out.println("id du bon plan selectioné=" + selectedBonplanID);
                        window.close();
                     
                           Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Choix de la reclamation ");
        alert.setHeaderText("Succées d'ajout");
        alert.setContentText("La reclamation concernant ce tuteur est selectionnée avec succès!");
        alert.showAndWait(); 
                              
         }
                }
    });
        
            Button closeButton = new Button("Fermer");

            closeButton.setOnAction(e -> window.close());
            
 VBox layout = new VBox(10);
            layout.getChildren().setAll(rechercheMaitresse,listeMaitresse,closeButton);
            layout.setAlignment(Pos.CENTER);
            Scene scene = new Scene(layout);
            window.setScene(scene);
            window.show();
        }
    
    }
    @FXML
    private boolean testemail() {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."
                + "[a-zA-Z0-9_+&*-]+)*@"
                + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
                + "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email.getText() == null) {
            return false;
        }

        if (pat.matcher(email.getText()).matches() == false) {
            emailCheckMark.setImage(new Image("UI/erreurCheckMark.png"));
//                erreur = erreur + ("Veuillez verifier votre adresse email\n");
            return false;
//            

        } else {
            emailCheckMark.setImage(new Image("UI/checkMark.png"));
        }
        return true;
    }


    @FXML
    private boolean testdate() {
        java.sql.Timestamp today_date = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
        if ( date.getValue() != null) {
            timestamp = java.sql.Timestamp.valueOf(date.getValue() + " " +  "00:00:00");
            if (timestamp.compareTo(today_date)>= 0) {
                dateCheckMark.setImage(new Image("UI/checkMark.png"));
                return true;
            } else {
                dateCheckMark.setImage(new Image("UI/erreurCheckMark.png"));
            }
            return false;
        } else if (date.getValue() == null) {
            return true;
        
        } else if ( date.getValue() != null) {
            dateCheckMark.setImage(new Image("UI/erreurcheckMark.png"));
        }
        return false;
    }
    public void listReclamationn() {
        ArrayList arrayList = null;
        ServiceCours sc = new ServiceCours();

            arrayList = (ArrayList) sc.recherchecours(rechercheReclamationn.getText());


        ObservableList observableList = FXCollections.observableArrayList(arrayList);
        listeCours.setItems(observableList);
    }

   
       public void listContes() {
        ArrayList arrayList = null;
           ServiceContes sc = new ServiceContes();

          arrayList = (ArrayList) sc.recherchecontes(rechercheReclamationn.getText()); 


        ObservableList observableList = FXCollections.observableArrayList(arrayList);
        listeContes.setItems(observableList);
    }
       public void listMaitresse() {
        ArrayList arrayList = null;
           ServiceDirecteur sd = new ServiceDirecteur();

          arrayList = (ArrayList) sd.recherchemaitresse(rechercheReclamationn.getText()); 


        ObservableList observableList = FXCollections.observableArrayList(arrayList);
        listeMaitresse.setItems(observableList);
    }
        public void listEnfant() {
        ArrayList arrayList = null;
           ServiceParent sp = new ServiceParent();

          arrayList = (ArrayList) sp.rechercheenfant(rechercheReclamationn.getText()); 


        ObservableList observableList = FXCollections.observableArrayList(arrayList);
        listeMaitresse.setItems(observableList);
    }

    @FXML
    private void Retour(ActionEvent event) {
        try {
        Stage stage = (Stage) btnRet.getScene().getWindow();
        stage.close();
        
        FXMLLoader fxmlloader = new FXMLLoader (getClass().getResource("Document.fxml"));
        Parent root;
        
            root = (Parent) fxmlloader.load();
        
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(root));
        stage1.show();
        } catch (IOException ex) {
            Logger.getLogger(ReclparentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}
