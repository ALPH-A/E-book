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
import static java.lang.Integer.parseInt;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaFxClasses.orderTable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
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
     ObservableList<User> filteredData = FXCollections.observableArrayList();
    @FXML
    private Pane pnlOrders;
    @FXML
    private Pane pnlMenus;
    @FXML
    private Pane pnlOverview;
    @FXML
    private TableView<orderTable> orderTable;
    @FXML
    private Button deleteid;
    @FXML
    private TableView<User> t;
    @FXML
    private TableColumn<User, Integer> idcolumn;
    @FXML
    private TableColumn<User, String> namecolumn;
    @FXML
    private TableColumn<User, String> lastnamecolumn;
    @FXML
    private TableColumn<User, Integer> phonecolumn;
    @FXML
    private TableColumn<User, String> emailcolumn;
    @FXML
    private TableColumn<User, String> adresscolumn;
    @FXML
    private TableColumn<User, String> verificationcolumn;
    @FXML
    private TableColumn<?, ?> imagecolumn;
    @FXML
    private TextField rechercherid;
    @FXML
    private ImageView imageuserid;
    @FXML
    private TextField invisible;
     private Image image;

    public TableView<orderTable> getOrderTable() {
        return orderTable;
    }

    public void setOrderTable(TableView<orderTable> orderTable) {
        this.orderTable = orderTable;
    }
    public TableView<User> getUserTable() {
        return t;
    }

    public void setUserTable(TableView<User> t) {
        this.t = t;
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
        t.setEditable(true);
        verificationcolumn.setCellFactory(TextFieldTableCell.forTableColumn());
        UpdateTable();
         ObservableList<User> filter = FXCollections.observableArrayList();
                userServices s= new userServices();
        filteredData.addAll(filter);
     
     filter.addListener(new ListChangeListener<User>() {
			@Override
			public void onChanged(ListChangeListener.Change<? extends User> change) {
                            try {
                                updateFilteredData();
                            } catch (SQLException ex) {
                                Logger.getLogger(panelAdminController.class.getName()).log(Level.SEVERE, null, ex);
                            }
			}
		});
     t.setItems(filteredData);
     rechercherid.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable,
					String oldValue, String newValue) {
				
                            try {
                                updateFilteredData();
                            } catch (SQLException ex) {
                                Logger.getLogger(panelAdminController.class.getName()).log(Level.SEVERE, null, ex);
                            }
			

                   }
		});
        


   
    

          
        
        
        

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

    @FXML
    private void delete(ActionEvent event) {
        User u = new User();

        try {
            System.out.println("Hiii" + t.getSelectionModel().getSelectedItem().getId());
            u.setId(t.getSelectionModel().getSelectedItem().getId());
            userServices ser = new userServices();
            ser.supprimer(u);
            JOptionPane.showMessageDialog(null, "Delete");
            UpdateTable();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private boolean matchesFilter(User user) {
		String filterString = rechercherid.getText();
		if (filterString == null || filterString.isEmpty()) {
			// No filter --> Add all.
			return true;
		}
		
		String lowerCaseFilterString = filterString.toLowerCase();
		
		if (user.getNom().toLowerCase().indexOf(lowerCaseFilterString) != -1) {
			return true;
		} else if (user.getPrenom().toLowerCase().indexOf(lowerCaseFilterString) != -1) {
			return true;
		}
		
		return false; // Does not match
	}
     private void updateFilteredData() throws SQLException {
		filteredData.clear();
                ObservableList<User> filter = FXCollections.observableArrayList();
                userServices s= new userServices();
                filter=s.readAll();
			
		for (User l : filter) {
			if (matchesFilter(l)) {
				filteredData.add(l);
			}
		}
		
		// Must re-sort table after items changed
		reapplyTableSortOrder();
	}
       private void reapplyTableSortOrder() {
		ArrayList<TableColumn<User, ?>> sortOrder = new ArrayList<>(t.getSortOrder());
		t.getSortOrder().clear();
		t.getSortOrder().addAll(sortOrder);
	}


    @FXML
    private void chargeimage(MouseEvent event) {
          User u= new User();
        u=t.getSelectionModel().getSelectedItem();
     invisible.setText(u.getImage());
     String s=invisible.getText();
     image=new Image(s);
     imageuserid.setImage(image);
    }
     public void UpdateTable() {
        idcolumn.setCellValueFactory(new PropertyValueFactory<User, Integer>("id"));
        namecolumn.setCellValueFactory(new PropertyValueFactory<User, String>("nom"));
        lastnamecolumn.setCellValueFactory(new PropertyValueFactory<User, String>("prenom"));
        phonecolumn.setCellValueFactory(new PropertyValueFactory<User, Integer>("num_telephone"));
        emailcolumn.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
        adresscolumn.setCellValueFactory(new PropertyValueFactory<User, String>("adresse"));
        imagecolumn.setCellValueFactory(new PropertyValueFactory<>("image"));
        verificationcolumn.setCellValueFactory(new PropertyValueFactory<>("verification_account"));
       
        userServices ser = new userServices();

        ObservableList<User> listu = FXCollections.observableArrayList();
        System.out.println(listu);
        System.out.println(listu.size());
        try {

            for (User u : ser.readAll()) {
                listu.add(u);
            }
        } catch (SQLException ex) {
            Logger.getLogger(panelAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        t.setItems(null);
        t.setItems(listu);
    }
public void setPaneCustomersOn(ActionEvent event) {
        pnlOrders.setVisible(false);
        pnlOverview.setVisible(false);
        pnlCustomer.setVisible(true);
    }

    @FXML
    public void onChanged1(CellEditEvent edditedCell) {
        User selectedCells = t.getSelectionModel().getSelectedItem();
        selectedCells.setVerification_account(edditedCell.getNewValue().toString());
        int id = selectedCells.getId();
        String n = edditedCell.getNewValue().toString();
        int number = parseInt(n);
        System.out.println(number);
        System.out.println(id);
        userServices u=new userServices();
       u.updateUserVerificationAccount(number, id);
       
    }

}
