package top.evanechecssss.fovchanger.network.common;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

/**
 * \* User: Evanechecssss
 * \* https://evanechecssss.github.io
 * \
 */
public class FOVMessage implements IMessage {

    private float type;
    public boolean reset;
    public FOVMessage()
    {
    }

    public FOVMessage(float type, boolean reset)
    {
        setType(type);
        this.reset = reset;
    }

    private void setType(float type)
    {
        this.type = type;
    }

    public float getType()
    {
        return this.type;
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        this.type = buf.readFloat();
        this.reset = buf.readBoolean();
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeFloat(this.type);
        buf.writeBoolean(reset);
    }
}