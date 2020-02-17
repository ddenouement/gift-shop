package com.giftshop.services;

import com.giftshop.models.Product;
import com.giftshop.repository.WishListDAO;
import com.giftshop.repository.interfaces.IWishListDAO;
import com.giftshop.services.interfaces.IWishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class WishListService implements IWishListService {

    private IWishListDAO wishListDAO;

    @Autowired
    WishListService(final WishListDAO wishListDAO) {
        this.wishListDAO = wishListDAO;
    }

    @Override
    public ArrayList<Product> getAllWishedProducts(String userId) {
        return wishListDAO.getAllWishedProducts(userId);
    }

    @Override
    public void addProductToUsersWishList(String productId, String userId) {
        wishListDAO.addProductToUsersWishList(productId, userId);
    }

    @Override
    public boolean removeProductFromUsersWishList(String productId, String userId) {
        return wishListDAO.removeProductFromUsersWishList(productId, userId);
    }


}

