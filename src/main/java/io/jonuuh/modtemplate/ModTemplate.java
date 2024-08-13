package io.jonuuh.modtemplate;

import io.jonuuh.modtemplate.command.Command;
import io.jonuuh.modtemplate.config.Settings;
import io.jonuuh.modtemplate.event.Events;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.lwjgl.input.Keyboard;

import java.io.File;

@Mod(
        modid = ModTemplate.ModID,
        version = "1.0.0",
        acceptedMinecraftVersions = "[1.8.9]"
)
public class ModTemplate
{
    public static final String ModID = "modTemplate";

    @Mod.EventHandler
    public void FMLPreInit(FMLPreInitializationEvent event)
    {
        File file = new File(ModID);

        if (!file.exists())
        {
            file.mkdir();
        }

        Settings.createInstance(file);
    }

    @Mod.EventHandler
    public void FMLInit(FMLInitializationEvent event)
    {
        KeyBinding keyBinding = new KeyBinding("<description>", Keyboard.KEY_NONE, ModID);
        ClientRegistry.registerKeyBinding(keyBinding);

        ClientCommandHandler.instance.registerCommand(new Command());

        MinecraftForge.EVENT_BUS.register(new Events(keyBinding));
    }
}
