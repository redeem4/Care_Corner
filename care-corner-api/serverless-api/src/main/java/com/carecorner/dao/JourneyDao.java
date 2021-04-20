package com.carecorner.dao;

import com.carecorner.db.Database;
import com.carecorner.pojo.Journey;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.math.BigDecimal;
import java.util.List;


public enum JourneyDao {
  INSTANCE;

  public void create(Journey journey) {
    final List<Journey> journeys = new ArrayList<>();
    String sql = String.format("insert into journey (user_id, journey_path, time, start_latitude, " + 
      "start_longitude) values ('%s', '%s', '%s', '%s', '%s')", 
      journey.getUserId(), journey.getJourneyPath(), journey.getTime(), journey.getStartLatitude(),
      journey.getStartLongitude());
    try (Connection conn = Database.connection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.executeUpdate();
      } catch (SQLException e) {
        e.printStackTrace();
      }
   }
  
  public List<Journey> findByUser(Integer userId) {
    final List<Journey> journeys = new ArrayList<>();
    String sql = String.format("SELECT * FROM journey WHERE user_id='%s'", userId);

    try (Connection conn = Database.connection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
          Journey journey = Journey.of(
              rs.getString("user_id"),
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

  public List<Journey> UpdateByUser(Integer userId) {
    final List<Journey> journeys = new ArrayList<>();
    String sql = String.format("select * from journey where user_id='%s'", userId);

    try (Connection conn = Database.connection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
          Journey journey = Journey.of(
              rs.getString("journey_id"),
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
