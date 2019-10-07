package duke.model;

import duke.model.order.Order;
import duke.model.shortcut.Shortcut;
import javafx.collections.ObservableList;

import java.util.List;

/**
 * Unmodifiable view of an address book.
 */
public interface ReadOnlyBakingHome {

    /**
     * Returns an unmodifiable view of the order list.
     */
    ObservableList<Order> getOrderList();

    List<Shortcut> getShortcutList();
}
