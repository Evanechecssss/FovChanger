package top.evanechecssss.fovchanger.network.common;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import javax.annotation.Nullable;

/**
 * \* User: Evanechecssss
 * \* https://evanechecssss.github.io
 * \
 */
public class TestItem extends Item {
    
    @Override
    public EntityEquipmentSlot getEquipmentSlot (ItemStack stack) {
        return EntityEquipmentSlot.LEGS;
    }
    
}