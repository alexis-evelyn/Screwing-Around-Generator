package me.alexisevelyn.screwingaroundgenerator;

import me.alexisevelyn.screwingaroundgenerator.generators.tutorial.firstgen.FirstGen;
import me.alexisevelyn.screwingaroundgenerator.generators.tutorial.secondgen.SecondGen;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @Override
    public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
        switch (id) {
            case "Tut1":
                return new FirstGen();
            case "Tut2":
                return new SecondGen();
            default:
                return new FirstGen();
        }
    }
}
