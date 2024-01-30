package functionalInterfaces;

import interfaces.bots.BotInterface;
/**
 * Functional function used to use methods as parameters (functional programming).
 */
@FunctionalInterface
public interface BotCommand {
    boolean execute(BotInterface bot);
}
