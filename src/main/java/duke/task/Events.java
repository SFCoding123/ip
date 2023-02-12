package duke.task;

import duke.utilities.Parser;

/**
 * The type Events.
 */
public class Events extends Task {

    private static final int DATEFORMAT = 3;
    /**
     * The Raw input.
     */
    public final String rawInput;
    /**
     * The Start.
     */
    private String start;
    /**
     * The End.
     */
    private String end;

    /**
     * Instantiates a new Events.
     *
     * @param name the name
     * @param done the done
     */
    public Events(String name, boolean done) {
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
        //acceptable format is project meeting /from Mon 2pm /to 4pm
        // or project meeting /from Aug 6th 2pm /to 4pm;
        String[] tokens = name.trim().split("/");

        if (tokens.length < 3) {
            return false;
        }
        String[] tokens2 = tokens[1].split(" ");
        String[] tokens3 = tokens[2].split(" ");

        return tokens.length == 3 && (tokens2.length == 4 || tokens2.length == 3) && tokens3.length == 2;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    private void extract() {
        //requies modifcation for fomatting
        try {
            String[] tokens = taskName.split("/");
            String[] startdate = tokens[1].split(" ");
            String[] enddate = tokens[2].split(" ");
            taskName = tokens[0];
            start = startdate.length == DATEFORMAT ? "(" + startdate[0] + ": " + startdate[1]
                    + " " + startdate[2] + " "
                    : "(" + startdate[0] + ": " + startdate[1] + " "
                    + startdate[2] + " " + startdate[3] + " ";
            end = enddate[0] + ": " + enddate[1] + ")";
        } catch (ArrayIndexOutOfBoundsException e) {
            //exception forwarded to Task manager;
        }
    }

    @Override
    public void add() {
        messageAdd = Parser.ADDED_THIS_TASK
                + Parser.EVENT_UNMARKED_SPACED + taskName + start + end;
    }

    @Override
    public void display() {
        if (isdone) {
            messageDisplay = Parser.EVENT_MARKED + taskName + start + end;
        } else {
            messageDisplay = Parser.EVENT_UNMARKED + taskName + start + end;
        }
    }

    @Override
    public void delete() {
        if (isdone) {
            messageDelete = Parser.REMOVED_THIS_TASK
                    + Parser.EVENT_MARKED_SPACED + taskName + start + end;
        } else {
            messageDelete = Parser.REMOVED_THIS_TASK
                    + Parser.EVENT_UNMARKED_SPACED + taskName + start + end;
        }
    }

    @Override
    public boolean isNull() {
        return taskName.isBlank() || start.isBlank() || end.isBlank();
    }

    @Override
    public void marked() {
        messageMarked = Parser.MARKED_THIS_TASK_AS_DONE
                + Parser.EVENT_MARKED_SPACED + taskName + start + end;
        isdone = true;
    }

    @Override
    public void unmarked() {
        messageUnmarked = Parser.MARKED_THIS_TASK_AS_NOT_DONE_YET
                + Parser.EVENT_UNMARKED_SPACED + taskName + start + end;
        isdone = false;
    }

    @Override
    public String toString() {
        return "E|" + this.isIsdone() + "|" + this.rawInput;
    }
}
