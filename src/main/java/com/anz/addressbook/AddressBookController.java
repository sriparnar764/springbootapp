package com.anz.addressbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Controller
@RequestMapping("api/")
@Api(tags = "AddressBook REST Resource")
public class AddressBookController {

	@Autowired
	private AddressBookService addressBookService;

	/**
	 * Method to add contacts of users
	 *
	 * @param addressBooksRequest the address books request
	 * @return the response entity
	 */
	@ApiOperation(value = "Add Address Books")
	@ApiResponses({ @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 502, message = "BAD_GATEWAY"),
			@ApiResponse(code = 500, message = "INTERNAL_SERVER_ERROR") })
	@PostMapping(value = "/addressBooks", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseObject> addAddressBooks(@RequestBody AddressBooksRequest addressBooksRequest) {
		if (null != addressBooksRequest && !addressBooksRequest.getContacts().isEmpty()) {
			ResponseObject responseObject = addressBookService.addAddressBooks(addressBooksRequest);
			return new ResponseEntity<>(responseObject, HttpStatus.OK);
		}
		return AbstractResponse.responseEntityError("Please enter all details", ResponseCodeEnum.ADDRESSBOOK_EMPTY);

	}

	/**
	 * Method to fetch contacts sorted by their name for a given user 
	 *
	 * @param name the name
	 * @return the address books
	 */
	@ApiOperation(value = "Fetch Contacts Sorted by Contact Name")
	@ApiResponses({ @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 502, message = "BAD_GATEWAY"),
			@ApiResponse(code = 500, message = "INTERNAL_SERVER_ERROR") })
	@GetMapping(value = "/addressBooks", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseObject> getAddressBooks(@RequestParam String name) {
		if (null !=name && !name.isEmpty()) {
			ResponseObject responseObject = addressBookService.getContacts(name);
			return new ResponseEntity<>(responseObject, HttpStatus.OK);
		}
		return AbstractResponse.responseEntityError("Please enter user name", ResponseCodeEnum.NAME_EMPTY);
	}
	
	/**
	 * Method to fetch unique contacts from two address books/usernames
	 *
	 * @param users the users
	 * @return the unique contact
	 */
	@ApiOperation(value = "Fetch Unique Contacts from Multiple Address Books of a User")
	@ApiResponses({ @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 502, message = "BAD_GATEWAY"),
			@ApiResponse(code = 500, message = "INTERNAL_SERVER_ERROR") })
	@PostMapping(value = "/uniqueAddressBooks", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseObject> getUniqueContact(@RequestBody UniqueAddressBookWrapperRequest users) {	
		if (null !=users && null!=users.getAddrBooks() &&  users.getAddrBooks().size()==2) {	
			ResponseObject responseObject = addressBookService.getUniqueContacts(users);
			return new ResponseEntity<>(responseObject, HttpStatus.OK);
		}
		return AbstractResponse.responseEntityError("Please enter two user names", ResponseCodeEnum.NAME_EMPTY);
	}

}
