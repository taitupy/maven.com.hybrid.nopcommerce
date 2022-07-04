package com.data.nopcommerce;

import ultilities.DataUtil;

public class Employee {
	private DataUtil fakeData;
	
	public Employee(){
		fakeData = DataUtil.getData();
	}
	
	public class Role{
		public static final String ADMIN_USERNAME = "Admin";
		public static final String ADMIN_PASSWORD = "admin123";
	}
	
	public class PersonalDetail {
		public final String  firstName = fakeData.getFirstName();
		public final String  lastName = fakeData.getLastName();
		public String existingEmail = fakeData.getEmailAddress();
		public String password = fakeData.getPassword();	
	}
	
	public class ContactDetail{
		public static final String EDIT_FIRSTNAME = "John";
		public static final String EDIT_LASTNAME = "Terry";
	}
	
	public class Job{
		
	}
	
	public class Salary{
		
	}
}
