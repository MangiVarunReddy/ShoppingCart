package com.shoppingcary.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.text.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shoppingcart.connection.DBCon;
import com.shoppingcart.dao.OrderDao;
import com.shoppingcart.model.Cart;
import com.shoppingcart.model.Order;
import com.shoppingcart.model.User;

/**
 * Servlet implementation class OrderNowServlet
 */
@WebServlet("/order-now")
public class OrderNowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.setContentType("text/html");

		try {
			PrintWriter out = response.getWriter();
			User auth = (User) request.getSession().getAttribute("auth");

			SimpleDateFormat formatter = new SimpleDateFormat("yyy-MM-dd");
			Date date = new Date();
			if (auth != null) {
				String productId = request.getParameter("id");
				int productQuantity = Integer.parseInt(request.getParameter("quantity"));
				if (productQuantity <= 0) {
					productQuantity = 1;
				}
				
				Order orderModel=new Order();
				orderModel.setId(Integer.parseInt(productId));
				orderModel.setUid(auth.getId());
				orderModel.setQuantity(productQuantity);
				orderModel.setDate(formatter.format(date));
				
				OrderDao orderDao=new OrderDao(DBCon.getConnection());
				boolean result=orderDao.insertOrder(orderModel);
				
				if(result==true) {
					ArrayList<Cart> cart_list=(ArrayList<Cart>)request.getSession().getAttribute("cart-list");
					if(productId!=null) {
						for(Cart c:cart_list) {
							 if(c.getId()==Integer.parseInt(productId)) {
								 cart_list.remove(cart_list.indexOf(c));
								 break;
							 }
						 }
					}
					response.sendRedirect("orders.jsp");
					
				}
				else {
					out.println("Order Failed");
				}
				

			} else {
				response.sendRedirect("login.jsp");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
