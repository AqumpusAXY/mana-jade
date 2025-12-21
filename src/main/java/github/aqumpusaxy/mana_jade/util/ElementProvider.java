package github.aqumpusaxy.mana_jade.util;

import github.aqumpusaxy.mana_jade.ui.ManaElement;
import net.minecraft.network.chat.Component;
import snownee.jade.api.ui.BoxStyle;
import snownee.jade.api.ui.IElement;
import snownee.jade.impl.ui.ProgressElement;
import snownee.jade.impl.ui.ProgressStyle;

public class ElementProvider {
    private static final int MANA_TEXT_COLOR = 0xFFFFFFFF;
    private static final int MANA_PROGRESS_COLOR = 0xFF05D5FF;
    private static final int MANA_BORDER_COLOR = 0xFF000000;

    public static IElement manaProgressElement(int mana, int maxMana) {
        float progress = (float) mana / maxMana;
        Component progressText = Component.translatable("tooltip.mana_jade.mana_receiver_storage", mana, maxMana);
        return new ProgressElement(progress, progressText, createManaProgressStyle(), createManaBoxStyle(), true);
    }

    public static IElement infiniteManaProgressElement() {
        float progress = 1;
        Component progressText = Component.translatable("tooltip.mana_jade.creative_mana_pool_storage");
        return new ProgressElement(progress, progressText, createManaProgressStyle(), createManaBoxStyle(), false);
    }

    private static ProgressStyle createManaProgressStyle() {
        ProgressStyle style = new ProgressStyle();
        style.shadow = false;
        style.autoTextColor = false;
        style.textColor(MANA_TEXT_COLOR);
        style.color(MANA_PROGRESS_COLOR);
        style.overlay(new ManaElement());
        return style;
    }

    private static BoxStyle createManaBoxStyle() {
        BoxStyle boxStyle = BoxStyle.DEFAULT;
        boxStyle.borderColor = MANA_BORDER_COLOR;
        return boxStyle;
    }
}
