package com.aofan.cardismantling.util;


import android.util.Log;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CalendarUtil {

	//把long类型转换为string类型:yyyy-MM-dd HH:mm
	public static String longToString4(long timelong)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(timelong);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return sdf.format(calendar.getTime());
	}
	
	// 把long类型转换为string类型:yyyy年MM月dd日
	public static String longToString3(long timelong) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(timelong);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
		return sdf.format(calendar.getTime());
	}
	
	//把long类型转换为string类型:yyyy-MM-dd HH:mm:dd
	public static String longToString2(long timelong)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(timelong);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:dd");
		return sdf.format(calendar.getTime());
	}
	
	//把long类型转换为string类型:yyyy-MM-dd
	public static String longToString(long timelong)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(timelong);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(calendar.getTime());
		
	}
	
	//把long类型值转换成calendar的值
	public static Calendar longToCalendar(long timelong)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(timelong);
		
		return calendar;
		
	}
	
	// 获取这一天是星期几或者是昨天，或者是当前这几天的时间，或者是日期
	//dateString是yyyy-MM-dd HH：mm格式
	public static String getDateInfo(String dateString) {
		int betWeenDays = CalendarUtil.getDayBetWeenNowAndChooseDay(dateString);
		String timeString = null;
		if (betWeenDays == 0) {
			timeString = dateString.substring(dateString.lastIndexOf(" "));
		} else if (betWeenDays == 1) {
			timeString = "昨天";
		} else if (betWeenDays < (CalendarUtil.getNowDayWeekDayDistance() - 1)) {
			timeString = CalendarUtil.getCalendarWeekDayByString(dateString);
		} else if (betWeenDays >= (CalendarUtil.getNowDayWeekDayDistance() - 1)) {
			timeString = dateString.substring(0, dateString.lastIndexOf(" "));
		}
		return timeString;
	}
	
	
	
	//从yyyy-MM-dd HH:mm 截取yyyy-MM-dd部分
	public static String subDatePart(String dateTimeString)
	{
		
		return dateTimeString.substring(0,dateTimeString.indexOf(" "));
	}
	
	

	
	//获取选择后的时间字符串
	private static String getHourMinuteString(int hour,int minute)
	{
		String hour_c = String.valueOf(hour);
		String minute_c = String.valueOf(minute);
		if (hour < 10) {
			hour_c = "0" + hour_c;
		}
		if (minute < 10) {
			minute_c = "0" + minute_c;
		}
		
		return hour_c+":"+minute_c;
	}
	
	//把int类型的年月日设置成yyyy-MM-dd类型的字符串
	public static String setYearMonthDayString(int year,int month,int day)
	{
		StringBuilder dateStringBuilder = new StringBuilder();
		dateStringBuilder.append(year);
		dateStringBuilder.append("-");
		//因为机器上的月是从0-11，而我们是从1-12，所以要加1
		++month;
		if (month<10) {
			dateStringBuilder.append("0").append(month);
		}else if (month>=10) {
			dateStringBuilder.append(month);
		}
		dateStringBuilder.append("-");
		
		if (day<10) {
			dateStringBuilder.append("0").append(day);
		}else if (day>=10) {
			dateStringBuilder.append(day);
		}
		
		return dateStringBuilder.toString();
	}
	
	//把int类型的年月日设置成yyyy年MM月dd日类型的字符串
		public static String setYearMonthDayString2(int year,int month,int day)
		{
			StringBuilder dateStringBuilder = new StringBuilder();
			dateStringBuilder.append(year);
			dateStringBuilder.append("年");
			//因为机器上的月是从0-11，而我们是从1-12，所以要加1
			++month;
			if (month<10) {
				dateStringBuilder.append("0").append(month);
			}else if (month>=10) {
				dateStringBuilder.append(month);
			}
			dateStringBuilder.append("月");
			
			if (day<10) {
				dateStringBuilder.append("0").append(day);
			}else if (day>=10) {
				dateStringBuilder.append(day);
			}
			dateStringBuilder.append("日");
			return dateStringBuilder.toString();
		}
	
	
	
	
	//获取当前日期零时零分的时间：yyyy-MM-dd HH:mm
	public static String getNowDateZeroTime(String timeString)
	{
		String  newtimeString = timeString.replaceAll(timeString.substring(11), "00:00");
		return newtimeString;
	}
	
	//获取两个时间之间相差的天数：yyyy-MM-dd
	public static int getDaysBetWeen2Calendar(String calendar1,String calendar2)
	{
		//SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal1=string2Calendar(calendar1);
		
		Calendar cal2=string2Calendar(calendar2);
		
		long l=cal2.getTimeInMillis()-cal1.getTimeInMillis();
		int days=new Long(l/(1000*60*60*24)).intValue();
		return days;
	}
	
	// 获取两个时间之间相差的天数：yyyy-MM-dd
	public static int getDaysBetWeen2Calendar2(String calendar1,
			String calendar2) {
		// SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Calendar cal1 = string2Calendar2(calendar1);

		Calendar cal2 = string2Calendar2(calendar2);

		long l = cal2.getTimeInMillis() - cal1.getTimeInMillis();
		int days = new Long(l / (1000 * 60 * 60 * 24)).intValue();
		return days;
	}
	
	//判断当前这一天和选择的某一天，两天之间的差的天数
	public static int getDayBetWeenNowAndChooseDay(String calendar2){
		Calendar nowCalendar = Calendar.getInstance();
		String nowCalendarString = calendar2String2(nowCalendar);
		nowCalendarString = nowCalendarString.substring(0, nowCalendarString.indexOf(" "));
		nowCalendar = string2Calendar(nowCalendarString);
		//System.out.println(nowCalendarString+"|");
		
		calendar2 = calendar2.substring(0, calendar2.indexOf(" "));
		//System.out.println(calendar2);
		
		Calendar cal2 = string2Calendar(calendar2);
		
		long l = nowCalendar.getTimeInMillis() - cal2.getTimeInMillis();
		int days = new Long(l / (1000 * 60 * 60 * 24)).intValue();
		return days;
	}
	
	//获取当前这一天是这周的第几天
	public static int getNowDayWeekDayDistance()
	{
		int dayDistance = 0;
		Calendar nowCalendar = Calendar.getInstance();
		switch (nowCalendar.get(Calendar.DAY_OF_WEEK)) {
		case Calendar.MONDAY:
			dayDistance = 1;
			break;
		case Calendar.TUESDAY:
			dayDistance = 2;

			break;
		case Calendar.WEDNESDAY:
			dayDistance = 3;

			break;
		case Calendar.THURSDAY:
			dayDistance =4;

			break;
		case Calendar.FRIDAY:
			dayDistance = 5;

			break;
		case Calendar.SATURDAY:
			dayDistance = 6;

			break;
		case Calendar.SUNDAY:
			dayDistance = 7;

			break;
		default:
			break;
		}
		return dayDistance;
	}
	
	
	//获取两个日期之间的毫秒
	public static long getMillisBetween2Calendar(String calendar1,String calendar2)
	{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Log.i("calendar1", calendar1);
		Log.i("calendar2", calendar2);
		Calendar cal1=string2Calendar(calendar1);
		
		Calendar cal2=string2Calendar(calendar2);
		
		long l=cal2.getTimeInMillis()-cal1.getTimeInMillis();
		Log.i("calendar1millis", cal1.getTimeInMillis()+"");
		Log.i("calendar2millis", cal2.getTimeInMillis()+"");
		Log.i("between millis", new Long(l/(1000)).intValue()+"");
		
		return l;
	}
	
	
	//根据calendar获取这一天是星期几
	public static String getCalendarWeekDay(Calendar calendar)
	{
		String dayName = new String();
		switch (calendar.get(Calendar.DAY_OF_WEEK)) {
		case Calendar.MONDAY:
			dayName = "星期一";
			break;
		case Calendar.TUESDAY:
			dayName = "星期二";
			break;
		case Calendar.WEDNESDAY:
			dayName = "星期三";
			break;
		case Calendar.THURSDAY:
			dayName = "星期四";
			break;
		case Calendar.FRIDAY:
			dayName = "星期五";
			break;
		case Calendar.SATURDAY:
			dayName = "星期六";
			break;
		case Calendar.SUNDAY:
			dayName = "星期日";
			break;
		default:
			break;
		}
		return dayName;
	}
	
	//根据dateString获取这一天是星期几
	public static String getCalendarWeekDayByString(String dateString)
	{
		String dayName = new String();
		Calendar calendar = string2Calendar(dateString);
		switch (calendar.get(Calendar.DAY_OF_WEEK)) {
		case Calendar.MONDAY:
			dayName = "星期一";
			break;
		case Calendar.TUESDAY:
			dayName = "星期二";
			break;
		case Calendar.WEDNESDAY:
			dayName = "星期三";
			break;
		case Calendar.THURSDAY:
			dayName = "星期四";
			break;
		case Calendar.FRIDAY:
			dayName = "星期五";
			break;
		case Calendar.SATURDAY:
			dayName = "星期六";
			break;
		case Calendar.SUNDAY:
			dayName = "星期日";
			break;
		default:
			break;
		}
		return dayName;
	}
	
	//根据dateString获取这一天是星期几yyyy-MM-dd HH:mm
		public static String getCalendarWeekDayByString2(String dateString)
		{
			String dayName = new String();
			Calendar calendar = string2Calendar2(dateString);
			switch (calendar.get(Calendar.DAY_OF_WEEK)) {
			case Calendar.MONDAY:
				dayName = "星期一";
				break;
			case Calendar.TUESDAY:
				dayName = "星期二";
				break;
			case Calendar.WEDNESDAY:
				dayName = "星期三";
				break;
			case Calendar.THURSDAY:
				dayName = "星期四";
				break;
			case Calendar.FRIDAY:
				dayName = "星期五";
				break;
			case Calendar.SATURDAY:
				dayName = "星期六";
				break;
			case Calendar.SUNDAY:
				dayName = "星期日";
				break;
			default:
				break;
			}
			return dayName;
		}
	
	//Calendar 转化 String:yyyy-MM-dd
	public static String calendar2String(Calendar calendar)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(calendar.getTime());
	}
	
	//Calendar 转化 String:yyyy-MM-dd HH:mm
	public static String calendar2String2(Calendar calendar)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return sdf.format(calendar.getTime());
	}
	
	/*public static String calendar2String3(Calendar calendar)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
		return sdf.format(calendar.getTime());
	}*/

	public static String calendar2String3(Calendar calendar)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(calendar.getTime());
	}
	
	//Calendar 转化 String:yyyy年MM月dd日
	public static String calendar2String4(Calendar calendar)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
		return sdf.format(calendar.getTime());
	}
	
	//String 转化Calendar yyyy-MM-dd
	public static Calendar string2Calendar(String dateString)
	{
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = sdf.parse(dateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}
	
	
	// String 转化Calendar yyyy-MM-dd HH:mm
	public static Calendar string2Calendar2(String dateString) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date date = null;
		try {
			date = sdf.parse(dateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}
	
	
	// String 转化Calendar yyyy.MM.dd
		public static Calendar string2Calendar3(String dateString) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
			Date date = null;
			try {
				date = sdf.parse(dateString);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			return calendar;
		}
	
	public static boolean isTheDayInThisWeek(Calendar calendar)
	{
		return false;
	}

	//date 转化 string
	public static String date2String(Date date)
	{
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}
	
	//string 转化date
	public static Date string2Date(String dateString)
	{
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date =  sdf.parse(dateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return date;
	}
	
	//date 转化 calendar
	public static Calendar date2Calendar(Date date)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}
	
	
	//calenar 转化 date
	public static Date calenar2Date(Calendar calendar)
	{
		return calendar.getTime();
	}
	
	
	//string 转化为timestamp
	public static Timestamp string2Timestamp(String dateString)
	{
		return Timestamp.valueOf(dateString);
	}
	
	//date 转化为timestamp
	public static Timestamp date2TimeStamp(Date date)
	{
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = df.format(new Date());
		return Timestamp.valueOf(time);
	}
	
	
	
	
	
	public static final String MONTH = "month";
	public static final String WEEKNUM = "weeknum";
	//获取当前周是月的第几周
	/**
	 * 
	 * @param month  当前的月份 calendar.month
	 * @param dayOFMonth 当前这一天是这个月的多少天 calenar.dayofmonth
	 * @return 返回month和周数的map  月份用MONTH获得  周数用WEEKNUM获得
	 */
	public static Map<String,Object> getNowMonthWeek(int month,int dayOFMonth)
	{	
		
		
		//firstday of month
		Calendar firstDay = Calendar.getInstance();
		firstDay.set(Calendar.MONTH, month);
		firstDay.set(Calendar.DAY_OF_MONTH, 1);
		//每个月第一天排周几      一周从星期日开始（日：1，一：2，二：3，三：4，四：5，五：6，六：7）
		int firstDayWeekDay = firstDay.get(Calendar.DAY_OF_WEEK);
		//System.out.println("firstDayWeekDay:"+firstDayWeekDay);
		
		//lastday of month
		Calendar lastDay = Calendar.getInstance();
		lastDay.set(Calendar.MONTH, month);
		lastDay.set(Calendar.DAY_OF_MONTH, lastDay.getActualMaximum(Calendar.DAY_OF_MONTH));
		//每个月最后一天排周几      一周从星期日开始（日：1，一：2，二：3，三：4，四：5，五：6，六：7）
		int lastDayWeekDay = lastDay.get(Calendar.DAY_OF_WEEK);
		//System.out.println("lastDayWeekDay:"+lastDayWeekDay);
		
		//now
		Calendar nowDay = Calendar.getInstance();
		nowDay.set(Calendar.MONTH, month);
		nowDay.set(Calendar.DAY_OF_MONTH, dayOFMonth);
		
		int betweenFirstDay = getTwoCalendarbetweenDays(firstDay, nowDay);		
		
		int betweenLastDay = getTwoCalendarbetweenDays(nowDay, lastDay);
		
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if (firstDayWeekDay > 4) {
	        //前几天在上一个月最后一周，正常周数减去1
	        if (betweenFirstDay <= 7-firstDayWeekDay) {
	        	//calendar 是从0-11  所以获取的当前月比实际的月份小1
	        	resultMap.put(MONTH,nowDay.get(Calendar.MONTH));
	        	resultMap.put(WEEKNUM,getThisMonthWeekCount(nowDay.get(Calendar.YEAR),nowDay.get(Calendar.MONTH)-1));
	            
	        } else {
	            if (lastDayWeekDay < 4) {
	                //这个月最后几天属于下个月第一周
	                if (betweenLastDay <= lastDayWeekDay) {
	                    //下个月第一周
	                	resultMap.put(MONTH,nowDay.get(Calendar.MONTH)+2);
	    	        	resultMap.put(WEEKNUM,1);
	                } else {
	                    //当前月第几周-1
	                	resultMap.put(MONTH,nowDay.get(Calendar.MONTH)+1);
	    	        	resultMap.put(WEEKNUM,nowDay.get(Calendar.WEEK_OF_MONTH)-1);
	                }
	            } else {
	                //当前月第几周-1
	            	resultMap.put(MONTH,nowDay.get(Calendar.MONTH)+1);
    	        	resultMap.put(WEEKNUM,nowDay.get(Calendar.WEEK_OF_MONTH)-1);
	            }
	        }
	    } else {
	        //前几天属于这个月的第一周
	        if (betweenFirstDay <= 7- firstDayWeekDay) {
	            //当前月第一周
	        	resultMap.put(MONTH,nowDay.get(Calendar.MONTH)+1);
	        	resultMap.put(WEEKNUM,1);
	        } else {
	            if (lastDayWeekDay< 4) {
	                if (betweenFirstDay <= lastDayWeekDay) {
	                    //下个月第一周
	                	resultMap.put(MONTH,nowDay.get(Calendar.MONTH)+2);
	    	        	resultMap.put(WEEKNUM,1);
	                } else {
	                    //当前月第几周
	                	resultMap.put(MONTH,nowDay.get(Calendar.MONTH)+1);
	    	        	resultMap.put(WEEKNUM,nowDay.get(Calendar.WEEK_OF_MONTH));
	                }
	            } else {
	                //当前月第几周
	            	resultMap.put(MONTH,nowDay.get(Calendar.MONTH)+1);
    	        	resultMap.put(WEEKNUM,nowDay.get(Calendar.WEEK_OF_MONTH));
	            }
	        }
	    }
		
		
		/*System.out.println("betweenFirstDay:"+betweenFirstDay);
		System.out.println("betweenLastDay:"+betweenLastDay);*/
		
		
		return resultMap;
	}
	
	
	/**
	 * 获取两个calendar之间的天数 
	 * @param c1 比较小的一天
	 * @param c2 比较大的一天
	 * @return 返回两天之间相差的天数
	 */
	public static int getTwoCalendarbetweenDays(Calendar c1,Calendar c2)
	{
		 //设置时间为0时   
		c1.set(Calendar.HOUR_OF_DAY, 0);   
		c1.set(Calendar.MINUTE, 0);   
		c1.set(Calendar.SECOND, 0);   
		c2.set(Calendar.HOUR_OF_DAY, 0);   
		c2.set(Calendar.MINUTE, 0);   
		c2.set(Calendar.SECOND, 0);  
		
		 
		return ((int)(c2.getTime().getTime()/1000)-(int)(c1.getTime().getTime()/1000))/3600/24; 
	}
	
	
	
	/**
	 * 获取月份的周数
	 * @param year 属于的年份
	 * @param month 属于的月份
	 * @return 返回这个月的周数
	 */
	public static int getThisMonthWeekCount(int year,int month)
	{
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month);
		//最多多少周
		int maxWeekCount = c.getActualMaximum(Calendar.WEEK_OF_MONTH);
		//System.out.println("max周数：" + c.getActualMaximum(Calendar.WEEK_OF_MONTH));
		//天数
		int dayCount =  c.getActualMaximum(Calendar.DAY_OF_MONTH);
		//System.out.println("天数：" + c.getActualMaximum(Calendar.DAY_OF_MONTH));
        
		c.set(Calendar.DAY_OF_MONTH, 1);
        //获取每月的第一天 如果第一天在这周的第四天之后,则少一周
		if (c.get(Calendar.DAY_OF_WEEK)>4) {
           maxWeekCount--;
		}

        
        c.set(Calendar.DAY_OF_MONTH, dayCount);
        
        //获取每月的最后一天  如果第一天在这周的第四天之前,则少一周
        if (c.get(Calendar.DAY_OF_WEEK)<4) {
            maxWeekCount--;

 		}
        return maxWeekCount;
        
	}
	
	
	

	
	
	
	public static SimpleDateFormat sdfFull1 = new SimpleDateFormat("yyyy-MM-dd");
	public static SimpleDateFormat sdfFull2= new SimpleDateFormat("yyyy.MM.dd");
	public static SimpleDateFormat sdfYearMonth1 = new SimpleDateFormat("yyyy-MM");
	public static SimpleDateFormat sdfYearMonth2 = new SimpleDateFormat("yyyy.MM");
	
	public static SimpleDateFormat sdfMonthDay1 = new SimpleDateFormat("MM.dd");


	
	/**
	 * 判断某一周是否属于某一个月
	 * @param weekStart  一周的开始的calendar  星期一
	 * @param weekEnd    一周的结束的calendar  星期日
	 * @param belongMonth  所属于的月份
	 * @return
	 */
	public static boolean validateWeekBelongMonth(Calendar weekStart,Calendar weekEnd,int belongMonth)
	{
		boolean result = false;
		if (weekStart.get(Calendar.MONTH )==belongMonth || weekEnd.get(Calendar.MONTH )==belongMonth) {
			result = true;
		}
		return result;
	}
	
	
	public static Calendar getMonthFirstDayCalendar(int month,int year) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month);
		c.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天

		return c;
	}

	public static Calendar getMonthLastDayCalendar(int year,int month) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));

		return c;
	}
	
}
