package borknbeans.lightweightinventorysorting.config;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

public enum SortTypes {
    ALPHANUMERIC,
    REVERSE_ALPHANUMERIC,
    DEFAULT_ID,
    RAW_ID;

    public int compare(ItemStack left, ItemStack right) {
        return switch (this) {
            case ALPHANUMERIC -> alphanumeric(left, right);
            case REVERSE_ALPHANUMERIC -> reverseAlphanumeric(left, right);
            case DEFAULT_ID -> defaultId(left, right);
            case RAW_ID -> rawId(left, right);
        };
    }

    private int alphanumeric(ItemStack left, ItemStack right) {
        if (left.isEmpty() && !right.isEmpty()) {
            return 0;
        } else if (right.isEmpty() && !left.isEmpty()) {
            return -1;
        }

        return (left.getName().getString().compareTo(right.getName().getString()));
    }

    private int reverseAlphanumeric(ItemStack left, ItemStack right) {
        if (left.isEmpty() && !right.isEmpty()) {
            return 0;
        } else if (right.isEmpty() && !left.isEmpty()) {
            return -1;
        }

        return (left.getName().getString().compareTo(right.getName().getString())) * -1;
    }

    private int defaultId(ItemStack left, ItemStack right) {
        if (left.isEmpty() && !right.isEmpty()) {
            return 0;
        } else if (right.isEmpty() && !left.isEmpty()) {
            return -1;
        }

        final Text leftName = left.getOrDefault(DataComponentTypes.ITEM_NAME, null), rightName = right.getOrDefault(DataComponentTypes.ITEM_NAME, null);
        return (leftName == null ? "" : leftName.getString()).compareTo(rightName == null ? "" : rightName.getString());
    }

    private int rawId(ItemStack left, ItemStack right) {
        if (left.isEmpty() && !right.isEmpty()) {
            return 0;
        } else if (right.isEmpty() && !left.isEmpty()) {
            return -1;
        }

        return Integer.compare(Item.getRawId(left.getItem()), Item.getRawId(right.getItem()));
    }
}
