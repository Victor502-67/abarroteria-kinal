package main.java.com.vyorg.abarroteria.kinal.service;

import javafx.collections.ObservableList;
import main.java.com.vyorg.abarroteria.kinal.model.Producto;
import main.java.com.vyorg.abarroteria.kinal.repository.ProductoRepository;


public class DashboardService {
  private final ProductoRepository productoRepository;
  private final DashboardService dashboardService;

public DashboardService(ProductoRepository productoRepository, DashboardService dashboardService){
    this.productoRepository = productoRepository;
    this.dashboardService=dashboardService;
} 

public ObservableList<Producto>findProducto(){
    if(productoRepository.findAll() == null){
        throw new RuntimeException("Sin productos");
    }else{
    return productoRepository.findAll();
        }
    }
}
