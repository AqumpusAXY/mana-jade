package github.aqumpusaxy.mana_jade.util;

public class ColorUtil {
    public static int rgbToHex(float r, float g, float b) {
        int red = Math.max(0, Math.min(255, (int) Math.round(r * 255)));
        int green = Math.max(0, Math.min(255, (int) Math.round(g * 255)));
        int blue = Math.max(0, Math.min(255, (int) Math.round(b * 255)));

        return red << 16 | green << 8 | blue;
    }
}
