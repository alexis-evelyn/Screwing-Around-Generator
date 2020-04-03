package me.alexisevelyn.screwingaroundgenerator.generators.tutorial.secondgen.populators;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.TreeType;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.generator.BlockPopulator;

import java.util.Random;

public class TreePopulator extends BlockPopulator {
    TreeType tree;
    Integer highest_amount = 4;
    Biome biome;
    Integer rarity;

    public TreePopulator(TreeType tree, Biome biome, Integer highest_amount) {
        this.tree = tree;
        this.biome = biome;
        this.highest_amount = highest_amount;
    }

    public TreePopulator(TreeType tree, Integer highest_amount) {
        this.tree = tree;
        this.highest_amount = highest_amount;
    }

    public TreePopulator(TreeType tree) {
        this.tree = tree;
    }

    // Allows Overwriting (say changing biomes or something)
    // Not Recommended Though
    public void setTree(TreeType tree) {
        this.tree = tree;
    }

    // Allows Overwriting (say changing biomes or something)
    // Not Recommended Though
    public void setHighestAmount(Integer highest_amount) {
        this.highest_amount = highest_amount;
    }

    @Override
    public void populate(World world, Random random, Chunk chunk) {
        // Psuedo-Randomly determine if going to generate tree or not
        if (random.nextBoolean()) {
            int amount = random.nextInt(highest_amount)+1;  // Amount of trees

            int X, Y, Z;
            for (int i = 1; i < amount; i++) {
                // Psuedo-Randomly Choose X,Z coordinates inside chunk (0-15)
                X = random.nextInt(15);
                Z = random.nextInt(15);

                // The empty for loop is intentional
                for (Y = world.getMaxHeight()-1; chunk.getBlock(X, Y, Z).getType() == Material.AIR; Y--); // Find the highest block of the (X,Z) coordinate chosen.

                // If Biome is set, make sure to check what biome tree is going to spawn in
                if (this.biome != null)
                    if (chunk.getBlock(X, Y, Z).getBiome() != this.biome)
                        continue;

                world.generateTree(chunk.getBlock(X, Y, Z).getLocation(), this.tree); // The tree type is set by the constructor
            }
        }
    }
}
