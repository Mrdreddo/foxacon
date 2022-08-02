package com.foxacons.base.service;

import java.util.List;

import com.foxacons.base.entity.ContactDetails;

public interface ContactDetailsService {
	
	public boolean addContactDetails(ContactDetails details);
	public List<ContactDetails> getAllContactDetails();
	public boolean deleteContactDetail(int id);
	
}
