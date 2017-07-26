package br.com.autoservice.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {
	
	public static Date getDate(){
		Date dateAtual = new Date();
		return dateAtual;
	}
	
	public static String padraoDate(Date date){
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return dateFormat.format(date);
	}

	public static Date pegarData(){
		GregorianCalendar calendar = new GregorianCalendar(); 
		return calendar.getTime();
	}
}
