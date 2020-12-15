/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaFxController;

import controller.livreController;
import dbConnection.MyDataBase;
import entity.Livre;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author amine
 */
public class GestionLivreController implements Initializable {

    @FXML
    private TextField tfTitre;
    @FXML
    private TextField tfDescription;
    @FXML
    private TextField tfAuteur;
    @FXML
    private TextField tfGenre;
    @FXML
    private TextField tfPrix;
    @FXML
    private TableView<Livre> tvBooks;
    @FXML
    private TableColumn<Livre, Integer> colId;
    @FXML
    private TableColumn<Livre, String> colTitre;
    @FXML
    private TableColumn<Livre, String> colDescription;
    @FXML
    private TableColumn<Livre, String> colAuteur;
    @FXML
    private TableColumn<Livre, String> colGenre;
    @FXML
    private TableColumn<Livre, Double> colPrix;
    @FXML
    private Button btnAjouter;
    @FXML
    private Button btnModifier;
    @FXML
    private Button btnSupprimer;
    @FXML
    private TextField tf_Id;
    ObservableList<Livre> observableList = FXCollections.observableArrayList();
    ObservableList<Livre> filteredData = FXCollections.observableArrayList();
    private Image image;
    private FileChooser fileChooser;
    private File file;
    @FXML
    private Button btnPromo;
    @FXML
    private TextField tfPromo;
    @FXML
    private TextField filterField;
    @FXML
    private ImageView iv_image;
    @FXML
    private Button btn_importer;
  
    @FXML
    private TextField path;
    @FXML
    private TableColumn<Livre, String> ColImage;

    /**
     * Initializes the controller class.
     */

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showBooks();
        filteredData.addAll(observableList);

        observableList.addListener(new ListChangeListener<Livre>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends Livre> change) {
                updateFilteredData();
            }
        });
        tvBooks.setItems(filteredData);
        filterField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {

                updateFilteredData();

            }
        });
    }

    private void clearFields() {
        tfTitre.clear();
        tfDescription.clear();
        tfAuteur.clear();
        tfGenre.clear();
        tfPrix.clear();
    }

    @FXML
    private void saveData() throws MalformedURLException {
        String path = file.toURI().toURL().toString();
        String titre = tfTitre.getText();
        String description = tfDescription.getText();
        String auteur = tfAuteur.getText();
        String genre = tfGenre.getText();
        double prix = Double.parseDouble(tfPrix.getText());
        double tauxPromo = 0;
        double prixSolde = prix * (1 - tauxPromo / 100);
        if (champsVides()) {
            Livre l = new Livre(titre, description, auteur, prix, genre, tauxPromo, prixSolde,path);
            livreController lc = new livreController();

            lc.ajouterLivre(l);
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Livre ajouté avec succès");

            clearFields();
            this.iv_image.setImage(null);
            showBooks();
            alert.showAndWait();
        } else {
            Alert alert3 = new Alert(AlertType.INFORMATION);
            alert3.setTitle("Erreur");
            alert3.setHeaderText(null);
            alert3.setContentText("Veuillez remplir les champs");
            alert3.showAndWait();
        }

    }

    @FXML
    private void supprimerLivre(ActionEvent event) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure to delete ?");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
            try {
                Connection cnx;
                cnx = MyDataBase.getInstance().getCnx();
                String requete = "Delete from livre where id=" + tf_Id.getText() + "";
                PreparedStatement pst = cnx
                        .prepareStatement(requete);
                pst.executeUpdate(requete);
            } catch (SQLException ex) {
                Logger.getLogger(GestionLivreController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Alert alert2 = new Alert(AlertType.INFORMATION);
        alert2.setTitle("Confirmation");
        alert2.setHeaderText(null);
        alert2.setContentText("Suppression avec succès");
        this.tf_Id.setText("");
        this.tfTitre.setText("");
        this.tfDescription.setText("");

        this.tfAuteur.setText("");
        this.tfGenre.setText("");
        this.tfPrix.setText("");
     
        observableList.clear();
        showBooks();
        alert2.showAndWait();
    }

    @FXML
    private void handlemouseclicked(MouseEvent event) {
        Livre l = tvBooks.getSelectionModel().getSelectedItem();
        tf_Id.setText("" + l.getId());
        tfTitre.setText(l.getTitre());
        tfDescription.setText(l.getDescription());
        tfAuteur.setText(l.getAuteur());
        tfGenre.setText(l.getGenre());
        tfPrix.setText(l.getPrix() + "");
         path.setText(l.getImage());
         String t = path.getText();
         image=new Image(t);
         iv_image.setImage(image);
    }

    @FXML
    private void modifierLivre() throws MalformedURLException {
        String titre = tfTitre.getText();
        String description = tfDescription.getText();
        String auteur = tfAuteur.getText();
        String genre = tfGenre.getText();
        double prix = Double.parseDouble(tfPrix.getText());
        String pathImage = file.toURI().toURL().toString();
        if (champsVides()) {
            try {
                Connection cnx;
                cnx = MyDataBase.getInstance().getCnx();
                String requete = "Update Livre set titre='" + titre + "',description='" + description + "',auteur='" + auteur + "',genre='" + genre + "',prix='" + prix +  "',image='"+ pathImage +"' where id=" + tf_Id.getText() + "";
                PreparedStatement pst = cnx
                        .prepareStatement(requete);
                pst.executeUpdate(requete);
            } catch (SQLException ex) {
                Logger.getLogger(GestionLivreController.class.getName()).log(Level.SEVERE, null, ex);
            }

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Modification avec succès");
            alert.showAndWait();
        }

        this.tf_Id.setText("");
        this.tfTitre.setText("");
        this.tfDescription.setText("");

        this.tfAuteur.setText("");
        this.tfGenre.setText("");
        this.tfPrix.setText("");
        this.iv_image.setImage(null);
       
        observableList.clear();
        showBooks();
    }

    @FXML
    private void promotionLivre() {

        double prix = Double.parseDouble(tfPrix.getText());
        double tauxPromo = Double.parseDouble(tfPromo.getText());
        double prixSolde = prix * (1 - tauxPromo / 100);

        if (champsVides()) {
            try {
                Connection cnx;
                cnx = MyDataBase.getInstance().getCnx();
                String requete = "Update Livre set tauxPromo='" + tauxPromo + "',prixSolde='" + prixSolde + "' where id=" + tf_Id.getText() + "";
                PreparedStatement pst = cnx
                        .prepareStatement(requete);
                pst.executeUpdate(requete);
            } catch (SQLException ex) {
                Logger.getLogger(GestionLivreController.class.getName()).log(Level.SEVERE, null, ex);
            }

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Le prix aprés la promotion est " + prixSolde + "DT");
            alert.showAndWait();
        }

        this.tf_Id.setText("");

        this.tfPromo.setText("");

        observableList.clear();
        showBooks();
    }

    /* @FXML
    private void saveData() {

        try {

            String requete = "INSERT INTO livre (titre,description,auteur,prix,genre) "
                    + "VALUES (?,?,?,?,?)";

            PreparedStatement pst = new MyDataBase().getCnx()
                    .prepareStatement(requete);
            pst.setString(1, tfTitre.getText());
            pst.setString(2, tfDescription.getText());
            pst.setString(3, tfAuteur.getText());
            pst.setDouble(4, Double.parseDouble(tfPrix.getText()));
            pst.setString(5, tfGenre.getText());

            pst.executeUpdate();
            System.out.println("Livre ajouté!");
            showBooks();
            clearFields();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
     */
    private boolean champsVides() {
        if (tfTitre.getText().isEmpty() | tfDescription.getText().isEmpty() | tfAuteur.getText().isEmpty() | tfPrix.getText().isEmpty() | tfGenre.getText().isEmpty()) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir les champs");
            alert.showAndWait();
            return false;
        }
        return true;
    }

    @FXML
    public void showBooks() {
        livreController l = new livreController();
        List<Livre> myList = new ArrayList<Livre>();
        myList = l.listerLivres();
        observableList = FXCollections.observableList(myList);
        colId.setCellValueFactory(new PropertyValueFactory<Livre, Integer>("id"));
        colTitre.setCellValueFactory(new PropertyValueFactory<Livre, String>("titre"));
        colDescription.setCellValueFactory(new PropertyValueFactory<Livre, String>("description"));
        colAuteur.setCellValueFactory(new PropertyValueFactory<Livre, String>("auteur"));
        colGenre.setCellValueFactory(new PropertyValueFactory<Livre, String>("genre"));
        colPrix.setCellValueFactory(new PropertyValueFactory<Livre, Double>("prix"));
       ColImage.setCellValueFactory(new PropertyValueFactory<Livre, String>("image"));

        tvBooks.setItems(observableList);
    }

    private boolean matchesFilter(Livre livre) {
        String filterString = filterField.getText();
        if (filterString == null || filterString.isEmpty()) {
            // No filter --> Add all.
            return true;
        }

        String lowerCaseFilterString = filterString.toLowerCase();

        if (livre.getTitre().toLowerCase().indexOf(lowerCaseFilterString) != -1) {
            return true;
        } else if (livre.getTitre().toLowerCase().indexOf(lowerCaseFilterString) != -1) {
            return true;
        }

        return false; // Does not match
    }

    private void updateFilteredData() {
        filteredData.clear();

        for (Livre l : observableList) {
            if (matchesFilter(l)) {
                filteredData.add(l);
            }
        }

        // Must re-sort table after items changed
        reapplyTableSortOrder();
    }

    private void reapplyTableSortOrder() {
        ArrayList<TableColumn<Livre, ?>> sortOrder = new ArrayList<>(tvBooks.getSortOrder());
        tvBooks.getSortOrder().clear();
        tvBooks.getSortOrder().addAll(sortOrder);
    }

    @FXML
    private void importImage(ActionEvent event) {
        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
        );

        file = fileChooser.showOpenDialog(null);
        if (file != null) {
            image = new Image(file.toURI().toString());

            iv_image.setImage(image);

        }
    }
}
