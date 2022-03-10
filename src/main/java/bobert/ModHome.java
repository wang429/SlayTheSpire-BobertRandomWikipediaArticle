package bobert;

import basemod.BaseMod;
import basemod.eventUtil.AddEventParams;
import basemod.eventUtil.EventUtils;
import bobert.events.FishingSimplified;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.TheBeyond;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SpireInitializer
public class ModHome {
    public static final String MOD_ID = "bobertMod";

    public static final Logger logger = LogManager.getLogger(ModHome.class.getName());

    public static final String MODNAME = "BobertG's Mod Thing";
    private static final String AUTHOR = "BobertG (wang429)";
    private static final String DESCRIPTION = "Stuff and Things";

    // =============== INPUT TEXTURE LOCATION =================

    //Mod Badge - A small icon that appears in the mod settings menu next to your mod.
    public static final String BADGE_IMAGE = "stsjorbsmodResources/images/Badge.png";


    // =============== MAKE RESOURCE PATHS =================

    public static String makeCardPath(String resourcePath) {
        return MOD_ID + "Resources/images/cards/" + resourcePath;
    }

    public static String makeRelicPath(String resourcePath) {
        return MOD_ID + "Resources/images/relics/" + resourcePath;
    }

    public static String makeCharPath(String resourcePath) {
        return MOD_ID + "Resources/images/characters/" + resourcePath;
    }

    public static String makeRelicOutlinePath(String resourcePath) {
        return MOD_ID + "Resources/images/relics/outline/" + resourcePath;
    }

    public static String makeMemoryPath(String resourcePath) {
        return MOD_ID + "Resources/images/memories/" + resourcePath;
    }

    public static String makeMonsterPath(String resourcePath) {
        return MOD_ID + "Resources/images/monsters/" + resourcePath;
    }

    public static String makePowerPath(String resourcePath) {
        return MOD_ID + "Resources/images/powers/" + resourcePath;
    }

    public static String makeImagePath(String resourcePath) {
        return MOD_ID + "Resources/images/" + resourcePath;
    }

    public static String makeLocalizedStringsPath(Settings.GameLanguage language, String resourcePath) {
        String languageFolder =
                // Disable this until we can get it back up to date
                // Settings.language == Settings.GameLanguage.FRA ? "fra" :
                language == Settings.GameLanguage.SPA ? "spa" :
                        language == Settings.GameLanguage.ZHS ? "zhs" :
                                /* default: */ "eng";

        return MOD_ID + "Resources/localization/" + languageFolder + "/" + resourcePath;
    }

    @SuppressWarnings("unused")
    public static void initialize() {
        logger.info("-------------------- Initializing BobertG's Mod --------------------");
        BaseMod.addEvent(new AddEventParams.Builder(FishingSimplified.ID, FishingSimplified.class)
                .eventType(EventUtils.EventType.NORMAL)
                .dungeonID(TheBeyond.ID)
        .create());
        logger.info("-------------------- BobertG's Mod Initialized. --------------------");
    }
}
