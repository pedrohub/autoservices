package br.com.autoservice.util;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Teste {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Date data = new Date();
		
		Calendar start = Calendar.getInstance(TimeZone.getDefault());
		start.setTime(data);
		
		
		Date data1 = new Date(System.currentTimeMillis()); 
		
		Date data2 = new Date();
		
		Calendar start1 = Calendar.getInstance(TimeZone.getTimeZone("BRT"));
		start1.setTime(data1);
	}

}
