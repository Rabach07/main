package duke.logic.command.order;

import duke.commons.core.Message;
import duke.commons.core.index.Index;
import duke.logic.command.CommandResult;
import duke.logic.command.exceptions.CommandException;
import duke.model.Model;
import duke.model.order.Order;

import java.util.List;

import static duke.commons.util.CollectionUtil.requireAllNonNull;
import static java.util.Objects.requireNonNull;

/**
 * A command to edit the details of an existing order.
 */
public class EditOrderCommand extends OrderCommand {

    public static final String COMMAND_WORD = "edit";

    private static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Order [%1$s]";

    private final Index index;
    private final OrderDescriptor orderDescriptor;

    /**
     * Creates an EditOrderCommand to modify the details of an {@code Order}.
     *
     * @param index               of the the order in the filtered order list
     * @param orderDescriptor details to edit the order with
     */
    public EditOrderCommand(Index index, OrderDescriptor orderDescriptor) {
        requireAllNonNull(index, orderDescriptor);

        this.index = index;
        this.orderDescriptor = new OrderDescriptor(orderDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Order> lastShownList = model.getFilteredOrderList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Message.MESSAGE_INVALID_INDEX);
        }

        Order orderToEdit = lastShownList.get(index.getZeroBased());
        Order editedOrder = OrderCommandUtil.createNewOrder(orderToEdit, orderDescriptor,
                model.getFilteredProductList());

        model.setOrder(orderToEdit, editedOrder);
        model.updateFilteredOrderList(Model.PREDICATE_SHOW_ALL_ORDERS);

        model.commit();

        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedOrder.getId()),
                CommandResult.DisplayedPage.ORDER);
    }

}
