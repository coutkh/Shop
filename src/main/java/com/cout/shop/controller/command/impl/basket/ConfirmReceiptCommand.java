package com.cout.shop.controller.command.impl.basket;

import com.cout.shop.controller.PagePath;
import com.cout.shop.controller.RequestParameter;
import com.cout.shop.controller.command.Command;
import com.cout.shop.model.dao.DaoException;
import com.cout.shop.model.dao.ReceiptDao;
import com.cout.shop.model.dao.impl.ReceiptDaoImpl;
import com.cout.shop.model.entity.Receipt;
import com.cout.shop.model.entity.ReceiptStatus;
import com.cout.shop.util.TypeRe;

import javax.servlet.http.HttpServletRequest;

public class ConfirmReceiptCommand extends Command {
    private static final ReceiptDao receiptDao = ReceiptDaoImpl.getInstance();
    @Override
    public String execute(HttpServletRequest request) {
        String page;
        int id = Integer.parseInt(request.getParameter(RequestParameter.ID));
        try {
            Receipt receipt = receiptDao.getReceipt(id);
            if(1 == receipt.getStatus().getId()){
                receipt.setStatus(ReceiptStatus.valueOf("in_the_process".toUpperCase()));
                receiptDao.updateReceipt(receipt);
            }
            page = TypeRe.redirect(PagePath.REDIRECT_TO_BASKET_PAGE);
        } catch (DaoException e) {
            page = PagePath.ERROR404;
            e.printStackTrace();
        }
        return page;
    }
}
