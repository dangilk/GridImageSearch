package com.dan.gridimagesearch;

import android.net.Uri;

public class Settings {
	
	public String site=null;
	public String size="any";
	public String color="any";
	public String type="any";
	
	public void setSite(String site) {
		this.site = site;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public String urlFrag(){
		String ret="";
		if(size != "any" && size.length() > 0){
			ret += "&imgsz="+Uri.encode(size);
		}
		if(color != "any" && color.length() > 0){
			ret += "&imgcolor="+Uri.encode(color);
		}
		if(type != "any" && type.length() > 0){
			ret += "&imgtype="+Uri.encode(type);
		}
		if(site != null && site.length() > 0){
			ret += "&as_sitesearch="+Uri.encode(site);
		}
		return ret;
	}
	

}
