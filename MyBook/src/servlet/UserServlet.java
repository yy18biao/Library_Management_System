package servlet;

import entity.Book;
import entity.User;
import entity.uilt;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        UserService userService = new UserService();
        String op = request.getParameter("op");

        if (op.equals("loginUser")) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String checkCode = request.getParameter("checkCode");
            String piccode = (String) request.getSession().getAttribute("piccode");
            checkCode = checkCode.toUpperCase();

            User user = userService.login(username, password, checkCode, piccode);
            HttpSession session = request.getSession();
            if (user == null) {
                out.print("<script>alert('登录失败！');location.href='index.jsp';</script>");
            } else {
                session.setAttribute("userInfo", user);
                request.setAttribute("userInfo", user);
                uilt.setUsername(user.getUsername());
                if (user.getStatus() == 0)
                    response.sendRedirect("admin_index.jsp");
                else if (user.getStatus() == 1)
                    response.sendRedirect("user_index.jsp");
            }
        } else if (op.equals("register")) {
            String name = request.getParameter("name");
            String pwd = request.getParameter("pwd1");
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");
            User u = new User();
            u.setPassword(pwd);
            u.setUsername(name);
            u.setPhone(phone);
            u.setEmail(email);
            if (userService.userAdd(u) == 1) {
                out.print("<script>alert('注册成功！');location.href='index.jsp';</script>");
            } else {
                out.print("<script>alert('注册失败！');location.href='register.jsp';</script>");
            }
        } else if (op.equals("useradd")) {
            String name = request.getParameter("name");
            String pwd = request.getParameter("pwd1");
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");
            User u = new User();
            u.setPassword(pwd);
            u.setUsername(name);
            u.setPhone(phone);
            u.setEmail(email);
            if (userService.userAdd(u) == 1) {
                out.print("<script>alert('新增用户成功！');location.href='reader.jsp';</script>");
            } else {
                out.print("<script>alert('新增用户失败！');location.href='reader.jsp';</script>");
            }
        } else if (op.equals("getAll")) {
            String keyword = request.getParameter("keyword");
            List<User> list = userService.getUserListByName(keyword);
            request.setAttribute("list", list);
            request.getRequestDispatcher("reader.jsp").forward(request, response);
        } else if (op.equals("delete")) {
            String id = request.getParameter("id");
            if (userService.delete(id) > 0) {
                out.print("<script>alert('删除成功！');location.href='reader.jsp';</script>");
            } else {
                out.print("<script>alert('删除失败！');location.href='reader.jsp';</script>");
            }
        } else if (op.equals("toupdate")) {
            String id = request.getParameter("id");
            User user = userService.getUserInfo(id);
            request.setAttribute("a", user);
            request.getRequestDispatcher("user_update.jsp").forward(request, response);
        } else if (op.equals("update")) {
            String id = request.getParameter("id");
            String username = request.getParameter("name");
            String password = request.getParameter("password");
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");
            int status = request.getIntHeader("status");
            User user = new User();
            user.setId(id);
            user.setEmail(email);
            user.setPhone(phone);
            user.setUsername(username);
            user.setPassword(password);
            user.setStatus(status);
            if (userService.update(user) > 0) {
                out.print("<script>alert('修改成功！');location.href='reader.jsp';</script>");
            } else {
                out.print("<script>alert('修改失败！');location.href='reader.jsp';</script>");
            }
        }
        out.flush();
        out.close();
    }
}
