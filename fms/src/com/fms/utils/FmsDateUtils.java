package com.fms.utils;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;

public class FmsDateUtils {

	/**
	 * 把当前系统日期格式化成yyyy-MM-dd 00:00:00
	 * @return
	 */
	public static Date getCurrentFormatDate(){
		try {
			 Calendar c = Calendar.getInstance();
			 c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE));
			 Date date = c.getTime();
			 date = DateUtils.truncate(date, Calendar.DATE);
			 return date;
			 //System.out.println(DateFormatUtils.ISO_DATE_FORMAT.format(date));
		}catch (Exception ex){
			System.out.println(ex.getMessage());
		}
		return null;
	}
}
