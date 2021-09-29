package com.njj.dao;

import com.njj.bean.Master;
import com.njj.util.DBHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MasterDAO {
//登录
    public Master login(String name,String password){
        Master master=null;
        //1创建连接
        Connection connection = DBHelper.getConnection();
        //2、创建sql
        String sql= "select * from master where name=? and password=? ";
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            //3、使用链接对象获取预编译对象
            ps= connection.prepareStatement(sql);
            ps.setString(1,name);
            ps.setString(2,password);
            //4、执行预编译第项
            rs=ps.executeQuery();
            if(rs.next()){
                master = new Master();
                master.setId(rs.getInt("id"));
                master.setName(rs.getString("name"));
                master.setSex(rs.getString("sex"));
                master.setAge(rs.getString("age"));
                master.setAccount(rs.getString("account"));
                master.setPassword(rs.getString("password"));
                master.setDid(rs.getString("did"));
                master.setPhone(rs.getString("phone"));
                master.setDel(rs.getString("del"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
      return master;
    }
//全查
public List<Master> selectAll(){
    ArrayList<Master> masters = new ArrayList<>();
    //1.创建出 连接对象
    Connection connection = DBHelper.getConnection();
    //2.创建出SQL语句
    String sql = "select * from master";
    //3.使用连接对象 获取 预编译对象
    PreparedStatement ps = null;
    ResultSet rs = null;
    try {
        ps = connection.prepareStatement(sql);
        //4.执行预编译，得到结果集
        rs = ps.executeQuery();
        //5.遍历结果集
        while (rs.next()){
            Master master = new Master();
            master.setId(rs.getInt("id"));
            master.setName(rs.getString("name"));
            master.setSex(rs.getString("sex"));
            master.setAge(rs.getString("age"));
            master.setAccount(rs.getString("account"));
            master.setPassword(rs.getString("password"));
            master.setDid(rs.getString("did"));
            master.setPhone(rs.getString("phone"));
            master.setDel(rs.getString("del"));
            masters.add(master);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }finally {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    return masters;
}

//添加
public int addMaster(Master master){
    //1、步骤1、创建链接对象
    Connection connection = DBHelper.getConnection();
    //2、sql语句因为添加的数据是变量 ，所以要用?代替
    String sql="insert into master values (null,?,?,?,?,?,?,?,?)";
    PreparedStatement ps=null;
    int i=0;
    try {
        //3、预编译
        ps = connection.prepareStatement(sql);
        ps.setString(1,master.getName());
        ps.setString(2,master.getSex());
        ps.setString(3,master.getAge());
        ps.setString(4,master.getAccount());
        ps.setString(5,master.getPassword());
        ps.setString(6,master.getDid());
        ps.setString(7,master.getPhone());
        ps.setString(8,master.getDel());
        i=ps.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }finally {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    return  i;

}

//修改全部
    public int updateMaster(Master master){
        //1、步骤1、创建链接对象
        Connection connection = DBHelper.getConnection();
        //2、sql语句
        String sql="update master set name=?,sex=?,age=?,account=?,password=?,did=?,phone=?,del=? where id=?";
        PreparedStatement ps=null;
        int i=0;
        try {
            //3、预编译
            ps = connection.prepareStatement(sql);
            ps.setString(1,master.getName());
            ps.setString(2,master.getSex());
            ps.setString(3,master.getAge());
            ps.setString(4,master.getAccount());
            ps.setString(5,master.getPassword());
            ps.setString(6,master.getDid());
            ps.setString(7,master.getPhone());
            ps.setString(8,master.getDel());
            ps.setInt(9,master.getId());
            i=ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return  i;
    }

    //删除
    public int deleteMaster(Integer id){
        //1、步骤1、创建链接对象
        Connection connection = DBHelper.getConnection();
        //2、sql语句因为添加的数据是变量 ，所以要用?代替
        String sql="delete from  master where id=?";

        PreparedStatement ps=null;
        int i=0;
        try {
            //3、预编译
            ps = connection.prepareStatement(sql);
            ps.setInt(1,id);
            System.out.println("dao d id = " + id);
            i=ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return  i;
    }


    public static void main(String[] args) {
        //登录
        MasterDAO masterDAO=new MasterDAO();
        /*Master name = masterDAO.login("张三", "123456");
        System.out.println("name = " + name);*/

        //全查
       /* List<Master> masters=masterDAO.selectAll();
        for (Master master : masters) {
            System.out.println("master = " + master);
        }*/

       /*//新增
        Master master=new Master();
        master.setName("李四");
        master.setSex("女");
        master.setAge("19");
        master.setAccount("lisi");
        master.setPassword("133");
        master.setDid("");
        master.setPhone("13837501098");
        master.setDel("0");
        int i = masterDAO.addMaster(master);
        System.out.println("i = " + i);*/

      //修改
       /* Master master=new Master();
        master.setId(15);
        master.setName("大乔2");
        master.setSex("女");
        master.setAge("20");
        master.setAccount("daqiao2");
        master.setPassword("97867345");
        master.setDid("");
        master.setPhone("1267043758");
        master.setDel("0");
        int i = masterDAO.updateMaster(master);
        System.out.println("i = " + i);*/

         //删除
       /* int i=masterDAO.deleteMaster(15);
        System.out.println("i = " + i);
*/
    }
}