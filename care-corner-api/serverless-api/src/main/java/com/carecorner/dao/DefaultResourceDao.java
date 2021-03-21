package com.carecorner.dao;

import com.carecorner.db.Database;
import com.carecorner.pojo.Resource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public enum DefaultResourceDao implements ResourceDao {
  INSTANCE;

  @Override
  public List<Resource> findAll() {
    final List<Resource> resources = new ArrayList<>();

    try (Connection conn = Database.connection();
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM resources")) {

      ResultSet rs = ps.executeQuery();

      while (rs.next()) {
        Resource resource = Resource.of(
            rs.getInt("id"),
            rs.getString("name"),
            rs.getString("phone"),
            rs.getString("category"));

        resources.add(resource);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return resources;
  }
}
