package bobert.patches;

import bobert.utils.ReflectionUtils;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.events.city.TheLibrary;
import com.megacrit.cardcrawl.ui.buttons.LargeDialogOptionButton;

import java.util.ArrayList;

@SuppressWarnings({"unchecked", "unused"})
@SpirePatch2(clz = TheLibrary.class, method = "buttonEffect")
public class ButtonEffect_TheLibraryPatch {
    @SpirePrefixPatch
    public static SpireReturn<Void> Prefix(TheLibrary __instance, int buttonPressed) {
        int bobertModBrowseOption = Integer.MAX_VALUE;
        ArrayList<LargeDialogOptionButton> optionList = __instance.imageEventText.optionList;
        int size = optionList.size();
        for (int i = 0; i < size; i++) {
            if (optionList.get(i).msg.startsWith("[Browse] #gGain #g1 #gKnowledge.")) {
                bobertModBrowseOption = i;
                break;
            }
        }
        int screenNum = ReflectionUtils.getPrivateField(__instance, TheLibrary.class, "screenNum");
        if (screenNum == 0 && buttonPressed == bobertModBrowseOption) {
            __instance.imageEventText.updateBodyText("Hello, World!");
            ReflectionUtils.setPrivateField(__instance, TheLibrary.class, "screenNum", 1);
            __instance.imageEventText.updateDialogOption(0, "[Leave]");
            __instance.imageEventText.clearRemainingOptions();
            return SpireReturn.Return();
        }
        return SpireReturn.Continue();
    }
}
