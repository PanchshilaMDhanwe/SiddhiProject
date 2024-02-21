package com.API.pages.LeadManagement;

import java.util.HashMap;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.utils.config.Configuration;

public class POST_SecondaryEmail extends AbstractApiMethodV2 {

	public POST_SecondaryEmail(HashMap<String, String> datamap) {
		super("api/SecondaryEmail/SecondaryEmail_RQ.json", null, "api/SecondaryEmail/SecondaryEmail.properties");

		replaceUrlPlaceholder("base_url", Configuration.getRequired("api_url"));
		replaceUrlPlaceholder("leadID", datamap.get("leadID"));
		setHeaders("Authorization=Bearer " + Configuration.getRequired("authToken"));
	}

	public void setRequestPlaceHolder(HashMap<String, String> Datamap) {

		addProperty("EmailID", Datamap.get("EmailID"));
	}

}
