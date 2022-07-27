package com.crm.comcast.genericUtilities;

import java.util.Random;

import sun.util.calendar.LocalGregorianCalendar.Date;

/**
 * 
 * @author -hp-
 *
 */
public class JavaUtility 
{
	/**
	 * this method is to get the randomNum
	 * @return
	 */
	public int getRandomNum()
	{
		Random random = new Random();
		int randomnum = random.nextInt(1000);
		return randomnum;

	}
	/**
	 * this method us to get the date
	 * @return
	 */
	public String getSystemDate()
	{
		java.util.Date date = new java.util.Date();
		String datetime = date.toString();
		return datetime;
	}
	/**
	 * this method is used to get the month date time and day
	 * @return
	 */
	public String getSystemDateFortmat()
	{
		java.util.Date date = new java.util.Date();
		String datetime = date.toString();
		String[] datearr = datetime.split("");
		int month = date.getMonth();
		String date1=datearr[2];
		String year=datearr[5];
		int day=date.getDay();
		String time=datearr[3];
		String Finalformat=month+""+date1+""+time+""+day;
		return Finalformat;	

	}

}
