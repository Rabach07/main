package duke.logic.command.order;

import duke.logic.command.CommandResult;
import duke.logic.command.exceptions.CommandException;
import duke.model.Model;
import duke.model.commons.Item;
import duke.model.order.Customer;
import duke.model.order.Order;
import duke.model.product.Product;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import static java.util.Objects.requireNonNull;

/**
 * A command to add an order to BakingHome.
 */
public class AddOrderCommand extends OrderCommand {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_SUCCESS = "New order added [Order ID: %s]";

    private static final String DEFAULT_CUSTOMER_NAME = "customer";
    private static final String DEFAULT_CUSTOMER_CONTACT = "N/A";
    private static final Date DEFAULT_DELIVERY_DATE = Calendar.getInstance().getTime();
    private static final String DEFAULT_REMARKS = "N/A";
    private static final Order.Status DEFAULT_STATUS = Order.Status.ACTIVE;
    private final OrderDescriptor addOrderDescriptor;

    /**
     * Creates an AddOrderCommand to add the specified {@code Order}.
     *
     * @param addOrderDescriptor details of the order to add
     */
    public AddOrderCommand(OrderDescriptor addOrderDescriptor) {
        requireNonNull(addOrderDescriptor);
        this.addOrderDescriptor = addOrderDescriptor;
    }


    public CommandResult execute(Model model) throws CommandException {

        Order toAdd = createOrder(addOrderDescriptor, model.getFilteredProductList());
        model.addOrder(toAdd);
        model.commit();

        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd.getId()), CommandResult.DisplayedPage.ORDER);
    }

    private Order createOrder(OrderDescriptor descriptor, List<Product> allProducts) throws CommandException {
        return new Order(new Customer(descriptor.getCustomerName().orElse(DEFAULT_CUSTOMER_NAME),
                descriptor.getCustomerContact().orElse(DEFAULT_CUSTOMER_CONTACT)),
                descriptor.getDeliveryDate().orElse(DEFAULT_DELIVERY_DATE),
                descriptor.getStatus().orElse(DEFAULT_STATUS),
                descriptor.getRemarks().orElse(DEFAULT_REMARKS),
                OrderCommandUtil.getProducts(
                        allProducts,
                        descriptor.getItems()
                                .orElse(new HashSet<Item<String>>()))
        );
    }
}
