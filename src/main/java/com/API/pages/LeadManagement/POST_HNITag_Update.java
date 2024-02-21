package com.API.pages.LeadManagement;

import java.util.HashMap;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.utils.config.Configuration;

public class POST_HNITag_Update extends AbstractApiMethodV2 {

	public POST_HNITag_Update(HashMap<String, String> datamap) {
		super("api/HNITagUpdate/HNITagUpdate_RQ.json", null, "api/HNITagUpdate/HNITagUpdate.properties");

		replaceUrlPlaceholder("base_url", Configuration.getRequired("api_url"));
		replaceUrlPlaceholder("leadID", datamap.get("leadID"));
		setHeaders("Authorization=Bearer " + Configuration.getRequired("authToken"));
	}

}
