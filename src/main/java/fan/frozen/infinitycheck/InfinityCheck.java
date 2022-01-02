package fan.frozen.infinitycheck;

import fan.frozen.infinitycheck.commands.CheckExchange;
import fan.frozen.infinitycheck.commands.CheckRemove;
import fan.frozen.infinitycheck.commands.CheckSet;
import fan.frozen.infinitycheck.configuration.Config;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class InfinityCheck extends JavaPlugin {
    public static Config config;
    public static Economy economy;
    @Override
    public void onEnable() {
        // Plugin startup logic
        config = new Config();
        //check if server has economy plugin, if not, disable this plugin
        if (!setupEconomy()){
            getServer().getPluginManager().disablePlugin(this);
            System.out.println("you don't have any economy plugin");
        }
        Objects.requireNonNull(getCommand("checkset")).setExecutor(new CheckSet());
        Objects.requireNonNull(getCommand("checkexchange")).setExecutor(new CheckExchange());
        Objects.requireNonNull(getCommand("checkremove")).setExecutor(new CheckRemove());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

    }
    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        economy = rsp.getProvider();
        return economy != null;
    }
}
