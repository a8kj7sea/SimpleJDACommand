package me.a8kj.commands.manager;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Nullable;

import lombok.Getter;
import lombok.NonNull;
import me.a8kj.commands.entity.Command;
import me.a8kj.commands.entity.ISimpleCommandRegister;
import net.dv8tion.jda.api.JDABuilder;

/**
 * 
 * 
 * SimpleCommandRegister class is a simple class that register commands with
 * simple and easily way , that implements ISimpleCommandRegister
 * 
 * @author a8kj
 * @version 0.0.2
 */

public class SimpleCommandRegister implements ISimpleCommandRegister {

	private @Nullable final Set<Command> commands = new HashSet<>();

	private @Getter final JDABuilder jdaBuilder;

	/**
	 * 
	 * Argument Constructor that had JDABuilder parameter , that used to declare
	 * <b>jdaBuilder</b> variable
	 * 
	 * @param (JDABuilder) jdaBuilder
	 */

	public SimpleCommandRegister(@NonNull final JDABuilder jdaBuilder) {
		this.jdaBuilder = jdaBuilder;
	}

	/**
	 * 
	 * addCommand function is and Implements Function from ISimpleCommandRegister
	 * that add command to set of commands if not contains
	 * 
	 * @param (Command) command
	 */

	@Override
	public void addCommand(@NonNull final Command command) {
		if (this.contains(command))
			return; // if contains command ignore and close function

		// else add command

		this.commands.add(command);

	}

	/**
	 * 
	 * removeCommand function is and Implements Function from ISimpleCommandRegister
	 * that remove command from set of commands if contains
	 * 
	 * @param (Command) command
	 */

	@Override
	public void removeCommand(@NonNull final Command command) {
		if (!this.contains(command))
			return; // if not contains command ignore and close function

		// else remove command

		this.commands.remove(command);
	}

	/**
	 * 
	 * getCommands function is an Implements Function from ISimpleCommandRegister
	 * that get set of commands if not empty and not null
	 * 
	 * @param (Set<Command>) commands
	 */

	@Override
	public Set<Command> getCommands() {
		return !(commands.isEmpty()) && this.commands != null ? this.commands : null;
	}

	/**
	 * 
	 * registerAll function is an Implements Function from ISimpleCommandRegister
	 * that register all commands
	 * 
	 */

	@Override
	public void registerAll() {
		// if set of commands is null ignore
		if (getCommands().isEmpty() || getCommands() == null) {
			System.err.println("Commands cannot be registerd ! set of commands is null ! or maybe is empty");
			return;
		}

		// else register all

		commands.forEach(command -> {

			this.jdaBuilder.addEventListeners(command);
			System.out.println("[Commands] registered " + command.getLabel());

		});

	}

	/**
	 * 
	 * registerCommand function is an Implements Function from
	 * ISimpleCommandRegister that register one command
	 *
	 * @param (Command) command
	 */

	@Override
	public void registerCommand(@NonNull final Command command) {

		if (getCommands().isEmpty() || getCommands() == null) {
			System.err.println("Commands cannot be registerd ! set of commands is null ! or maybe is empty");
			return;
		}

		// if it not contains commands ignore
		if (!this.contains(command))
			throw new IllegalStateException(
					String.format("%s cannot be register please make sure it added to set of commands !\n",
							command.getClass().getCanonicalName()));

		// else register command
		this.jdaBuilder.addEventListeners(command);
		System.out.println("[Commands] registered " + command.getLabel());
	}

	/**
	 * 
	 * Simple function that check if command contains in Set<Command> , if found
	 * return true else false
	 * 
	 * @param command
	 * @return (boolean) true if command contains
	 */

	private boolean contains(@NonNull final Command command) {
		return this.commands.contains(command);
	}

	/**
	 * 
	 * unRegisterAll function is an Implements Function from ISimpleCommandRegister
	 * that used to unregister all commands
	 * 
	 */

	@Override
	public void unRegisterAll() {
		// if set of commands is null ignore
		if (getCommands().isEmpty() || getCommands() == null) {
			System.err.println("Commands cannot be registerd ! set of commands is null ! or maybe is empty");
			return;
		}

		// else unregister all

		commands.forEach(command -> {

			this.jdaBuilder.removeEventListeners(command);
			this.commands.remove(command);
			System.out.println("[Commands] unregistered " + command.getLabel());

		});

	}

	/**
	 * 
	 * unRegisterCommand function is an Implements Function from
	 * ISimpleCommandRegister that used to unregister one command
	 *
	 * @param (Command) command
	 */

	@Override
	public void unRegisterCommand(@NonNull final Command command) {

		if (getCommands().isEmpty() || getCommands() == null) {
			System.err.println("Commands cannot be registerd ! set of commands is null ! or maybe is empty");
			return;
		}

		// if it not contains commands ignore
		if (!this.contains(command))
			throw new IllegalStateException(String.format("%s cannot be unregister becuase its already not found !\n",
					command.getClass().getCanonicalName()));

		// else unregister command
		this.jdaBuilder.removeEventListeners(command);
		this.commands.remove(command);
		System.out.println("[Commands] unregistered " + command.getLabel());
	}
}
