package com.carecorner.conf;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

public class ConfigurationHolderTest {

  @Test
  public void thatConfigurationValuesAreLoaded() {
    final Configuration configuration = ConfigurationHolder.INSTANCE.configuration();

    assertThat(configuration, notNullValue());
    assertThat(configuration.getDatasource(), notNullValue());
    assertThat(configuration.getDatasource().getDriverClassName(), is("org.h2.Driver"));
    assertThat(configuration.getDatasource().getUrl(), is("jdbc:h2:mem:test"));
    assertThat(configuration.getDatasource().getUsername(), is("test-username"));
    assertThat(configuration.getDatasource().getPassword(), is("test-password"));
  }

}
