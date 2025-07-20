package com.footwise.service;

import com.footwise.config.DBConnection;
import com.footwise.model.Shoe;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShoeService {
    public boolean addShoe(Shoe shoe) {
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO shoes (name, brand, price, quantity) VALUES (?, ?, ?, ?)");
            ps.setString(1, shoe.getName());
            ps.setString(2, shoe.getBrand());
            ps.setDouble(3, shoe.getPrice());
            ps.setInt(4, shoe.getQuantity());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Shoe> getAllShoes() {
        List<Shoe> shoes = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM shoes");
            while (rs.next()) {
                Shoe shoe = new Shoe();
                shoe.setId(rs.getInt("id"));
                shoe.setName(rs.getString("name"));
                shoe.setBrand(rs.getString("brand"));
                shoe.setPrice(rs.getDouble("price"));
                shoe.setQuantity(rs.getInt("quantity"));
                shoes.add(shoe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return shoes;
    }

    public boolean deleteShoe(int id) {
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM shoes WHERE id=?");
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateShoe(Shoe shoe) {
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("UPDATE shoes SET name=?, brand=?, price=?, quantity=? WHERE id=?");
            ps.setString(1, shoe.getName());
            ps.setString(2, shoe.getBrand());
            ps.setDouble(3, shoe.getPrice());
            ps.setInt(4, shoe.getQuantity());
            ps.setInt(5, shoe.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}