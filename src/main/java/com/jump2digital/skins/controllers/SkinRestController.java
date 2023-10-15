package com.jump2digital.skins.controllers;

import com.jump2digital.skins.persistence.entities.SkinEntity;
import com.jump2digital.skins.services.ShopService;
import com.jump2digital.skins.services.SkinService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/skins")
public class SkinRestController {

    private final SkinService skinService;

    private final ShopService shopService;


    public SkinRestController(SkinService skinService, ShopService shopService) {
        this.skinService = skinService;
        this.shopService = shopService;
    }

    @GetMapping("/available")
    public ResponseEntity<?> getAvailableSkins() {
        return ResponseEntity.ok(skinService.getAvailableSkins());
    }


    @PostMapping("/buy")
    public ResponseEntity<?> buySkin(@RequestParam Long userId,
                                     @RequestParam Long skinId) {

        try {
            return ResponseEntity.ok(shopService.purchaseSkin(userId, skinId));
        } catch (NoSuchElementException | IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    @GetMapping("/myskins")
    public ResponseEntity<?> getUserSkins(@RequestParam Long userId) {
        try {
            return ResponseEntity.ok(skinService.getUserSkins(userId));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }

    }

    @PutMapping("/color")
    public ResponseEntity<?> changeColor(@RequestParam Long userId,
                                         @RequestParam Long skinId,
                                         @RequestParam String color) {
        try {
            return ResponseEntity.ok(shopService.changeSkinColor(userId, skinId, color));
        } catch (NoSuchElementException | IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{skinId}")
    public ResponseEntity<?> deleteSkin(@PathVariable Long skinId,
                                        @RequestParam Long userId) {
        try {
            return ResponseEntity.ok(skinService.deleteSkin(userId, skinId));
        } catch (NoSuchElementException | IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }

    }

    @GetMapping("/getskin/{id}")
    public ResponseEntity<?> getSkin(@PathVariable Long id) {
        return ResponseEntity.ok(skinService.getSkin(id));
    }

    @PostMapping("/import")
    public ResponseEntity<?> importSkins(@RequestBody List<SkinEntity> skins) {
        return ResponseEntity.ok(skinService.importSkins(skins));
    }

}
