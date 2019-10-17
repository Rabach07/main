package duke.logic.parser.shopping;

import duke.logic.command.shopping.AddShoppingCommand;
import duke.logic.parser.commons.ArgumentMultimap;
import duke.logic.parser.commons.ArgumentTokenizer;
import duke.logic.parser.commons.Parser;
import duke.logic.parser.exceptions.ParseException;
import duke.model.commons.Item;
import duke.model.commons.Quantity;
import duke.model.inventory.Ingredient;
import org.ocpsoft.prettytime.shade.org.apache.commons.lang.StringUtils;

import static duke.logic.parser.commons.CliSyntax.PREFIX_SHOPPING_NAME;
import static duke.logic.parser.commons.CliSyntax.PREFIX_SHOPPING_COST;
import static duke.logic.parser.commons.CliSyntax.PREFIX_SHOPPING_QUANTITY;
import static duke.logic.parser.commons.CliSyntax.PREFIX_SHOPPING_REMARKS;

public class AddShoppingCommandParser implements Parser<AddShoppingCommand> {

    @Override
    public AddShoppingCommand parse(String args) throws ParseException {
        ArgumentMultimap map = ArgumentTokenizer.tokenize(args,
                PREFIX_SHOPPING_NAME,
                PREFIX_SHOPPING_COST,
                PREFIX_SHOPPING_QUANTITY,
                PREFIX_SHOPPING_REMARKS
        );

        Ingredient ingredient = new Ingredient(
                StringUtils.capitalize(map.getValue(PREFIX_SHOPPING_NAME).orElse("").toLowerCase()),
                Double.parseDouble(map.getValue(PREFIX_SHOPPING_COST).orElse(String.valueOf(0))),
                map.getValue(PREFIX_SHOPPING_REMARKS).orElse("")
        );

        Quantity quantity = new Quantity(
                Integer.parseInt(map.getValue(PREFIX_SHOPPING_QUANTITY).orElse(String.valueOf(0)))
        );

        Item<Ingredient> shoppingItem = new Item<Ingredient>(ingredient, quantity);

        return new AddShoppingCommand(shoppingItem);
    }
}