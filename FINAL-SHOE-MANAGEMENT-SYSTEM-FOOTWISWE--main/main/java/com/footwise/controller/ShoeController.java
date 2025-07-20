package com.footwise.controller;

import com.footwise.model.Shoe;
import com.footwise.service.ShoeService;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/shoes")
public class ShoeController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ShoeService service = new ShoeService();
        List<Shoe> shoes = service.getAllShoes();
        request.setAttribute("shoes", shoes);
        RequestDispatcher rd = request.getRequestDispatcher("shoes.jsp");
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        ShoeService service = new ShoeService();

        if ("add".equals(action)) {
            Shoe shoe = new Shoe();
            shoe.setName(request.getParameter("name"));
            shoe.setBrand(request.getParameter("brand"));
            shoe.setPrice(Double.parseDouble(request.getParameter("price")));
            shoe.setQuantity(Integer.parseInt(request.getParameter("quantity")));
            service.addShoe(shoe);
        } else if ("delete".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            service.deleteShoe(id);
        } else if ("update".equals(action)) {
            Shoe shoe = new Shoe();
            shoe.setId(Integer.parseInt(request.getParameter("id")));
            shoe.setName(request.getParameter("name"));
            shoe.setBrand(request.getParameter("brand"));
            shoe.setPrice(Double.parseDouble(request.getParameter("price")));
            shoe.setQuantity(Integer.parseInt(request.getParameter("quantity")));
            service.updateShoe(shoe);
        }
        response.sendRedirect("shoes");
    }
}