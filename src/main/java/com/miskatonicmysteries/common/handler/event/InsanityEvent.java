package com.miskatonicmysteries.common.handler.event;

import com.miskatonicmysteries.common.capability.sanity.ISanity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.minecraftforge.fml.common.eventhandler.Event;

@Cancelable
public class InsanityEvent extends Event {
    private final EntityPlayer player;
    private final ISanity sanity;
    private final Event upcomingEvent;

    public InsanityEvent(EntityPlayer player, ISanity sanity, Event event) {
        super();
        this.player = player;
        this.sanity = sanity;
        this.upcomingEvent = event;
    }

    public EntityPlayer getPlayer() {
        return player;
    }

    public ISanity getSanity() {
        return sanity;
    }

    public Event getUpcomingEvent() {
        return upcomingEvent;
    }
}
