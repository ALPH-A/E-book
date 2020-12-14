/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaFxController;

import controller.commandeController;
import entity.Commande;
import entity.Livre;
import entity.User;
import java.net.URL;
import java.util.ResourceBundle;
import javaFxClasses.orderTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import services.commandServices;
import services.livreServices;
import services.userServices;

/**
 * FXML Controller class
 *
 * @author nizar
 */
public class panelAdminController implements Initializable {

    @FXML
    private Button btnOverview;
    @FXML
    private Button btnOrders;
    @FXML
    private Button btnSignout;
    @FXML
    private Button btnMenus;
    @FXML
    private Button btnCustomers;
    @FXML
    private Button btnPackages;
    @FXML
    private Button btnSettings;
    @FXML
    private Pane pnlCustomer;
    @FXML
    private Pane pnlOrders;
    @FXML
    private Pane pnlMenus;
    @FXML
    private Pane pnlOverview;
    @FXML
    private TableView<orderTable> orderTable;
    @FXML
    private TableColumn<orderTable, Integer> commandeID;
    @FXML
    private TableColumn<orderTable, Integer> clientID;
    @FXML
    private TableColumn<orderTable, Integer> livreID;
    @FXML
    private TableColumn<orderTable, String> dateCommande;
    @FXML
    private TableColumn<orderTable, String> fullName;
    @FXML
    private TableColumn<orderTable, String> bookTitle;
    @FXML
    private TableColumn<orderTable, Float> prixTotal;
    @FXML
    private TableColumn<orderTable, String> orderState;
    @FXML
    private TableColumn<orderTable, Button> action;

    ObservableList<orderTable> oblist = FXCollections.observableArrayList();
    @FXML
    private Label totalOrders;
    @FXML
    private Label orderOnHold;
    @FXML
    private Label pedingOrder;
    @FXML
    private Label orderDelivered;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //intialise total order number
        setOrderStats();
        //fill orderTable with data from database
        fillOrderTable();
        pnlOrders.setVisible(false);
        
      

    }

    @FXML
    private void handleClicks(ActionEvent event) {

    }

    public void fillOrderTable() {
        commandeController c = new commandeController();
        userServices u = new userServices();
        livreServices l = new livreServices();

        for (Commande p1 : c.listerCommandes()) {

            User user1 = u.getUserById(p1.getClientId());
            Livre livre1 = l.getLivreById(p1.getLivreId());
            oblist.add(new orderTable(p1.getId(), p1.getPrixTotal(), p1.getClientId(), p1.getLivreId(), p1.getDateCommande(), user1.getNom() + " " + user1.getPrenom(), livre1.getTitre(), p1.getState()));

        }
        commandeID.setCellValueFactory(new PropertyValueFactory("commandeID"));
        clientID.setCellValueFactory(new PropertyValueFactory("clientID"));
        livreID.setCellValueFactory(new PropertyValueFactory("livreID"));
        dateCommande.setCellValueFactory(new PropertyValueFactory("dateCommande"));
        prixTotal.setCellValueFactory(new PropertyValueFactory("prixTotal"));
        fullName.setCellValueFactory(new PropertyValueFactory("fullName"));
        bookTitle.setCellValueFactory(new PropertyValueFactory("bookTitle"));
        orderState.setCellValueFactory(new PropertyValueFactory("state"));
        action.setCellValueFactory(new PropertyValueFactory("action"));

        orderTable.setItems(oblist);
    }

    public void setOrderStats() {
        commandServices cs = new commandServices();
        totalOrders.setText(cs.countCommandes());
        orderOnHold.setText(cs.countCommandesOnHold());
        orderDelivered.setText(cs.countCommandesDelivered());
        pedingOrder.setText(cs.countCommandesPeding());
    }
    @FXML
    public void setPaneOrderOn(ActionEvent event) {
        pnlOverview.setVisible(false);
        pnlOrders.setVisible(true);
    }
    @FXML
    public void setPaneOverviewOn(ActionEvent event) {
        pnlOrders.setVisible(false);
        pnlOverview.setVisible(true);
    }

}
