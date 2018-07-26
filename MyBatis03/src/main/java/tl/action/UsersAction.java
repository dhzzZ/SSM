package tl.action;


import com.alibaba.fastjson.JSON;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import tl.entity.Users;
import tl.layui.Table;
import tl.service.UsersService;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class UsersAction {
    public Users users;
    @Autowired
    UsersService usersService;

    @Action(value="login",results = {@Result(name = "success",location = "/index.html")})
    public String login(){
        Users user=usersService.login(users);
        if(user!=null){
             return "success";
        }else{
            return "error";
        }
    }
    @Action(value = "showUsersTable")
    public void showUsersTable(){
        try {
            ServletActionContext.getRequest().setCharacterEncoding("utf-8");
            ServletActionContext.getResponse().setCharacterEncoding("utf-8");
            ServletActionContext.getResponse().setContentType("text/html");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        List<Users> list=usersService.findAllUsers();
        Table table=new Table();
        table.setData(list);
        try {
            ServletActionContext.getResponse().getWriter().write(JSON.toJSONString(table));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Action(value = "addUsers")
    public void addUsers(){
        int num=usersService.addUsers(users);
        if(num>0){
            try {
                ServletActionContext.getResponse().sendRedirect("/index.html");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }
}
