package com.API.pages.LeadManagement;

import java.util.HashMap;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.utils.config.Configuration;

public class POST_EditNote extends AbstractApiMethodV2 {

	public POST_EditNote(HashMap<String, String> datamap) {
		super("api/EditNote/EditNote_RQ.json", null, "api/EditNote/EditNote.properties");

		replaceUrlPlaceholder("base_url", Configuration.getRequired("api_url"));
		replaceUrlPlaceholder("leadID", datamap.get("leadID"));
		setHeaders("Authorization=Bearer " + Configuration.getRequired("authToken"));
	}
	public void seteditNote(HashMap<String, String> Datamap) {

		addProperty("Date", Datamap.get("Date"));
	}

}
