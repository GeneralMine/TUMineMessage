package ga.tumgaming.tumine.tumessage.message;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import ga.tumgaming.tumine.tumessage.util.Config;

public class Messages {

	public Messages(Config pl) {
		players = pl;
	}

	private Config players;

	public String getMessage(String name, int index) {
		String allMessages = players.get(getUUIDfromPlayer(name).toString());
		if (allMessages != null && !allMessages.equals("")) {
			String[] messages = allMessages.split("$");
			if (index < messages.length) {
				return messages[index];
			}
		}
		return "";
	}

	public String getMessage(UUID _uuid, int index) {
		String allMessages = players.get(_uuid.toString());
		if (allMessages != null && !allMessages.equals("")) {
			String[] messages = allMessages.split("$");
			if (index < messages.length) {
				return messages[index];
			}
		}
		return "";
	}

	public void addMessage(String name, String message) {
		String uuid = getUUIDfromPlayer(name).toString();
		String allMessages = players.get(uuid);
		if (allMessages.equals("") || allMessages == null) {
			players.set(uuid, message);
		} else {
			allMessages = allMessages + "$" + message;
		}
	}

	public void addMessage(UUID _uuid, String message) {
		String uuid = _uuid.toString();
		String allMessages = players.get(uuid);
		if (allMessages.equals("") || allMessages == null) {
			players.set(uuid, message);
		} else {
			allMessages = allMessages + "$" + message;
		}
	}

	public String deleteMessage(String name, int index) {
		String uuid = getUUIDfromPlayer(name).toString();
		String allMessages = players.get(uuid);
		if (allMessages != null && !allMessages.equals("")) {
			String[] messages = allMessages.split("$");
			if (index < messages.length) {
				if (index > 0) {
					String[] newMessages = new String[messages.length - 1];
					int counterIndex = 0;
					for (int i = 0; i < messages.length; i++) {
						if (index != i) {
							newMessages[counterIndex] = messages[i];
							counterIndex++;
						}
					}
					String result = "";
					for (int i = 0; i < newMessages.length - 1; i++) {
						result = result + newMessages[i] + "$";
					}
					result = result + newMessages[newMessages.length - 1];
					players.set(uuid, result);
					return "Message deleted";
				}
				return "Please type a positive number!";
			}
			return "You dont have that many messages!";
		}
		return "You dont have any message!";
	}

	public String deleteMessage(UUID _uuid, int index) {
		String uuid = _uuid.toString();
		String allMessages = players.get(uuid);
		if (allMessages != null && !allMessages.equals("")) {
			String[] messages = allMessages.split("$");
			if (index < messages.length) {
				if (index > 0) {
					String[] newMessages = new String[messages.length - 1];
					int counterIndex = 0;
					for (int i = 0; i < messages.length; i++) {
						if (index != i) {
							newMessages[counterIndex] = messages[i];
							counterIndex++;
						}
					}
					String result = "";
					for (int i = 0; i < newMessages.length - 1; i++) {
						result = result + newMessages[i] + "$";
					}
					result = result + newMessages[newMessages.length - 1];
					players.set(uuid, result);
					return "Message deleted";
				}
				return "Please type a positive number!";
			}
			return "You dont have that many messages!";
		}
		return "You dont have any message!";
	}

	public String deleteAll(String name) {
		String uuid = getUUIDfromPlayer(name).toString();
		players.delete(uuid);
		return "Deleted all messages!";
	}

	public String deleteAll(UUID _uuid) {
		String uuid = _uuid.toString();
		players.delete(uuid);
		return "Deleted all messages!";
	}

	public int getMessagesCount(String name) {
		String uuid = getUUIDfromPlayer(name).toString();
		String allMessages = players.get(uuid);
		if (allMessages != null && !allMessages.equals("")) {
			String[] messages = allMessages.split("$");
			return messages.length;
		}else {
			return 0;
		}	
	}
	
	public int getMessagesCount(UUID _uuid) {
		String uuid = _uuid.toString();
		String allMessages = players.get(uuid);
		if (allMessages != null && !allMessages.equals("")) {
			String[] messages = allMessages.split("$");
			return messages.length;
		}else {
			return 0;
		}
	}

	public UUID getUUIDfromPlayer(String name) {
		if (Bukkit.getServer().getPlayerExact(name) != null) {
			return Bukkit.getServer().getPlayerExact(name).getUniqueId();
		} else {
			return getUUIDfromOfflinePlayer(name);
		}
	}

	public UUID getUUIDfromOfflinePlayer(String name) {
		OfflinePlayer[] op = Bukkit.getServer().getOfflinePlayers();
		for (int i = 0; i < op.length; i++) {
			if (op[i].getName().equals(name)) {
				return op[i].getUniqueId();
			}
		}
		return null;
	}

}
