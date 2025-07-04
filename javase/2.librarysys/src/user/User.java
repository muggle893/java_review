package user;

import book.BookList;
import op.Operation;

public abstract class User {
    public String name;
    public Operation[] ops;
    public abstract int menu();
    public abstract void doOperation(int opId, BookList bookList0);
}
