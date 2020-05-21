package com.codefountain.security.utils;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

public final class CommonUtils {

	private CommonUtils() {}
	
	public static String getUserName() {
		return ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
	}
}
