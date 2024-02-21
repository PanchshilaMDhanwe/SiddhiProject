package com.API.pages.LeadManagement;

import java.util.HashMap;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.utils.config.Configuration;

public class POST_CreateNote extends AbstractApiMethodV2 {

	public POST_CreateNote(HashMap<String, String> datamap) {
		super("api/CreateNote/CreateNote_RQ.json", null, "api/CreateNote/CreateNote.properties");

		replaceUrlPlaceholder("base_url", Configuration.getRequired("api_url"));
		replaceUrlPlaceholder("leadID", datamap.get("leadID"));
		setHeaders("Authorization=Bearer " + Configuration.getRequired("authToken"));
	}
	public void setCreatenote(HashMap<String, String> Datamap) {

		addProperty("Date", Datamap.get("Date"));
	}


}
