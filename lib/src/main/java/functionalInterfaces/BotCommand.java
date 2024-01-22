package functionalInterfaces;

import interfaces.BotInterface;

@FunctionalInterface
public interface BotCommand {
    void execute(BotInterface bot, Object... parameters);
}
