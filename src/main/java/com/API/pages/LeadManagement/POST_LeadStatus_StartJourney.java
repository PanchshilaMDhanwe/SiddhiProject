package com.API.pages.LeadManagement;

import java.util.HashMap;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.utils.config.Configuration;

public class POST_LeadStatus_StartJourney extends AbstractApiMethodV2 {

	public POST_LeadStatus_StartJourney(HashMap<String, String> datamap) {
		super("api/LeadStatus_StartJourney/LeadStatus_StartJourney_RQ.json", null, "api/LeadStatus_StartJourney/LeadStatus_StartJourney.properties");

		replaceUrlPlaceholder("base_url", Configuration.getRequired("api_url"));
		replaceUrlPlaceholder("leadID", datamap.get("leadID"));
		setHeaders("Authorization=Bearer " + Configuration.getRequired("authToken"));
	}

}
