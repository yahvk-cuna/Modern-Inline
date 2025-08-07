package moe.yahvk.moderninline.forge;

import moe.yahvk.moderninline.Moderninline;
import net.minecraftforge.fml.common.Mod;

@Mod(Moderninline.MOD_ID)
public final class ModerninlineForge {
    public ModerninlineForge() {
        // Run our common setup.
        Moderninline.init();
    }
}
