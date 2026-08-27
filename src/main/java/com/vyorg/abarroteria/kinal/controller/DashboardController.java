package main.java.com.vyorg.abarroteria.kinal.controller;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.java.com.vyorg.abarroteria.kinal.model.Producto;
import main.java.com.vyorg.abarroteria.kinal.service.DashboardService;
import main.java.com.vyorg.abarroteria.kinal.util.SceneManager;


public class DashboardController implements Initializable {

    private DashboardService dashboardService;
    private SceneManager sceneManager;
    @FXML
    private TableView<Producto> tableProducto;
    @FXML
    private TableColumn<Producto,String> tableColumnIdProducto;
    @FXML
    private TableColumn<Producto,String>tableColumnNombre;
    @FXML
    private TableColumn<Producto,Integer> tableColumnStock;
    @FXML
    private TableColumn<Producto,BigDecimal> tableColumnPrecio;
    
    public DashboardController (DashboardService dashboardService,SceneManager sceneManager){
        this.dashboardService=dashboardService;
        this.sceneManager = sceneManager;
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        handleLoadDataTableView();
    }    
    
    private void handleLoadDataTableView(){
        tableColumnIdProducto.setCellValueFactory(new PropertyValueFactory<>("idProducto"));
    tableColumnNombre.setCellValueFactory(new PropertyValueFactory("nombreProducto"));
    tableColumnStock.setCellValueFactory(new PropertyValueFactory("stock"));
    tableColumnPrecio.setCellValueFactory(new PropertyValueFactory("precio"));
    
    }
    
}
