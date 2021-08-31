package adris.altoclef.mixins;

import adris.altoclef.StaticMixinHookups;
import net.minecraft.client.world.ClientChunkManager;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.world.biome.source.BiomeArray;
import net.minecraft.world.chunk.WorldChunk;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.BitSet;

@Mixin(ClientChunkManager.class)
public class LoadChunkMixin {

    @Inject(
            method = "loadChunkFromPacket",
            at = @At("RETURN")
    )
    private void onLoadChunk(int x, int z, BiomeArray biomes, PacketByteBuf buf, NbtCompound nbt, BitSet bitSet, CallbackInfoReturnable<WorldChunk> ci) {
        StaticMixinHookups.onChunkLoad(ci.getReturnValue());
    }

    @Inject(
            method = "unload",
            at = @At("TAIL")
    )
    private void onChunkUnload(int x, int z, CallbackInfo ci) {
        StaticMixinHookups.onChunkUnload(x, z);
    }
}
