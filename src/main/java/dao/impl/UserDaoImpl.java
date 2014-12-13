package dao.impl;

import dao.UserDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDAO{
    private final String sql ="SELECT id,role FROM user WHERE username=? and password=?";
    private int id;
    private String role;

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
            PreparedStatement pr = db.getConnect().prepareStatement(sql);
            pr.setString(1,username);
            pr.setString(2,password);
            ResultSet s =  pr.executeQuery();
            while(s.next()){
                role=s.getString("role");
                id=s.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        db.closeConnection();
    }


}
