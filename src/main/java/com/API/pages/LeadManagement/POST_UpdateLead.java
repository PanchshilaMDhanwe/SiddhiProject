package com.API.pages.LeadManagement;

import java.util.HashMap;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.utils.config.Configuration;

public class POST_UpdateLead extends AbstractApiMethodV2 {

	public POST_UpdateLead(HashMap<String, String> datamap) {
		super("api/Lead_Update/UpdateLead_RQ.json", null, "api/Lead_Update/UpdateLead.properties");

		replaceUrlPlaceholder("base_url", Configuration.getRequired("api_url"));
		replaceUrlPlaceholder("leadID", datamap.get("leadID"));
		setHeaders("Authorization=Bearer " + Configuration.getRequired("authToken"));
	}

	public void setleadUpdate(HashMap<String, String> Datamap) {

		addProperty("EmailID", Datamap.get("EmailID"));
		addProperty("MobileNo", Datamap.get("MobileNo"));
		addProperty("City", Datamap.get("City"));
		addProperty("AddressType", Datamap.get("AddressType"));
		addProperty("State", Datamap.get("State"));
		addProperty("PinCode", Datamap.get("PinCode"));
		addProperty("CityCode", Datamap.get("CityCode"));
		addProperty("Landmark", Datamap.get("Landmark"));
		addProperty("Locality1", Datamap.get("Locality1"));
		addProperty("Locality2", Datamap.get("Locality2"));
		addProperty("Locality3", Datamap.get("Locality3"));
		addProperty("StateCode", Datamap.get("StateCode"));
		addProperty("CountryCode", Datamap.get("CountryCode"));
		addProperty("Nationality", Datamap.get("Nationality"));

	}

}
