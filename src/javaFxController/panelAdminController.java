/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaFxController;

import controller.EvenementController;
import controller.commandeController;
import e.book.EBook;
import entity.Commande;
import entity.Livre;
import entity.User;
import java.io.IOException;

import static java.lang.Double.parseDouble;
import static java.lang.Float.parseFloat;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javaFxClasses.orderTable;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
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

    public TableView<orderTable> getOrderTable() {
        return orderTable;
    }

    public void setOrderTable(TableView<orderTable> orderTable) {
        this.orderTable = orderTable;
    }

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
    private TableColumn<orderTable, String> prixTotal;
    @FXML
    private TableColumn<orderTable, String> orderState;
    @FXML
    private TableColumn<orderTable, Button> action;
    @FXML
    private TableColumn<orderTable, Button> validate;

    public static ObservableList<orderTable> oblist = FXCollections.observableArrayList();
    @FXML
    private Label totalOrders;
    @FXML
    private Label orderOnHold;
    @FXML
    private Label pedingOrder;
    @FXML
    private Label orderDelivered;
    @FXML
    private Button id_evenement;

    public static TableView<orderTable> order;
    public static Label totalOrders1;
    public static Label orderOnHold1;
    public static Label pedingOrder1;
    public static Label orderDelivered1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        commandeController c = new commandeController();
        //intialise total order number
        pnlOrders.setVisible(false);
        order = orderTable;
        totalOrders1 = totalOrders;
        orderOnHold1 = orderOnHold;
        pedingOrder1 = pedingOrder;
        orderDelivered1 = orderDelivered;

        setOrderStats();
        //fill orderTable with data from database
        fillOrderTable();
        order.setEditable(true);
        prixTotal.setCellFactory(TextFieldTableCell.forTableColumn());
        fullName.setCellFactory(TextFieldTableCell.forTableColumn());
        bookTitle.setCellFactory(TextFieldTableCell.forTableColumn());
        
        


   
    

          
        
        
        

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
            
            oblist.add(new orderTable(p1.getId(), String.valueOf(p1.getPrixTotal()), p1.getClientId(), p1.getLivreId(), p1.getDateCommande(), user1.getNom() + " " + user1.getPrenom(), livre1.getTitre(), p1.getState()));

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
        validate.setCellValueFactory(new PropertyValueFactory("validate"));

        order.setItems(oblist);
    }

    public void setOrderStats() {
        commandServices cs = new commandServices();
        totalOrders1.setText(cs.countCommandes());
        orderOnHold1.setText(cs.countCommandesOnHold());
        orderDelivered1.setText(cs.countCommandesDelivered());
        pedingOrder1.setText(cs.countCommandesPeding());
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
    @FXML
    public void onChanged(CellEditEvent edditedCell) {
        orderTable selectedCells = order.getSelectionModel().getSelectedItem();
        selectedCells.setPrixTotal(edditedCell.getNewValue().toString());
        int commandId = selectedCells.getCommandeID();
        int livreId = selectedCells.getLivreID();
        int clientId = selectedCells.getClientID();
        String n = edditedCell.getNewValue().toString();
        Float number = parseFloat(n);
        commandeController c = new commandeController();
        long millis=System.currentTimeMillis();  
        java.sql.Date currentDate=new java.sql.Date(millis);
        c.modifierCommande(number, currentDate, clientId, livreId, commandId);
    }

    @FXML
    private void gestionEvenement(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("../javaFxInterface/Evenement.fxml"));
            Parent root2=loader.load();
            orderTable.getScene().setRoot(root2);
    }

}
