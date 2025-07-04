package user;

import book.BookList;
import op.*;

import java.util.Scanner;

public class OdinaryUser extends User{
    public OdinaryUser(String name) {
        this.name = name;
        this.ops = new Operation[] {
                new ExitOperation(),
                new SerachOperation(),
                new BorrowOperation(),
                new ReturnOperation()
        };
    }
    @Override
    public void doOperation(int opId, BookList bookList) {
        this.ops[opId].work(bookList);
    }

    public int menu(){
        System.out.println("*********普通用户********");
        System.out.println("1.查找图书");
        System.out.println("2.借阅图书");
        System.out.println("3.归还图书");
        System.out.println("0.退出系统");
        System.out.println("**********************");
        System.out.println("请输入您的操作：");
        Scanner scanner = new Scanner(System.in);
        int opId = scanner.nextInt();
        return opId;
    }
}
