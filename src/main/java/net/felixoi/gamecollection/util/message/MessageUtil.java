package net.felixoi.gamecollection.util.message;

import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

public class MessageUtil {

    private static String PREFIX = "GameCollection";
    private static String DIVIDER = "\u00BB";

    public static void sendMessage(CommandSource src, MessageTypes messageTypes, Text message) {
        src.sendMessage(Text.of(
                messageTypes.getTextColor(), PREFIX,
                " ", TextColors.GRAY, DIVIDER, " ",
                TextColors.WHITE, message));
    }

}
