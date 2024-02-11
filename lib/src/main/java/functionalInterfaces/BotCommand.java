package functionalInterfaces;

import interfaces.bots.BotInterface;
/**
 * Functional interface used to use methods as parameters.
 *  The botCommands are supposed to be used by programs to set the bots' status.
 */
@FunctionalInterface
public interface BotCommand {
    boolean execute(BotInterface bot);
}
