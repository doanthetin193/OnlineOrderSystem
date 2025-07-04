package com.onlineordersystem.model.order;

/**
 * Lớp OrderObserver đóng vai trò là một Observer trong mô hình Observer, chịu trách nhiệm
 * nhận và xử lý các thông báo (Notification) liên quan đến đơn hàng.
 * 
 * Trong hệ thống thực tế, thay vì chỉ dùng username đơn giản, chúng ta thường có các thực thể
 * User đầy đủ với nhiều thông tin hơn. Khi đó, có thể áp dụng mẫu thiết kế Adapter để kết nối
 * giữa hệ thống thông báo (vốn hoạt động dựa trên Observer) và các lớp người dùng thực tế.
 * 
 * Việc này giúp tách biệt logic lắng nghe/thông báo khỏi logic nghiệp vụ của người dùng,
 * đồng thời tăng tính mở rộng và khả năng tái sử dụng.
 */
public class OrderObserver implements Observer<Notification> {
	private String username;
	
	public OrderObserver(String username) {
		super();
		this.username = username;
	}

	@Override
	public void update(Notification notification) {
		String title = notification.getTitle();
        String body = notification.getBody();
        Notifiable origin = notification.getOrigin();
        String originHref = origin.toString().substring(origin.toString().indexOf("@"));

        String message = String.format("User %s received notification: %s - %s - href: %s", 
                username, title, body, originHref);

        System.out.println(message);
	}

}
