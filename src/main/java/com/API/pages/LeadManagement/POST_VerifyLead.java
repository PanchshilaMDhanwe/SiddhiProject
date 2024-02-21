package com.API.pages.LeadManagement;

import java.util.HashMap;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.utils.config.Configuration;

public class POST_VerifyLead extends AbstractApiMethodV2 {

	public POST_VerifyLead(HashMap<String, String> datamap) {
		super("api/VerifyLead/VerifyLead_RQ.json", null, "api/VerifyLead/VerifyLead.properties");

		replaceUrlPlaceholder("base_url", Configuration.getRequired("api_url"));
		replaceUrlPlaceholder("leadID", datamap.get("leadID"));
		setHeaders("Authorization=Bearer " + Configuration.getRequired("authToken"));
	}

}
