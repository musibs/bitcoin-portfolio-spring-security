package com.codefountain.security.entity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
@EqualsAndHashCode
@Document
public class SupportQuery {
	
	@Id
	private String id;
	private final String username;
	private final String subject;
	private List<Post> posts = new ArrayList<>();
	private Calendar created = Calendar.getInstance();
	private boolean resolved;
	
	public void addPost(Post post) {
		this.posts.add(post);
	}
	
	public void addPost(String post, String userName) {
		this.posts.add(new Post(userName, post, System.currentTimeMillis()));
	}
	
	public void setResolved(boolean resolved) {
		this.resolved = resolved;
	}
	
	public void resolve() {
		this.resolved = true;
	}
	

}
