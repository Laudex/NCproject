package DBclasses;

import Entity.*;

import java.sql.*;
import java.util.List;

public class DBConnection {
    public static void selectUsers(String sqlQuery, List specificUsers){
        Connection connection;
        Statement stmt;
        try {
            connection = DBConnectionFactory.conFactory();
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sqlQuery);
            while(rs.next()){
                User nextUser = new User();
                nextUser.setUserId(rs.getInt("user_id"));
                nextUser.setAdmin(rs.getBoolean("is_admin"));
                specificUsers.add(nextUser);
            }
            rs.close();
            stmt.close();
            connection.close();
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    public static void selectOffers(String sqlQuery, List specificOffers){
        Connection connection;
        Statement stmt;
        try {
            connection = DBConnectionFactory.conFactory();
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sqlQuery);
            while(rs.next()){
                Offer nextOffer = new Offer();
                nextOffer.setOffer_id(rs.getInt("offer_id"));
                nextOffer.setName(rs.getString("name"));
                specificOffers.add(nextOffer);
            }
            rs.close();
            stmt.close();
            connection.close();
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    public static void selectOrders(String sqlQuery, List specificOrders){
        Connection connection;
        Statement stmt;
        try {
            connection = DBConnectionFactory.conFactory();
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sqlQuery);
            while(rs.next()){
                Orders nextOrder = new Orders();
                nextOrder.setOrderId(rs.getInt("order_id"));
                nextOrder.setOfferId(rs.getInt("offer_id"));
                nextOrder.setUserId(rs.getInt("user_id"));
                nextOrder.setStartDate(rs.getString("start_date"));
                specificOrders.add(nextOrder);
            }
            rs.close();
            stmt.close();
            connection.close();
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    public static void selectAttrs(String sqlQuery, List specificAttrs){
        Connection connection;
        Statement stmt;
        try {
            connection = DBConnectionFactory.conFactory();
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sqlQuery);
            while(rs.next()){
                Attr nextAttr = new Attr();
                nextAttr.setAttrId(rs.getInt("attr_id"));
                nextAttr.setName(rs.getString("name"));
                specificAttrs.add(nextAttr);
            }
            rs.close();
            stmt.close();
            connection.close();
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    public static void selectAttrsValues(String sqlQuery, List specificAttrs){
        Connection connection;
        Statement stmt;
        try {
            connection = DBConnectionFactory.conFactory();
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sqlQuery);
            while(rs.next()){
                AttrValues nextAttrValues = new AttrValues();
                nextAttrValues.setOfferId(rs.getInt("offer_id"));
                nextAttrValues.setAttrId(rs.getInt("attr_id"));
                nextAttrValues.setValue(rs.getString("value"));
                specificAttrs.add(nextAttrValues);
            }
            rs.close();
            stmt.close();
            connection.close();
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
