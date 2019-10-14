package duke.logic;

import duke.logic.command.Command;
import duke.logic.command.CommandResult;
import duke.logic.command.exceptions.CommandException;
import duke.logic.command.order.AddOrderCommand;
import duke.logic.command.order.CompleteOrderCommand;
import duke.logic.command.order.DeleteOrderCommand;
import duke.logic.command.order.EditOrderCommand;
import duke.logic.command.order.OrderCommand;
import duke.logic.command.product.AddProductCommand;
import duke.logic.command.product.EditProductCommand;
import duke.logic.command.product.ProductCommand;
import duke.logic.parser.commons.AutoCompleter;
import duke.logic.parser.commons.BakingHomeParser;
import duke.logic.parser.exceptions.ParseException;
import duke.model.Model;
import duke.model.commons.Ingredient;
import duke.model.order.Order;
import duke.model.product.Product;
import duke.storage.Storage;
import javafx.collections.ObservableList;

public class LogicManager implements Logic {
    private final Model model;
    private final Storage storage;
    private final BakingHomeParser bakingHomeParser;
    private final AutoCompleter autoCompleter;

    /**
     * Creates a logic manager.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        this.bakingHomeParser = new BakingHomeParser();
        this.autoCompleter = new AutoCompleter();
        addFieldsToAutoComplete();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        CommandResult commandResult;
        Command command = bakingHomeParser.parseCommand(commandText);
        commandResult = command.execute(model);

//        try {
//            storage.saveAddressBook(model.getAddressBook());
//        } catch (IOException ioe) {
//            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
//        }

        return commandResult;
    }

    @Override
    public Boolean isAutoCompletable(AutoCompleter.UserInputState currentState) {
        return autoCompleter.isAutoCompletable(currentState);
    }

    @Override
    public AutoCompleter.UserInputState complete() {
        return autoCompleter.complete();
    }

    @Override
    public ObservableList<Order> getFilteredOrderList() {
        return model.getFilteredOrderList();
    }

    @Override
    public ObservableList<Product> getFilteredProductList() {
        model.updateFilteredProductList(Model.PREDICATE_SHOW_ACTIVE_PRODUCTS);
        return model.getFilteredProductList();
    }

    @Override
    public ObservableList<Ingredient> getFilteredInventoryList() {
        return model.getFilteredInventoryList();
    }

    private void addFieldsToAutoComplete() {
        autoCompleter.addCommand(OrderCommand.class);
        autoCompleter.addCommand(AddOrderCommand.class);
        autoCompleter.addCommand(DeleteOrderCommand.class);
        autoCompleter.addCommand(EditOrderCommand.class);
        autoCompleter.addCommand(CompleteOrderCommand.class);
        autoCompleter.addCommand(ProductCommand.class);
        autoCompleter.addCommand(AddProductCommand.class);
        autoCompleter.addCommand(EditProductCommand.class);

    }
}
