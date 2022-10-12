package bobert;

import basemod.BaseMod;
import basemod.interfaces.EditStringsSubscriber;
import basemod.interfaces.PostInitializeSubscriber;
import bobert.utils.TextureLoader;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.localization.UIStrings;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SpireInitializer
public class ModHome implements
    EditStringsSubscriber,
    PostInitializeSubscriber {
    public static final String MOD_ID = "bobertWikiMod";

    public static final Logger logger = LogManager.getLogger(ModHome.class.getName());

    public static final String MODNAME = "BobertG's Wiki Lookup Mod";
    private static final String AUTHOR = "BobertG";
    private static final String DESCRIPTION = "Various things will perform a Wikipedia lookup, for whatever reason.";

    // =============== INPUT TEXTURE LOCATION =================

    private static final String BASE_RESOURCE_PATH = MOD_ID + "Resources/";
    //Mod Badge - A small icon that appears in the mod settings menu next to your mod.
    public static final String BADGE_IMAGE = makeImagePath("badge.png");


    // =============== MAKE RESOURCE PATHS =================

    public static String makeCardPath(String resourcePath) {
        return BASE_RESOURCE_PATH + "/images/cards/" + resourcePath;
    }

    public static String makeRelicPath(String resourcePath) {
        return BASE_RESOURCE_PATH + "/images/relics/" + resourcePath;
    }

    public static String makeCharPath(String resourcePath) {
        return BASE_RESOURCE_PATH + "/images/characters/" + resourcePath;
    }

    public static String makeRelicOutlinePath(String resourcePath) {
        return BASE_RESOURCE_PATH + "/images/relics/outline/" + resourcePath;
    }

    public static String makeMonsterPath(String resourcePath) {
        return BASE_RESOURCE_PATH + "/images/monsters/" + resourcePath;
    }

    public static String makePowerPath(String resourcePath) {
        return BASE_RESOURCE_PATH + "/images/powers/" + resourcePath;
    }

    public static String makeImagePath(String resourcePath) {
        return BASE_RESOURCE_PATH + "/images/" + resourcePath;
    }

    public static String makeLocalizedStringsPath(Settings.GameLanguage language, String resourcePath) {
        String languageFolder = language.name().toLowerCase();

        return BASE_RESOURCE_PATH + "/localization/" + languageFolder + "/" + resourcePath;
    }

    /**
     * Initialize the mod
     */
    @SuppressWarnings("unused")
    public static void initialize() {
        logger.info("-------------------- Initializing BobertG's Mod --------------------");
        logger.debug("Subscribe to BaseMod hooks");
        BaseMod.subscribe(new ModHome());
        logger.debug("Done subscribing");

        logger.debug("Adding mod settings");
        ModSettings.initialize();
        logger.debug("Done adding mod settings");
        logger.info("-------------------- BobertG's Mod Initialized. --------------------");
    }

    // ================ /LOAD THE KEYWORDS/ ===================

    // this adds "ModName:" before the ID of any card/relic/power etc.
    // in order to avoid conflicts if any other mod uses the same ID.
    public static String makeID(String idText) {
        return MOD_ID + ":" + idText;
    }

    public static String makeID(Class idClass) {
        return makeID(idClass.getSimpleName());
    }

    /**
     * Post-Initialize
     */
    @Override
    public void receivePostInitialize() {
        logger.info("Registering mod config page");
        Texture badgeTexture = TextureLoader.getTexture(BADGE_IMAGE);
        BaseMod.registerModBadge(badgeTexture, MODNAME, AUTHOR, DESCRIPTION, ModSettings.createSettingsPanel());
    }

    private void loadStrings(Class<?> stringType, String stringsFileName) {
        // We load english first as a fallback for yet-to-be-translated things, then load the "true" language
        BaseMod.loadCustomStringsFile(stringType, makeLocalizedStringsPath(Settings.GameLanguage.ENG, stringsFileName));
        BaseMod.loadCustomStringsFile(stringType, makeLocalizedStringsPath(Settings.language, stringsFileName));
    }

    /**
     * Loads text for the mods from localization directory
     */
    @Override
    public void receiveEditStrings() {
        logger.info("Beginning to edit strings for mod with ID: " + MOD_ID);
        loadStrings(UIStrings.class, "UI.json");
        logger.info("Done editing strings");
    }
}
