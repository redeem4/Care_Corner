package com.carecorner.pojo;

import java.math.BigDecimal;
import javax.print.attribute.standard.DateTimeAtCompleted;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value(staticConstructor = "of")
public class Journey {
  Integer journey_id;
  String journey_path;
  String time;
  BigDecimal start_latitude;
  BigDecimal start_longitude;
  BigDecimal end_latitude;
  BigDecimal end_longitude;
}
