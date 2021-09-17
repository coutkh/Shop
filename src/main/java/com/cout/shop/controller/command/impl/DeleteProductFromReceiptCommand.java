package com.cout.shop.controller.command.impl;

import com.cout.shop.controller.PagePath;
import com.cout.shop.controller.RequestParameter;
import com.cout.shop.controller.command.Command;
import com.cout.shop.model.dao.ReceiptHasProductDao;
import com.cout.shop.model.dao.impl.ReceiptHasProductDaoImpl;
import com.cout.shop.util.TypeRe;
import javax.servlet.http.HttpServletRequest;

public class DeleteProductFromReceiptCommand extends Command {
    //private static final Logger logger = LogManager.getLogger();
    private static final ReceiptHasProductDao receiptHasProd = ReceiptHasProductDaoImpl.getInstance();
    @Override
    public String execute(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter(RequestParameter.RECEIPT_HAS_PROD_ID));
        receiptHasProd.deleteProductFromReceipt(id);
        return TypeRe.redirect(PagePath.REDIRECT_TO_BASKET_PAGE);
    }
}
