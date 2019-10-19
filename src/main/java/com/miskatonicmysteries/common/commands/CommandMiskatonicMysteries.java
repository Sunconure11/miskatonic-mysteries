package com.miskatonicmysteries.common.commands;

import com.miskatonicmysteries.common.capability.blessing.BlessingCapability;
import com.miskatonicmysteries.common.capability.blessing.blessings.Blessing;
import com.miskatonicmysteries.common.capability.sanity.Sanity;
import com.miskatonicmysteries.common.capability.spells.SpellKnowledge;
import com.miskatonicmysteries.common.handler.effects.InsanityEffect;
import com.miskatonicmysteries.common.misc.spells.Spell;
import com.miskatonicmysteries.common.network.PacketHandler;
import com.miskatonicmysteries.common.network.message.event.PacketHandleInsanity;
import com.miskatonicmysteries.registry.ModInsanityEffects;
import net.minecraft.command.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import scala.actors.threadpool.Arrays;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

//should probably make the code here nicer to look at
public class CommandMiskatonicMysteries extends CommandBase{
    public static final String COMMAND_SANITY = "setSanity";
    public static final String COMMAND_BLESSING = "setBlessing";
    public static final String COMMAND_SPELL = "addSpell";
    public static final String COMMAND_RESET_COOLDOWN = "resetSpellCooldowns";
    public static final String COMMAND_RESET_SPELLS = "resetSpells";
    public static final String COMMAND_PLAY_INSANITY_EFFECT = "playInsanityEffect";
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
            return getListOfStringsMatchingLastWord(args, COMMAND_SANITY, COMMAND_BLESSING, COMMAND_SPELL, COMMAND_RESET_COOLDOWN, COMMAND_RESET_SPELLS, COMMAND_PLAY_INSANITY_EFFECT);
        } else if (args.length == 3){
            return args[1].equalsIgnoreCase(COMMAND_SANITY) ? Arrays.asList(new String[]{"150", "50", "1"}) :
                    args[1].equalsIgnoreCase(COMMAND_BLESSING) ? getListOfStringsMatchingLastWord(args, Blessing.getBlessings()) :
                            args[1].equalsIgnoreCase(COMMAND_SPELL) ? getListOfStringsMatchingLastWord(args, Spell.SPELLS.keySet().toArray(new String[Spell.SPELLS.size()])) :
                                    args[1].equalsIgnoreCase(COMMAND_PLAY_INSANITY_EFFECT)? getListOfStringsMatchingLastWord(args, ModInsanityEffects.getInsanityEffectStrings()): Arrays.asList(new String[]{});
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
        String value = "";
        EntityPlayer player;

        if (args.length >= 2){
            player = server.getEntityWorld().getPlayerEntityByName(args[0]);
            mode = args[1];
        }else{
            throw new WrongUsageException(getUsage(sender)); //todo add localization to that later
        }
        if (args.length >= 3)
            value = args[2];

        if (player == null){
            throw new SyntaxErrorException("command.miskmyst.player_null", args[0]);
        }

        if (mode.equalsIgnoreCase(COMMAND_SANITY)){
              int amount = Integer.valueOf(value);
            if (Sanity.Util.setSanity(amount, player))
                sendNotification(sender, player, "commands.miskmyst.sanity.success", player.getDisplayName(), value);
            else
                throw new SyntaxErrorException("commands.miskmyst.sanity.fail_badnum", value);
        }else if (mode.equalsIgnoreCase(COMMAND_BLESSING)){
            String blessing = value;
            if (BlessingCapability.Util.setBlessing(Blessing.getBlessingWithNull(value), player))
                sendNotification(sender, player, "commands.miskmyst.blessing.success", player.getDisplayName(), Blessing.getBlessing(blessing).getName());
            else
                throw new SyntaxErrorException("commands.miskmyst.blessing.fail_badblessing", value);
        }else if (mode.equalsIgnoreCase(COMMAND_SPELL)){
            String spell = value;
            if (Spell.SPELLS.containsKey(spell)) {
                SpellKnowledge.Util.addSpell(Spell.SPELLS.get(spell), player);
                sendNotification(sender, player, "commands.miskmyst.spell.success", value, player.getDisplayName());
            }else
                throw new SyntaxErrorException("commands.miskmyst.spell.fail_no_spell", value);
        }else if (mode.equalsIgnoreCase(COMMAND_RESET_COOLDOWN)){
            if (SpellKnowledge.Util.getKnowledge(player).getSpells().length > 0) {
                for (Spell spell : SpellKnowledge.Util.getKnowledge(player).getSpells()){
                    SpellKnowledge.Util.setCooldownFor(spell,0, player, true);
                }
                sendNotification(sender, player, "commands.miskmyst.reset_cooldown.success", player.getDisplayName(), SpellKnowledge.Util.getKnowledge(player).getSpells().length);
            }else
                throw new SyntaxErrorException("commands.miskmyst.reset_cooldown.fail_no_spells", player.getDisplayName());
        }else if (mode.equalsIgnoreCase(COMMAND_RESET_SPELLS)){
            if (SpellKnowledge.Util.getKnowledge(player).getSpells().length > 0) {
                SpellKnowledge.Util.getKnowledge(player).getSpellCooldowns(true).clear();
                sendNotification(sender, player, "commands.miskmyst.reset_spells.success", player.getDisplayName());
            }else
                throw new SyntaxErrorException("commands.miskmyst.reset_spells.fail_no_spells", player.getDisplayName());
        }else if (mode.equalsIgnoreCase(COMMAND_PLAY_INSANITY_EFFECT)){
            InsanityEffect effect = ModInsanityEffects.INSANITY_EFFECTS.getOrDefault(new ResourceLocation(value), null);
            if (effect != null){
                effect.handle(player.world, player, Sanity.Util.getSanityCapability(player));
                if (!player.world.isRemote){
                    PacketHandler.sendTo(player, new PacketHandleInsanity(effect.getName()));
                }
                sendNotification(sender, player, "commands.miskmyst.play_insanity_effect.success", value, player.getDisplayName());
            }else
                throw new SyntaxErrorException("commands.miskmyst.play_insanity_effect.fail", value);
        }
        else{
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
