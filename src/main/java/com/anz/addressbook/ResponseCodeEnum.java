package com.anz.addressbook;

import java.util.Arrays;

public enum ResponseCodeEnum {

	ADDRESSBOOK_EMPTY("ERR001"),
	
	ADDRESSBOOK_ERROR("ERR002"),
	
	NAME_EMPTY("ERR003"),
	
	ADDRESSBOOK_NOTFOUND("ERR004"),
	
	ADDRESSBOOK_FETCHED("SUCC002"),

	ADDRESSBOOK_ADDED("SUCC001"),
	ADDRESSBOOK_UNIQUECONTACTS_FETCHED("SUCC003");

	private String value;

	/**
	 * Instantiates a new response code enum.
	 *
	 * @param value
	 *            the value
	 */
	ResponseCodeEnum(String value) {
		this.value = value;
	}

	/**
	 * From value response code enum.
	 *
	 * @param value
	 *            the value
	 * @return the response code enum
	 */
	public static ResponseCodeEnum fromValue(String value) {
		for (ResponseCodeEnum category : values()) {
			if (category.value.equalsIgnoreCase(value)) {
				return category;
			}
		}
		throw new IllegalArgumentException(
				"Unknown enum type " + value + ", Allowed values are " + Arrays.toString(values()));
	}

	/**
	 * Gets value.
	 *
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
}
