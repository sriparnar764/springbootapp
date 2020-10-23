package com.anz.addressbook;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * The type Response object.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"timestamp", "status", "responseCode", "attrs", "message"})
@Data
@Builder
public class ResponseObject implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The time stamp. */
	@JsonProperty("timestamp")
	@ApiModelProperty(position = 1, value = "timestamp")
	private Long timeStamp;
	
	/** The status. */
	@ApiModelProperty(position = 2, value = "status")
	@JsonProperty("status")
	private String status;
	
	/** The response code. */
	@JsonProperty("responseCode")
	@ApiModelProperty(position = 3, required = true, value = "responseCode")
	private String responseCode;
	
	/** The message. */
	@JsonProperty("message")
	@ApiModelProperty(position = 5, value = "message")
	private String message;
	
	
	/** The contacts. */
	@JsonProperty("contacts")
	@ApiModelProperty(position = 5, value = "message")
	private List<ContactDetailsRequest> contacts;
	
	
	@Override
	public String toString() {
		return "ResponseObject [timeStamp=" + timeStamp + ", status=" + status + ", responseCode=" + responseCode
				+ ", message=" + message + ",contacts=" + contacts + "]";
	}
	
	

}
