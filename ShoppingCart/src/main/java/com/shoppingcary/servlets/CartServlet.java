package com.shoppingcary.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shoppingcart.model.Cart;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartServlet() {
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
		
		try {
			ArrayList<Cart> cartList=new ArrayList<>();
			int id=Integer.parseInt(request.getParameter("id"));
			Cart c=new Cart();
			c.setId(id);
			c.setQuantity(1);
			
			HttpSession session=request.getSession();
			 
			ArrayList<Cart> cart_list=(ArrayList<Cart>) session.getAttribute("cart-list");
			
			if(cart_list==null) {
				cartList.add(c);
				session.setAttribute("cart-list", cartList);
				response.sendRedirect("index.jsp");
			}
			else {
				int q;
				cartList=cart_list;
				boolean exist=false;
				for(Cart n:cart_list) {
					if(n.getId()==id) {
						exist=true;
						
						pw.println("<h3 style='color:crimson; text-align:center'>Item is already in Cart.<a href='cart.jsp'> Go to Cart page</a>");
					}
				}
				if(exist!=true) {
					cartList.add(c);
					response.sendRedirect("index.jsp");
				}
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
