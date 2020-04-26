package me.alexisevelyn.screwingaroundgenerator.generators.checkerboard;

import me.alexisevelyn.screwingaroundgenerator.generators.tutorial.secondgen.populators.GrassPopulator;
import me.alexisevelyn.screwingaroundgenerator.generators.tutorial.secondgen.populators.OrePopulator;
import me.alexisevelyn.screwingaroundgenerator.generators.tutorial.secondgen.populators.TreePopulator;
import org.bukkit.Material;
import org.bukkit.TreeType;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.util.noise.SimplexOctaveGenerator;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

// Tutorial At: https://bukkit.gamepedia.com/Developing_a_World_Generator_Plugin
public class Checkerboard extends ChunkGenerator {
    int maxHeight = 70;
    Material black = Material.BLACK_WOOL;
    Material white = Material.WHITE_WOOL;
    Material bottom = Material.BEDROCK;
    boolean evenY = true;

    @Override
    public ChunkData generateChunkData(World world, Random random, int chunkX, int chunkZ, BiomeGrid biome) {
        ChunkData chunk = createChunkData(world);

        for (int X = 0; X < 16; X++) {
            for (int Z = 0; Z < 16; Z++) {
                // TODO Heights and blocks generation code here.

                for (int Y = 1; Y <= maxHeight; Y++) {
                    if (evenY && Z % 2 == 0)
                        chunk.setBlock(X, Y, Z, black);
                    else if (evenY && Z % 2 != 0)
                        chunk.setBlock(X, Y, Z, white);
                    else if (!evenY && Z % 2 == 0)
                        chunk.setBlock(X, Y, Z, white);
                    else if (!evenY && Z % 2 != 0)
                        chunk.setBlock(X, Y, Z, black);

                    evenY = !evenY;
                }

                chunk.setBlock(X, 0, Z, bottom);
            }
        }

        return chunk;
    }
}
