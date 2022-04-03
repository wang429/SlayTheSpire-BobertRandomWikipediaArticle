package bobert.patches;

import bobert.utils.WebServiceClient;
import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.events.city.TheLibrary;

@SpirePatch(clz = TheLibrary.class, method = SpirePatch.CLASS)
public class TheLibraryFields {
  public static SpireField<WebServiceClient> webServiceClient = new SpireField<>(WebServiceClient::new);
}
