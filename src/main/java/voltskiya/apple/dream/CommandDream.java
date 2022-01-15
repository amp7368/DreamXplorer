package voltskiya.apple.dream;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Subcommand;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import voltskiya.apple.dream.player.PlayerConfigGeneral;
import voltskiya.apple.utilities.util.message.SendMessage;

@CommandAlias("dream")
public class CommandDream extends BaseCommand {
    public CommandDream() {
        VoltskiyaPlugin.get().registerCommand(this);
    }

    @Subcommand("ping")
    public void ping(CommandSender sender) {
        SendMessage.sendMessageGreen(sender, "pong");
    }

    @Subcommand("config")
    public class Config extends BaseCommand {
        @Subcommand("world")
        public void world(Player player) {
            World world = player.getWorld();

            PlayerConfigGeneral.get().dreamWorld = world.getUID();
            PlayerConfigGeneral.get().save();
            SendMessage.sendMessageGreen(player, "Set world to %s [%s]", world.getName(), world.getUID());
        }
    }
}
