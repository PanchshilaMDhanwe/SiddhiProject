package com.API.pages.LeadManagement;

import java.util.HashMap;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.utils.config.Configuration;

public class POST_SubSource_Update extends AbstractApiMethodV2 {

	public POST_SubSource_Update(HashMap<String, String> datamap) {
		super("api/SubSourceUpdate/SubSourceUpdate_RQ.json", null, "api/SubSourceUpdate/SubSourceUpdate.properties");

		replaceUrlPlaceholder("base_url", Configuration.getRequired("api_url"));
		replaceUrlPlaceholder("leadID", datamap.get("leadID"));
		setHeaders("Authorization=Bearer " + Configuration.getRequired("authToken"));
	}

}
