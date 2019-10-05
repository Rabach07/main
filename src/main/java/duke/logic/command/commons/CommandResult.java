package duke.logic.command.commons;

import static java.util.Objects.requireNonNull;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    public static enum DisplayedPage {
        RECIPE,
        ORDER,
        INVENTORY,
        SALE
    }

    private final String feedbackToUser;

    /**
     * Help information should be shown to the user.
     */
    private final DisplayedPage displayedPage;

    /**
     * The application should exit.
     */
    private final boolean exit;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, DisplayedPage displayedPage, boolean exit) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.displayedPage = displayedPage;
        this.exit = exit;
    }

    public CommandResult(String feedbackToUser, DisplayedPage displayedPage) {
        this(feedbackToUser, displayedPage, false);
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public DisplayedPage getDisplayedPage() {
        return displayedPage;
    }

    public boolean isExit() {
        return exit;
    }


}