package com.carecorner.pojo;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor(staticName = "of")
public class User {
  Integer id;
  String username;
  String fname;
  String email;
  String password;
}
