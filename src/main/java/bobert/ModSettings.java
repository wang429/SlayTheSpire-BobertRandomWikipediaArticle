package bobert;

import basemod.ModLabel;
import basemod.ModLabeledToggleButton;
import basemod.ModPanel;
import basemod.ModToggleButton;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.modthespire.lib.SpireConfig;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.FontHelper;

import java.util.Properties;

import static bobert.ModHome.makeID;

public class ModSettings {
  private static Properties DEFAULT_SETTINGS = new Properties();
  private static final String ALLOW_SEND_REQUEST = "allow_send_request";
  static {
    DEFAULT_SETTINGS.setProperty(ALLOW_SEND_REQUEST, "false");
  }

  private static final String MOD_SETTINGS_FILE = "bobertwikimod_config";

  private static SpireConfig config;

  public static void initialize() {
    try {
      config = new SpireConfig(ModHome.MODNAME, MOD_SETTINGS_FILE, DEFAULT_SETTINGS);
      config.load();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void onAllowSendRequestToggle(ModToggleButton toggle) {
    try {
      config.setBool(ALLOW_SEND_REQUEST, toggle.enabled);
      config.save();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static boolean getAllowSendRequest() {
    return config.getBool(ALLOW_SEND_REQUEST);
  }

  public static ModPanel createSettingsPanel() {
    String[] modSettingsPanelText = CardCrawlGame.languagePack.getUIString(makeID("ModSettingsPanel")).TEXT;
    ModPanel settingsPanel = new ModPanel();

    ModLabeledToggleButton allowHttpsRequest = new ModLabeledToggleButton(
        modSettingsPanelText[0],
        350.0F,
        700.0F,
        Color.WHITE,
        FontHelper.tipHeaderFont,
        getAllowSendRequest(),
        settingsPanel,
        (label) -> {},
        ModSettings::onAllowSendRequestToggle);
    settingsPanel.addUIElement(allowHttpsRequest);

    return settingsPanel;
  }
}
