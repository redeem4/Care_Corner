package com.carecorner.pojo;

import javax.print.attribute.standard.DateTimeAtCompleted;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value(staticConstructor = "of")
public class Contact {
  String contactId;
  Integer userId;
  String name;
  String phone;
}
