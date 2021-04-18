package com.carecorner.dao;

import com.carecorner.db.Database;
import com.carecorner.pojo.Contact;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public enum ContactDao {
  INSTANCE;

  public List<Contact> findByUser(String user_id) {
    final List<Contact> contacts = new ArrayList<>();
    String sql = String.format("SELECT * FROM contact WHERE user_id='%s'", user_id);

    try (Connection conn = Database.connection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
          Contact contact = Contact.of(
              rs.getInt("contact_id"),
              rs.getInt("user_id"),
              rs.getString("name"),
              rs.getString("phone"));

          contacts.add(contact);
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    return contacts;
  }
}
