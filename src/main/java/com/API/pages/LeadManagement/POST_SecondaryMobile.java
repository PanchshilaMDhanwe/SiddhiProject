package com.API.pages.LeadManagement;

import java.util.HashMap;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.utils.config.Configuration;

public class POST_SecondaryMobile extends AbstractApiMethodV2 {

	public POST_SecondaryMobile(HashMap<String, String> datamap) {
		super("api/SecondaryMobile/SecondaryMobile_RQ.json", null, "api/SecondaryMobile/SecondaryMobile.properties");

		replaceUrlPlaceholder("base_url", Configuration.getRequired("api_url"));
		replaceUrlPlaceholder("leadID", datamap.get("leadID"));
		setHeaders("Authorization=Bearer " + Configuration.getRequired("authToken"));
	}

	public void setRequestPlaceHolder(HashMap<String, String> Datamap) {

		addProperty("MobileNo", Datamap.get("MobileNo"));
	}

}
