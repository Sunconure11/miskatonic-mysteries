package com.miskatonicmysteries.common.commands;

import com.miskatonicmysteries.common.capability.blessing.BlessingCapability;
import com.miskatonicmysteries.common.capability.blessing.blessings.Blessing;
import com.miskatonicmysteries.common.capability.sanity.Sanity;
import net.minecraft.command.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import scala.actors.threadpool.Arrays;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class CommandMiskatonicMysteries extends CommandBase{
    public static final String COMMAND_SANITY = "setSanity";
    public static final String COMMAND_BLESSING = "setBlessing";
    @Override
    public String getName() {
        return "miskatonicmysteries";
    }

    @Override
    public List<String> getAliases() {
        List<String> aliases = new ArrayList<>();
        aliases.add("miskmyst");
        return aliases;
    }

    public int getRequiredPermissionLevel()
    {
        return 2;
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
        if (args.length == 1){
            return getListOfStringsMatchingLastWord(args, server.getOnlinePlayerNames());
        } else if (args.length == 2){
            return getListOfStringsMatchingLastWord(args, new String[] {COMMAND_SANITY, COMMAND_BLESSING});
        } else if (args.length == 3){
            return args[1].equalsIgnoreCase(COMMAND_SANITY) ? Arrays.asList(new String[]{"150", "50", "1"}) :  args[1].equalsIgnoreCase(COMMAND_BLESSING) ?  getListOfStringsMatchingLastWord(args, Blessing.getBlessings()) : Arrays.asList(new String[]{"false"});
        }
        return super.getTabCompletions(server, sender, args, targetPos);
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "commands.miskmyst.usage";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        String mode;
        String value;
        EntityPlayer player;

        if (args.length >= 3){
            player = server.getEntityWorld().getPlayerEntityByName(args[0]);
            mode = args[1];
            value = args[2];
        }else{
            throw new WrongUsageException(getUsage(sender), new Object[0]); //todo add localization to that later
        }

        if (player == null){
            throw new WrongUsageException("command.miskmyst.player_null", args[0]);
        }

        if (mode.equalsIgnoreCase(COMMAND_SANITY)){
              int amount = Integer.valueOf(value);
            if (Sanity.Util.setSanity(amount, player))
                sendNotification(sender, player, "commands.miskmyst.sanity.success", player.getDisplayName(), value);
            else
                sendNotification(sender, player, "commands.miskmyst.sanity.fail_badnum", value);
        }else if (mode.equalsIgnoreCase(COMMAND_BLESSING)){
            String blessing = value;
            if (BlessingCapability.Util.setBlessing(Blessing.getBlessingWithNull(value), player))
                sendNotification(sender, player, "commands.miskmyst.blessing.success", player.getDisplayName(), Blessing.getBlessing(blessing).getName());
            else
                sendNotification(sender, player, "commands.miskmyst.sanity.fail_badblessing", value);
        }else{
            throw new WrongUsageException(getUsage(sender), args[1]);
        }
    }

    public void sendNotification(ICommandSender sender, EntityPlayer player, String transKey, Object... args){
        if (sender.getEntityWorld().getGameRules().getBoolean("sendCommandFeedback")) {
            player.sendMessage(new TextComponentTranslation(transKey, args));
        }
        notifyCommandListener(sender, this, 1, transKey, player.getDisplayName(), args);
    }
}
