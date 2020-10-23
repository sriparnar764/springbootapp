package com.anz.addressbook;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The Class AddressBook.
 */

@Getter
@Setter
@NoArgsConstructor
public class AddressBooksRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	/** The user name. */
	@JsonProperty(value = "userName", required = true)
	private String userName;

	/** The contacts. */
	@JsonProperty(value = "contacts", required = true)
	/** The contacts. */
	private List<ContactDetailsRequest> contacts = new ArrayList<>();

}
