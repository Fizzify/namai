package net.coca.baseclient.mod;

import lombok.Getter;
import lombok.Setter;
import net.coca.baseclient.api.event.EventManager;

@Setter
@Getter
public abstract class Module {

    private String name, description, displayName;
    private int key;
    private boolean enabled;
    private Category category;
    private boolean expanded;

    public Module() {
        ModInfo modInfo = this.getClass().getAnnotation(ModInfo.class);
        if (modInfo != null) {
            this.name = modInfo.name();
            this.description = modInfo.description();
            this.category = modInfo.category();
        }
    }

    public void toggle() {
        setEnabled(!isEnabled());
    }

    protected void onEnable() {
        EventManager.register(this);
    }

    protected void onDisable() {
        EventManager.unregister(this);
    }
}
