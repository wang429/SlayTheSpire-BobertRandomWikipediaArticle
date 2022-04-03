package bobert.events;

import bobert.ModHome;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.relics.CeramicFish;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FishingSimplified extends AbstractImageEvent {

    private static final Logger logger = LoggerFactory.getLogger(FishingSimplified.class);

    public static final String ID = "FishingSimplified";
    private static final String BODY = "Try your luck and maybe catch a fish!";

    private final int fishChance = 50;
    private final int bootChance = 25;
    private final int cost = 120;

    public FishingSimplified() {
        super(ID, BODY, ModHome.makeImagePath("events/test.png"));

        int total = fishChance + bootChance;
        this.imageEventText.setDialogOption(
                String.format("[Go Fishing] #y%1$s #yGold - #g%2$s%%: #gObtain #ga #grelic", cost, total)
        );
        this.imageEventText.setDialogOption("[Leave]");
    }

    @Override
    protected void buttonEffect(int buttonPressed) {
        logger.info("Button pressed: {}", buttonPressed);
        switch (this.screenNum) {
            case 0:
                switch (buttonPressed) {
                    case 0:
                        AbstractDungeon.player.loseGold(cost);
                        int random = AbstractDungeon.miscRng.random(0, 30);
                        if (random < fishChance) {
                            this.imageEventText.updateBodyText("Well, a fish bit!");
                            AbstractDungeon.getCurrRoom().spawnRelicAndObtain(Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F, new CeramicFish());
                        }
                        else if (random < bootChance) {
                            this.imageEventText.updateBodyText("W");
                        }
                        else {
                            this.imageEventText.updateBodyText("Better luck next time.");
                        }
                        this.imageEventText.updateDialogOption(0, "[Leave]");
                        this.imageEventText.clearRemainingOptions();
                        this.screenNum = 1;
                        break;
                    case 1:
                    default:
                        openMap();
                        break;
                }
                logger.warn("Weird...!??!?");
                break;
            case 1:
            default:
                openMap();
                break;
        }
    }
}
