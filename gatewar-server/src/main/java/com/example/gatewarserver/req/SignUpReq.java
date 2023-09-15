package com.example.gatewarserver.req;

import java.time.LocalDate;

public record SignUpReq(
		String name,
		LocalDate birthday,
		String email,
		String address,
		String username,
		String password,
		String chkPassword) {
}
