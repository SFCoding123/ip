# User Guide

## Features

### Keep track of a list of tasks

*Duke* will help the user to keep track of a list of tasks. Tasks can be in the form of `todo`, `event`, or `deadline`.
This list of tasks can be added, deleted and marked as done or undone. If properly triggered, *Alpha Beast* will record the
list of tasks when it is closed and load this record when it is opened next time.

## Usage

Please note that all the sample output below are displayed as text sent from the *Alpha Beast* in GUI application.

### `list` - List out tasks

By entering the `list` command, *Alpha Beast* will print out a list of current tasks *(both marked as done and undone)*.

#### Example

Command: `list`

```
Here are the tasks in your list:
1. [T][] borrow book
2. [D][X] return book (by: Sunday)
3. [E][] project meeting (from: mon 2pm to :4pm)
```

### Add new Task

Depending on the nature of the task, you can add `Todo`, `Event` and `Deadline`.

- `Todo`: `todo {description}`. For example, `todo buy protein` will add a new `Todo` with description `buy protein`
  to the pending list;
- `Event`: `event {description} /from ({day} or {Month day}) {time} /to {time} `. For example, `event project meeting /from Mon 2pm /to 4pm` will add a new `Event` with
  description `project meeting` at the date specified as `(from: Mon 2pm to: 4pm)` ;
- `Deadline`: `deadline {description} /by {time}`. For example, `deadline homework /by 2019-10-10 1800` will add a new
  `Deadline` with description `homework` at the date specified as `Oct 10 2019 1800`.

Please note that *Alpha Beast* only supports the time format of `yyyy-MM-dd` and `yyyy/MM/dd time(24 hours)`
Otherwise, the date cannot be recognised.

#### Example

Command: `todo buy protein`

```
Got it. I've added this task:
[T][ ] buy protein
```

Command: `event project meeting /from Mon 2pm /to 4pm`

```
Got it. I've added this task:
[E][] project meeting (from Mon 2pm to: 4pm)
```

Command: `deadline homework /by 2019-10-10`

```
Got it. I've added this task:
[D][] homework (by: Oct 10 2019)
```

### `mark` - Mark Task done and undone

`mark {index}` will mark the task in the `index` position to be done.
Similarly, `unmark {index}` will mark the task in the `index` position to be undone.

#### Example

Command: `mark 1` assuming the first item is `[T][ ]: buy milk`

```
Nice! I've marked this task as done:
[T][X] buy milk
```

Command: `unmark 1` after the previous command.

```
Nice! I've marked this task as not done yet:
[T][ ] buy milk
```

### `delete` - Delete Task or Mass Ops Delete

`delete {index}` will delete the task at the `index` position.
`delete {index1 , index2 .. index 4}` will all the tasks at the `index` positions.
`delete {Starting index to ending index}` will all the tasks at the `from start index to end index` positions.

#### Example

Command: `delete 7` assuming the 7-th item is `[T][] buy milk` 

```
Noted. I've removed this task:
[T][] buy milk
Now you have 6 tasks in the list.
```

Command: `delete 1 2` assuming the 1st item is `[T][] buy milk` and 2nd item is `[T][] buy egg` 

```
Noted. I've removed this task:
[T][ ] buy egg
Noted. I've removed this task:
[T][ ] buy milk
Now you have 0 tasks in the list.
```

Command: `delete 1-2` assuming the 1st item is `[T][] buy milk` and 2nd item is `[T][] buy egg`

```
Noted. I've removed this task:
[T][] buy milk
Noted. I've removed this task:
[T][] buy egg
Now you have 0 tasks in the list.
```
### `Find` - Search in list of Tasks

`find {argument}` will find all the tasks with `{argument}` included in its description.

#### Example

Command: `find gym` assuming there is one task as `[T][ ] gym` and there is no other task
which contains the keyword `gym` in its description.

```
Here are the tasks in your list:
1. [T][ ] gym
```


### Read and Store task

`bye` command will terminate the program and gets *Duke* to store the current task for usage next time.
When opening the app, *Duke* will automatically load tasks from the previous usage.

#### Example

Command: `bye`

```
Terminal closed
```

