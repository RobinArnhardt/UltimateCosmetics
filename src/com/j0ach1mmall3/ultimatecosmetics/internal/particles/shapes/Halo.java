package com.j0ach1mmall3.ultimatecosmetics.internal.particles.shapes;

import com.j0ach1mmall3.ultimatecosmetics.Main;
import com.j0ach1mmall3.ultimatecosmetics.api.cosmetics.Particle;
import com.j0ach1mmall3.ultimatecosmetics.api.storage.ParticleStorage;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 20/12/15
 */
public final class Halo extends BukkitRunnable {
    private final Main plugin;

    public Halo(Main plugin) {
        this.plugin = plugin;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void run() {
        for (Particle particle : this.plugin.getApi().getParticles()) {
            final ParticleStorage particleStorage = particle.getParticleStorage();
            if(particleStorage.getShape() == ParticleShape.HALO) {
                final Location l = particle.getPlayer().getLocation();
                new BukkitRunnable() {
                    double i = 0;

                    @Override
                    public void run() {
                        this.i += Math.PI/32;
                        double x = Math.cos(this.i) * 0.5;
                        double z = Math.sin(this.i) * 0.5;
                        l.getWorld().spigot().playEffect(new Location(l.getWorld(), l.getX() + x, l.getY() + 2.3, l.getZ() + z), Effect.valueOf(particleStorage.getParticle()), particleStorage.getId(), particleStorage.getData(), 0, 0, 0, particleStorage.getSpeed(), 1, Halo.this.plugin.getParticles().getViewDistance());
                        if (this.i > 2 * Math.PI) this.cancel();
                    }

                }.runTaskTimer(this.plugin, 0, 1);
            }
        }
    }
}
