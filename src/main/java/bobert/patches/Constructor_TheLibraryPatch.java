package bobert.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.events.city.TheLibrary;

@SuppressWarnings({"unchecked", "unused"})
@SpirePatch2(clz = TheLibrary.class, method = SpirePatch.CONSTRUCTOR)
public class Constructor_TheLibraryPatch {
    @SpirePostfixPatch
    public static void PostFix(TheLibrary __instance) {
        __instance.imageEventText.setDialogOption("[Browse] #gGain #g1 #gKnowledge. Knowledge is useful, but probably does nothing here!");
    }
}
