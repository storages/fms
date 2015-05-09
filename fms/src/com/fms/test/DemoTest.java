package com.fms.test;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

public class DemoTest {
	@Test
	public void formatDate() {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println(format.format(date));

	}
}
