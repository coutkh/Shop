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
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        System.out.println("Go init");
    }

    private static final Logger logger = LogManager.getLogger();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {
        System.out.println("Go doFilter");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();

        response.setHeader("Cache-Control", "private, must-revalidate"); // HTTP 1.1.
        response.setDateHeader("Expires", 0);

        if (session.getAttribute(SessionAttribute.ROLE) == null) {
            session.setAttribute(SessionAttribute.ROLE, "guest");
        }

        String commandName = request.getParameter(RequestParameter.COMMAND);
        Command command = CommandProvider.getCommand(commandName);

        String roleName = (String)session.getAttribute(SessionAttribute.ROLE);
        UserRole userRole;

        if (roleName == null) {
            userRole = UserRole.GUEST;
        }else {
            userRole = UserRole.valueOf(roleName.toUpperCase());
        }
        /*Set<Command> commands = switch (userRole) {
            case GUEST -> Access.GUEST.getCommands();
            case USER -> Access.USER.getCommands();
            case ADMIN -> Access.ADMIN.getCommands();
        };*/

        Set<Command> commands = null;
        if("guest".equals(userRole.getRole())){
            commands = Access.GUEST.getCommands();
        }else if ("user".equals(userRole.getRole())) {
            commands = Access.USER.getCommands();
        }else if ("admin".equals(userRole.getRole())) {
            commands = Access.ADMIN.getCommands();
        }


        if (!commands.contains(command)) {
            logger.info("Role {} tried to access {} command", roleName, commandName);
            response.sendRedirect(PagePath.REDIRECT_SIGN_IN);
            return;
        }
        chain.doFilter(servletRequest, servletResponse);
    }
}
