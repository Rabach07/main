package duke.logic.parser.product;
import duke.commons.core.Message;
import duke.commons.core.index.Index;
import duke.logic.command.product.EditProductCommand;
import duke.logic.parser.commons.ArgumentMultimap;
import duke.logic.parser.commons.ArgumentTokenizer;
import duke.logic.parser.commons.Parser;
import duke.logic.parser.commons.ParserUtil;
import duke.logic.parser.exceptions.ParseException;

import static duke.logic.parser.commons.CliSyntax.PREFIX_PRODUCT_INGREDIENT;
import static duke.logic.parser.commons.CliSyntax.PREFIX_PRODUCT_INGREDIENT_COST;
import static duke.logic.parser.commons.CliSyntax.PREFIX_PRODUCT_NAME;
import static duke.logic.parser.commons.CliSyntax.PREFIX_PRODUCT_RETAIL_PRICE;
import static duke.logic.parser.commons.CliSyntax.PREFIX_PRODUCT_STATUS;
import static duke.logic.parser.product.ProductParserUtil.createProductDescriptor;

/**
 * A parser that parses {@code EditProductCommand}.
 */
public class EditProductCommandParser implements Parser<EditProductCommand> {
    @Override
    public EditProductCommand parse(String args) throws ParseException {
        ArgumentMultimap map = ArgumentTokenizer.tokenize(args,
                PREFIX_PRODUCT_NAME,
                PREFIX_PRODUCT_INGREDIENT,
                PREFIX_PRODUCT_RETAIL_PRICE,
                PREFIX_PRODUCT_INGREDIENT_COST,
                PREFIX_PRODUCT_STATUS
        );

        Index index;

        try {
            index = ParserUtil.parseIndex(map.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(Message.MESSAGE_INVALID_INDEX);
        }

        return new EditProductCommand(index, createProductDescriptor(map));
    }
}
