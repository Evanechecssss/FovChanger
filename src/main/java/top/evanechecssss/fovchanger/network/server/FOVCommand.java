package top.evanechecssss.fovchanger.network.server;

import com.google.common.collect.Lists;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import top.evanechecssss.fovchanger.Fovchanger;
import top.evanechecssss.fovchanger.network.common.FOVMessage;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * \* User: Evanechecssss
 * \* https://evanechecssss.github.io
 * \
 */
public class FOVCommand extends CommandBase {

    public FOVCommand() {
        super();
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    private final List<String> aliases = Lists.newArrayList("fov", "f");

    @Override
    public List<String> getAliases() {
        return aliases;
    }


    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos pos) {
        List<String> first = new ArrayList<String>() {
        };
        first.add("1.0");
        first.add("reset");
        if (args.length == 1) {
            return first;
        } else if (args.length == 2) {
            return getListOfStringsMatchingLastWord(args, server.getOnlinePlayerNames());
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public String getName() {
        return "fov";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "fov.usage";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        int length = args.length;
        if (length<=0)
        {
            sender.sendMessage(new TextComponentTranslation(getUsage(sender)));
            return;
        }
        String param = args[0];
        if (length == 1)
        {
            if (param.equals("reset")){
                if (sender instanceof EntityPlayerMP) {
                    Fovchanger.NETWORK.sendTo(new FOVMessage(0,true), (EntityPlayerMP) sender);
                }else {
                    sender.sendMessage(new TextComponentTranslation("fov.player"));
                }
                return;
            }

            try
            {
               float type = getValidValue(param);
               if (sender instanceof EntityPlayerMP) {
                   Fovchanger.NETWORK.sendTo(new FOVMessage(type,false), (EntityPlayerMP) sender);
               }else {
                   sender.sendMessage(new TextComponentTranslation("fov.player"));
               }
            }catch (CommandException e)
            {
                sender.sendMessage(new TextComponentTranslation("fov.exc"));
                Fovchanger.logger.error(e.getMessage());
            }

        }else if (length==2){
            if (param.equals("reset")) {
                Entity entity = getEntity(server, sender, args[1]);
                if (entity instanceof EntityPlayerMP){
                    Fovchanger.NETWORK.sendTo(new FOVMessage(0,true), (EntityPlayerMP) entity);
                }else {
                    sender.sendMessage(new TextComponentTranslation("fov.selector"));
                }
                return;
            }
            try
            {
                float type = getValidValue(param);
                Entity entity = getEntity(server, sender, args[1]);
                if (entity instanceof EntityPlayerMP){
                    Fovchanger.NETWORK.sendTo(new FOVMessage(type,false), (EntityPlayerMP) entity);
                }else {
                    sender.sendMessage(new TextComponentTranslation("fov.selector"));
                }

            }catch (CommandException e)
            {
                sender.sendMessage(new TextComponentTranslation("fov.exc"));
                Fovchanger.logger.error(e.getMessage());
            }
        }else {
            sender.sendMessage(new TextComponentTranslation(getUsage(sender)));
        }
    }

    private float getValidValue(String param) throws CommandException
    {
        try {
           float n =  Float.parseFloat(param);
           if (n>400){
               return 400;
           }else if (n<-400){
               return -400;
           }else {
               return n;
           }
        }catch (NumberFormatException e) {
            throw new CommandException("fov.exc", new Object[1]);
        }
    }
}