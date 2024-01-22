package functionalInterfaces;

import interfaces.BotInterface;

@FunctionalInterface
public interface BotCommand<B extends BotInterface<B>> {
    void execute(B bot, Object... parameters);
}
