package me.alexisevelyn.screwingaroundgenerator.generators.tutorial.secondgen.populators;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.TreeType;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;

import java.util.Random;

public class OrePopulator extends BlockPopulator {
    Material ore; // Doesn't have to be an ore, that's just what I'm designing this class for
    Material replace = Material.STONE; // Material to Replace (Doesn't have to be stone, but that's what this is designed for)
    Integer maxTries = 15;

    Integer maxHeight = 60;
    Integer minHeight = 4;

    Integer chanceContinueVein = 40; // Chance for continue vein of ore - Defaults to 40%
    Integer chanceInitialSpawn = 60; // Chance for initial spawn of ore - Defaults to 60%
    Integer chanceMax = 100; // Percentage to Calculate Out Of - Defaults to 100%

    public OrePopulator(Material ore, Integer minHeight, Integer maxHeight) {
        this.ore = ore;

        this.setHeightConstraints(minHeight, maxHeight);
    }

    public OrePopulator(Material ore, Material replace, Integer minHeight, Integer maxHeight) {
        this.ore = ore;
        this.replace = replace;

        this.setHeightConstraints(minHeight, maxHeight);
    }

    public OrePopulator(Material ore, Material replace, Integer minHeight, Integer maxHeight, Integer chanceInitialSpawn, Integer chanceContinueVein) {
        this.ore = ore;
        this.replace = replace;

        this.setHeightConstraints(minHeight, maxHeight);

        if (!checkChance(chanceInitialSpawn))
            return;

        if (!checkChance(chanceContinueVein))
            return;

        this.chanceInitialSpawn = chanceInitialSpawn;
        this.chanceContinueVein = chanceContinueVein;
    }

    public OrePopulator(Material ore, Integer minHeight, Integer maxHeight, Integer chanceInitialSpawn, Integer chanceContinueVein) {
        this.ore = ore;

        this.setHeightConstraints(minHeight, maxHeight);

        if (!checkChance(chanceInitialSpawn))
            return;

        if (!checkChance(chanceContinueVein))
            return;

        this.chanceInitialSpawn = chanceInitialSpawn;
        this.chanceContinueVein = chanceContinueVein;
    }

    public void setHeightConstraints(Integer minHeight, Integer maxHeight) {
        // Ensure Minimum Height is a Valid Coordinate
        if (minHeight < 0 || minHeight > 255) {
            // Log message here
            return;
        }

        // Ensure Maximum Height is a Valid Coordinate
        if (maxHeight < 0 || maxHeight > 255) {
            // Log message here
            return;
        }

        // Ensure Minimum Height is Not More Than Maximum Height
        if (minHeight > maxHeight) {
            // Log message here
            return;
        }

        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
    }

    private boolean checkChance(Integer chance) {
        // Ensure, not over 100% chance
        if (chance > this.chanceMax)
            return false;

        // Ensure, not less than 0% chance
        if (chance < 0)
            return false;

        return true;
    }

    @Override
    public void populate(World world, Random random, Chunk chunk) {
        int X, Y, Z;
        boolean isStone = true;

        for (int i = 1; i < this.maxTries; i++) {  // Number of tries
            // Determine if starting initial spawn out of 100%
            if (random.nextInt(this.chanceMax) <= this.chanceInitialSpawn) {
                // Psuedo-Randomly Choose X,Z coordinates inside chunk (0-15)
                X = random.nextInt(15);
                Z = random.nextInt(15);

                // Choose y value based on maximum and minimum constraints
                Y = random.nextInt(this.maxHeight - this.minHeight + 1) + this.minHeight;

                if (chunk.getBlock(X, Y, Z).getType() == this.replace) {
                    while (isStone) {
                        chunk.getBlock(X, Y, Z).setType(this.ore);

                        // Determine if Continuing The Vein
                        if (random.nextInt(this.chanceMax) <= this.chanceContinueVein)  {
                            // Choose A Random Direction
                            switch (random.nextInt(6)) {
                                case 0: X++; break;
                                case 1: Y++; break;
                                case 2: Z++; break;
                                case 3: X--; break;
                                case 4: Y--; break;
                                case 5: Z--; break;
                            }

                            // Ensure we are inside chunk
                            X = ensureInChunk(X);
                            Z = ensureInChunk(Z);

                            // Ensure Generator Does Not Extend Past Min/Max Settings
                            Y = ensureInValidHeight(Y);

                            isStone = chunk.getBlock(X, Y, Z).getType() == this.replace;
                        } else {
                            isStone = false;
                        }
                    }
                }
            }
        }
    }

    public Integer ensureInChunk(Integer coordinate) {
        if (coordinate < 0) {
            coordinate = 0;
        } else if (coordinate > 15) {
            coordinate = 15;
        }

        return coordinate;
    }

    public Integer ensureInValidHeight(Integer coordinate) {
        if (coordinate < this.minHeight) {
            coordinate = this.minHeight;
        } else if (coordinate > maxHeight) {
            coordinate = maxHeight;
        }

        return coordinate;
    }
}
