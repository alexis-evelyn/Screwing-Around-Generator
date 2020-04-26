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

    // Should Default to True At (0, 2, 0) per chunk
    boolean evenY = true;
    boolean evenX = true;
    boolean evenZ = true;

    @Override
    public ChunkData generateChunkData(World world, Random random, int chunkX, int chunkZ, BiomeGrid biome) {
        ChunkData chunk = createChunkData(world);

        for (int X = 0; X < 16; X++) {
            evenX = X % 2 == 0;

            for (int Z = 0; Z < 16; Z++) {
                evenZ = Z % 2 == 0;

                for (int Y = 1; Y <= maxHeight; Y++) {
                    evenY = Y % 2 == 0;

                    // Inverted X Even Z Even Y
                    if (evenY && evenZ && evenX) // (0, 2, 0) - Black
                        chunk.setBlock(X, Y, Z, black);
                    else if (evenY && evenZ) // (1, 2, 0) - White
                        chunk.setBlock(X, Y, Z, white);

                    // Inverted X Odd Z Even Y
                    else if (evenY && evenX) // (2, 2, 1) - White
                        chunk.setBlock(X, Y, Z, white);
                    else if (evenY) // (3, 2, 1) - Black
                        chunk.setBlock(X, Y, Z, black);


                    // Inverted X Even Z Odd Y
                    if (!evenY && evenZ && evenX) // (0, 3, 0) - White
                        chunk.setBlock(X, Y, Z, white);
                    else if (!evenY && evenZ) // (1, 3, 0) - Black
                        chunk.setBlock(X, Y, Z, black);

                    // Inverted X Odd Z Odd Y
                    else if (!evenY && evenX) // (2, 3, 1) - Black
                        chunk.setBlock(X, Y, Z, black);
                    else if (!evenY) // (3, 3, 1) - White
                        chunk.setBlock(X, Y, Z, white);
                }

                chunk.setBlock(X, 0, Z, bottom);
            }
        }

        return chunk;
    }
}
