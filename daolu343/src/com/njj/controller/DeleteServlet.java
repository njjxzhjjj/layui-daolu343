package com.njj.controller;

import com.alibaba.fastjson.JSON;
import com.njj.bean.Master;
import com.njj.service.MasterService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "DeleteServlet",urlPatterns = "/DeleteServlet")
public class DeleteServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //2.接收前端传来的参数
        String id=req.getParameter("id");
        System.out.println("id = " + id);

        Master master = new Master();
        master.setId(Integer.parseInt(id));

        MasterService masterService = new MasterService();
        int map = masterService.deleteMaster(Integer.parseInt(id));
        System.out.println("map = " + map);

        String s= JSON.toJSONString(map);
        System.out.println("s = " + s);

        PrintWriter printWriter=resp.getWriter();
        printWriter.println(s);
        printWriter.close();
    }
}
