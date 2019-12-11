package ga.tumgaming.tumine.tumessage.message;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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
				if (args[0].equals("send")) {
					if (player.hasPermission("tumsg.send")) {

					}
				} else if (args[0].equalsIgnoreCase("deleteall")) {
					player.sendMessage(messages.deleteAll(player.getName()));
				} else if (args[0].equalsIgnoreCase("delete")) {
					if (args.length == 2) {
						try {
							int index = Integer.parseInt(args[1]);
							player.sendMessage(messages.deleteMessage(player.getName(), index));
							return true;
						} catch (NumberFormatException e) {
							player.sendMessage("Please type in a number");
							return true;
						}
					}

					player.sendMessage("Please type in a number!");
				} else if (args[0].equalsIgnoreCase("read")) {
					if (args.length == 2) {
						try {
							int index = Integer.parseInt(args[1]);
							player.sendMessage(messages.getMessage(player.getName(), index));
							return true;
						} catch (NumberFormatException e) {
							player.sendMessage("Please type in a number");
							return true;
						}
					}
				}else if(args[0].equalsIgnoreCase("list")) {
					player.sendMessage("You have " + messages.getMessagesCount(player.getName()) + " messages!");
					return true;
				}
			}
		}
		return false;
	}
}