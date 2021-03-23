package com.carecorner.dao;

import com.carecorner.AbstractIntegrationTest;
import com.carecorner.pojo.Resource;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;


public class DefaultResourceDaoTest extends AbstractIntegrationTest {

  private final ResourceDao service = DefaultResourceDao.INSTANCE;

  public void testThatFindReturnsResourceList() {
    final List<Resource> resources = service.findAll();

    assertThat(resources, notNullValue());
    assertThat(resources.size(), is(3));
    assertThat(resources.get(1), notNullValue());
    assertThat(resources.get(1).getId(), is(2));
    assertThat(resources.get(1).getName(), is("Assault Hotline"));
    assertThat(resources.get(1).getPhone(), is("1-800-555-5555"));
    assertThat(resources.get(1).getCategory(), is("hotline"));
  }

  @Override
  protected String dataSet() {
    return "/db/data.xml";
  }
}
