package com.njj.controller;

import com.alibaba.fastjson.JSONObject;
import com.njj.service.MasterService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet(name = "MasterStelectAllServlet",urlPatterns = "/MasterStelectAllServlet")
public class MasterStelectAllServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、修正编码
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html; charset=UTF-8");


        MasterService masterService=new MasterService();
        Map map = masterService.selectAll();

        PrintWriter printWriter=resp.getWriter();
        String s= JSONObject.toJSONString(map);
        printWriter.println(s);
        printWriter.close();
    }
}
