package main.java.com.vyorg.abarroteria.kinal.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import main.java.com.vyorg.abarroteria.kinal.controller.DashboardController;
import main.java.com.vyorg.abarroteria.kinal.controller.LoginController;
import main.java.com.vyorg.abarroteria.kinal.repository.AuthRepository;
import main.java.com.vyorg.abarroteria.kinal.repository.ProductoRepository;
import main.java.com.vyorg.abarroteria.kinal.service.AuthService;
import main.java.com.vyorg.abarroteria.kinal.service.DashboardService;

public class SceneManager {
    private final Stage stage;
    private final String FXML_PATH = "/main/resources/view/";
    //constructor
    public SceneManager(Stage stage){
        this.stage = stage;
    }
    
    public void showLoginView() throws Exception{
        FXMLLoader loader= new FXMLLoader(getClass().getResource(FXML_PATH +"login-view.fxml"));
        
        loader.setControllerFactory(
        clazz->{
            if(clazz == LoginController.class){
                AuthRepository authRepository = new AuthRepository();
            AuthService authService = new AuthService(authRepository);
            return new LoginController(authService,this);
        }
            try{
                return clazz.getDeclaredConstructor().newInstance();     
                
            }catch(Exception e){
                throw new RuntimeException("error al crear el constructor" + e.getMessage());
            }
        }
        );
        Parent root = loader.load();
        Scene scene = new Scene(root,600,600);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }
    
    // dashboard stage
    public void showDashboardView()throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML_PATH+"dashboard-view.fxml"));
        loader.setControllerFactory(
        clazz ->{
            if(clazz == DashboardController.class){
                ProductoRepository productoRepository= new ProductoRepository();
                DashboardService service = new DashboardService(productoRepository); 
            return new DashboardController(service,this);
            
            }
            try{
                return clazz.getDeclaredConstructor().newInstance();
            }catch(Exception e){
                throw new RuntimeException("Erro al cargar el constructor");
            }
        }
        );
    
        Parent root = loader.load();
        Scene scene = new Scene(root,600,600);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }
    
//Alerta modal reutilizable
    public void showAlertInfo(String head, String title, String content, AlertType type){
        Alert alert= new Alert(type);
    alert.initOwner(this.stage);
    alert.setHeaderText(head);
    alert.setTitle(title);
    alert.setContentText(content);
    alert.showAndWait();
    }

    public boolean showConfirmation(String head, String title, String content){
    Alert alert = new Alert(AlertType.CONFIRMATION);
    alert.initOwner(this.stage);
    alert.setHeaderText(head);
    alert.setTitle(title);
    alert.setContentText(content);
    return alert.showAndWait()
            .filter(buttonType -> buttonType == javafx.scene.control.ButtonType.OK)
            .isPresent();
}
    
}
