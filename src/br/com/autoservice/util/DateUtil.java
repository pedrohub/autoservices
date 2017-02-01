package br.com.autoservice.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	public static Date getDate(){
		Date dateAtual = new Date();
		return dateAtual;
	}
	
	public static String padraoDate(Date date){
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return dateFormat.format(date);
	}

}
