package me.alexisevelyn.screwingaroundgenerator.generators.tutorial.secondgen;

import me.alexisevelyn.screwingaroundgenerator.generators.tutorial.secondgen.populators.GrassPopulator;
import me.alexisevelyn.screwingaroundgenerator.generators.tutorial.secondgen.populators.LakePopulator;
import me.alexisevelyn.screwingaroundgenerator.generators.tutorial.secondgen.populators.OrePopulator;
import me.alexisevelyn.screwingaroundgenerator.generators.tutorial.secondgen.populators.TreePopulator;
import org.bukkit.Material;
import org.bukkit.TreeType;
import org.bukkit.World;
import org.bukkit.block.Biome;
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

        // TODO: Optimize Generator and Populators
        // TODO: Take advantage of Paper's Chunk Gen Asynchronously

        return Arrays.asList(
                // Ores
                new OrePopulator(Material.COAL_ORE, 4, 60, 90, 90),
                new OrePopulator(Material.IRON_ORE, 4, 60, 80, 90),
                new OrePopulator(Material.GOLD_ORE, 4, 40,60, 70),
//                new OrePopulator(Material.REDSTONE_ORE, 4, 30,60, 70),
                new OrePopulator(Material.DIAMOND_ORE, 4, 15,90, 90),
//                new OrePopulator(Material.LAPIS_ORE, 4, 15,90, 90),
//                new OrePopulator(Material.EMERALD_ORE, 4, 15,90, 90),
//                new OrePopulator(Material.NETHER_QUARTZ_ORE, 4, 15,90, 90),

                // Shrubbery
                new GrassPopulator(Material.GRASS, 4),
                new GrassPopulator(Material.TALL_GRASS, 4),
                new GrassPopulator(Material.SWEET_BERRY_BUSH, 4),
//                new GrassPopulator(Material.DRAGON_EGG, 4),
//                new GrassPopulator(Material.SPAWNER, 4),
//                new GrassPopulator(Material.OAK_LOG, 4),

                // Trees
                new TreePopulator(TreeType.DARK_OAK, Biome.DARK_FOREST, 1),
                new TreePopulator(TreeType.TREE, Biome.PLAINS, 4),
                new TreePopulator(TreeType.BIG_TREE, Biome.PLAINS, 1),
                new TreePopulator(TreeType.MEGA_REDWOOD, Biome.TAIGA,2),
                new TreePopulator(TreeType.RED_MUSHROOM, Biome.DARK_FOREST, 1),
                new TreePopulator(TreeType.BROWN_MUSHROOM, Biome.DARK_FOREST, 1),
                new TreePopulator(TreeType.COCOA_TREE, Biome.JUNGLE, 3));


    }
}
