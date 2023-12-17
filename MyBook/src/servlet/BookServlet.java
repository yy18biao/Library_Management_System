package servlet;

import entity.Book;
import entity.BookHistory;
import entity.Booksend;
import service.BookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/BookServlet")
public class BookServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        String op = request.getParameter("op");
        BookService bookService = new BookService();
        if (op.equals("getAllNoSendBook")) {
            String keyword = request.getParameter("keyword");
            List<Book> list = bookService.getBookListNoSendByName(keyword);
            request.setAttribute("list", list);
            request.getRequestDispatcher("admin_index.jsp").forward(request, response);
        }else if(op.equals("getAllNoReturnBook")){
            String keyword = request.getParameter("keyword");
            List<Booksend> list = bookService.getBookListNoReturnByNameOfAdmin(keyword);
            request.setAttribute("list", list);
            request.getRequestDispatcher("admin_send.jsp").forward(request, response);
        } else if(op.equals("getAllHistoryBook")){
            String keyword = request.getParameter("keyword");
            List<BookHistory> list = bookService.getBookListHistoryByNameOfAdmin(keyword);
            request.setAttribute("list", list);
            request.getRequestDispatcher("admin_history.jsp").forward(request, response);
        } else if(op.equals("return_book")){
            String id = request.getParameter("id");
            if (bookService.return_book(id) > 0) {
                out.print("<script>alert('归还成功！');location.href='admin_send.jsp';</script>");
            } else {
                out.print("<script>alert('归还失败！');location.href='admin_send.jsp';</script>");
            }
        } else if (op.equals("bookadd")) {
            String name = request.getParameter("name");
            String author = request.getParameter("author");
            String press = request.getParameter("press");
            String number = request.getParameter("number");
            Book book = new Book();
            book.setName(name);
            book.setAuthor(author);
            book.setPress(press);
            book.setNumber(number);
            if (bookService.add(book) > 0) {
                out.print("<script>alert('新增图书成功！');location.href='admin_index.jsp';</script>");
            } else {
                out.print("<script>alert('新增图书失败！');location.href='admin_index.jsp';</script>");
            }
        } else if (op.equals("toupdate")) {
            String id = request.getParameter("id");
            Book book = bookService.getBookInfo(id);
            request.setAttribute("a", book);
            request.getRequestDispatcher("admin_book_update.jsp").forward(request, response);
        } else if (op.equals("update")) {
            String id = request.getParameter("id");
            String name = request.getParameter("name");
            String author = request.getParameter("author");
            String press = request.getParameter("press");
            String number = request.getParameter("number");
            Book book = new Book();
            book.setId(id);
            book.setName(name);
            book.setAuthor(author);
            book.setPress(press);
            book.setNumber(number);
            if (bookService.update(book) > 0) {
                out.print("<script>alert('修改成功！');location.href='admin_index.jsp';</script>");
            } else {
                out.print("<script>alert('修改失败！');location.href='admin_index.jsp';</script>");
            }
        } else if (op.equals("deleteBook")) {
            String id = request.getParameter("id");
            if (bookService.deleteBook(id) > 0) {
                out.print("<script>alert('删除成功！');location.href='admin_index.jsp';</script>");
            } else {
                out.print("<script>alert('删除失败！');location.href='admin_index.jsp';</script>");
            }
        }else if (op.equals("deleteHistroy")) {
            String id = request.getParameter("id");
            if (bookService.deleteHistory(id) > 0) {
                out.print("<script>alert('删除成功！');location.href='admin_history.jsp';</script>");
            } else {
                out.print("<script>alert('删除失败！');location.href='admin_history.jsp';</script>");
            }
        }
        out.flush();
        out.close();
    }
}
