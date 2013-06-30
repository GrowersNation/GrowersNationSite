package org.growersnation.site.domain.thirdparty.metoffice.gdd;

import java.util.List;

public class GDDDataPoints {
   	private String dayOfYear;
   	private String value;

 	public String getDayOfYear(){
		return this.dayOfYear;
	}
	public void setDayOfYear(String dayOfYear){
		this.dayOfYear = dayOfYear;
	}
 	public String getValue(){
		return this.value;
	}
	public void setValue(String value){
		this.value = value;
	}
}
