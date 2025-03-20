package benmod.modcore;

import basemod.helpers.RelicType;
import basemod.interfaces.*;
import benmod.cards.*;
import benmod.characters.SoulWizard;
import benmod.monsters.RecklessGoblin;
import benmod.relics.*;
import com.badlogic.gdx.Gdx;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import basemod.BaseMod;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.localization.*;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.monsters.MonsterInfo;

import java.nio.charset.StandardCharsets;
import static benmod.characters.SoulWizard.PlayerColorEnum.BENMOD_GOLDEN;
import static benmod.characters.SoulWizard.PlayerColorEnum.Soul_Wizard;

@SpireInitializer
public class BenMod implements EditCardsSubscriber, EditStringsSubscriber, EditRelicsSubscriber, EditKeywordsSubscriber, EditCharactersSubscriber {
    public BenMod() {
        BaseMod.subscribe(this);
        BaseMod.addColor(BENMOD_GOLDEN, SoulWizard_Color, SoulWizard_Color, SoulWizard_Color, SoulWizard_Color, SoulWizard_Color, SoulWizard_Color, SoulWizard_Color, SoulWizard_ATTACK_512, SoulWizard_SKILL_512, SoulWizard_POWER_512, SoulWizard_ENEYGY_ORB, SoulWizard_ATTACK_1024, SoulWizard_SKILL_1024, SoulWizard_POWER_1024, SoulWizard_BIG_ORB, SoulWizard_SMALL_ORB);
    }

    public static void initialize() {
        new BenMod();
    }

    private static final String SoulWizard_BUTTON = "BenModResources/images/Characters/SoulWizard_BUTTON.png";

    private static final String SoulWizard_PORTRAIT = "BenModResources/images/Characters/SoulWizard_PORTRAIT.png";

    private static final String SoulWizard_ATTACK_512 = "BenModResources/images/Characters/bg_attack_512.png";

    private static final String SoulWizard_POWER_512 = "BenModResources/images/Characters/bg_power_512.png";

    private static final String SoulWizard_SKILL_512 = "BenModResources/images/Characters/bg_skill_512.png";

    private static final String SoulWizard_SMALL_ORB = "BenModResources/images/Characters/small_orb.png";

    private static final String SoulWizard_ATTACK_1024 = "BenModResources/images/Characters/bg_attack.png";

    private static final String SoulWizard_POWER_1024 = "BenModResources/images/Characters/bg_power.png";

    private static final String SoulWizard_SKILL_1024 = "BenModResources/images/Characters/bg_skill.png";

    private static final String SoulWizard_BIG_ORB = "BenModResources/images/Characters/card_orb.png";

    private static final String SoulWizard_ENEYGY_ORB = "BenModResources/images/Characters/cost_orb.png";

    public static final Color SoulWizard_Color = new Color(224.0F / 255.0F, 181.0F / 255.0F, 64.0F / 255.0F, 1.0F);

    @Override
    public void receiveEditCards() {
        BaseMod.addCard(new DeepMaintenance());
        BaseMod.addCard(new FastCharge());
        BaseMod.addCard(new Firewall());
        BaseMod.addCard(new Focus());
        BaseMod.addCard(new HardShield());
        BaseMod.addCard(new SharpBlade());
        BaseMod.addCard(new WaterCooling());
        BaseMod.addCard(new Format());
        BaseMod.addCard(new AntiVirus());
        BaseMod.addCard(new ComputilityImprovement());
        BaseMod.addCard(new Supernova());
        BaseMod.addCard(new Invulnerable());
        BaseMod.addCard(new Missile());
        BaseMod.addCard(new PowerOn());
        BaseMod.addCard(new Crushing());
        BaseMod.addCard(new Strike_Golden());
        BaseMod.addCard(new Defend_Golden());
        BaseMod.addCard(new GoldenBody());
        BaseMod.addCard(new Condensation());
        BaseMod.addCard(new LayersOfMist());
        BaseMod.addCard(new GhostFire());
        BaseMod.addCard(new RetreatToAdvance());
        BaseMod.addCard(new Prey());
        BaseMod.addCard(new Trial());
        BaseMod.addCard(new DeadlyStrike());
        BaseMod.addCard(new SoulShield());
        BaseMod.addCard(new SoulShock());
        BaseMod.addCard(new Replacement());
        BaseMod.addCard(new HellForm());
        BaseMod.addCard(new Visions());
        BaseMod.addCard(new Throughout());
        BaseMod.addCard(new GrongVjug());
        BaseMod.addCard(new PackBag());
        BaseMod.addCard(new Divination());
        BaseMod.addCard(new Stronger());
        BaseMod.addCard(new VoodooDoll());
        BaseMod.addCard(new StarAbsorption());
        BaseMod.addCard(new SoulClone());
        BaseMod.addCard(new SoulUrn());
        BaseMod.addCard(new Possession());
        BaseMod.addCard(new SoulSlaying());
        BaseMod.addCard(new SoulSeed());
        BaseMod.addCard(new CutWaist());
        BaseMod.addCard(new HellBridge());
        BaseMod.addCard(new Frame());
        BaseMod.addCard(new Conceal());
        BaseMod.addCard(new Track());
        BaseMod.addCard(new Wanted());
        BaseMod.addCard(new Channeling());
        BaseMod.addCard(new PermitRoad());
        BaseMod.addCard(new Stunned());
        BaseMod.addCard(new SoulFlame());
        BaseMod.addCard(new InSight());
        BaseMod.addCard(new AllSpiritFormation());
        BaseMod.addCard(new Rebirth());
        BaseMod.addCard(new DeadRadiation());
        BaseMod.addCard(new SoulRefining());
        BaseMod.addCard(new Retreat());
        BaseMod.addCard(new Surveillance());
        BaseMod.addCard(new ChainStrike());
        BaseMod.addCard(new BloodPact());
        BaseMod.addCard(new SelfMutilation());
        BaseMod.addCard(new Punishment());
        BaseMod.addCard(new ShatteredWave());
        BaseMod.addCard(new Incendies());
        BaseMod.addCard(new Absorb());
        BaseMod.addCard(new BodyGuard());
        BaseMod.addCard(new GodTravel());
        BaseMod.addCard(new DeathBreath());
        BaseMod.addCard(new EndlessBlade());
        BaseMod.addCard(new VirtualMachine());
        BaseMod.addCard(new Iteration());
        BaseMod.addCard(new DeadlyRhythm());
        BaseMod.addCard(new MirrorSpell());
        BaseMod.addCard(new StealFromOthers());
        BaseMod.addCard(new MemoryModule());
        BaseMod.addCard(new ForgettingSoup());
        BaseMod.addCard(new Confession());
        BaseMod.addCard(new Kowtow());
        BaseMod.addCard(new SoulPressure());
        BaseMod.addCard(new Unpardonable());
        BaseMod.addCard(new Destroyer());
        BaseMod.addCard(new Outburst());
        BaseMod.addCard(new DeathBook());
        BaseMod.addCard(new SoulLink());
        BaseMod.addCard(new DangerSpot());
        BaseMod.addCard(new BecomeBuddha());
        BaseMod.addCard(new SoulFlying());
        BaseMod.addCard(new SneakyAttack());
        BaseMod.addCard(new BounceBack());
        BaseMod.addCard(new Compassion());
    }

    @Override
    public void receiveEditRelics() {
        BaseMod.addRelic(new BlackHeart(), RelicType.SHARED);
        BaseMod.addRelic(new EnergyDrink(), RelicType.SHARED);
        BaseMod.addRelic(new ConsumerCoupon(), RelicType.SHARED);
        BaseMod.addRelic(new JudgeOrder(), RelicType.SHARED);
        BaseMod.addRelic(new DevilOrder(), RelicType.SHARED);
        BaseMod.addRelic(new SoulBell(), RelicType.SHARED);
        BaseMod.addRelic(new SoulBanner(), RelicType.SHARED);
        BaseMod.addRelic(new DemonSlayer(), RelicType.SHARED);
        BaseMod.addRelic(new GoldenFleece(), RelicType.SHARED);
        BaseMod.addRelic(new SoulCalmingStone(), RelicType.SHARED);
        BaseMod.addRelic(new LittleBuddha(), RelicType.SHARED);
        BaseMod.addRelic(new EscapePod(), RelicType.SHARED);
        BaseMod.addRelic(new ForceFieldShield(), RelicType.SHARED);
        BaseMod.addRelic(new BassDrum(), RelicType.SHARED);
    }

    @Override
    public void receiveEditCharacters() {
        BaseMod.addCharacter(new SoulWizard(CardCrawlGame.playerName), SoulWizard_BUTTON, SoulWizard_PORTRAIT, Soul_Wizard);
    }

    public void receivePostInitialize() {
        receiveEditMonsters();
    }

    private void receiveEditMonsters() {
        BaseMod.addMonster("BenMod:RecklessGoblin", RecklessGoblin.NAME, () -> new RecklessGoblin(0.0F, 0.0F));
        BaseMod.addStrongMonsterEncounter("TheCity", new MonsterInfo("BenMod:RecklessGoblin", 1.0F));
    }

    @Override
    public void receiveEditKeywords() {
        Gson gson = new Gson();
        String lang = "ENG";
        if (Settings.language == Settings.GameLanguage.ZHS) {
            lang = "ZHS";
        }

        String json = Gdx.files.internal("BenModResources/localization/" + lang + "/keywords.json")
                .readString(String.valueOf(StandardCharsets.UTF_8));
        Keyword[] keywords = gson.fromJson(json, Keyword[].class);
        if (keywords != null) {
            for (Keyword keyword : keywords) {
                BaseMod.addKeyword("benmod", keyword.NAMES[0], keyword.NAMES, keyword.DESCRIPTION);
            }
        }
    }

    public void receiveEditStrings() {
        String lang = "ENG";
        if (Settings.language == Settings.GameLanguage.ZHS)
            lang = "ZHS";
        BaseMod.loadCustomStringsFile(CardStrings.class, "BenModResources/localization/" + lang + "/cards.json");
        BaseMod.loadCustomStringsFile(RelicStrings.class, "BenModResources/localization/" + lang + "/relics.json");
        BaseMod.loadCustomStringsFile(OrbStrings.class, "BenModResources/localization/" + lang + "/orbs.json");
        BaseMod.loadCustomStringsFile(PowerStrings.class, "BenModResources/localization/" + lang + "/powers.json");
        BaseMod.loadCustomStringsFile(CharacterStrings.class, "BenModResources/localization/" + lang + "/characters.json");
        BaseMod.loadCustomStringsFile(MonsterStrings.class, "BenModResources/localization/" + lang + "/monsters.json");
    }
}