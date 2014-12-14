package dao.impl;

import dao.UserDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDAO{
    private static UserDaoImpl ownInstance = new UserDaoImpl();
    private int id;
    private String role;

    public  static UserDaoImpl getInstance(){
        return ownInstance;
    }

    public String getRole() {
        return role;
    }

    public int getId() {
        return id;
    }

    @Override
    public void authorization(String username, String password) {
        DatabaseConnection db = new DatabaseConnection();
        db.connectToDb();
        try {
            String sql = "SELECT id,role FROM user WHERE username=? and password=?";
            PreparedStatement pr = db.getConnect().prepareStatement(sql);
            pr.setString(1, username);
            pr.setString(2, password);
            ResultSet s =  pr.executeQuery();
            while(s.next()){
                role=s.getString("role");
                id=s.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.closeConnection();
    }
}
