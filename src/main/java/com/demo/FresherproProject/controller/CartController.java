package com.demo.FresherproProject.controller;

import com.demo.FresherproProject.model.Item;
import com.demo.FresherproProject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("cart")
public class CartController {

    @Autowired
    private ProductService productService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(ModelMap modelMap, HttpSession session)
    {
        modelMap.put("total", total(session));
        return "cart/index";
    }
    @RequestMapping(value="buy/{id}", method = RequestMethod.GET)
    public String buy(@PathVariable("id") Long id, ModelMap modelMap, HttpSession session)
    {
        if(session.getAttribute("cart") == null)
        {
            List<Item> cart = new ArrayList<Item>();
            cart.add(new Item(productService.get(id),1));
            session.setAttribute("cart", cart);
        }
        else
        {
            List<Item> cart = (List<Item>) session.getAttribute("cart");
            int index = isExists(id,cart);
            if( index == -1)
            {
                cart.add(new Item(productService.get(id),1));

            }
            else
            {
                int quantity = cart.get(index).getQuantity() + 1;
                cart.get(index).setQuantity(quantity);

            }
            session.setAttribute("cart",cart);

        }
        return "redirect:../../cart";
    }

    @RequestMapping(value="remove/{id}", method = RequestMethod.GET)
    public String remove(@PathVariable("id") Long id , HttpSession session) {
        List<Item> cart = (List<Item>) session.getAttribute("cart");
        int index = isExists(id,cart);
        cart.remove(index);
        session.setAttribute("cart",cart);

        return "redirect:../../cart";
    }

    @RequestMapping(value="update", method = RequestMethod.POST)
    public String update(HttpServletRequest request, HttpSession session) {
        String[] quantities = request.getParameterValues("quantity");
        List<Item> cart = (List<Item>) session.getAttribute("cart");
        for(int i=0;i<cart.size();i++)
        {
            cart.get(i).setQuantity(Integer.parseInt(quantities[i]));
        }
        session.setAttribute("cart",cart);

        return "redirect:../cart";
    }

    private int isExists(Long id, List<Item> cart)
    {
        for(int i =0 ; i < cart.size(); i++)
        {
            if(cart.get(i).getProduct().getCode() == id)
            {
                return i;
            }
        }
        return -1;
    }

    private double total(HttpSession session)
    {
        List<Item> cart = (List<Item>) session.getAttribute("cart");
        double s = 0;
        for(Item item : cart)
        {
            s += item.getQuantity() * item.getProduct().getPrice();
        }

        return s;

    }
}
