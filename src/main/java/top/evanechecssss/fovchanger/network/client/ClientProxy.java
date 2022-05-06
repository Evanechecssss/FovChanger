package top.evanechecssss.fovchanger.network.client;

import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.relauncher.Side;
import top.evanechecssss.fovchanger.network.common.CommonPoxy;

/**
 * \* User: Evanechecssss
 * \* https://evanechecssss.github.io
 * \
 */

public class ClientProxy extends CommonPoxy {

    public static float zoom;
    public static boolean use;
    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
        MinecraftForge.EVENT_BUS.register(new EventHandler());
    }

    @Override
    public void postinit(FMLPostInitializationEvent event) {
        super.postinit(event);
    }

    @Override
    public void server(FMLServerStartingEvent event) {
        super.server(event);
    }
}