package top.evanechecssss.fovchanger.network.client;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * \* User: Evanechecssss
 * \* https://evanechecssss.github.io
 * \
 */
@SideOnly(Side.CLIENT)
public class EventHandler {

   @SubscribeEvent(priority = EventPriority.LOWEST)
   public void fovModeEvent(EntityViewRenderEvent.FOVModifier event){
       
       if (ClientProxy.use)
       {
           event.setFOV( ClientProxy.zoom);
           Minecraft mc = Minecraft.getMinecraft();
           mc.renderGlobal.setDisplayListEntitiesDirty();
           mc.entityRenderer.loadEntityShader(mc.getRenderViewEntity());
       }
       else {
           event.setFOV(Minecraft.getMinecraft().gameSettings.fovSetting);
           Minecraft mc = Minecraft.getMinecraft();
           mc.renderGlobal.setDisplayListEntitiesDirty();
           mc.entityRenderer.loadEntityShader(mc.getRenderViewEntity());
       }
   }
   
}