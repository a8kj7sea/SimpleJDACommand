package me.a8kj.commands.entity;

import javax.security.auth.login.LoginException;

import lombok.Getter;
import lombok.NonNull;
//import me.a8kj.commands.manager.SimpleCommandRegister;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

/**
 * 
 * The Bot class is a simple class used to start the bot, load its elements, and
 * register commands/events.
 * 
 * @author a8kj
 * @version 0.0.1
 *
 */

@Getter
public class Bot {

	private String token;
	private JDA jda;
	private JDABuilder jdaBuilder;
	// private SimpleCommandRegister simpleCommandRegister;

	/**
	 * 
	 * Bot constructor is a simple argument constructor that had 1 parameter for bot
	 * token
	 * 
	 * @param (String) token
	 */

	public Bot(@NonNull final String token) {
		this.token = token;
	}

	/**
	 * 
	 * Load is a function return boolean value : true when bot loaded successfully !
	 * without problems like token not valid and else false
	 * 
	 * @return (boolean) false/true
	 */

	public boolean load() {

		try {
			this.jdaBuilder = JDABuilder.createDefault(token);
			// this.simpleCommandRegister = new SimpleCommandRegister(jdaBuilder);
			System.out.println("Bot loaded successfully !");
			return true;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();

			return false;
		}

	}

	/**
	 * 
	 * Connect is a function that start/build bot
	 * 
	 */

	public void start() {
		try {
			this.jda = this.jdaBuilder.build();
			// this.simpleCommandRegister.registerAll();
			System.out.println("Bot started Successfully !");
		} catch (LoginException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * Simple function to register listeners (classes) that extends ListenerAdpater
	 * 
	 * @param (ListenerAdapter...) listeners
	 */

	public void registerListeners(ListenerAdapter... listeners) {
		for (ListenerAdapter listener : listeners) {
			this.jdaBuilder.addEventListeners(listener);
		}
	}

	/**
	 * 
	 * Simple function to unregister listeners (classes) that extends
	 * ListenerAdpater
	 * 
	 * @param (ListenerAdapter...) listeners
	 */

	public void unRegisterListeners(ListenerAdapter... listeners) {
		for (ListenerAdapter listener : listeners) {
			this.jdaBuilder.addEventListeners(listener);
		}
	}

}