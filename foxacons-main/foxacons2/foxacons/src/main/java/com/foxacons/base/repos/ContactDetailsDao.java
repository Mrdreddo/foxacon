package com.foxacons.base.repos;

import org.springframework.data.repository.CrudRepository;

import com.foxacons.base.entity.ContactDetails;

public interface ContactDetailsDao extends CrudRepository<ContactDetails, Integer> {
	public ContactDetails findById(int id);
}
