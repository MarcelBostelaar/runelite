package net.runelite.client.plugins.UsernameManager;

import com.google.common.base.Splitter;
import com.google.inject.Provides;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.util.Text;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@PluginDescriptor(
		name = "Username Manager",
		description = "Protects chosen items against alchemy",
		tags = {"high", "low", "alchemy", "protect", "alch"}
)
public class UsernameManagerPlugin extends Plugin
{
	private List<AliasNamePair> AliasUsernames;

	@Inject
	private UsernameManagerConfig config;

	@Provides
	UsernameManagerConfig getConfig(ConfigManager configManager)
	{
		return configManager.getConfig(UsernameManagerConfig.class);
	}

	private AliasNamePair parsealiasnamepair(String value)
	{
		List<String> values = Splitter.on(",")
				.omitEmptyStrings()
				.trimResults()
				.splitToList(value);
		if (values.size() == 2)
		{
			return new AliasNamePair(values.get(0), values.get(1));
		}
		String error = "Error in value: " + value;
		return new AliasNamePair(error, error);
	}

	private void reset()
	{
		AliasUsernames = Text.fromCSV(config.usernames()).stream()
				.map(this::parsealiasnamepair).collect(Collectors.toList());
	}
}
