package com.cout.shop.controller.command.impl.users;

import com.cout.shop.controller.command.Command;

import javax.servlet.http.HttpServletRequest;

public class SignUpCommand extends Command {
    public SignUpCommand() {
        super.commandName = "SIGN_UP";
    }
    @Override
    public String execute(HttpServletRequest request) {
        return null;
    }
}
