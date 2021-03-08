package com.prophaze.luxduels.file;

import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Author: Zilleyy
 * <br>
 * Date: 9/03/2021 @ 10:12 am AEST
 */
public class Yaml {

    final Logger logger = Logger.getLogger("minecraft");

    final String YML = ".yml";
    final String SLASH = "/";

    private String name;
    private String path;

    private File file;
    private FileConfiguration configuration;

    private String pathPrefix = "";

    // SETTINGS
    private boolean autoSave = false;

    public Yaml(final String name, final String path) {
        this.name = name;
        this.path = path;

        this.file = new File(path + this.SLASH + name + this.YML);
        this.configuration = YamlConfiguration.loadConfiguration(this.file);

        if (!this.file.exists()) {
            this.logger.log(Level.INFO, "File \"" + name + "\" does not exist, creating it...");
            save();
        }
    }

    public void toggleAutoSave(final boolean onOff) {
        this.autoSave = onOff;
    }

    public void save() {
        try {
            this.configuration.save(this.file);
        } catch (final IOException ex) {
            ex.printStackTrace();
        }
    }

    public void autoSave() {
        if (this.autoSave) save();
    }

    public void set(final String key, final Object value) {
        getSection().set(key, value);
        autoSave();
    }

    public Object get(final String key) {
        return getSection().get(key);
    }

    public Object getObject(final String key, final Class<?> clazz) {
        return getSection().get(key, clazz);
    }

    public String getString(final String key) {
        return getSection().getString(key);
    }

    public int getInt(final String key) {
        return getSection().getInt(key);
    }

    public double getDouble(final String key) {
        return getSection().getDouble(key);
    }

    public long getLong(final String key) {
        return getSection().getLong(key);
    }

    public Location getLocation(final String key) {
        return getSection().getLocation(key);
    }

    public Vector getVector(final String key) {
        return getSection().getVector(key);
    }

    public ItemStack getItemStack(final String key) {
        return getSection().getItemStack(key);
    }

    public List<?> getList(final String key) {
        return getSection().getList(key);
    }

    public List<Integer> getIntegerList(final String key) {
        return getSection().getIntegerList(key);
    }

    public List<String> getStringList(final String key) {
        return getSection().getStringList(key);
    }

    public Map<String, Object> getValues() {
        return getSection().getValues(false);
    }

    public <T extends ConfigurationSerializable> T getSerializable(final String key, final Class<T> clazz) {
        return getSection().getSerializable(key, clazz);
    }

    public Set<String> keySet() {
        return getSection().getKeys(false);
    }

    public List<String> getKeysAsList() {
        final List<String> keys = new ArrayList<>();

        keys.addAll(keySet());

        return keys;
    }

    public <T> T getOrSetDefault(final String key, final T def) {
        final T ret;

        if (get(key) != null) {
            ret = (T) get(key);
        } else {
            ret = def;
            set(key, def);
        }

        return ret;
    }

    public <T> T getOrDefault(final String key, final T def) {
        return (get(key) == null ? def : (T) get(key));
    }

    public ConfigurationSection getSection(final String path) {
        querySection();
        return this.configuration.getConfigurationSection(path);
    }

    public ConfigurationSection getSection() {
        if (getPathPrefix() == null) {
            return getSection("");
        } else {
            return getSection(getPathPrefix());
        }
    }

    public boolean hasSection(final String path) {
        return this.configuration.getConfigurationSection(path) != null;
    }

    public boolean hasSection() {
        return hasSection(getPathPrefix());
    }

    public void createSection(final String path) {
        this.configuration.createSection(path);
    }

    public void createSection() {
        createSection(getPathPrefix());
    }

    public void querySection(final String path) {
        if (!hasSection(path)) createSection(path);
    }

    public void querySection() {
        querySection(getPathPrefix());
    }

    public boolean hasKey(final String key) {
        return keySet().contains(key);
    }

    public String getFileName() {
        return this.name;
    }

    public String getFilePath() {
        return this.path;
    }

    public String getPathPrefix() {
        return this.pathPrefix;
    }

    public void setPathPrefix(final String pathPrefix) {
        this.pathPrefix = pathPrefix;
    }

    public void shiftPathPrefix(final String key) {
        final String[] frags = this.pathPrefix.split("[.]");
        frags[frags.length - 1] = key;

        this.pathPrefix = "";
        for (final String frag : frags) {
            this.pathPrefix += frag;
            this.pathPrefix += (!frag.equals(key) ? "." : "");
        }
    }

    public String isolateKey(final String total, final String... fragments) {
        final String[] a = total.split("[.]");
        for (final String b : fragments) {
            for (final String c : a) {
                if (b.equals(c)) return c;
            }
        }
        return "";
    }

}
