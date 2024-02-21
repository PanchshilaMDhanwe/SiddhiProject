package com.API.pages.LeadManagement;

import java.util.HashMap;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.utils.config.Configuration;

public class POST_Source_Update extends AbstractApiMethodV2 {

	public POST_Source_Update(HashMap<String, String> datamap) {
		super("api/SourceUpdate/SourceUpdate_RQ.json", null, "api/SourceUpdate/SourceUpdate.properties");

		replaceUrlPlaceholder("base_url", Configuration.getRequired("api_url"));
		replaceUrlPlaceholder("leadID", datamap.get("leadID"));
		setHeaders("Authorization=Bearer " + Configuration.getRequired("authToken"));
	}

}
