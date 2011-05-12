package br.com.braziljs.loiane.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import br.com.braziljs.loiane.model.Contact;

/**
 * Contact DAO class.
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
@Repository
public class ContactDAO {
	
	private HibernateTemplate hibernateTemplate;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	
	/**
	 * Get List of contacts from database
	 * @return list of all contacts
	 */
	@SuppressWarnings("unchecked")
	public List<Contact> getContacts(int start, int limit) {
		
		DetachedCriteria criteria = DetachedCriteria.forClass(Contact.class);

		return hibernateTemplate.findByCriteria(criteria, start, limit);
	}

	/**
	 * Delete a contact with the id passed as parameter
	 * @param id
	 */
	public void deleteContact(int id){
		Object record = hibernateTemplate.load(Contact.class, id);
		hibernateTemplate.delete(record);
	}
	
	/**
	 * Create a new Contact on the database or
	 * Update contact
	 * @param contact
	 * @return contact added or updated in DB
	 */
	public Contact saveContact(Contact contact){
		hibernateTemplate.saveOrUpdate(contact);
		return contact;
	}
	
	/**
	 * Get total of Contacts from database
	 * @return
	 */
	public int getTotalContacts(){
		return DataAccessUtils.intResult(hibernateTemplate.find("SELECT COUNT(*) FROM Contact"));
	}

}
