package com.anz.addressbook;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;

@Service
public class AddressBookServiceImpl implements AddressBookService {

	/* Method to fetch sorted contacts
	 */
	public ResponseObject getContacts(String name) {
		Path path = Paths.get(name + ".txt");
		LocalDateTime now = LocalDateTime.now();
		long milliSeconds = Timestamp.valueOf(now).getTime();
		Gson gson = new Gson();
		List<ContactDetailsRequest> contacts = new ArrayList<>();
		try (BufferedReader reader = Files.newBufferedReader(path)) {
			String contentLine = reader.toString();
			while (contentLine != null) {
				contentLine = reader.readLine();
				AddressBooksRequest addressBooksRequest = gson.fromJson(contentLine, AddressBooksRequest.class);
				if (null != addressBooksRequest) {
					contacts.addAll(addressBooksRequest.getContacts());
				}

			}
			Collections.sort(contacts);
			ResponseObject responseObject = ResponseObject.builder().timeStamp(milliSeconds)
					.message("Successfully Fetched").responseCode(ResponseCodeEnum.ADDRESSBOOK_FETCHED.getValue())
					.status("SUCCESS").contacts(contacts).build();
			return responseObject;
		} catch (IOException e) {
			ResponseObject responseObject = ResponseObject.builder().timeStamp(milliSeconds).message("Failed Operation")
					.responseCode(ResponseCodeEnum.ADDRESSBOOK_ERROR.getValue()).status("FAILURE").build();
			return responseObject;
		}

	}

	/* Method to add contacts
	 */
	public ResponseObject addAddressBooks(AddressBooksRequest addressBooksRequest) {
		String fileName = addressBooksRequest.getUserName() + ".txt";
		LocalDateTime now = LocalDateTime.now();
		long milliSeconds = Timestamp.valueOf(now).getTime();
		Gson gson = new Gson();
		try (BufferedWriter out = new BufferedWriter(new FileWriter(fileName, true));) {
			String json = gson.toJson(addressBooksRequest);
			out.newLine();
			out.write(json.toString());
			ResponseObject responseObject = ResponseObject.builder().timeStamp(milliSeconds)
					.message("Successfully Added").responseCode(ResponseCodeEnum.ADDRESSBOOK_ADDED.getValue())
					.status("SUCCESS").build();
			return responseObject;
		} catch (IOException e) {
			ResponseObject responseObject = ResponseObject.builder().timeStamp(milliSeconds).message("Failed Operation")
					.responseCode(ResponseCodeEnum.ADDRESSBOOK_ERROR.getValue()).status("FAILURE").build();
			return responseObject;
		}

	}

	/* Method to fetch unique contacts
	 */
	@Override
	public ResponseObject getUniqueContacts(UniqueAddressBookWrapperRequest addressBooksRequest) {
		List<AddressBooksRequest> addrBookList = new ArrayList<>();
		List<ContactDetailsRequest> contacts = new ArrayList<>();
		LocalDateTime now = LocalDateTime.now();
		long milliSeconds = Timestamp.valueOf(now).getTime();
		AddressBooksRequest addr = new AddressBooksRequest();
		Gson gson = new Gson();
		String fileName;
		List<UniqueContactRequest> list = new ArrayList<>();
		list = addressBooksRequest.getAddrBooks();
		boolean status = checkUsers(list);
		if(!status) {
			ResponseObject responseObject = ResponseObject.builder().timeStamp(milliSeconds)
					.message("Please enter valid user names").responseCode(ResponseCodeEnum.ADDRESSBOOK_NOTFOUND.getValue())
					.status("FAILURE").build();
			return responseObject;
		}
		for (UniqueContactRequest books : list) {
			fileName = books.getUserName() + ".txt";
			if (new File(fileName).exists()) {
				Path path = Paths.get(fileName);

				try (BufferedReader reader = Files.newBufferedReader(path)) {
					String contentLine = reader.toString();
					while (contentLine != null) {
						contentLine = reader.readLine();
						addr = gson.fromJson(contentLine, AddressBooksRequest.class);
						if (null != addr) {
							addrBookList.add(addr);
						}
					}
				} catch (IOException e) {
					ResponseObject responseObject = ResponseObject.builder().timeStamp(milliSeconds)
							.message("Failed Operation").responseCode(ResponseCodeEnum.ADDRESSBOOK_NOTFOUND.getValue())
							.status("FAILURE").build();
					return responseObject;
				}
			}
		}

		contacts = getUniqueResult(addrBookList);
		ResponseObject responseObject = ResponseObject.builder().timeStamp(milliSeconds)
				.message("Successfully Fetched Unique Contacts")
				.responseCode(ResponseCodeEnum.ADDRESSBOOK_UNIQUECONTACTS_FETCHED.getValue()).status("SUCCESS")
				.contacts(contacts).build();
		return responseObject;
	}

	/* Method to get unique contacts from two address books
	 */
	public static List<ContactDetailsRequest> getUniqueResult(List<AddressBooksRequest> addressBooks) {

		Set<ContactDetailsRequest> commonContacts = new HashSet<>();
		Set<ContactDetailsRequest> uniqueContacts = new HashSet<>();

		for (AddressBooksRequest addressBook : addressBooks) {
			List<ContactDetailsRequest> contacts = addressBook.getContacts();
			List<ContactDetailsRequest> allContacts = new ArrayList<>();
			allContacts.addAll(uniqueContacts);
			allContacts.addAll(contacts);
			contacts.retainAll(uniqueContacts);
			commonContacts.addAll(contacts);
			allContacts.removeAll(commonContacts);
			uniqueContacts.clear();
			uniqueContacts.addAll(allContacts);

		}
		List<ContactDetailsRequest> mainList = new ArrayList<>();
		mainList.addAll(uniqueContacts);
		return mainList;
	}

	/* Method to check if any invalid user name has been entered
	 */
	public boolean checkUsers(List<UniqueContactRequest> list) {
		String fileName;
		List<String> fileNames = new ArrayList<>();
		for (UniqueContactRequest books : list) {
			fileName = books.getUserName() + ".txt";
			if (new File(fileName).exists()) {

			} else {
				fileNames.add(fileName);
				return false;
			}
		}
		return true;
	}

}
