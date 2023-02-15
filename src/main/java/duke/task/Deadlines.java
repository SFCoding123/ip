package duke.task;

import duke.utilities.DateTranslator;
import duke.utilities.Parser;

/**
 * The type Deadlines.
 */
public class Deadlines extends Task {
    /**
     * The constant FORMATSIZE.
     */
    private static final int FORMATSIZE = 3;
    /**
     * The Raw input.
     */
    public final String rawInput;
    /**
     * The End date.
     */
    private String endDate;
    /**
     * The Date translator.
     */
    private DateTranslator dateTranslator;

    /**
     * Instantiates a new Deadlines.
     *
     * @param name the name
     * @param done the done
     */
    public Deadlines(String name, boolean done) {
        super(name, done);
        rawInput = name;
        extract();

    }

    /**
     * Is format boolean.
     *
     * @param name the name
     * @return the boolean
     */
    public static boolean isFormat(String name) {
        //acceptable format is xxx /by Sunday 2pm ....
        // or xxx/by 2/12/2019 1800
        // or xxx/by 2019-12-2

        String[] tokens = name.trim().split("/");

        if (tokens.length == 2) {
            if (tokens[0].contains("-")) {
                String[] tokens2 = tokens[0].split("-");
                return tokens2.length == 3;
            }
            return true;
        } else {
            return tokens.length == 4;
        }
    }

    public String getEndDate() {
        return endDate;
    }

    private void extract() {
        try {
            String[] tokens = taskName.split("/");
            taskName = tokens[0];

            if (!DateTranslator.isDate(rawInput)) {

                String[] date = tokens[1].split(" ");
                StringBuilder temp = new StringBuilder("(" + date[0] + ": ");
                if (date.length > FORMATSIZE) {
                    for (int x = 1; x < date.length; x++) {
                        temp.append(date[x]).append(" ");
                    }
                    temp.append(")");
                    endDate = temp.toString();
                } else {
                    endDate = date.length == 2 ? "(" + date[0] + ": " + date[1] + ")"
                            : "(" + date[0] + ": " + date[1] + " " + date[2] + ")";
                }

            } else {

                dateTranslator = new DateTranslator(rawInput);
                endDate = " (by: " + dateTranslator.getOutput() + ")";
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            //exception forwarded to Task manager;
        }

    }

    @Override
    public void add() {
        messageAdd = Parser.ADDED_THIS_TASK
                + Parser.DEADLINE_UNMARKED_SPACED + taskName + endDate;
    }

    @Override
    public void display() {
        if (isdone) {
            messageDisplay = Parser.DEADLINE_MARKED + taskName + endDate;
        } else {
            messageDisplay = Parser.DEADLINE_UNMARKED + taskName + endDate;
        }
    }

    @Override
    public void delete() {
        if (isdone) {
            messageDelete = Parser.REMOVED_THIS_TASK
                    + Parser.DEADLINE_MARKED_SPACED + taskName + endDate;
        } else {
            messageDelete = Parser.REMOVED_THIS_TASK
                    + Parser.DEADLINE_UNMARKED_SPACED + taskName + endDate;
        }
    }

    @Override
    public boolean isNull() {
        return taskName.isBlank() || endDate.isBlank() || endDate.matches("wrong Date format");
    }


    @Override
    public void marked() {
        messageMarked = Parser.MARKED_THIS_TASK_AS_DONE
                + Parser.DEADLINE_MARKED_SPACED + taskName + endDate;
        isdone = true;
    }

    @Override
    public void unmarked() {
        messageUnmarked = Parser.MARKED_THIS_TASK_AS_NOT_DONE_YET
                + Parser.DEADLINE_UNMARKED_SPACED + taskName + endDate;
        isdone = false;
    }

    @Override
    public String toString() {
        return "D|" + this.isIsdone() + "|" + this.rawInput;
    }
}
