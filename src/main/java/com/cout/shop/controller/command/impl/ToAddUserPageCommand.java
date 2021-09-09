package com.cout.shop.controller.command.impl;

import com.cout.shop.controller.PagePath;
import com.cout.shop.controller.command.Command;
import com.cout.shop.model.entity.UserRole;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ToAddUserPageCommand extends Command {
    public ToAddUserPageCommand() {
        super.commandName = "TO_ADD_USER_PAGE";
    }

    @Override
    public String execute(HttpServletRequest request) {
        return PagePath.TO_ADD_USER_PAGE;
    }
}
