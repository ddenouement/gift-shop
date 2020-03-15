package com.giftshop.controllers;

import com.giftshop.config.TokenProvider;
import com.giftshop.models.Product;
import com.giftshop.services.WishListService;
import com.giftshop.services.interfaces.IWishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
@CrossOrigin
public class WishListController {

    private IWishListService wishListService;
    private TokenProvider jwtTokenProvider;


    @Autowired
    WishListController(
            final WishListService wishListService,
            final TokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.wishListService = wishListService;
    }

    @GetMapping("user/wishlist/{userId}")
    public Iterable<Product> getUsersWishList(@PathVariable String userId) {
        return wishListService.getAllWishedProducts(userId);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @GetMapping("user/wishlist/")
    public Iterable<Product> getCurrentUsersWishlist(@CookieValue("token") String token)
    {  String my_id = String.valueOf(jwtTokenProvider.getUserId(token));
        return wishListService.getAllWishedProducts(my_id);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @PutMapping("user/wishlist/{productId}")
    @ResponseStatus(value = HttpStatus.OK)
    public void addProduct(@CookieValue("token") String token, @PathVariable String productId) {
        String my_id = String.valueOf(jwtTokenProvider.getUserId(token));
        wishListService.addProductToUsersWishList(productId, my_id);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @DeleteMapping("user/wishlist/{productId}")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteProduct(@CookieValue("token") String token, @PathVariable String productId) {
        String my_id = String.valueOf(jwtTokenProvider.getUserId(token));
        boolean res = wishListService.removeProductFromUsersWishList(productId, my_id);
    }

}
