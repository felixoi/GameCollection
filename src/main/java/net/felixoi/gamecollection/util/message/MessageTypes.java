package net.felixoi.gamecollection.util.message;

import org.spongepowered.api.text.format.TextColor;
import org.spongepowered.api.text.format.TextColors;

public enum MessageTypes {

    INFO(TextColors.AQUA),
    SUCCESS(TextColors.GREEN),
    WARNING(TextColors.YELLOW),
    ERROR(TextColors.RED),
    DEBUG(TextColors.DARK_GRAY);

    private TextColor textColor;

    MessageTypes(TextColor textColor) {
        this.textColor = textColor;
    }

    public TextColor getTextColor() {
        return textColor;
    }

}
