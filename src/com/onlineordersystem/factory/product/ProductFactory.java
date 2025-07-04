package com.onlineordersystem.factory.product;

import com.onlineordersystem.model.product.*;

/**
 * Lớp ProductFactory chịu trách nhiệm tạo các đối tượng sản phẩm cụ thể dựa trên loại sản phẩm.
 * 
 * Lưu ý: Trong thực tế, mỗi loại sản phẩm thường có các thuộc tính (trường dữ liệu) khác nhau.
 * Ví dụ: sản phẩm điện tử (ElectronicProduct) có thể cần thông tin về bảo hành, công suất, v.v.,
 * trong khi sản phẩm thời trang (FashionProduct) lại có size, màu sắc, chất liệu,...
 * 
 * Do đó, việc truyền trực tiếp name và price như hiện tại chỉ phù hợp với các ví dụ đơn giản.
 * Trong ứng dụng thực tế, chúng ta nên truyền vào một Map<String, Object> chứa các dữ liệu đầu vào,
 * và trong mỗi nhánh xử lý của factory, sẽ ép kiểu và trích xuất dữ liệu phù hợp để tạo đối tượng cụ thể.
 * 
 * Điều này giúp tăng tính linh hoạt và phù hợp với yêu cầu đa dạng của từng loại sản phẩm.
 */
public class ProductFactory {
    public Product createProduct(String type, String name, double price) {
        switch (type.toLowerCase()) {
            case "electronic":
                return new ElectronicProduct(name, price);
            case "fashion":
                return new FashionProduct(name, price);
            default:
                throw new IllegalArgumentException("Unknown product type: " + type);
        }
    }
}