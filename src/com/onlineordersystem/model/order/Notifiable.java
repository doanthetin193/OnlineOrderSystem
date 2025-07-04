package com.onlineordersystem.model.order;


/**
 * Interface đánh dấu cho biết lớp có khả năng phát ra hoặc kích hoạt thông báo.
 * 
 * Interface này không định nghĩa phương thức nào, mà chỉ dùng để đánh dấu (marker interface),
 * giúp hệ thống nhận diện và xử lý các đối tượng có thể gửi thông báo.
 * 
 * Cách sử dụng tương tự như java.io.Serializable.
 * 
 * Mục đích tồn tại của interface này là để truyền subject đi, và observer có thể cast lại
 * để truy cập các thông tin của đối tượng subject. Tương tự như việc observer có thể truy 
 * cập mainState từ subject, nhưng ở đây subject có thể phát ra thông báo từ nhiều thành phần 
 * (thêm sản phẩm, thay đổi trạng thái,...), nên chúng ta sẽ truyền cả subject và để observer
 * tự truy cập những gì họ cần.
 */
public interface Notifiable {
}
