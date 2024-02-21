package com.API.pages.LeadManagement;

import java.util.HashMap;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.utils.config.Configuration;

public class POST_LeadStatusKill  extends AbstractApiMethodV2{

	public POST_LeadStatusKill(HashMap<String, String> datamap) {
		super("api/Lead_StatusKill/Lead_StatusKill.json",null, "api/Lead_StatusKill/Lead_StatusKill.properties");
		
			replaceUrlPlaceholder("base_url", Configuration.getRequired("api_url"));
			replaceUrlPlaceholder("leadID", datamap.get("leadID"));
			setHeaders("Authorization=Bearer "+Configuration.getRequired("authToken"));
	}
	
}
