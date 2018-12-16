package io.plainapp.core.data;

import java.util.Comparator;
import java.util.List;

/**
 *  Classes related to sorting {@link PlaidItem}s.
 */
public class PlaidItemSorting {

    /**
     * A comparator that compares {@link PlaidItem}s based on their {@code weight} attribute.
     */
    public static class PlaidItemComparator implements Comparator<PlaidItem> {

        @Override
        public int compare(PlaidItem lhs, PlaidItem rhs) {
            return Float.compare(lhs.getWeight(), rhs.getWeight());
        }
    }

    /**
     *  Interface for weighing a group of {@link PlaidItem}s
     */
    public interface PlaidItemGroupWeigher<T extends PlaidItem> {
        void weigh(List<T> items);
    }

    /**
     *  Applies a weight to a group of {@link PlaidItem}s according to their natural order.
     */
    public static class NaturalOrderWeigher implements PlaidItemGroupWeigher<PlaidItem> {

        @Override
        public void weigh(List<PlaidItem> items) {
            final float step = 1f / (float) items.size();
            for (int i = 0; i < items.size(); i++) {
                PlaidItem item = items.get(i);
                item.setWeight(item.getPage() + ((float) i) * step);
            }
        }
    }

}
