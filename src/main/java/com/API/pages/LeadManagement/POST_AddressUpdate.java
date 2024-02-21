package com.API.pages.LeadManagement;

import java.util.HashMap;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.utils.config.Configuration;

public class POST_AddressUpdate extends AbstractApiMethodV2 {

	public POST_AddressUpdate(HashMap<String, String> datamap) {
		super("api/AddressUpdate/AddressUpdate_RQ.json", null, "api/AddressUpdate/AddressUpdate.properties");

		replaceUrlPlaceholder("base_url", Configuration.getRequired("api_url"));
		replaceUrlPlaceholder("leadID", datamap.get("leadID"));
		setHeaders("Authorization=Bearer " + Configuration.getRequired("authToken"));
	}

	public void setleadUpdate(HashMap<String, String> Datamap) {

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
