package com.jump2digital.skins.services;

import com.jump2digital.skins.persistence.entities.SkinEntity;
import com.jump2digital.skins.persistence.entities.UserEntity;
import com.jump2digital.skins.persistence.repositories.SkinRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class SkinServiceImpl implements SkinService {


    private final SkinRepository skinRepository;

    private final UserService userService;

    public SkinServiceImpl(SkinRepository skinRepository, UserService userService) {
        this.skinRepository = skinRepository;
        this.userService = userService;
    }

    @Override
    public List<SkinEntity> getAvailableSkins() {
        return skinRepository.findByAvailableIsTrue();
    }


    @Override
    public String deleteSkin(Long userId, Long skinId) {
        SkinEntity skin = getUserSkin(userId, skinId);
        skin.setOwner(null);
        skin.setAvailable(true);
        skinRepository.save(skin);
        return "Skin eliminada con exito ;)";
    }

    @Override
    public SkinEntity getUserSkin(Long userId, Long skinId) {
        List<SkinEntity> userSkins = getUserSkins(userId);
        for (SkinEntity skin:userSkins) {
            if (skin.getId().equals(skinId)){
                return skin;
            }
        }
        throw new NoSuchElementException("El usuario "+ userId + " no tiene esa skin");
    }

    @Override
    public List<SkinEntity> getUserSkins(Long userId) {
        UserEntity user = userService.getUser(userId);
        return skinRepository.findByOwner(user);

    }

    @Override
    public SkinEntity getSkin(Long id) {
        Optional<SkinEntity> skinEntityOptional = skinRepository.findById(id);
        if (skinEntityOptional.isPresent()){
            return skinEntityOptional.get();
        }else throw new NoSuchElementException("No se encontró la skin con el ID: " + id);

    }

    @Override
    public void updateSkin(SkinEntity skin) {
        skinRepository.save(skin);
    }

    @Override
    public SkinEntity updateSkinColor(SkinEntity skin, String color) {
        skin.setColor(color);
        return skinRepository.save(skin);
    }

    @Override
    public List<SkinEntity> importSkins(List<SkinEntity> skins) {
        return skinRepository.saveAll(skins);
    }
}
