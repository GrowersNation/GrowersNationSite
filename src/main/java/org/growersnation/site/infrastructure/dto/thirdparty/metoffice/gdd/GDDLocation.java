package org.growersnation.site.infrastructure.dto.thirdparty.metoffice.gdd;

import java.util.List;

public class GDDLocation {
   	private List<GDDDataPoints> dataPoints;
   	private List<GDDLocation> loc;

 	public List<GDDDataPoints> getDataPoints(){
		return this.dataPoints;
	}
	public void setDataPoints(List<GDDDataPoints> dataPoints){
		this.dataPoints = dataPoints;
	}
 	public List<GDDLocation> getLoc(){
		return this.loc;
	}
	public void setLoc(List<GDDLocation> loc){
		this.loc = loc;
	}
}
