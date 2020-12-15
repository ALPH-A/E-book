/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaFxInterface;

import dbConnection.MyDataBase;
import entity.Evenement;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author ahmed
 */
public class EvenementController implements Initializable {

    @FXML
    private Button btnMenus;
    @FXML
    private Button btnSettings;
    private Button btn_retour;
    @FXML
    private TableView<Evenement> table;
    @FXML
    private TableColumn<Evenement, Integer> col_id;
    @FXML
    private TableColumn<Evenement, String> col_titre;
    @FXML
    private TableColumn<Evenement, String> col_lieu;
    @FXML
    private TableColumn<Evenement, String> col_description;
    @FXML
    private TableColumn<Evenement, String> col_date;
    @FXML
    private TableColumn<Evenement, String> col_image;

    ObservableList<Evenement> oblist = FXCollections.observableArrayList();
    @FXML
    private TextField tf_titre;
    @FXML
    private TextField tf_lieu;
    @FXML
    private TextField tf_description;
    @FXML
    private TextField tf_id;
    @FXML
    private Button btn_ajouter;
    @FXML
    private DatePicker db_date;
    @FXML
    private Button btn_supprimer;
    @FXML
    private Button btn_modifier;
    @FXML
    private Button btnOverview;
    @FXML
    private Button btnOrders;
    @FXML
    private Button id_evenement;
    @FXML
    private Button btnSignout;
    @FXML
    private Button btnCustomers;
    @FXML
    private Button btn_annuler;
    @FXML
    private Button btn_importer;
    @FXML
    private ImageView iv_image;
    private Image image;
    private FileChooser fileChooser;
    private File file;
    @FXML
    private TextField tf_path;
    @FXML
    private TextField filtre_recherche;

    ObservableList<Evenement> filteredData = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showEvents();
        filteredData.addAll(oblist);

        oblist.addListener(new ListChangeListener<Evenement>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends Evenement> change) {
                updateFilteredData();
            }
        });
        table.setItems(filteredData);
        filtre_recherche.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {

                updateFilteredData();

            }
        });

        db_date.setDayCellFactory(picker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate today = LocalDate.now();

                setDisable(empty || date.compareTo(today) < 0);
            }
        });
    }

    private boolean matchesFilter(Evenement livre) {
        String filterString = filtre_recherche.getText();
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

        for (Evenement l : oblist) {
            if (matchesFilter(l)) {
                filteredData.add(l);
            }
        }

        // Must re-sort table after items changed
        reapplyTableSortOrder();
    }

    private void reapplyTableSortOrder() {
        ArrayList<TableColumn<Evenement, ?>> sortOrder = new ArrayList<>(table.getSortOrder());
        table.getSortOrder().clear();
        table.getSortOrder().addAll(sortOrder);
    }

    @FXML
    private void handleClicks(ActionEvent event) {
    }

    private void retourPage(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../javaFxInterface/panelAdminInterface.fxml"));
        Parent root2 = loader.load();
        btn_retour.getScene().setRoot(root2);
    }

    @FXML
    private void handlemouseclicked(MouseEvent event) {
        Evenement e = table.getSelectionModel().getSelectedItem();
        tf_id.setText("" + e.getId());
        tf_titre.setText(e.getTitre());
        tf_lieu.setText(e.getLieu());
        tf_description.setText(e.getDescription());
        ((TextField) db_date.getEditor()).setText(e.getDate());
        tf_path.setText(e.getImage());
        String t = tf_path.getText();
        image = new Image(t);
        iv_image.setImage(image);
        btn_ajouter.setDisable(true);
    }

    private boolean champsVides() {

        if (tf_titre.getText().isEmpty() | db_date.getEditor().getText().isEmpty() | tf_lieu.getText().isEmpty() | tf_description.getText().isEmpty() | (tf_path.getText().isEmpty())) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir les champs");
            alert.showAndWait();
            return false;
        }
        return true;
    }

    @FXML
    private void ajoutEvenement(ActionEvent event) throws MalformedURLException {
        String titre = tf_titre.getText();
        String description = tf_description.getText();
        String lieu = tf_lieu.getText();
        String date = db_date.getEditor().getText();
        String imagePath = file.toURI().toURL().toString();

        if (champsVides()) {
            Connection cnx;
            cnx = MyDataBase.getInstance().getCnx();
            try {
                String requete = "INSERT INTO evenement2 (titre,lieu,description,date,image) values (?,?,?,?,?)";
                Evenement e = new Evenement(titre, lieu, description, date, imagePath);
                PreparedStatement pst = cnx
                        .prepareStatement(requete);
                pst.setString(1, e.getTitre());
                pst.setString(2, e.getLieu());
                pst.setString(3, e.getDescription());
                pst.setString(4, e.getDate());
                pst.setString(5, e.getImage());

                pst.executeUpdate();
                System.out.println("Evenement ajouté!");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Ajout avec succès");
            this.tf_id.setText("");
            this.tf_titre.setText("");
            this.tf_lieu.setText("");
            this.tf_description.setText("");
            this.tf_path.setText("");
            db_date.setValue(null);
            this.iv_image.setImage(null);
            oblist.clear();
            showEvents();
            alert.showAndWait();
        }
    }

    private void showEvents() {
        try {
            Connection cnx;
            cnx = MyDataBase.getInstance().getCnx();
            String requete = "SELECT * FROM evenement2";
            Statement st = cnx
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                oblist.add(new Evenement(rs.getInt("id"),
                        rs.getString("titre"), rs.getString("lieu"), rs.getString("description"), rs.getString("date"), rs.getString("image")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        col_lieu.setCellValueFactory(new PropertyValueFactory<>("lieu"));
        col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        col_image.setCellValueFactory(new PropertyValueFactory<>("image"));
        table.setItems(oblist);
    }

    @FXML
    private void supprimerEvenement(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure to delete ?");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
            try {
                Connection cnx;
                cnx = MyDataBase.getInstance().getCnx();
                String requete = "Delete from evenement2 where id=" + tf_id.getText() + "";
                PreparedStatement pst = cnx
                        .prepareStatement(requete);
                pst.executeUpdate(requete);
            } catch (SQLException ex) {
                Logger.getLogger(EvenementController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
        alert2.setTitle("Confirmation");
        alert2.setHeaderText(null);
        alert2.setContentText("Suppression avec succès");

        this.tf_id.setText("");
        this.tf_titre.setText("");
        this.tf_lieu.setText("");
        this.tf_description.setText("");
        this.db_date.setValue(null);
        this.iv_image.setImage(null);
        this.tf_path.setText("");
        btn_ajouter.setDisable(false);
        oblist.clear();
        showEvents();
        alert2.showAndWait();
    }

    @FXML
    private void modifierEvenement(ActionEvent event) throws MalformedURLException {
        String titre = tf_titre.getText();
        String description = tf_description.getText();
        String lieu = tf_lieu.getText();
        String date = db_date.getEditor().getText();
        String imagePath = tf_path.getText();

        if (champsVides()) {
            try {
                Connection cnx;
                cnx = MyDataBase.getInstance().getCnx();
                String requete = "Update evenement2 set titre='" + titre + "',lieu='" + lieu + "',description='" + description + "',date='" + date + "',image='" + imagePath + "' where id=" + tf_id.getText() + "";
                PreparedStatement pst = cnx
                        .prepareStatement(requete);
//                String requete = "UPDATE evenement (titre,lieu,description,date,image) values (?,?,?,?,?)where id="+ tf_id.getText() + "";
//                Event e = new Event(description, lieu, titre, date,image);
//                PreparedStatement pst = cnx
//                        .prepareStatement(requete);
//                pst.setString(1, e.getTitre());
//                pst.setString(2, e.getLieu());
//                pst.setString(3, e.getDescription());
//                pst.setString(4, e.getDate());
//                pst.setBinaryStream(5, (InputStream) fis, (int) file.length());
                pst.executeUpdate(requete);
            } catch (SQLException ex) {
                Logger.getLogger(EvenementController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Modification avec succès");
            alert.showAndWait();
        }

        this.tf_id.setText("");
        this.tf_titre.setText("");
        this.tf_lieu.setText("");
        this.tf_description.setText("");
        this.tf_path.setText("");
        this.db_date.setValue(null);
        this.iv_image.setImage(null);
        btn_ajouter.setDisable(false);
        oblist.clear();
        showEvents();
    }

    @FXML
    private void setPaneOrderOn(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../javaFxInterface/panelAdminInterface.fxml"));
        Parent root2 = loader.load();
        table.getScene().setRoot(root2);
    }

    @FXML
    private void gestionEvenement(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../javaFxInterface/Evenement.fxml"));
        Parent root2 = loader.load();
        table.getScene().setRoot(root2);
    }

    @FXML
    private void annulerajout(ActionEvent event) {
        this.tf_id.setText("");
        this.tf_titre.setText("");
        this.tf_lieu.setText("");
        this.tf_description.setText("");
        this.tf_path.setText("");
        this.db_date.setValue(null);
        this.iv_image.setImage(null);
        btn_ajouter.setDisable(false);
        oblist.clear();
        showEvents();

    }

    @FXML
    private void importer(ActionEvent event) {
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
