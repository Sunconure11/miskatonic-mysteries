package com.miskatonicmysteries.common.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.CommandGameMode;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;

import java.util.ArrayList;
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

    @Override
    public String getUsage(ICommandSender sender) {
        return "miskatonicmysteries <player> <setSanity> <amount>";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        String playerUUID;
        String mode;
        String value;
        EntityPlayer player;
        if (args.length == 2){
            player = getCommandSenderAsPlayer(sender);
        }else if (args.length >= 3){

        }
    }
}
