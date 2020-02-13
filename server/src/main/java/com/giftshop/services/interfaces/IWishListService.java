package com.giftshop.services.interfaces;

import com.giftshop.models.Product;

import java.util.ArrayList;

public interface IWishListService {

    ArrayList<Product> getAllWishedProducts(String userId);

    void addProductToUsersWishList(String productId, String userId);

    boolean removeProductFromUsersWishList(String productId, String userId);


}
