package com.njj.service;

import com.njj.bean.Master;
import com.njj.dao.MasterDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MasterService {
    //登录
    public Map login(String name, String password, HttpServletRequest request){
        Map map=new HashMap();
        //service 层要调用dao层
        MasterDAO dao = new MasterDAO();
        Master masterFromDB = dao.login(name, password);
        if(null==masterFromDB){
            map.put("code",4001);
            map.put("msg","账户名或者密码不正确");
            return map;
        }else {
            HttpSession session=request.getSession();
            session.setAttribute("master",masterFromDB);
            map.put("code",0);
            map.put("msg","登录成功");
            //给一个账户  给前端渲染
            map.put("name",name);
            return map;
        }
    }

    //全查业务员
    public  Map selectAll(){
        MasterDAO dao = new MasterDAO();
        List<Master> masters = dao.selectAll();
        Map codemap = new HashMap();
        codemap.put("code",0);//必须和layui的JSON返回的格式一样
        codemap.put("msg","ok");
        codemap.put("data",masters);
        return codemap;
    }

    //添加
    public Map insertByUser(Master master){
        Map map = new HashMap();
        // service 层要调用dao层
        MasterDAO dao = new MasterDAO();
        System.out.println("dao = " + dao);
        System.out.println("master = " + master);

        int a= dao.addMaster(master);
        System.out.println("a = " + a);
        if(a>0){
            System.out.println(" 提交成功" );
            map.put("code", 0);
            map.put("msg", "添加成功");
        }else{
            System.out.println(" 提交失败" );
            map.put("code",4002);
            map.put("msg","添加失败");
        }
        return  map;
    }

    //修改全部
    public Map updateMaster(Master master){
        Map codeMap = new HashMap();
        // service 层要调用dao层
        MasterDAO dao = new MasterDAO();
        //System.out.println("dao = " + dao);

        int i= dao.updateMaster(master);
        //System.out.println("i = " + i);
        if(i==1){
            //System.out.println(" 提交成功" );
            codeMap.put("code", 0);
            codeMap.put("msg", "修改成功");
        }else{
            //System.out.println(" 提交失败" );
            codeMap.put("code",400);
            codeMap.put("msg","修改失败");
        }
        return  codeMap;

    }
    //删除
    public int deleteMaster(Integer id){
        MasterDAO dao =new MasterDAO();
        int i = dao.deleteMaster(id);
        return i;
    }
}
