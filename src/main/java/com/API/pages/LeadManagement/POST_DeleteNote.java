package com.API.pages.LeadManagement;

import java.util.HashMap;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.utils.config.Configuration;

public class POST_DeleteNote extends AbstractApiMethodV2 {

	public POST_DeleteNote(HashMap<String, String> datamap) {
		super("api/DeleteNote/DeleteNote_RQ.json", null, "api/DeleteNote/DeleteNote.properties");

		replaceUrlPlaceholder("base_url", Configuration.getRequired("api_url"));
		replaceUrlPlaceholder("leadID", datamap.get("leadID"));
		setHeaders("Authorization=Bearer " + Configuration.getRequired("authToken"));
	}
	
}
