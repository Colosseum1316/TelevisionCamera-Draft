package colosseum.televisioncamera;

import colosseum.televisioncamera.checks.CheckManager;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class TelevisionCamera extends JavaPlugin {
    private CheckManager checkManager;

    private static TelevisionCamera PLUGIN;

    public void onEnable() {
        PLUGIN = this;
        this.checkManager = new CheckManager();
    }

    public static TelevisionCamera getInstance() {
        return PLUGIN;
    }
}
