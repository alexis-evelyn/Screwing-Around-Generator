package me.alexisevelyn.screwingaroundgenerator.generators.tutorial.secondgen.populators;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.TreeType;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;

import java.util.Random;

public class GrassPopulator extends BlockPopulator {
    Material grass;
    Material grass_block = Material.GRASS_BLOCK;
    Integer highest_amount = 4;
    Integer rarity;

    public GrassPopulator(Material grass, Integer highest_amount) {
        this.grass = grass;
        this.highest_amount = highest_amount;
    }

    public GrassPopulator(Material grass) {
        this.grass = grass;
    }

    // Allows Overwriting (say changing biomes or something)
    // Not Recommended Though
    public void setGrass(Material grass) {
        this.grass = grass;
    }

    // Allows Overwriting (say changing biomes or something)
    // Not Recommended Though
    public void setHighestAmount(Integer highest_amount) {
        this.highest_amount = highest_amount;
    }

    @Override
    public void populate(World world, Random random, Chunk chunk) {
        // Psuedo-Randomly determine if going to generate grass or not
        if (random.nextBoolean()) {
            int amount = random.nextInt(highest_amount)+1;  // Amount of grass

            int X, Y, Z;
            for (int i = 1; i < amount; i++) {
                // Psuedo-Randomly Choose X,Z coordinates inside chunk (0-15)
                X = random.nextInt(15);
                Z = random.nextInt(15);

                // The empty for loop is intentional
                for (Y = world.getMaxHeight()-1; chunk.getBlock(X, Y, Z).getType() == Material.AIR; Y--); // Find the highest block of the (X,Z) coordinate chosen.

                if (chunk.getBlock(X, Y, Z).getType() == this.grass_block) {
                    chunk.getBlock(X, Y+1, Z).setType(this.grass);
                }
            }
        }
    }
}
