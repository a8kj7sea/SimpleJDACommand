package me.a8kj.commands.entity;

import java.util.Set;

import lombok.NonNull;

/**
 * 
 * ISimpleCommandRegister interface is a simple class/interface that handle
 * register commands process
 * 
 * @author a8kj
 * @version 0.0.2
 * 
 */

public interface ISimpleCommandRegister {

	/**
	 * 
	 * Simple function that add commands to Set<Command>
	 * 
	 * @param (Command) command
	 */

	public void addCommand(@NonNull final Command command);

	/**
	 * 
	 * Simple function that remove commands from Set<Command>
	 * 
	 * @param (Command) command
	 */

	public void removeCommand(@NonNull final Command command);

	/**
	 * 
	 * Simple function that return commands as Set
	 * 
	 * return (Set<Command>) commands
	 */

	public Set<Command> getCommands();

	/**
	 * 
	 * Simple function that register all commands
	 * 
	 */
	public void registerAll();

	/**
	 * 
	 * Simple function that used to Unregister all commands
	 * 
	 */
	public void unRegisterAll();

	/**
	 * 
	 * Simple function that register one command
	 * 
	 * @param (Command) command
	 */
	public void registerCommand(@NonNull final Command command);

	/**
	 * 
	 * Simple function that used to Unregister command
	 * 
	 * @param (Command) command
	 */
	public void unRegisterCommand(@NonNull final Command command);

}
