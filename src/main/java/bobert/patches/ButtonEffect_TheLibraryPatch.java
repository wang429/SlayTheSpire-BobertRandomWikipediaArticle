package bobert.patches;

import bobert.utils.WebServiceClient;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.megacrit.cardcrawl.events.city.TheLibrary;

import java.util.Arrays;
import java.util.Map;

@SuppressWarnings({"unchecked", "unused"})
@SpirePatch2(clz = TheLibrary.class, method = "getBook")
public class ButtonEffect_TheLibraryPatch {

  public static final String EXTRACT = "extract";

  @SpirePrefixPatch
  public static SpireReturn<String> Prefix(TheLibrary __instance) {
    WebServiceClient client = TheLibraryFields.webServiceClient.get(__instance);
    if (client.getStatus() == 200) {
      return new JsonParser()
          .parse(client.getResponseBody())
          .getAsJsonObject()
          .get("query")
          .getAsJsonObject()
          .get("pages")
          .getAsJsonObject()
          .entrySet()
          .stream()
          .map(Map.Entry::getValue)
          .map(JsonElement::getAsJsonObject)
          .filter(a -> a.get(EXTRACT) != null)
          .findAny()
//          .max(Comparator.comparing(a -> a.get("extract").getAsString().length()))
          .map(a -> yellowTitle(a.get("title").getAsString()) + " NL NL " + a.get("extract").getAsString())
          .map(SpireReturn::Return)
          .orElse(SpireReturn.Continue());
    }
    return SpireReturn.Continue();
  }

  private static String yellowTitle(String title) {
    return String.join(" ", Arrays.stream(title.split(" ")).map(s -> "#y" + s).toArray(String[]::new));
  }
}
