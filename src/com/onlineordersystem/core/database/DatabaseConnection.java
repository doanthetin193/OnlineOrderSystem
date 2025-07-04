package com.onlineordersystem.core.database;

/**
 * Lớp DatabaseConnection triển khai mẫu thiết kế Singleton để đảm bảo chỉ có 
 * một instance duy nhất của kết nối cơ sở dữ liệu trong toàn bộ ứng dụng.
 * 
 * Sử dụng Double-checked locking pattern để đảm bảo thread-safe và hiệu suất tối ưu.
 * 
 * @author Online Order System Team
 * @version 1.0
 * @since 2025
 */
public class DatabaseConnection {
    private static DatabaseConnection instance;

    /**
     * Constructor private để ngăn khởi tạo trực tiếp từ bên ngoài.
     * Mô phỏng việc thiết lập kết nối database.
     */
    private DatabaseConnection() {
        // Kết nối giả lập
        System.out.println("Database connection established.");
    }

    /**
     * Phương thức getInstance() triển khai Singleton pattern với Double-checked locking.
     * Đảm bảo thread-safe và chỉ tạo instance khi thực sự cần thiết (lazy initialization).
     * 
     * @return instance duy nhất của DatabaseConnection
     */
    public static DatabaseConnection getInstance() {
        if (instance == null) {
            synchronized (DatabaseConnection.class) {
                if (instance == null) {
                    instance = new DatabaseConnection();
                }
            }
        }
        return instance;
    }

    /**
     * Thực thi một câu lệnh SQL query.
     * Trong thực tế, đây sẽ là nơi thực hiện các operations với database thực.
     * 
     * @param query câu lệnh SQL cần thực thi
     */
    public void executeQuery(String query) {
        System.out.println("Executing query: " + query);
    }
}