package com.example.coursessystem.tags;

import com.example.coursessystem.beans.User;
import com.example.coursessystem.service.DBUserUtils;

import jakarta.servlet.jsp.tagext.TagSupport; // Обновлено на jakarta
import jakarta.servlet.jsp.JspWriter; // Обновлено на jakarta
import jakarta.servlet.jsp.JspException; // Обновлено на jakarta
import java.sql.Connection;
import jakarta.servlet.http.HttpSession; // Обновлено на jakarta

public class UserBalanceTag extends TagSupport {
    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        try {
            HttpSession session = pageContext.getSession();
            Connection conn = (Connection) session.getAttribute("connection");
            User user = (User) session.getAttribute("user");

            if (conn == null) {
                throw new JspException("Нет подключения к базе данных в сессии");
            }

            user = DBUserUtils.findUserById(conn, user.getId());
            assert user != null;
            out.print("Balance: " + user.getBalance());
        } catch (Exception e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }
}

