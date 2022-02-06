package com.poscoict.jblog.vo;

public class BlogVo {
	private String title;
	private String logo;
	private String userId;
	
	private long postNo;
	private String postTitle;
	private String contents;
	private String regDate;
	private long categoryNo;
	private String categoryName;
	private String description;
	private long postCount;


	public long getPostCount() {
		return postCount;
	}
	public void setPostCount(long postCount) {
		this.postCount = postCount;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public long getPostNo() {
		return postNo;
	}
	public void setPostNo(long postNo) {
		this.postNo = postNo;
	}
	public String getPostTitle() {
		return postTitle;
	}
	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public long getCategoryNo() {
		return categoryNo;
	}
	public void setCategoryNo(long categoryNo) {
		this.categoryNo = categoryNo;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "BlogVo [title=" + title + ", logo=" + logo + ", userId=" + userId + ", postNo=" + postNo
				+ ", postTitle=" + postTitle + ", contents=" + contents + ", regDate=" + regDate + ", categoryNo="
				+ categoryNo + ", categoryName=" + categoryName + ", description=" + description + ", postCount="
				+ postCount + "]";
	}


}
