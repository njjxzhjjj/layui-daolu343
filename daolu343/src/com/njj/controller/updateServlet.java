package com.njj.controller;

import com.alibaba.fastjson.JSONObject;
import com.njj.bean.Master;
import com.njj.service.MasterService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet(name = "updateServlet",urlPatterns = "/updateServlet")
public class updateServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //接受登陆传过来的3个参数
        //1、修正编码
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html; charset=UTF-8");


        //2、接收参数
        String id=req.getParameter("id");
        String name = req.getParameter("name");
        String sex = req.getParameter("sex");
        String age = req.getParameter("age");
        String account = req.getParameter("account");
        String password = req.getParameter("password");
        String did = req.getParameter("did");
        String phone = req.getParameter("phone");
        String del = req.getParameter("del");

        //3、赋值到实体类
        Master master=new Master();
        master.setId(Integer.parseInt(id));
        master.setName(name);
        master.setSex(sex);
        master.setAge(age);
        master.setAccount(account);
        master.setPassword(password);
        master.setDid(did);
        master.setPhone(phone);
        master.setDel(del);


        //4、调用service
        MasterService service= new MasterService();
        Map map=service.updateMaster(master);

        String s= JSONObject.toJSONString(map);
        PrintWriter writer=resp.getWriter();
        writer.print(s);
        writer.close();
    }
}
