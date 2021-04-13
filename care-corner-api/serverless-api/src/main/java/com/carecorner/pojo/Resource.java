package com.carecorner.pojo;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor(staticName = "of")
public class Resource {
  Integer id;
  String name;
  String phone;
  String category;
}
