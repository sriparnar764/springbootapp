package com.anz.addressbook;

/**
 * The Interface AddressBookService.
 */
public interface AddressBookService {

	/**
	 * Gets the contacts.
	 *
	 * @param addressBooksRequest
	 *            the address books request
	 * @return the contacts
	 */
	public ResponseObject getContacts(String addressBooksRequest);

	/**
	 * Adds the address books.
	 *
	 * @param addressBooksRequest
	 *            the address books request
	 * @return the response object
	 */
	public ResponseObject addAddressBooks(AddressBooksRequest addressBooksRequest);

	/**
	 * Gets the unique contacts.
	 *
	 * @param addressBooksRequest
	 *            the address books request
	 * @return the unique contacts
	 */
	public ResponseObject getUniqueContacts(UniqueAddressBookWrapperRequest addressBooksRequest);

}
