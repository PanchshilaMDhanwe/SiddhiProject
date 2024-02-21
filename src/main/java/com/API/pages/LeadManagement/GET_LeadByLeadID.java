package com.API.pages.LeadManagement;

import java.util.HashMap;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.utils.config.Configuration;


public class GET_LeadByLeadID extends AbstractApiMethodV2{

	public GET_LeadByLeadID(HashMap<String,String> Datamap) {
		
		super(null,null, "api/Get_ByLeadID/Get_ByLeadID.properties");
		
		replaceUrlPlaceholder("base_url", Configuration.getRequired("api_url"));
		replaceUrlPlaceholder("leadID", Datamap.get("leadID"));
		replaceUrlPlaceholder("AgentCode", Datamap.get("AgentCode"));
		setHeaders("Authorization=Bearer "+Configuration.getRequired("authToken"));
	}
}
