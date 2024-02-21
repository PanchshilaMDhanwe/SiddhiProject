package com.API.pages.LeadManagement;

import java.util.HashMap;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.utils.config.Configuration;

public class POST_SilverTag_Update extends AbstractApiMethodV2 {

	public POST_SilverTag_Update(HashMap<String, String> datamap) {
		super("api/SilverTagUpdate/SilverTagUpdate_RQ.json", null, "api/SilverTagUpdate/SilverTagUpdate.properties");

		replaceUrlPlaceholder("base_url", Configuration.getRequired("api_url"));
		replaceUrlPlaceholder("leadID", datamap.get("leadID"));
		setHeaders("Authorization=Bearer " + Configuration.getRequired("authToken"));
	}

}
