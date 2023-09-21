package com.example.gatewarserver.req;

public record SignUpReq(
		String name,
		String birthday,
		String email,
		String address,
		String username,
		String password,
		String chkPassword) {
}
