package service;

import dao.BookDao;
import entity.Book;
import entity.BookHistory;
import entity.Booksend;

import java.util.List;

public class BookService {
    BookDao bookDao = new BookDao();

    public int add(Book book) {
        return bookDao.add(book);
    }

    public int deleteBook(String id) {
        return bookDao.deleteBook(id);
    }

    public int deleteHistory(String id){
        return bookDao.deleteHistory(id);
    }

    public int update(Book book) {
        return bookDao.update(book);
    }

    public int send(String id) {
        return bookDao.send(id);
    }

    public int return_book(String id) {
        return bookDao.return_book(id);
    }

    public List<Book> getBookListNoSendByName(String name) {
        return bookDao.getBookListNoSendByName(name);
    }

    public List<Booksend> getBookListNoReturnByNameOfAdmin(String name) {
        return bookDao.getBookListNoReturnByNameOfAdmin(name);
    }

    public List<Booksend> getBookListNoReturnByNameOfUser(String name){
        return bookDao.getBookListNoReturnByNameOfUser(name);
    }

    public Book getBookInfo(String id) {
        return bookDao.getBookInfo(id);
    }

    public List<BookHistory> getBookListHistoryByNameOfAdmin(String name){
        return bookDao.getBookListHistoryByNameOfAdmin(name);
    }
    public List<BookHistory> getBookListHistoryByNameOfUser(String name){
        return bookDao.getBookListHistoryByNameOfUser(name);
    }
}
