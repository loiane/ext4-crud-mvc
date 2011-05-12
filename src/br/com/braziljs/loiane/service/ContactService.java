package br.com.braziljs.loiane.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.braziljs.loiane.dao.ContactDAO;
import br.com.braziljs.loiane.model.Contact;

/**
 * Contact Service
 * 
 * Sample project presented at BrazilJS
 * Brazilian JavaScript Conference
 * Fortaleza - Ceará - 13-14 May 2011
 * http://braziljs.com.br/2011
 * 
 * @author Loiane Groner
 * http://loianegroner.com (English)
 * http://loiane.com (Portuguese)
 */
@Service
public class ContactService {
	
	private ContactDAO contactDAO;

	/**
	 * Get all contacts
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<Contact> getContactList(int start, int limit){
		
		return contactDAO.getContacts(start, limit);
	}
	
	/**
	 * Create new Contact/Contacts
	 * @param data - json data from request
	 * @return created contacts
	 */
	@Transactional
	public List<Contact> create(Contact contact){
		
        List<Contact> newContacts = new ArrayList<Contact>();
		
		newContacts.add(contactDAO.saveContact(contact));
		
		return newContacts;
	}
	
	
	/**
	 * Update contact/contacts
	 * @param data - json data from request
	 * @return updated contacts
	 */
	@Transactional
	public List<Contact> update(Contact contact){
		
		List<Contact> returnContacts = new ArrayList<Contact>();
		
		returnContacts.add(contactDAO.saveContact(contact));
		
		return returnContacts;
	}
	
	/**
	 * Delete contact/contacts
	 * @param contact - json data from request
	 */
	@Transactional
	public void delete(Contact contact){
		
		contactDAO.deleteContact(contact.getId());
	}
	
	/**
	 * Get total of Contacts from database.
	 * Need to set this value on ExtJS Store
	 * @return
	 */
	public int getTotalContacts(){

		return contactDAO.getTotalContacts();
	}

	/**
	 * Spring use - DI
	 * @param contactDAO
	 */
	@Autowired
	public void setContactDAO(ContactDAO contactDAO) {
		this.contactDAO = contactDAO;
	}
	
}
