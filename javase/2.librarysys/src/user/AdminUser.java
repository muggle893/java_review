package user;

import book.BookList;
import op.*;

import java.util.Scanner;

public class AdminUser extends User{
    public AdminUser(String name) {
        this.name = name;
        this.ops = new Operation[] {
                new ExitOperation(),
                new SerachOperation(),
                new AddOperation(),
                new DelOperation(),
                new ShowOperation()
        };
    }
    @Override
    public void doOperation(int opId, BookList bookList) {
        this.ops[opId].work(bookList);
    }

    public int menu(){
        System.out.println("*******管理员用户******");
        System.out.println("1.查找图书");
        System.out.println("2.新增图书");
        System.out.println("3.删除图书");
        System.out.println("4.显示图书");
        System.out.println("0.退出系统");
        System.out.println("**********************");
        System.out.println("请输入您的操作：");
        Scanner scanner = new Scanner(System.in);
        int opId = scanner.nextInt();
        return opId;
    }
}
