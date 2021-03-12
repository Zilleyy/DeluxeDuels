package com.prophaze.luxduels.util.world;

import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;

import java.util.Random;

/**
 * Author: Zilleyy
 * <br>
 * Date: 12/03/2021 @ 12:45 pm AEST
 */
public class VoidWorld extends ChunkGenerator {

    @Override
    public ChunkData generateChunkData(final World world, final Random random, final int chunkx, final int chunkz, final BiomeGrid biome) {
        return createChunkData(world);
    }

}

