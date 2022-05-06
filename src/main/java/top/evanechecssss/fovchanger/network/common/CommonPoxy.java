package top.evanechecssss.fovchanger.network.common;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.relauncher.Side;
import top.evanechecssss.fovchanger.Fovchanger;
import top.evanechecssss.fovchanger.network.client.FOVChangeHandler;
import top.evanechecssss.fovchanger.network.server.FOVCommand;

/**
 * \* User: Evanechecssss
 * \* https://evanechecssss.github.io
 * \
 */

public class CommonPoxy {

    /*un used*/
    public void postinit(FMLPostInitializationEvent event) {

    }

    public void preInit(FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
        Fovchanger.NETWORK.registerMessage(FOVChangeHandler.class, FOVMessage.class, 0, Side.CLIENT);
    }

    public void init(FMLInitializationEvent event) {
    }

    public void server(FMLServerStartingEvent event) {
        event.registerServerCommand(new FOVCommand());
    }
}