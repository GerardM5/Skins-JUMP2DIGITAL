package com.jump2digital.skins.services;

import com.jump2digital.skins.persistence.entities.SkinEntity;

import java.util.List;

public interface SkinService {

    List<SkinEntity> getAvailableSkins();

    String deleteSkin(Long userId, Long skinId);

    List<SkinEntity> getUserSkins(Long userId);

    SkinEntity getSkin(Long id);

    void updateSkin(SkinEntity skin);

    SkinEntity updateSkinColor(SkinEntity skin, String color);

    SkinEntity getUserSkin(Long userId, Long skinId);

    List<SkinEntity> importSkins(List<SkinEntity> skins);
}
