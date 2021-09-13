package com.cout.shop.controller;


import com.cout.shop.controller.command.Command;
import com.cout.shop.controller.command.CommandProvider;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Controller extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String commandName = req.getParameter(RequestParameter.COMMAND);
        Command command = CommandProvider.getCommand(commandName);

        String temp = command.execute(req);
        String typeRe = temp.substring(0, temp.indexOf(":")+1);
        String page = temp.substring(temp.indexOf(":")+1);

        if(page != null){
            HttpSession session = req.getSession();
            session.setAttribute(SessionAttribute.CURRENT_PAGE, page);
            if("redirect:".equals(typeRe)){
                resp.sendRedirect(page);
            }else {
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
                dispatcher.forward(req, resp);
            }
        }
    }
}
