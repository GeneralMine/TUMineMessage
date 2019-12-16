package ga.tumgaming.tumine.tuminemessage.listeners;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ga.tumgaming.tumine.tuminemessage.TUMineMessage;
import net.md_5.bungee.api.ChatColor;

public class CommandKit implements CommandExecutor {

	private Messages messages;

	public CommandKit(Messages msg) {
		messages = msg;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (command.getName().equalsIgnoreCase("tumsg")) {
				if (args.length > 0) {
					if (args[0].equals("send")) {
						if (player.hasPermission("tumsg.send")) {
							if (args.length > 2) {
								String msg = args[2];
								for (int i = 3; i < args.length; i++) {
									msg = msg + " " + args[i];
								}
								player.sendMessage(TUMineMessage.getPrefix() + messages.addMessage(args[1], msg));
								return true;
							}
						}else {
							player.sendMessage(TUMineMessage.getPrefix() +ChatColor.RED +  "You dont have permission!");
						}
					} else if (args[0].equalsIgnoreCase("deleteall")) {
						player.sendMessage(TUMineMessage.getPrefix() + messages.deleteAll(player.getName()));
						return true;
					} else if (args[0].equalsIgnoreCase("delete")) {
						if (args.length == 2) {
							try {
								int index = Integer.parseInt(args[1]);
								player.sendMessage(TUMineMessage.getPrefix() + messages.deleteMessage(player.getName(), index));
								return true;
							} catch (NumberFormatException e) {
								player.sendMessage(TUMineMessage.getPrefix() +ChatColor.RED + "Please type in a number");
								return true;
							}
						}
						player.sendMessage(TUMineMessage.getPrefix() +ChatColor.RED + "Please type in a number!");
						return true;
					} else if (args[0].equalsIgnoreCase("read")) {
						if (args.length == 2) {
							try {
								int index = Integer.parseInt(args[1]);
								player.sendMessage(TUMineMessage.getPrefix() + messages.getMessage(player.getName(), index));
								return true;
							} catch (NumberFormatException e) {
								player.sendMessage(TUMineMessage.getPrefix() +ChatColor.RED + "Please type in a number");
								return true;
							}
						}
					} else if (args[0].equalsIgnoreCase("list")) {
						player.sendMessage(TUMineMessage.getPrefix() + "You have " + messages.getMessagesCount(player.getName()) + " messages!");
						return true;
					}
				}
			}
		}
		return false;
	}
}