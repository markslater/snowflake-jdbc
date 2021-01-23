/*
 * Copyright (c) 2012-2019 Snowflake Computing Inc. All rights reserved.
 */

package net.snowflake.client.core;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import net.snowflake.client.jdbc.ErrorCode;
import org.junit.Assert;
import org.junit.Test;

public class SFConnectionPropertyTest {
  @Test
  public void testCheckApplicationName() throws SFException {
    String[] validApplicationName = {"test1234", "test_1234", "test-1234", "test.1234"};

    String[] invalidApplicationName = {"1234test", "test$A", "test<script>"};

    for (String valid : validApplicationName) {
      Object value =
          SFConnectionProperty.checkPropertyValue(SFConnectionProperty.APPLICATION, valid);

      assertThat((String) value, is(valid));
    }

    for (String invalid : invalidApplicationName) {
      try {
        SFConnectionProperty.checkPropertyValue(SFConnectionProperty.APPLICATION, invalid);
        Assert.fail();
      } catch (SFException e) {
        assertThat(e.getVendorCode(), is(ErrorCode.INVALID_PARAMETER_VALUE.getMessageCode()));
      }
    }
  }
}
