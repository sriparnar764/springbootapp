package com.anz.addressbook;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UniqueAddressBookWrapperRequest {
	
	/** The address books. */
	@JsonProperty(value = "addrBooks", required = true)
	/** The contacts. */
	private List<UniqueContactRequest> addrBooks = new ArrayList<>();

}
