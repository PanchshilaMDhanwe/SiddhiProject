package com.API.pages.LeadManagement;

import java.util.HashMap;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.utils.config.Configuration;

public class POST_LeadStatus_NotContactable extends AbstractApiMethodV2 {

	public POST_LeadStatus_NotContactable(HashMap<String, String> datamap) {
		super("api/LeadStatus_NotContactable/LeadStatus_NotContactable_RQ.json", null, "api/LeadStatus_NotContactable/LeadStatus_NotContactable.properties");

		replaceUrlPlaceholder("base_url", Configuration.getRequired("api_url"));
		replaceUrlPlaceholder("leadID", datamap.get("leadID"));
		setHeaders("Authorization=Bearer " + Configuration.getRequired("authToken"));
	}

}
