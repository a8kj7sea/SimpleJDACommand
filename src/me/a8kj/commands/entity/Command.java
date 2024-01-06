package me.a8kj.commands.entity;

import java.util.Arrays;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
//import lombok.Setter;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

/**
 * 
 * Command class is a simple abstract class that extends ListenerAdapter and
 * contains on onCommand abstract function that used to handle command through
 * event , the main idea of this class is easy way to handle commands
 * 
 * @author a8kj
 * @version 0.0.1
 */

public abstract class Command extends ListenerAdapter {

	private @Getter final String label;

	private @Setter String prefix;

	/**
	 * 
	 * Simple 2 Argument Constructor that contain a pass String as argument that
	 * return back to command label also another argument that return to prefix
	 * 
	 * @param (String) command label
	 * @param (String) command prefix
	 * 
	 * 
	 *                 public Command(@NonNull final String label, @NonNull final
	 *                 String prefix) { this.label = label; this.prefix = prefix; }
	 */

	/**
	 * 
	 * Simple one Argument Constructor that contain a pass String as argument that
	 * return back to command label
	 * 
	 * @param (String) command label
	 */

	public Command(@NonNull final String label) {
		this.label = label;
		this.prefix = getPrefix() != null && getPrefix().length() <= 3 ? getPrefix() : "?";
	}

	/**
	 * An abstract function that used to return a prefix to command entered by user
	 * 
	 * @return (String) command prefix
	 * 
	 */

	public abstract String getPrefix();

	/**
	 * 
	 * An abstract function that to handle execute command process with these
	 * parameters :
	 * 
	 * 
	 * @param (String)                    label
	 * @param (String[])                  arguments
	 * @param (GuildMessageReceivedEvent) event
	 * 
	 */

	public abstract void onCommand(@NonNull final String label, @NonNull final String arguments[],
			@NonNull final GuildMessageReceivedEvent event);

	@Override
	public void onGuildMessageReceived(@NonNull final GuildMessageReceivedEvent event) {
		String arguments[] = event.getMessage().getContentRaw().split(" ");

		if (event.getMember() == null) {
			System.err.println("Member cannot be null ! in : " + this.getClass().getCanonicalName());
			return; // if event member is null ignore it and close function
		}

		if (event.getMember().getUser().isBot())
			return; // ignore messages from bot (to ignore commands that ran by bots !)

		// check if prefix valid or not
		if (getPrefix() == null || getPrefix().length() < 1) {
			System.err.println("Sorry your command prefix not valid !");
			return;

		}

		// check if not who run this command type prefix then command label like this
		// template "!command" then ignore his order
		if (arguments.length == 0 || !arguments[0].startsWith(getPrefix().toLowerCase()) || !arguments[0]
				.equalsIgnoreCase(getPrefix() + this.getLabel())) {
			return;
		}

		// else
		// handle command logic with abstract function !

		// copy old array after remove first element (command-label)
		// the first element is return to command label so should be remove it
		String[] copyArray = Arrays.copyOfRange(arguments, 1, arguments.length);

		onCommand(label, copyArray, event);

	}

}
