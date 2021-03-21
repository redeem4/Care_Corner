package com.carecorner.conf;

import lombok.Data;

@Data
public class Configuration {

  DataSource datasource;

  @Data
  public static final class DataSource {
    String driverClassName;
    String url;
    String username;
    String password;
  }
}
