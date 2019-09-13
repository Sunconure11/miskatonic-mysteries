package com.miskatonicmysteries.common.commands;

import com.miskatonicmysteries.common.capability.Sanity;
import net.minecraft.command.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import org.apache.commons.lang3.ArrayUtils;
import scala.actors.threadpool.Arrays;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CommandMiskatonicMysteries extends CommandBase{
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
            return getListOfStringsMatchingLastWord(args, new String[] {"setSanity"});
        } else if (args.length == 3){
            return args[1].equalsIgnoreCase("setSanity") ? Arrays.asList(new String[]{"150"}) : Arrays.asList(new String[]{"false"});
        }
        return super.getTabCompletions(server, sender, args, targetPos);
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "commands.gamemode.usage";//"miskatonicmysteries <player> <setSanity> <amount>";
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

        if (mode.equalsIgnoreCase("setSanity")){
              int amount = Integer.valueOf(value);
            System.out.println(Sanity.Util.getSanity(player));
            if (Sanity.Util.setSanity(amount, player))
                notifyCommandListener(sender, this, 1, "commands.miskmyst.sanity.success", player.getDisplayName(), value);
            else
                notifyCommandListener(sender, this, 1, "commands.miskmyst.sanity.fail_badnum", value);
        }else{
            throw new WrongUsageException("command.miskmyst.no_command", args.length == 2 ? args[0] : args[1]);
        }
    }
}
