package com.jump2digital.skins.services;

import com.jump2digital.skins.persistence.entities.SkinEntity;
import com.jump2digital.skins.persistence.entities.UserEntity;
import org.springframework.stereotype.Service;

@Service
public class ShopServiceImpl implements ShopService {

    private final UserService userService;

    private final SkinService skinService;

    public ShopServiceImpl(UserService userService, SkinService skinService) {
        this.userService = userService;
        this.skinService = skinService;
    }

    @Override
    public String purchaseSkin(Long userId, Long skinId) throws IllegalArgumentException{

        UserEntity user = userService.getUser(userId);
        SkinEntity skin = skinService.getSkin(skinId);

        if (!skin.isAvailable()){
            throw new IllegalArgumentException("La skin no esta disponible");
        }
        if(user.getAmount()<skin.getPrice()){
            throw new IllegalArgumentException("Saldo insuficiente para comprar la skin");
        }

        performPurchase(user,skin);
        return "Compra realizada con exito";

    }

    @Override
    public SkinEntity changeSkinColor(Long userId, Long skinId, String color) {
        SkinEntity skin = skinService.getUserSkin(userId, skinId);
        return skinService.updateSkinColor(skin, color);

    }

    private void performPurchase(UserEntity user, SkinEntity skin) {
        user.setAmount(user.getAmount()-skin.getPrice());
        skin.setAvailable(false);
        skin.setOwner(user);
        userService.updateUser(user);
        skinService.updateSkin(skin);

    }
}
