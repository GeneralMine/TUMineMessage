package ga.tumgaming.tumine.tumessage.message;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {
	//player.sendMessage("You have " + messages.getMessagesCount(player.getName()) + " messages!");
	private Messages messages;
	
	public JoinListener(Messages msg) {
		messages = msg;
	}
	
	
	@EventHandler
    public void onPlayerJoin(PlayerJoinEvent event)
    {
        if(event.getPlayer().hasPermission("al.joinvip"))
        {
            Player p = event.getPlayer();
        p.sendMessage("You have " + messages.getMessagesCount(p.getName()) + " messages!");
        }
    }
}