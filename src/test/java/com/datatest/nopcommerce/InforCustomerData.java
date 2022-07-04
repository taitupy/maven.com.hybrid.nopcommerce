package com.datatest.nopcommerce;

import java.io.File;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import commons.GlobalConstants;

public class InforCustomerData {
	
	
	public static InforCustomerData getInforCustomer() {
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			return mapper.readValue(new File(GlobalConstants.PROJECT_PATH + "\\testdata\\com\\nopcommerce\\datatest\\InforCustomer.json"), InforCustomerData.class);
		}catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getInvalidEmail() {
		return invalidEmail;
	}

	public String getExistingEmail() {
		return existingEmail;
	}

	public String getNotFoundEmail() {
		return notFoundEmail;
	}

	public String getPassword() {
		return password;
	}

	@JsonProperty("firstName")
	private String firstName;
	
	@JsonProperty("lastName")
	private String lastName;
	
	@JsonProperty("invalidEmail")
	private String invalidEmail;
	
	@JsonProperty("existingEmail")
	private String existingEmail;
	
	@JsonProperty("notFoundEmail")
	private String notFoundEmail;
	
	@JsonProperty("password")
	private String password;
	
	@JsonProperty("ContactDetail")
	ContactDetail contactDetail;
	
	public static class ContactDetail{
		@JsonProperty("editFirstName")
		private String editFirstName;
		
		@JsonProperty("editLastName")
		private String editLastName;
	}
	
	public String getEditFirstName() {
		return contactDetail.editFirstName;
	}
	
	public String getEditLastName() {
		return contactDetail.editLastName;
	}
}
