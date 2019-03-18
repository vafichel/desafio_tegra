package br.com.tegra.dev.apibuscavoo.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class SiteUtil {
	
	private static SiteUtil INSTANCE;
	
	private SiteUtil(){
	}
	
	public static synchronized SiteUtil getInstance(){
		if(INSTANCE == null){
			INSTANCE = new SiteUtil();
		}
		return INSTANCE;
	}
	
	
	public LocalDateTime formatStringToLocalDateTime(String dateTime, String pattern){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		return LocalDateTime.parse(dateTime, formatter);
	}
	
	public String concatDataHora (LocalDate date, LocalTime time){
		String dataHoraConcat = date.toString() + " " + time.toString()+ ":00.000";
		
		return dataHoraConcat;
	}
}
