package benmod.orbs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.defect.LightningOrbEvokeAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.vfx.combat.*;
import com.megacrit.cardcrawl.vfx.combat.OrbFlareEffect.OrbFlareColor;

public class Destruction extends AbstractOrb {
    public static final String ORB_ID = "BenMod:Destruction";
    private static final OrbStrings orbString;
    public static final String[] DESC;
    private float vfxTimer = 1.0F;
    private static final float PI_DIV_16 = 0.19634955F;
    private static final float ORB_WAVY_DIST = 0.05F;
    private static final float PI_4 = 12.566371F;
    private static final float ORB_BORDER_SCALE = 1.2F;

    public Destruction() {
        this.ID = "BenMod:Destruction";
        this.img = new Texture("BenModResources/images/Orbs/Destruction.png");
        this.name = orbString.NAME;
        this.baseEvokeAmount = 12;
        this.evokeAmount = this.baseEvokeAmount;
        this.basePassiveAmount = 7;
        this.passiveAmount = this.basePassiveAmount;
        this.updateDescription();
        this.angle = MathUtils.random(360.0F);
        this.channelAnimTimer = 0.5F;
    }

    public void updateDescription() {
        this.applyFocus();
        this.description = orbString.DESCRIPTION[0] + this.passiveAmount + orbString.DESCRIPTION[1] + this.evokeAmount + orbString.DESCRIPTION[2];
    }

    public void onEvoke() {
        AbstractDungeon.actionManager.addToTop(new LightningOrbEvokeAction(new DamageInfo(AbstractDungeon.player, this.evokeAmount, DamageInfo.DamageType.THORNS), true));
    }

    public void onEndOfTurn() {
        float speedTime = 0.2F / (float)AbstractDungeon.player.orbs.size();
        if (Settings.FAST_MODE) {
            speedTime = 0.0F;
        }
        AbstractDungeon.actionManager.addToBottom(new VFXAction(new OrbFlareEffect(this, OrbFlareColor.LIGHTNING), speedTime));
        AbstractDungeon.actionManager.addToBottom(new LightningOrbEvokeAction(new DamageInfo(AbstractDungeon.player, this.passiveAmount, DamageInfo.DamageType.THORNS), true));
    }

    private void triggerPassiveEffect(DamageInfo info, boolean hitAll) {
        if (!hitAll) {
            AbstractCreature m = AbstractDungeon.getRandomMonster();
            if (m != null) {
                float speedTime = 0.2F / (float)AbstractDungeon.player.orbs.size();
                if (Settings.FAST_MODE) {
                    speedTime = 0.0F;
                }

                AbstractDungeon.actionManager.addToBottom(new DamageAction(m, info, AbstractGameAction.AttackEffect.NONE, true));
                AbstractDungeon.actionManager.addToBottom(new VFXAction(new OrbFlareEffect(this, OrbFlareColor.LIGHTNING), speedTime));
                AbstractDungeon.actionManager.addToBottom(new VFXAction(new LightningEffect(m.drawX, m.drawY), speedTime));
                AbstractDungeon.actionManager.addToBottom(new SFXAction("ORB_LIGHTNING_EVOKE"));
            }
        } else {
            float speedTime = 0.2F / (float)AbstractDungeon.player.orbs.size();
            if (Settings.FAST_MODE) {
                speedTime = 0.0F;
            }

            AbstractDungeon.actionManager.addToBottom(new VFXAction(new OrbFlareEffect(this, OrbFlareColor.LIGHTNING), speedTime));
            AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(AbstractDungeon.player, DamageInfo.createDamageMatrix(info.base, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.NONE));

            for(AbstractMonster m3 : AbstractDungeon.getMonsters().monsters) {
                if (!m3.isDeadOrEscaped() && !m3.halfDead) {
                    AbstractDungeon.actionManager.addToBottom(new VFXAction(new LightningEffect(m3.drawX, m3.drawY), speedTime));
                }
            }

            AbstractDungeon.actionManager.addToBottom(new SFXAction("ORB_LIGHTNING_EVOKE"));
        }

    }

    public void triggerEvokeAnimation() {
        CardCrawlGame.sound.play("ORB_LIGHTNING_EVOKE", 0.1F);
        AbstractDungeon.effectsQueue.add(new LightningOrbActivateEffect(this.cX, this.cY));
    }

    public void updateAnimation() {
        super.updateAnimation();
        this.angle += Gdx.graphics.getDeltaTime() * 180.0F;
        this.vfxTimer -= Gdx.graphics.getDeltaTime();
        if (this.vfxTimer < 0.0F) {
            AbstractDungeon.effectList.add(new LightningOrbPassiveEffect(this.cX, this.cY));
            if (MathUtils.randomBoolean()) {
                AbstractDungeon.effectList.add(new LightningOrbPassiveEffect(this.cX, this.cY));
            }

            this.vfxTimer = MathUtils.random(0.15F, 0.8F);
        }

    }

    public void render(SpriteBatch sb) {
        this.shineColor.a = this.c.a / 2.0F;
        sb.setColor(this.shineColor);
        sb.setBlendFunction(770, 1);
        sb.draw(this.img, this.cX - 48.0F, this.cY - 48.0F + this.bobEffect.y, 48.0F, 48.0F, 96.0F, 96.0F, this.scale + MathUtils.sin(this.angle / 12.566371F) * 0.05F + 0.19634955F, this.scale * 1.2F, this.angle, 0, 0, 96, 96, false, false);
        sb.draw(this.img, this.cX - 48.0F, this.cY - 48.0F + this.bobEffect.y, 48.0F, 48.0F, 96.0F, 96.0F, this.scale * 1.2F, this.scale + MathUtils.sin(this.angle / 12.566371F) * 0.05F + 0.19634955F, -this.angle, 0, 0, 96, 96, false, false);
        sb.setBlendFunction(770, 771);
        sb.setColor(this.c);
        sb.draw(this.img, this.cX - 48.0F, this.cY - 48.0F + this.bobEffect.y, 48.0F, 48.0F, 96.0F, 96.0F, this.scale, this.scale, this.angle / 12.0F, 0, 0, 96, 96, false, false);
        this.renderText(sb);
        this.hb.render(sb);
    }

    public void playChannelSFX() {
        CardCrawlGame.sound.play("ORB_LIGHTNING_CHANNEL", 0.1F);
    }

    public AbstractOrb makeCopy() {
        return new Destruction();
    }

    static {
        orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
        DESC = orbString.DESCRIPTION;
    }
}
