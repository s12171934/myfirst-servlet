package com.example.myfirstservlet.toDoLIst;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "del", value = "/toDoList/del")
public class Del extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        ServletContext sc = this.getServletContext();
        ArrayList<String> toDo = (ArrayList<String>)sc.getAttribute("toDo");
        ArrayList<String> toDoDate = (ArrayList<String>)sc.getAttribute("toDoDate");
        String url;

        if(sc.getAttribute("mode").equals("수정")){
            String list = req.getParameter("toDoList");
            sc.setAttribute("ch",list);
            toDoDate.remove(toDo.indexOf(req.getParameter("toDoList")));
            toDo.remove(req.getParameter("toDoList"));
            url = "/toDoList/input";
        } else{
            for(String list : req.getParameterValues("toDoList")){
                toDoDate.remove(toDo.indexOf(list));
                toDo.remove(list);
            }
            url = "/toDoList/main";
        }

        sc.setAttribute("toDo",toDo);
        sc.setAttribute("toDODate",toDoDate);


        RequestDispatcher rd = sc.getRequestDispatcher(url);
        rd.forward(req,resp);
    }
}