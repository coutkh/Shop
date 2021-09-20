package com.cout.shop.controller.command.impl.basket;

import com.cout.shop.controller.PagePath;
import com.cout.shop.controller.RequestParameter;
import com.cout.shop.controller.command.Command;
import com.cout.shop.model.service.BasketService;
import com.cout.shop.model.service.impl.BasketServiceImpl;
import com.cout.shop.util.TypeRe;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class DeleteReceiptCommand extends Command {
    private static final BasketService basketService = BasketServiceImpl.getInstance();
    @Override
    public String execute(HttpServletRequest request) {
        String page;
        int id = Integer.parseInt(request.getParameter(RequestParameter.ID));
        try {
            basketService.deleteReceipt(id);
            page = TypeRe.redirect(PagePath.REDIRECT_TO_BASKET_PAGE);
        } catch (SQLException e) {
            page = PagePath.ERROR404;
            e.printStackTrace();
        }
        return page;
    }
}
