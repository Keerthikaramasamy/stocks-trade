package com.stock.dto;

import lombok.Data;

@Data
public class ErrorResponse {

	private int status;
	private String message;

	public ErrorResponse() {
		super();
	}

	public ErrorResponse(int status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	@Override
	public String toString() {
		return "ErrorResponse [status=" + status + ", message=" + message + "]";
	}

}
