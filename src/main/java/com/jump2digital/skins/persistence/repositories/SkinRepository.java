package com.jump2digital.skins.persistence.repositories;

import com.jump2digital.skins.persistence.entities.SkinEntity;
import com.jump2digital.skins.persistence.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkinRepository extends JpaRepository<SkinEntity, Long> {
    List<SkinEntity> findByAvailableIsTrue();

    List<SkinEntity> findByOwner(UserEntity user);
}
