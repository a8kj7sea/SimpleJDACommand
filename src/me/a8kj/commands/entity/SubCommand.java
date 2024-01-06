package me.a8kj.commands.entity;

import lombok.NonNull;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

/**
 * 
 * SubCommand is an interface provides a streamlined set of methods and
 * functionalities designed for managing sub-commands and facilitating their
 * execution
 * 
 * 
 * @author a8kj
 * @version 0.0.2
 * 
 */

@Deprecated
public interface SubCommand {

	/**
	 * 
	 * This function returns a string representing the sub-command label entered by
	 * the user
	 * 
	 * @return (String) label of sub-command
	 */

	public String getLabel();

	/**
	 * 
	 * This function returns the permission associated with a sub-command,
	 * defaulting to null if no specific permission is set
	 * 
	 * @return (net.dv8tion.jda.api.Permission) A sub-command permission
	 */

	public default Permission getPermission() {
		return null;
	}

	/**
	 * 
	 * This function returns a description for a sub-command
	 * 
	 * @return (String) description of sub Command
	 */

	public String getDescription();

	/**
	 * 
	 * This function returns the usage information for a sub-command
	 * 
	 * @return (String) usage of sub Command
	 */

	public String getUsage();

	/**
	 * Handles the sub-command and executes it.
	 *
	 * @param (String[])                  An array of strings representing the
	 *                                    command arguments
	 * @param (GuildMessageReceivedEvent) The GuildMessageReceivedEvent associated
	 *                                    with the command
	 */
	public void handle(@NonNull final String arguments[], @NonNull final GuildMessageReceivedEvent event);

}
