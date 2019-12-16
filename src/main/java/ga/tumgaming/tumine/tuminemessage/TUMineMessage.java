package ga.tumgaming.tumine.tuminemessage;

import java.util.HashMap;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import ga.tumgaming.tumine.tuminemessage.util.Config;
import ga.tumgaming.tumine.tuminemessage.listeners.CommandKit;
import ga.tumgaming.tumine.tuminemessage.listeners.JoinListener;
import ga.tumgaming.tumine.tuminemessage.listeners.Messages;

public class TUMineMessage extends JavaPlugin {
	private static Config player;
	private static Messages messages;
	private static Plugin plugin;
	
	private static HashMap<Player, Location[]> plLoc = new HashMap<>();
	@Override
	public void onEnable() {
		this.plugin = this;
		player = new Config(this, "player");
		messages = new Messages(player);
		getCommand("tumsg").setExecutor(new CommandKit(messages));
		registerEvents();

		log("Plugin erfolgreich geladen");
	}

	/**
	 * logs a String in the console
	 *
	 * @param str logged String
	 */
	public void log(String str) {
		Logger.getLogger(str);
	}

	private static void registerEvents() {
		PluginManager pluginManager = Bukkit.getPluginManager();
		pluginManager.registerEvents(new JoinListener(messages), plugin);

	}

	public static Config getPlayerConfig() {
		return player;
	}

	public static Plugin getPlugin() {
		return plugin;
	}

	
	public static String getPrefix() {
		return new String(ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "TUMine" + ChatColor.DARK_GRAY + "]" + ChatColor.WHITE + " ");
	}

}
