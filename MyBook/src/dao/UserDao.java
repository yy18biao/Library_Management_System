package dao;

import entity.Book;
import entity.User;
import util.DbUtil;

import java.util.ArrayList;
import java.util.List;

public class UserDao extends DbUtil {
    public User login(String username, String password, String checkCode, String piccode) {
        User user = null;
        try {
            if(checkCode.equals(piccode) == false)
                return null;
            String sql = " select * from user where username=? and password=?";
            super.executeQuery(sql, username, password);
            while (rs.next()) {
                user = new User();
                user.setId(rs.getString("id"));
                user.setPassword(rs.getString("password"));
                user.setUsername(rs.getString("username"));
                user.setStatus(rs.getInt("status"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            super.closeAll();
        }
        return user;
    }

    public int userAdd(User user) {
        String sql = " insert into user (username,password,phone,email) values (?,?,?,?) ";
        return super.executeUpdate(sql, user.getUsername(), user.getPassword(), user.getPhone(), user.getEmail());
    }

    public int update(User user) {
        String sql = "update user set username=?,password=?,phone=?,email=? where id=?";
        return super.executeUpdate(sql, user.getUsername(), user.getPassword(), user.getPhone(), user.getEmail(), user.getId());
    }

    public int delete(String id) {
        String sql = "delete from user where id=?";
        return super.executeUpdate(sql, id);
    }

    public List<User> getUserListByName(String name) {
        List<User> list = new ArrayList<User>();
        String sql = "";
        if (name == null || name.equals("")) {
            sql = "select * from user";
        } else {
            sql = "select * from user where username='" + name + "'";
        }
        try {
            super.executeQuery(sql);
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getString("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone"));
                list.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            super.closeAll();
        }
        return list;
    }

    public User getUserInfo(String id) {
        User user = null;
        String sql = "select * from user where id=?";
        try {
            super.executeQuery(sql, id);
            while (rs.next()) {
                user = new User();
                user.setId(rs.getString("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setPhone(rs.getString("phone"));
                user.setEmail(rs.getString("email"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            super.closeAll();
        }
        return user;
    }
}
