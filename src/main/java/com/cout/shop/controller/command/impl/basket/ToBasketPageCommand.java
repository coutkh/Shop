package com.cout.shop.controller.command.impl.basket;

import com.cout.shop.controller.PagePath;
import com.cout.shop.controller.SessionAttribute;
import com.cout.shop.controller.command.Command;
import com.cout.shop.model.dao.ReceiptDao;
import com.cout.shop.model.dao.ReceiptHasProductDao;
import com.cout.shop.model.dao.impl.ReceiptDaoImpl;
import com.cout.shop.model.dao.impl.ReceiptHasProductDaoImpl;
import com.cout.shop.model.entity.Receipt;
import com.cout.shop.model.entity.ReceiptHasProduct;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ToBasketPageCommand extends Command {
    private static final ReceiptDao receiptDao = ReceiptDaoImpl.getInstance();
    private static final ReceiptHasProductDao receiptHasProductDao = ReceiptHasProductDaoImpl.getInstance();


    public ToBasketPageCommand() {
        super.commandName = "TO_BASKET_PAGE";
    }
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        HttpSession session = request.getSession();

        try {
                List<Receipt> receiptList = receiptDao.getAllReceipts();
                List<ReceiptHasProduct> receiptHasProductList = receiptHasProductDao.getAllProductsInReceipt();
                session.setAttribute(SessionAttribute.RECEIPT_LIST, receiptList);
                session.setAttribute(SessionAttribute.RECEIPT_HAS_PRODUCT_LIST, receiptHasProductList);
                page = PagePath.TO_BASKET_PAGE;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return page;
    }
}
