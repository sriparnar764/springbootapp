package com.anz.addressbook;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The Class ContactDetails.
 */
@Getter
@Setter
@NoArgsConstructor
public class ContactDetailsRequest implements Comparable<ContactDetailsRequest>, Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The contact name. */
	@JsonProperty(value ="contactName",required = true)
	private String contactName;

	/** The ph number. */
	@JsonProperty(value ="phNumber",required = true)
	private String phNumber;

	/*
	 * 
	 * 
	 * compareTo to sort objects by name
	 */
	public int compareTo(ContactDetailsRequest o) {

		return this.contactName.compareTo(o.contactName);
	}

	/*
	 * 
	 * 
	 * Overriding Equals Method
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ContactDetailsRequest) {
			ContactDetailsRequest contact = (ContactDetailsRequest) obj;
			return (contactName.equals(contact.getContactName()) && phNumber.equals(contact.getPhNumber()));
		}

		return false;
	}

	/*
	 * 
	 * 
	 * Overriding hashCode() Method
	 */
	@Override
	public int hashCode() {
		return (contactName.length() + phNumber.length());
	}

}
