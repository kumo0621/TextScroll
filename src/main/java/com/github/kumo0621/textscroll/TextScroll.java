package com.github.kumo0621.textscroll;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Team;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public final class TextScroll extends JavaPlugin implements org.bukkit.event.Listener {


    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    Map<Team, ArmorStand> map = new HashMap<>();
    @EventHandler
    public void onPlayerchat(AsyncPlayerChatEvent e) {
        Bukkit.getScheduler().runTask(this, () -> {
            String chat;
            chat = e.getMessage();
            Collection<? extends Player> onlinePlayers = Bukkit.getOnlinePlayers();
            for (Player onlinePlayer : onlinePlayers) {
                Location location = onlinePlayer.getLocation();
                Team team = onlinePlayer.getScoreboard().getEntryTeam(onlinePlayer.getName());
                if (team != null) {
                    if (!map.containsKey(team)) {
                        @NotNull ArmorStand entity = location.getWorld().spawn(location, ArmorStand.class);
                        entity.setGravity(false);
                        entity.setInvisible(true);
                        entity.setInvulnerable(true);
                        entity.setCustomNameVisible(true);
                        entity.setCustomName(chat);
                    }
                }
            }
        });
    }
}