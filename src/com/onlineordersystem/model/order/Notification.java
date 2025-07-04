package com.onlineordersystem.model.order;

/**
 * DTO (Data Transfer Object) dùng để truyền thông tin từ Subject đến Observer trong mô hình Observer.
 * 
 * Thuộc tính {@code origin} đại diện cho đối tượng phát ra thông báo, cho phép Observer 
 * có thể ép kiểu (cast) về lớp cụ thể và truy cập vào trạng thái chính (mainState) nếu cần thiết.
 */
public class Notification {
	private String title;
	private String body;
	private Notifiable origin;

	public Notification() {
		super();
	}

	public Notification(String title, String body, Notifiable origin) {
		super();
		this.title = title;
		this.body = body;
		this.origin = origin;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Notifiable getOrigin() {
		return origin;
	}

	public void setOrigin(Notifiable origin) {
		this.origin = origin;
	}

}
