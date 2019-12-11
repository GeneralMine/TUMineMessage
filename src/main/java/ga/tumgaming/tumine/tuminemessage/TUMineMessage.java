package ga.tumgaming.tumine.tuminemessage;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

import ga.tumgaming.tumine.tuminemessage.listeners.BlockBreakListener;
import ga.tumgaming.tumine.tuminemessage.listeners.ClickListener;
import ga.tumgaming.tumine.tuminemessage.listeners.JoinListener;
import ga.tumgaming.tumine.tuminemessage.listeners.MessagesCommand;
import ga.tumgaming.tumine.tuminemessage.util.*;

public class TUMineMessage extends JavaPlugin {

	private static Config config;
	private static Plugin plugin;

	@Override
	public void onEnable() {
		TUMineMessage.plugin = this;

		config = new Config(plugin, "skills");
		
	    // Register our command (set an instance of your command class as executor)
	    this.getCommand("messages").setExecutor(new MessagesCommand());
		
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

		pluginManager.registerEvents(new JoinListener(), plugin);
		pluginManager.registerEvents(new ClickListener(), plugin);
		pluginManager.registerEvents(new BlockBreakListener(), plugin);
	}

}
