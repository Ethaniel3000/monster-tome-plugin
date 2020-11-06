package com.monstertome;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class MonsterTomePluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(MonsterTomePlugin.class);
		RuneLite.main(args);
	}
}