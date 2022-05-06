package top.evanechecssss.fovchanger.network.client;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import scala.collection.parallel.ParIterableLike;
import top.evanechecssss.fovchanger.network.common.FOVMessage;

/**
 * \* User: Evanechecssss
 * \* https://evanechecssss.github.io
 * \
 */
public class FOVChangeHandler implements IMessageHandler<FOVMessage, IMessage>
{

    @SideOnly(Side.CLIENT)
    public void changeFOV(float type, boolean reset)
    {
        if (!reset)
        {
            ClientProxy.use = true;
            ClientProxy.zoom = type;
        }else {
            ClientProxy.use = false;
            ClientProxy.zoom = Minecraft.getMinecraft().gameSettings.fovSetting;
        }
    }

    @Override
    public IMessage onMessage(FOVMessage message, MessageContext ctx)
    {
        if (ctx.side==Side.CLIENT)
        {
            Minecraft.getMinecraft().addScheduledTask(() -> {
                changeFOV(message.getType(),message.reset);
            });
        }
        return null;
    }
}