package dao;

import entity.*;
import util.DbUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class BookDao extends DbUtil {
    public int add(Book book) {
        String sql = "insert into books(name, author, press, number) value(?,?,?,?)";
        return super.executeUpdate(sql, book.getName(), book.getAuthor(), book.getPress(), book.getNumber());
    }

    public int update(Book book) {
        String sql = "update books set name=?,author=?,press=?,number=? where id=?";
        return super.executeUpdate(sql, book.getName(), book.getAuthor(), book.getPress(), book.getNumber(), book.getId());
    }

    public int deleteBook(String id) {
        String sql = "delete from books where id=?";
        return super.executeUpdate(sql, id);
    }

    public int deleteHistory(String id) {
        String sql = "delete from bookhirstory where id=?";
        return super.executeUpdate(sql, id);
    }

    public int send(String id) {
        String sql = "update books set number=number-1 where id=?";
        Book book = getBookInfo(id);

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DATE);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        String data = year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second;

        String sql2 = "insert into booksend(bid, username, bookname, author, press, data) value(?,?,?,?,?,?)";
        int i = super.executeUpdate(sql, id);
        int k = super.executeUpdate(sql2, book.getId(), uilt.getUsername(), book.getName(), book.getAuthor(), book.getPress(), data);
        return i & k;
    }

    public int return_book(String id) {
        String bid = getBookSendBid(id);
        Book book = getBookInfo(bid);
        String username = getBookSendUsername(book.getName());
        String sdata = getBookSendSdata(book.getName());
        String sql2 = "delete from booksend where id=?";
        String sql3 = "insert into bookhirstory(bookid, username, bookname, author, press, sdata, rdata) value(?,?,?,?,?,?,?)";
        String sql = "update books set number=number+1 where id=?";

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DATE);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        String rdata = year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second;

        int i = super.executeUpdate(sql, bid);
        int k = super.executeUpdate(sql2, id);
        int m = super.executeUpdate(sql3, book.getId(), username, book.getName(), book.getAuthor(), book.getPress(), sdata, rdata);
        return i & k & m;
    }

    public String getBookSendBid(String id) {
        String sql = "select * from booksend where id='" + id + "'";
        try {
            super.executeQuery(sql);
            while (rs.next()) {
                return rs.getString("bid");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            super.closeAll();
        }
        return "";
    }

    public String getBookSendUsername(String bookname) {
        String sql = "select * from booksend where bookname='" + bookname + "'";
        try {
            super.executeQuery(sql);
            while (rs.next()) {
                return rs.getString("username");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            super.closeAll();
        }
        return "";
    }

    public String getBookSendSdata(String bookname){
        String sql = "select * from booksend where bookname='" + bookname + "'";
        try {
            super.executeQuery(sql);
            while (rs.next()) {
                return rs.getString("data");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            super.closeAll();
        }
        return "";
    }

    public List<Book> getBookListNoSendByName(String name) {
        List<Book> list = new ArrayList<Book>();
        String sql = "";
        if (name == null || name.equals("")) {
            sql = "select * from books where number>0";
        } else {
            sql = "select * from books where name='" + name + "' and number>0";
        }
        try {
            super.executeQuery(sql);
            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getString("id"));
                book.setName(rs.getString("name"));
                book.setAuthor(rs.getString("author"));
                book.setPress(rs.getString("press"));
                book.setNumber(rs.getString("number"));
                list.add(book);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            super.closeAll();
        }
        return list;
    }

    public List<BookHistory> getBookListHistoryByNameOfAdmin(String name) {
        List<BookHistory> list = new ArrayList<BookHistory>();
        String sql = "";
        if (name == null || name.equals("")) {
            sql = "select * from bookhirstory";
        } else {
            sql = "select * from bookhirstory where username='" + name + "'";
        }
        try {
            super.executeQuery(sql);
            while (rs.next()) {
                BookHistory bookHistory = new BookHistory();
                bookHistory.setId(rs.getString("id"));
                bookHistory.setBookid(rs.getString("bookid"));
                bookHistory.setUsername(rs.getString("username"));
                bookHistory.setBookname(rs.getString("bookname"));
                bookHistory.setAuthor(rs.getString("author"));
                bookHistory.setPress(rs.getString("press"));
                bookHistory.setSdata(rs.getString("sdata"));
                bookHistory.setRdata(rs.getString("rdata"));
                list.add(bookHistory);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            super.closeAll();
        }
        return list;
    }

    public List<BookHistory> getBookListHistoryByNameOfUser(String name) {
        List<BookHistory> list = new ArrayList<BookHistory>();
        String sql = "";
        if (name == null || name.equals("")) {
            sql = "select * from bookhirstory where username='" + uilt.getUsername() + "'";
        } else {
            sql = "select * from bookhirstory where bookname='" + name + "' and username='" + uilt.getUsername() + "'";
        }
        try {
            super.executeQuery(sql);
            while (rs.next()) {
                BookHistory bookHistory = new BookHistory();
                bookHistory.setId(rs.getString("id"));
                bookHistory.setBookid(rs.getString("bookid"));
                bookHistory.setUsername(rs.getString("username"));
                bookHistory.setBookname(rs.getString("bookname"));
                bookHistory.setAuthor(rs.getString("author"));
                bookHistory.setPress(rs.getString("press"));
                bookHistory.setSdata(rs.getString("sdata"));
                bookHistory.setRdata(rs.getString("rdata"));
                list.add(bookHistory);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            super.closeAll();
        }
        return list;
    }

    public List<Booksend> getBookListNoReturnByNameOfAdmin(String name) {
        List<Booksend> list = new ArrayList<Booksend>();
        String sql = "";
        if (name == null || name.equals("")) {
            sql = "select * from booksend";
        } else {
            sql = "select * from booksend where username='" + name + "'";
        }
        try {
            super.executeQuery(sql);
            while (rs.next()) {
                Booksend booksend = new Booksend();
                booksend.setId(rs.getString("id"));
                booksend.setBid(rs.getString("bid"));
                booksend.setUsername(rs.getString("username"));
                booksend.setBookame(rs.getString("bookname"));
                booksend.setAuthor(rs.getString("author"));
                booksend.setPress(rs.getString("press"));
                booksend.setData(rs.getString("data"));
                list.add(booksend);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            super.closeAll();
        }
        return list;
    }

    public List<Booksend> getBookListNoReturnByNameOfUser(String name) {
        List<Booksend> list = new ArrayList<Booksend>();
        String sql = "";
        if (name == null || name.equals("")) {
            sql = "select * from booksend where username='" + uilt.getUsername() + "'";
        } else {
            sql = "select * from booksend where bookname='" + name + "' and username='" + uilt.getUsername() + "'";
        }
        try {
            super.executeQuery(sql);
            while (rs.next()) {
                Booksend booksend = new Booksend();
                booksend.setId(rs.getString("id"));
                booksend.setBid(rs.getString("bid"));
                booksend.setUsername(rs.getString("username"));
                booksend.setBookame(rs.getString("bookname"));
                booksend.setAuthor(rs.getString("author"));
                booksend.setPress(rs.getString("press"));
                booksend.setData(rs.getString("data"));
                list.add(booksend);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            super.closeAll();
        }
        return list;
    }

    public Book getBookInfo(String id) {
        Book book = null;
        String sql = "select * from books where id=?";
        try {
            super.executeQuery(sql, id);
            while (rs.next()) {
                book = new Book();
                book.setId(rs.getString("id"));
                book.setName(rs.getString("name"));
                book.setAuthor(rs.getString("author"));
                book.setPress(rs.getString("press"));
                book.setNumber(rs.getString("number"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            super.closeAll();
        }
        return book;
    }
}
