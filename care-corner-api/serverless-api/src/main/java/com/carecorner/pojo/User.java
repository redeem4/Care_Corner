package com.carecorner.pojo;

import lombok.Getter;
import lombok.Value;

@Value(staticConstructor = "of")
public class User {
  Integer id;
  String username;
  String fname;
  String email;
  String password;
}
