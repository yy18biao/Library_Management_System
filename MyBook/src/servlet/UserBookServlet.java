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

@WebServlet("/UserBookServlet")
public class UserBookServlet extends HttpServlet {
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
            request.getRequestDispatcher("user_index.jsp").forward(request, response);
        } else if (op.equals("getAllNoReturnBook")) {
            String keyword = request.getParameter("keyword");
            List<Booksend> list = bookService.getBookListNoReturnByNameOfUser(keyword);
            request.setAttribute("list", list);
            request.getRequestDispatcher("user_send.jsp").forward(request, response);
        } else if (op.equals("getAllHistoryBook")) {
            String keyword = request.getParameter("keyword");
            List<BookHistory> list = bookService.getBookListHistoryByNameOfUser(keyword);
            request.setAttribute("list", list);
            request.getRequestDispatcher("user_history.jsp").forward(request, response);
        }else if (op.equals("send")) {
            String id = request.getParameter("id");
            if (bookService.send(id) > 0) {
                out.print("<script>alert('借阅成功！');location.href='user_index.jsp';</script>");
            } else {
                out.print("<script>alert('借阅失败！');location.href='user_index.jsp';</script>");
            }
        }else if (op.equals("return_book")) {
            String id = request.getParameter("id");
            if (bookService.return_book(id) > 0) {
                out.print("<script>alert('归还成功！');location.href='user_send.jsp';</script>");
            } else {
                out.print("<script>alert('归还失败！');location.href='user_send.jsp';</script>");
            }
        }

    }
}
