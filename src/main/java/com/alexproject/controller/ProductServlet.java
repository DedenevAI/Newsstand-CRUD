package com.alexproject.controller;

import com.alexproject.Config;
import com.alexproject.dao.Dao;
import com.alexproject.model.Book;
import com.alexproject.model.Magazine;
import com.alexproject.model.Newspaper;
import com.alexproject.model.Publication;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(urlPatterns = {"/home"})
public class ProductServlet extends HttpServlet {

    private Dao dao = Config.getDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        String id = request.getParameter("id");
        if("/edit".equals(action)) {
            //Publication publication = dao.get(Long.parseLong(id));
            //request.setAttribute("publication", publication);
            RequestDispatcher dispatcher = request.getRequestDispatcher("pub_form.jsp");
            dispatcher.forward(request, response);
            return;
        } else if("/del".equals(action)) {
            dao.delete(Long.parseLong(id));
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        if("/store".equals(action)) {
            try {
                storePublication(request, response);
            }catch(Exception e) {
                System.out.println(e);
                RequestDispatcher dispatcher = request.getRequestDispatcher("pub_form.jsp");
                dispatcher.forward(request, response);
                return;
            }
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }

    private void storePublication(HttpServletRequest request, HttpServletResponse response) {
        String id    = request.getParameter("id");
        String type  = request.getParameter("type");
        String name  = request.getParameter("name");
        String value = request.getParameter("value");

        System.out.println("id = "+id);
        System.out.println("type = "+type);
        System.out.println("name = "+name);
        System.out.println("value = "+value);

        Publication publication = null;
        if("book".equalsIgnoreCase(type)) {
            publication = new Book(name, value);
        }else if("newspaper".equalsIgnoreCase(type)) {
            publication = new Newspaper(name, Integer.parseInt(value));
        }else if("magazine".equalsIgnoreCase(type)) {
            publication = new Magazine(name, value);
        }
        if(id==null || id.trim().isEmpty()) {
            dao.save(publication);
        } else {
            publication.setId((long) Integer.parseInt(id));
            dao.update(publication);
        }
    }
}
