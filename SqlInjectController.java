package com.alibaba.security.bench.hello.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;


@RestController
public class SqlInjectController {

    @RequestMapping(value = "/sql/query", method = RequestMethod.GET)
    public ResponseEntity<?> sqlQuery(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            // 建立数据库连接
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/database", "user", "password");
            // 创建Statement对象
            stmt = conn.createStatement();
            // 不安全的SQL查询，直接把用户输入拼接到查询中
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT * FROM users WHERE username = '")
                .append(username)
                .append("'");
            // 执行查询
            rs = stmt.executeQuery(sql.toString());
            // 处理结果集...
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源...
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
