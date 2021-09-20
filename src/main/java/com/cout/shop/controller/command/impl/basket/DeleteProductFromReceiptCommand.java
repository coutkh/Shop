package com.cout.shop.controller.command.impl.basket;

import com.cout.shop.controller.PagePath;
import com.cout.shop.controller.RequestParameter;
import com.cout.shop.controller.command.Command;
import com.cout.shop.model.dao.ReceiptHasProductDao;
import com.cout.shop.model.dao.impl.ReceiptHasProductDaoImpl;
import com.cout.shop.model.service.BasketService;
import com.cout.shop.model.service.impl.BasketServiceImpl;
import com.cout.shop.util.TypeRe;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class DeleteProductFromReceiptCommand extends Command {
    //private static final Logger logger = LogManager.getLogger();
    private static final ReceiptHasProductDao receiptHasProd = ReceiptHasProductDaoImpl.getInstance();
    private static final BasketService basketService = BasketServiceImpl.getInstance();
    @Override
    public String execute(HttpServletRequest request) {
        String page;
        int id = Integer.parseInt(request.getParameter(RequestParameter.RECEIPT_HAS_PROD_ID));

        try {
            basketService.deleteProductFromBasket(receiptHasProd.getProductFromReceipt(id));
            page = TypeRe.redirect(PagePath.REDIRECT_TO_BASKET_PAGE);
        } catch (SQLException e) {
            page = PagePath.ERROR404;
            e.printStackTrace();
        }
        return page;
    }
}
