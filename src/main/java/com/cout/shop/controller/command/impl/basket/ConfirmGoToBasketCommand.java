package com.cout.shop.controller.command.impl.basket;

import com.cout.shop.controller.PagePath;
import com.cout.shop.controller.RequestParameter;
import com.cout.shop.controller.SessionAttribute;
import com.cout.shop.controller.command.Command;
import com.cout.shop.model.dao.ReceiptDao;
import com.cout.shop.model.dao.ReceiptHasProductDao;
import com.cout.shop.model.dao.impl.ReceiptDaoImpl;
import com.cout.shop.model.dao.impl.ReceiptHasProductDaoImpl;
import com.cout.shop.model.entity.Receipt;
import com.cout.shop.model.entity.ReceiptHasProduct;
import com.cout.shop.model.entity.User;
import com.cout.shop.model.service.BasketService;
import com.cout.shop.model.service.impl.BasketServiceImpl;
import com.cout.shop.util.TypeRe;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ConfirmGoToBasketCommand extends Command {
    private static final BasketService basketService = BasketServiceImpl.getInstance();
    private static final ReceiptDao receiptDao = ReceiptDaoImpl.getInstance();
    private static final ReceiptHasProductDao receiptHasProductDao = ReceiptHasProductDaoImpl.getInstance();


    public ConfirmGoToBasketCommand() {
        super.commandName = "CONFIRM_GO_TO_BASKET";
    }
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        HttpSession session = request.getSession();
        int productId = Integer.parseInt(request.getParameter(RequestParameter.ID));
        int productCount = Integer.parseInt(request.getParameter(RequestParameter.COUNT));
        int productPrice = Integer.parseInt(request.getParameter(RequestParameter.PRICE));
        User user = (User) session.getAttribute(SessionAttribute.CURRENT_USER);

        try {
            boolean isAdd = basketService.addProductToBasket(productId, productCount, productPrice, user);
            if (isAdd) {
                List<Receipt> receiptList = receiptDao.getAllReceipts();
                List<ReceiptHasProduct> receiptHasProductList = receiptHasProductDao.getAllProductsInReceipt();
                session.setAttribute(SessionAttribute.RECEIPT_LIST, receiptList);
                session.setAttribute(SessionAttribute.RECEIPT_HAS_PRODUCT_LIST, receiptHasProductList);
                page = PagePath.TO_BASKET_PAGE;
            } else {
                page = PagePath.ERROR404;
                //request.setAttribute(RequestAttribute.SIGN_UP_ERROR, "signup.incorrectSignup");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return page;
    }
}
