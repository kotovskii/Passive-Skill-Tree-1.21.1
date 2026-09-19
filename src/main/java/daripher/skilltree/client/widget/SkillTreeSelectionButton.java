package daripher.skilltree.client.widget;

import daripher.skilltree.client.screen.SkillTreeScreen;
import daripher.skilltree.client.tooltip.TooltipHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class SkillTreeSelectionButton extends Button {
    private final ResourceLocation skillTreeId;

    public SkillTreeSelectionButton(int x, int y, int width, int height, ResourceLocation skillTreeId) {
        super(x, y, width, height, getDisplayName(skillTreeId));
        setPressFunc(b -> onPress(skillTreeId));
        this.skillTreeId = skillTreeId;
    }

    private static void onPress(ResourceLocation skillTreeId) {
        getMinecraft().setScreen(new SkillTreeScreen(skillTreeId));
    }

    protected void renderBackground(@NotNull GuiGraphics graphics) {
        String texturesFolder = "textures/icons/skill_tree/";
        ResourceLocation texture = skillTreeId.withPrefix(texturesFolder).withSuffix(".png");
        if (getMinecraft().getResourceManager().getResource(texture).isEmpty()) {
            texture = ResourceLocation.parse("skilltree:textures/icons/skill_tree/alchemist.png");
        }
        int v = getTextureVariant() * 19;
        graphics.blit(texture, getX(), getY(), 0, v, width, height, 19, 57);
    }

    protected void renderText(@NotNull GuiGraphics graphics) {
    }

    private static Minecraft getMinecraft() {
        return Minecraft.getInstance();
    }

    private static Component getDisplayName(ResourceLocation skillTreeId) {
        MutableComponent title = Component.translatable(skillTreeId.toString());
        if (title.getString().equals(skillTreeId.toString())) {
            return Component.literal(TooltipHelper.idToName(skillTreeId.getPath()));
        }
        return title;
    }
}
