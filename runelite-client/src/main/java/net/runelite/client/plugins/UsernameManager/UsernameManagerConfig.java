package net.runelite.client.plugins.UsernameManager;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("usernamemanager")
public interface UsernameManagerConfig extends Config
{
	@ConfigItem(
			keyName = "usernames",
			name = "Alias:Username pairs",
			description = "The alias with the usernames",
			position = 1
	)
	default String usernames()
	{
		return "alias1:username1, alias2:username2";
	}
}
