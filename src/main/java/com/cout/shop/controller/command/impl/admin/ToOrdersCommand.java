package com.cout.shop.controller.command.impl.admin;

import com.cout.shop.controller.PagePath;
import com.cout.shop.controller.SessionAttribute;
import com.cout.shop.controller.command.Command;
import com.cout.shop.model.dao.ReceiptDao;
import com.cout.shop.model.dao.impl.ReceiptDaoImpl;
import com.cout.shop.model.entity.Receipt;
import com.cout.shop.model.entity.ReceiptHasProduct;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ToOrdersCommand extends Command {
    private static final ReceiptDao receiptDao = ReceiptDaoImpl.getInstance();
    public ToOrdersCommand() {
        super.commandName = "TO_ORDERS";
    }
    @Override
    public String execute(HttpServletRequest request) {

            String page = null;
            HttpSession session = request.getSession();

            try {
                List<Receipt> receiptList = receiptDao.getAllReceipts();
                session.setAttribute(SessionAttribute.RECEIPT_LIST, receiptList);
                page = PagePath.TO_ORDERS_PAGE;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return page;
    }
}
