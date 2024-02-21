package com.API.pages.LeadManagement;

import java.util.HashMap;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.utils.config.Configuration;
public class POST_CreateLead extends AbstractApiMethodV2{

	public POST_CreateLead() {
		super("api/Lead_Creation/createLeadBody.json",null, "api/Lead_Creation/createLeadBody.properties");
		
			replaceUrlPlaceholder("base_url", Configuration.getRequired("api_url"));
			//setHeaders("x-aia-apikey=18adc0b6bbdf4801aba68cf4b698737b");
			setHeaders("Authorization=Bearer "+Configuration.getRequired("authToken"));
//        setHeaders("auth-token="+Configuration.getEnvArg("authToken"));
//        String credentials = Configuration.getEnvArg("username")+":"+Configuration.getEnvArg("password");
//        String base64Credentials = new String(Base64.getEncoder().encode(credentials.getBytes()));
//        setHeaders("Authorization=Basic "+base64Credentials);
	}
	public void setRequestPlaceHolder(HashMap<String,String> Datamap) {
		

		addProperty("firstName", Datamap.get("firstName"));
		addProperty("lastName", Datamap.get("lastName"));
		addProperty("middleName", Datamap.get("middleName"));
		addProperty("Gender", Datamap.get("Gender"));
	}

}
