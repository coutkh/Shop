package com.cout.shop.controller.filters;

import com.cout.shop.controller.PagePath;
import com.cout.shop.controller.RequestParameter;
import com.cout.shop.controller.SessionAttribute;
import com.cout.shop.controller.command.Command;
import com.cout.shop.controller.command.CommandProvider;
import com.cout.shop.model.entity.UserRole;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Set;

public class AccessFilter implements Filter {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();

        response.setHeader("Cache-Control", "private, must-revalidate"); // HTTP 1.1.
        response.setDateHeader("Expires", 0);

        if (session.getAttribute(SessionAttribute.ROLE) == null) {
            session.setAttribute(SessionAttribute.ROLE, "guest");
        }

        String commandName = request.getParameter(RequestParameter.COMMAND);
        Command command;
        if(commandName == null){
            command = CommandProvider.getCommand("to_main");
        }else {
            command = CommandProvider.getCommand(commandName);
        }

        String roleName = (String)session.getAttribute(SessionAttribute.ROLE);
        UserRole userRole;

        if (roleName == null) {
            userRole = UserRole.GUEST;
        }else {
            userRole = UserRole.valueOf(roleName.toUpperCase());
        }

        Set<Command> commands = null;
        if ("admin".equals(userRole.getRole())) {
            commands = Access.ADMIN.getCommands();
        }else if("user".equals(userRole.getRole())){
            commands = Access.USER.getCommands();
        }else/*("guest".equals(userRole.getRole()))*/ {
            commands = Access.GUEST.getCommands();
        }

        if (!commands.contains(command)) {
            logger.info("Role {} tried to access {} command", roleName, commandName);
            response.sendRedirect(PagePath.REDIRECT_SIGN_IN_PAGE);
            return;
        }
        chain.doFilter(servletRequest, servletResponse);
    }
}
