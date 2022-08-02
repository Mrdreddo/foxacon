package com.foxacons.base.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foxacons.base.entity.ContactDetails;
import com.foxacons.base.repos.ContactDetailsDao;

@Service
public class ContactDetailsSeviceImpl implements ContactDetailsService {
	@Autowired
	private ContactDetailsDao dao;

	@Override
	public boolean addContactDetails(ContactDetails details) {
		boolean isAdded = false;
		if (details != null) {
			ContactDetails details2 = dao.save(details);
			if (details2 != null) {
				isAdded = true;
			}
		}
		return isAdded;
	}

	@Override
	public List<ContactDetails> getAllContactDetails() {
		List<ContactDetails> contactDetails = (List<ContactDetails>) dao.findAll();
		return contactDetails;
	}

	@Override
	public boolean deleteContactDetail(int id) {
		boolean isDeleted = false;
		ContactDetails contactDetails = dao.findById(id);
		if (contactDetails != null) {
			dao.delete(contactDetails);
			isDeleted = true;
		}
		return isDeleted;
	}

}
