package com.carecorner.dao;

import com.carecorner.db.Database;
import com.carecorner.pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public enum UserDao {
  INSTANCE;

  public List<User> findAll() {
    final List<User> users = new ArrayList<>();

    try (Connection conn = Database.connection();
      PreparedStatement ps = conn.prepareStatement("SELECT * FROM user")) {

      ResultSet rs = ps.executeQuery();

      while (rs.next()) {
        User user = User.of(
            rs.getInt("id"),
            rs.getString("username"),
            rs.getString("fname"),
            rs.getString("email"),
            rs.getString("password"));

        users.add(user);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return users;
  }
}
