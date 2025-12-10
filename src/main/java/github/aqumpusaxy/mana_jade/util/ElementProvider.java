package github.aqumpusaxy.mana_jade.util;

import github.aqumpusaxy.mana_jade.ui.ManaElement;
import net.minecraft.network.chat.Component;
import snownee.jade.api.ui.BoxStyle;
import snownee.jade.api.ui.IElement;
import snownee.jade.impl.ui.ProgressElement;
import snownee.jade.impl.ui.ProgressStyle;

public class ElementProvider {
    public static IElement manaProgressElement(int mana, int maxMana) {
        float progress = (float) mana / maxMana;

        Component progressText = Component.translatable("tooltip.mana_jade.mana_receiver_storage", mana, maxMana);

        ProgressStyle style = new ProgressStyle();
        style.shadow = false;
        style.autoTextColor = false;
        style.textColor(0xFFFFFFFF);
        style.color(0xFF05D5FF);

        style.overlay(new ManaElement());

        BoxStyle boxStyle = BoxStyle.DEFAULT;
        boxStyle.borderColor = 0xFF000000;

        return new ProgressElement(progress, progressText, style, boxStyle, true);
    }

    public static IElement infiniteManaProgressElement() {
        float progress = 1;

        Component progressText = Component.translatable("tooltip.mana_jade.creative_mana_pool_storage");

        ProgressStyle style = new ProgressStyle();
        style.shadow = false;
        style.autoTextColor = false;
        style.textColor(0xFFFFFFFF);
        style.color(0xFF05D5FF);

        style.overlay(new ManaElement());

        BoxStyle boxStyle = BoxStyle.DEFAULT;
        boxStyle.borderColor = 0xFF000000;

        return new ProgressElement(progress, progressText, style, boxStyle, false);
    }
}
