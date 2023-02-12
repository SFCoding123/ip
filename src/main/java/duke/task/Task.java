package duke.task;

/**
 * The type Task.
 */
public abstract class Task {
    /**
     * The Task name.
     */
    protected String taskName;
    /**
     * The Message add.
     */
    protected String messageAdd;
    /**
     * The Message marked.
     */
    protected String messageMarked;
    /**
     * The Message unmarked.
     */
    protected String messageUnmarked;
    /**
     * The Message display.
     */
    protected String messageDisplay;
    /**
     * The Message delete.
     */
    protected String messageDelete;
    /**
     * The Done.
     */
    protected boolean isdone;

    /**
     * Instantiates a new Task.
     *
     * @param name the name
     * @param isdone the done
     */
    Task(String name, boolean isdone) {
        this.taskName = name;
        this.isdone = isdone;
        this.messageAdd = "";
        this.messageMarked = "";
        this.messageUnmarked = "";
        this.messageDelete = "";
    }

    public String getTaskName() {
        return taskName;
    }

    public String getMessageAdd() {
        return messageAdd;
    }

    public String getMessageMarked() {
        return messageMarked;
    }

    public String getMessageUnmarked() {
        return messageUnmarked;
    }

    public String getMessageDisplay() {
        return messageDisplay;
    }

    public String getMessageDelete() {
        return messageDelete;
    }

    public boolean isIsdone() {
        return isdone;
    }

    /**
     * Add.
     */
    public abstract void add();

    /**
     * Marked.
     */
    public abstract void marked();

    /**
     * Unmarked.
     */
    public abstract void unmarked();

    /**
     * Display.
     */
    public abstract void display();

    /**
     * Delete.
     */
    public abstract void delete();

    public abstract boolean isNull();


    //todo create one for to check format
}
