package top.evanechecssss.fovchanger;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import org.apache.logging.log4j.Logger;
import top.evanechecssss.fovchanger.network.common.CommonPoxy;

@Mod(
        modid = Fovchanger.MOD_ID,
        name = Fovchanger.MOD_NAME,
        version = Fovchanger.VERSION
)
public class Fovchanger {

    public static final String MOD_ID = "fov_changer";
    public static final String MOD_NAME = "fovchanger";
    public static final String VERSION = "1.0";

    public static SimpleNetworkWrapper NETWORK = NetworkRegistry.INSTANCE.newSimpleChannel("fovchanger");

    @SidedProxy(serverSide = "top.evanechecssss.fovchanger.network.common.CommonPoxy",clientSide = "top.evanechecssss.fovchanger.network.client.ClientProxy")
    public static CommonPoxy proxy;

    public static Logger logger;

    @EventHandler
    public void preinit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
        logger = event.getModLog();

    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @EventHandler
    public void server(FMLServerStartingEvent event) {
        proxy.server(event);
    }
}
