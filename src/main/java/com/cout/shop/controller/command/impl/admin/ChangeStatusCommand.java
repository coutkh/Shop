package com.cout.shop.controller.command.impl.admin;

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

public class ChangeStatusCommand extends Command {
    private static final ReceiptDao receiptDao = ReceiptDaoImpl.getInstance();
    @Override
    public String execute(HttpServletRequest request) {
        String page;
        int receiptId = Integer.parseInt(request.getParameter(RequestParameter.RECEIPT_ID));
        int statusId = Integer.parseInt(request.getParameter(RequestParameter.STATUS_ID));
        try {
            Receipt receipt = receiptDao.getReceipt(receiptId);
            boolean close = 3 != receipt.getStatus().getId();
            if(close && 1 == statusId){
                receipt.setStatus(ReceiptStatus.valueOf("open".toUpperCase()));
            } else if (close && 2 == statusId){
                receipt.setStatus(ReceiptStatus.valueOf("in_the_process".toUpperCase()));
            }else if (close && 3 == statusId){
                receipt.setStatus(ReceiptStatus.valueOf("closed".toUpperCase()));
            }else if (close && 4 == statusId){
                receipt.setStatus(ReceiptStatus.valueOf("canceled".toUpperCase()));
            }
            receiptDao.updateReceipt(receipt);
            page = TypeRe.redirect(PagePath.REDIRECT_TO_ORDERS_PAGE);
        } catch (DaoException e) {
            page = PagePath.ERROR404;
            e.printStackTrace();
        }
        return page;
    }
}
