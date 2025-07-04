package com.onlineordersystem.model.order;

/**
 * Interface đại diện cho một Observer trong mô hình Observer.
 * 
 * @param <T> kiểu dữ liệu của thông báo được nhận từ Subject.
 * 
 * Các lớp triển khai interface này sẽ đăng ký với Subject để nhận cập nhật (notification).
 * Khi Subject thay đổi, phương thức {@code update(T t)} sẽ được gọi để truyền thông tin mới.
 */
public interface Observer<T> {
	void update(T t);
}
