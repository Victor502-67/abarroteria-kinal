package main.java.com.vyorg.abarroteria.kinal.repository;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import main.java.com.vyorg.abarroteria.kinal.config.DataBaseConnection;
import main.java.com.vyorg.abarroteria.kinal.dto.request.LoginDTORequest;
import main.java.com.vyorg.abarroteria.kinal.dto.response.LoginDTOResponse;

public class AuthRepository {
        public LoginDTOResponse findUserByEmail(LoginDTORequest loginDTORequest){
    String sql = "select u.nombre, u.apellido, u.contrasena_hash, r.nombre_rol from usuarios as u " +
                     "inner join roles as r " +
                     "on u.id_rol = r.id_ro l" +
                     "where u.email = ? ";
    try(PreparedStatement pstm = DataBaseConnection.getDataBaseConnection().prepareStatement(sql)){
        pstm.setString(1, loginDTORequest.getEmail());
        ResultSet rs = pstm.executeQuery();
        if(rs.next()){
            return new LoginDTOResponse(
            rs.getString("nombre"),
            rs.getString("apellido"),
            rs.getString("contrasena_hash"),
            rs.getString("nombre_rol")
            );
        }
    }catch(SQLException e){
        System.out.println("Error al buscar el usuario");
                
        }
    return null;
}
}
