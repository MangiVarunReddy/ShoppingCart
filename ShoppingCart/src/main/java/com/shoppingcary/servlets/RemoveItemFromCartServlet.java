package com.shoppingcary.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shoppingcart.model.Cart;

/**
 * Servlet implementation class RemoveItemFromCartServlet
 */
@WebServlet("/removeItem")
public class RemoveItemFromCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoveItemFromCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		String id=request.getParameter("id");
		if(id!=null) {
			ArrayList<Cart> cart_list=(ArrayList<Cart>)request.getSession().getAttribute("cart-list");
			if(id!=null) {
				for(Cart c:cart_list) {
					 if(c.getId()==Integer.parseInt(id)) {
						 cart_list.remove(cart_list.indexOf(c));
						 break;
					 }
				 }
				response.sendRedirect("cart.jsp");
			}
		}
			else {
				response.sendRedirect("cart.jsp");
			}
		}
		
	}


