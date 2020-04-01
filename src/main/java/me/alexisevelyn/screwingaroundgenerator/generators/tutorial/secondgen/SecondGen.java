package me.alexisevelyn.screwingaroundgenerator.generators.tutorial.secondgen;

import me.alexisevelyn.screwingaroundgenerator.generators.tutorial.secondgen.populators.GrassPopulator;
import me.alexisevelyn.screwingaroundgenerator.generators.tutorial.secondgen.populators.LakePopulator;
import me.alexisevelyn.screwingaroundgenerator.generators.tutorial.secondgen.populators.OrePopulator;
import me.alexisevelyn.screwingaroundgenerator.generators.tutorial.secondgen.populators.TreePopulator;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.util.noise.SimplexOctaveGenerator;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

// Tutorial At: https://bukkit.gamepedia.com/Developing_a_World_Generator_Plugin
public class SecondGen extends ChunkGenerator {
    int currentHeight = 50;

    @Override
    public ChunkData generateChunkData(World world, Random random, int chunkX, int chunkZ, BiomeGrid biome) {
        // TODO Chunk generation code here.

        ChunkData chunk = createChunkData(world);
        SimplexOctaveGenerator generator = new SimplexOctaveGenerator(new Random(world.getSeed()), 8);
        generator.setScale(0.005D);

        for (int X = 0; X < 16; X++) {
            for (int Z = 0; Z < 16; Z++) {
                // TODO Heights and blocks generation code here.
                currentHeight = (int) ((generator.noise(chunkX*16+X, chunkZ*16+Z, 0.5D, 0.5D, true)+1)*15D+50D);

                chunk.setBlock(X, currentHeight, Z, Material.GRASS_BLOCK);
                chunk.setBlock(X, currentHeight-1, Z, Material.DIRT);

                for (int i = currentHeight-2; i > 0; i--)
                    chunk.setBlock(X, i, Z, Material.STONE);

                chunk.setBlock(X, 0, Z, Material.BEDROCK);
            }
        }

        return chunk;
    }

    @Override
    public List<BlockPopulator> getDefaultPopulators(World world) {

        // TODO: The commented out populators are broken and will break your server
        // don't use them unless you want to manually remove the worlds files after the server shuts down!!!
        // Just use Tut1 instead!!!
        // TODO: Fix
//        return Arrays.asList(new LakePopulator());

        // TODO: Fix
//        return Arrays.asList(new OrePopulator());

//        return Arrays.asList(
//                new TreePopulator(),
//                new GrassPopulator(),
//                new LakePopulator(),
//                new OrePopulator());

        return Arrays.asList(
                new TreePopulator(),
                new GrassPopulator());
    }
}
