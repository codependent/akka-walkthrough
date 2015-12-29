package com.codependent.akka.sample3.dto;

public class Story {
	private String story;
	private String img;
	private String author;
	public String getStory() {
		return story;
	}
	public void setStory(String story) {
		this.story = story;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	@Override
	public String toString() {
		return "Story [story=" + story + ", img=" + img + ", author=" + author
				+ "]";
	}
	
}
