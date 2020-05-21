package com.codefountain.security.entity;

import org.bson.types.ObjectId;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class Post {

	private String id;
	private final String userName;
	private final String content;
	private final long timestamp;;
	
	public Post(String userName, String content, long timestamp) {
		this.userName = userName;
		this.content = content;
		this.timestamp = timestamp;
		this.id = new ObjectId().toHexString();
	}
	
	
}
