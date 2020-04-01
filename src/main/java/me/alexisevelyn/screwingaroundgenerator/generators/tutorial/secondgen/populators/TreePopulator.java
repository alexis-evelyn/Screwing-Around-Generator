package me.alexisevelyn.screwingaroundgenerator.generators.tutorial.secondgen.populators;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.TreeType;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;

import java.util.Random;

public class TreePopulator extends BlockPopulator {
    @Override
    public void populate(World world, Random random, Chunk chunk) {
        // TODO Tree populator's code here

        if (random.nextBoolean()) {
            // TODO Tree generation code here

            int amount = random.nextInt(4)+1;  // Amount of trees
            for (int i = 1; i < amount; i++) {
                int X = random.nextInt(15);
                int Z = random.nextInt(15);
                int Y;
                for (Y = world.getMaxHeight()-1; chunk.getBlock(X, Y, Z).getType() == Material.AIR; Y--); // Find the highest block of the (X,Z) coordinate chosen.
                world.generateTree(chunk.getBlock(X, Y, Z).getLocation(), TreeType.TREE); // The tree type can be changed if you want.
            }
        }
    }
}
