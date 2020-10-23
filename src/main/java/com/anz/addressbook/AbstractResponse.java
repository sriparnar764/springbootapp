package com.anz.addressbook;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * The type Abstract response.
 */
public abstract class AbstractResponse {

	/**
	 * Response error response object.
	 *
	 * @param message
	 *            the message
	 * @param codeEnum
	 *            the code enum
	 * @return the response object
	 */
	public static ResponseEntity<ResponseObject> responseEntityError(String message, ResponseCodeEnum codeEnum) {
		ResponseObject responseObject = new ResponseObject(null, message, message, message, null);
		responseObject.setTimeStamp(System.currentTimeMillis());
		responseObject.setResponseCode(codeEnum.getValue());
		responseObject.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString());
		responseObject.setMessage(message);
		return new ResponseEntity<>(responseObject, HttpStatus.OK);
	}

}
