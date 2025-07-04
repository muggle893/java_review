import book.BookList;
import user.AdminUser;
import user.OdinaryUser;
import user.User;

import java.util.Scanner;

public class Main {
    public static final Scanner scan = new java.util.Scanner(System.in);
    public static BookList bookList = new BookList();
    public static User login() {
        System.out.println("请输入姓名：");
        String name = scan.nextLine();
        System.out.println("请输入你的身份: 1.管理员  2.普通用户");
        int code = scan.nextInt();
        if (code == 1) {
            return new AdminUser(name);
        } else {
            return new OdinaryUser(name);
        }
    }

    public static void main(String[] args) {
        User user = login();
        while (true) {
            int opId = user.menu();
            user.doOperation(opId, bookList);
        }
    }
}
