package com.jump2digital.skins.services;

import com.jump2digital.skins.persistence.entities.SkinEntity;

public interface ShopService {
    String purchaseSkin(Long userId, Long skinId) ;
    SkinEntity changeSkinColor(Long userId, Long skinId, String color);
}
