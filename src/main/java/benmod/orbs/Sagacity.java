package benmod.orbs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.vfx.combat.OrbFlareEffect;
import com.megacrit.cardcrawl.vfx.combat.PlasmaOrbActivateEffect;
import com.megacrit.cardcrawl.vfx.combat.PlasmaOrbPassiveEffect;
import com.megacrit.cardcrawl.vfx.combat.OrbFlareEffect.OrbFlareColor;

public class Sagacity extends AbstractOrb {
    public static final String ORB_ID = "BenMod:Sagacity";
    private static final OrbStrings orbString;
    public static final String[] DESC;
    private float vfxTimer = 1.0F;
    private float vfxIntervalMin = 0.1F;
    private float vfxIntervalMax = 0.4F;
    private static final float ORB_WAVY_DIST = 0.04F;
    private static final float PI_4 = 12.566371F;

    public Sagacity() {
        this.ID = "BenMod:Sagacity";
        this.img = new Texture("BenModResources/images/Orbs/Sagacity.png");
        this.name = orbString.NAME;
        this.baseEvokeAmount = 3;
        this.evokeAmount = this.baseEvokeAmount;
        this.basePassiveAmount = 2;
        this.passiveAmount = this.basePassiveAmount;
        this.updateDescription();
        this.angle = MathUtils.random(360.0F);
        this.channelAnimTimer = 0.5F;
    }

    public void updateDescription() {
//        this.applyFocus();
        this.description = DESC[0] + this.passiveAmount + DESC[1] + this.evokeAmount + DESC[2];
    }

    public void onEvoke() {
//        int focusAmount = 0;
//        if (AbstractDungeon.player.getPower("Focus") != null ) {
//            focusAmount += AbstractDungeon.player.getPower("Focus").amount;
//        }
//        if(focusAmount>=-baseEvokeAmount)
//            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new FocusPower(AbstractDungeon.player, this.evokeAmount-focusAmount), this.evokeAmount-focusAmount));
//        else
        AbstractDungeon.actionManager.addToTop(new DrawCardAction(AbstractDungeon.player, this.baseEvokeAmount));
    }

    public void onStartOfTurn() {
        AbstractDungeon.actionManager.addToBottom(new VFXAction(new OrbFlareEffect(this, OrbFlareColor.PLASMA), 0.1F));
//        int focusAmount = 0;
//        if (AbstractDungeon.player.getPower("Focus") != null ) {
//            focusAmount += AbstractDungeon.player.getPower("Focus").amount;
//        }
//        if(focusAmount>=-basePassiveAmount)
//            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new FocusPower(AbstractDungeon.player, this.passiveAmount-focusAmount), this.passiveAmount-focusAmount));
//        else
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, this.basePassiveAmount));
    }

    public void triggerEvokeAnimation() {
        CardCrawlGame.sound.play("ORB_PLASMA_EVOKE", 0.1F);
        AbstractDungeon.effectsQueue.add(new PlasmaOrbActivateEffect(this.cX, this.cY));
    }

    public void updateAnimation() {
        super.updateAnimation();
        this.angle += Gdx.graphics.getDeltaTime() * 45.0F;
        this.vfxTimer -= Gdx.graphics.getDeltaTime();
        if (this.vfxTimer < 0.0F) {
            AbstractDungeon.effectList.add(new PlasmaOrbPassiveEffect(this.cX, this.cY));
            this.vfxTimer = MathUtils.random(this.vfxIntervalMin, this.vfxIntervalMax);
        }

    }

    public void render(SpriteBatch sb) {
        this.shineColor.a = this.c.a / 2.0F;
        sb.setColor(this.shineColor);
        sb.draw(this.img, this.cX - 48.0F, this.cY - 48.0F + this.bobEffect.y, 48.0F, 48.0F, 96.0F, 96.0F, this.scale + MathUtils.sin(this.angle / 12.566371F) * 0.04F * Settings.scale, this.scale, this.angle, 0, 0, 96, 96, false, false);
        sb.setBlendFunction(770, 1);
        sb.draw(this.img, this.cX - 48.0F, this.cY - 48.0F + this.bobEffect.y, 48.0F, 48.0F, 96.0F, 96.0F, this.scale, this.scale + MathUtils.sin(this.angle / 12.566371F) * 0.04F * Settings.scale, -this.angle, 0, 0, 96, 96, false, false);
        sb.setBlendFunction(770, 771);
        this.renderText(sb);
        this.hb.render(sb);
    }

    protected void renderText(SpriteBatch sb) {
        if (this.showEvokeValue) {
            FontHelper.renderFontCentered(sb, FontHelper.cardEnergyFont_L, Integer.toString(this.baseEvokeAmount), this.cX + NUM_X_OFFSET, this.cY + this.bobEffect.y / 2.0F + NUM_Y_OFFSET - 4.0F * Settings.scale, new Color(0.2F, 1.0F, 1.0F, this.c.a), this.fontScale);
        }

    }

    public void playChannelSFX() {
        CardCrawlGame.sound.play("ORB_PLASMA_CHANNEL", 0.1F);
    }

    public AbstractOrb makeCopy() {
        return new benmod.orbs.Sagacity();
    }

    static {
        orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
        DESC = orbString.DESCRIPTION;
    }
}
