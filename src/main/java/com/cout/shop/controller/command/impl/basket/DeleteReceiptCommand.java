package com.cout.shop.controller.command.impl.basket;

import com.cout.shop.controller.PagePath;
import com.cout.shop.controller.RequestParameter;
import com.cout.shop.controller.SessionAttribute;
import com.cout.shop.controller.command.Command;
import com.cout.shop.model.dao.DaoException;
import com.cout.shop.model.dao.ReceiptDao;
import com.cout.shop.model.dao.impl.ReceiptDaoImpl;
import com.cout.shop.model.entity.Receipt;
import com.cout.shop.model.service.BasketService;
import com.cout.shop.model.service.impl.BasketServiceImpl;
import com.cout.shop.util.TypeRe;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

public class DeleteReceiptCommand extends Command {
    private static final BasketService basketService = BasketServiceImpl.getInstance();
    private static final ReceiptDao receiptDao = ReceiptDaoImpl.getInstance();
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        HttpSession session = request.getSession();
        int id = Integer.parseInt(request.getParameter(RequestParameter.ID));
        String prePage = request.getParameter(RequestParameter.PAGE);
        try {
            Receipt receipt = receiptDao.getReceipt(id);
            if(1 == receipt.getStatus().getId()){
                basketService.deleteReceipt(id);
                if("basket".equals(prePage)){
                    page = TypeRe.redirect(PagePath.REDIRECT_TO_BASKET_PAGE);
                }else if ("orders".equals(prePage)){
                    page = TypeRe.redirect(PagePath.REDIRECT_TO_ORDERS_PAGE);
                }
                // Use error message
            } else {
                if("basket".equals(prePage)){
                    page = TypeRe.redirect(PagePath.REDIRECT_TO_BASKET_PAGE);
                }else if ("orders".equals(prePage)){
                    page = TypeRe.redirect(PagePath.REDIRECT_TO_ORDERS_PAGE);
                }
            }

        } catch (SQLException | DaoException e) {
            page = PagePath.ERROR404;
            e.printStackTrace();
        }
        return page;
    }
}
