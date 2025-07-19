package com.cursee.new_shield_variants.core;

import com.cursee.monolib.platform.Services;
import com.cursee.monolib.util.toml.Toml;
import com.cursee.new_shield_variants.Constants;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class NSVConfig {
    public static List<String> BANNED_SHIELDS = new ArrayList<>();

    private static final String FILE_SUFFIX = Constants.MOD_ID + "-server";
    private static final String CONFIG_DIR_FILEPATH = Services.PLATFORM.getGameDirectory() + File.separator + "config";

    public static void onLoad() {

        final File CONFIG_DIR = new File(CONFIG_DIR_FILEPATH);
        if (!CONFIG_DIR.isDirectory() && !CONFIG_DIR.mkdirs()) {
            throw new RuntimeException("Unable to access or create directory: " + CONFIG_DIR_FILEPATH);
        }

        handle(new File(CONFIG_DIR_FILEPATH + File.separator + FILE_SUFFIX + ".toml"));
    }

    private static final LinkedList<String> DEFAULTS = new LinkedList<>();
    private static void handle(File file) {

        if (!file.isFile()) {
            loadDefaults();
            try (PrintWriter writer = new PrintWriter(file)) {
                DEFAULTS.forEach(writer::println);
            }
            catch (Exception e) {
                System.out.println("Filed to write " + file.getAbsolutePath());
                System.out.println(e.getMessage());
            }
        }
        else {
            Toml toml = new Toml().read(file);

            BANNED_SHIELDS = toml.getList("banned_shields");
        }
    }

    private static void loadDefaults() {
        DEFAULTS.add("# banned_shields is a list of shields that will do nothing if a player attempts to use them");
        DEFAULTS.add("# banned_shields = [\"stone_shield\", \"iron_shield\"]");
        DEFAULTS.add("banned_shields = []");
    }
}
