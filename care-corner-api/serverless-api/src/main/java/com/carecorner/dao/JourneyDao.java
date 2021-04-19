package com.carecorner.dao;

import com.carecorner.db.Database;
import com.carecorner.pojo.Journey;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public enum JourneyDao {
  INSTANCE;

  public List<Journey> findByUser(Integer user_id) {
    final List<Journey> journeys = new ArrayList<>();
    String sql = String.format("SELECT * FROM journey WHERE user_id='%s'", user_id);

    try (Connection conn = Database.connection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
          Journey journey = Journey.of(
              rs.getInt("journey_id"),
              rs.getString("journey_path"),
              rs.getString("time"),
              rs.getBigDecimal("start_latitude"),
              rs.getBigDecimal("start_longitude"),
              rs.getBigDecimal("end_latitude"),
              rs.getBigDecimal("end_longitude"));

          journeys.add(journey);
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    return journeys;
  }
}
