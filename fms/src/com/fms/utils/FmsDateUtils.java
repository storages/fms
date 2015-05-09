package com.fms.utils;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;

public class FmsDateUtils {

	/**
	 * 把当前系统日期格式化成yyyy-MM-dd 00:00:00
	 * 
	 * @return
	 */
	public static Date getCurrentFormatDate() {
		try {
			Calendar c = Calendar.getInstance();
			c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE));
			Date date = c.getTime();
			date = DateUtils.truncate(date, Calendar.DATE);
			return date;
			// System.out.println(DateFormatUtils.ISO_DATE_FORMAT.format(date));
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return null;
	}

	/**
	 * 获取起始时间(注意：hql查询日期条件一定要用这个方法)
	 * 
	 * @param date
	 * @return
	 * @格式：Thu Apr 16 12:00:00 CST 2015
	 */
	public static Date getStartDate(Date date) {
		Calendar todayStart = Calendar.getInstance();
		todayStart.setTime(date);
		todayStart.set(Calendar.HOUR, 0);
		todayStart.set(Calendar.MINUTE, 0);
		todayStart.set(Calendar.SECOND, 0);
		todayStart.set(Calendar.MILLISECOND, 0);
		return new Date(todayStart.getTime().getTime());
	}

	/**
	 * 获取结束时间(注意：hql查询日期条件一定要用这个方法)
	 * 
	 * @param date
	 * @return
	 * @格式：Fri Apr 17 11:59:59 CST 2015
	 */
	public static Date getEndDate(Date date) {
		Calendar todayEnd = Calendar.getInstance();
		todayEnd.setTime(date);
		todayEnd.set(Calendar.HOUR, 23);
		todayEnd.set(Calendar.MINUTE, 59);
		todayEnd.set(Calendar.SECOND, 59);
		todayEnd.set(Calendar.MILLISECOND, 999);
		return new Date(todayEnd.getTime().getTime());
	}

	public static void main(String[] args) {
		System.out.println(getStartDate(new Date()));
		System.out.println(getEndDate(new Date()));
	}
}
