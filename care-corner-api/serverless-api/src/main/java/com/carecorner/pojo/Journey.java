package com.carecorner.pojo;

import java.math.BigDecimal;
import javax.print.attribute.standard.DateTimeAtCompleted;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value(staticConstructor = "of")
public class Journey {
  Integer userId;
  String journeyPath;
  String time;
  BigDecimal startLatitude;
  BigDecimal startLongitude;
  BigDecimal endLatitude;
  BigDecimal endLongitude;
}
