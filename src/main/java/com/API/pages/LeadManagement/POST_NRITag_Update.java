package com.API.pages.LeadManagement;

import java.util.HashMap;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.utils.config.Configuration;

public class POST_NRITag_Update extends AbstractApiMethodV2 {

	public POST_NRITag_Update(HashMap<String, String> datamap) {
		super("api/NRITagUpdate/NRITagUpdate_RQ.json", null, "api/NRITagUpdate/NRITagUpdate.properties");

		replaceUrlPlaceholder("base_url", Configuration.getRequired("api_url"));
		replaceUrlPlaceholder("leadID", datamap.get("leadID"));
		setHeaders("Authorization=Bearer " + Configuration.getRequired("authToken"));
	}

}
