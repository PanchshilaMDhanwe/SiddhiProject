package com.API.pages.LeadManagement;

import java.util.HashMap;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.utils.config.Configuration;

public class POST_LeadFilter extends AbstractApiMethodV2{

	public POST_LeadFilter() {
		super("api/Lead_filter/Lead_filter_RQ.json",null, "api/Lead_filter/Lead_Filter.properties");
		
			replaceUrlPlaceholder("base_url", Configuration.getRequired("api_url"));
			setHeaders("Authorization=Bearer "+Configuration.getRequired("authToken"));
	}
	
	public void setleadfilter(HashMap<String,String> Datamap) {
		
		addProperty("ToDate", Datamap.get("ToDate"));
		addProperty("FromDate", Datamap.get("FromDate"));
		
	}
}
