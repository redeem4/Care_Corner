package com.carecorner.pojo;

import javax.print.attribute.standard.DateTimeAtCompleted;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value(staticConstructor = "of")
public class Contact {
  String contact_id;
  Integer user_id;
  String name;
  String phone;
}
