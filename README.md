Android application using Java and SequelLite DataBase:

1. Acc2 Class:
Purpose: Splash screen activity to display a logo briefly before navigating to the main activity.
Uses a Handler to delay the transition to the MainActivity.

2. AddNewTaskStorageAdap Class:
Purpose: Bottom sheet dialog fragment for adding or editing tasks.
Uses a TextWatcher to dynamically enable/disable the save button based on text input.
Communicates with the hosting activity using the OnDialogCloseListener interface.

3. DataBaseHelper Class:
Purpose: Manages SQLite database operations for the To-Do List.
Creates a table for tasks with columns for ID, task description, and status.
Provides methods for inserting, updating, deleting, and retrieving tasks.

4. MainActivity Class:
Purpose: Main activity displaying a RecyclerView of tasks.
Uses RecyclerViewTouchHelper for swipe actions (edit and delete).
Implements OnDialogCloseListener to update the RecyclerView when a dialog is closed.
Integrates a FloatingActionButton for adding new tasks.

5. Model Class:
Purpose: Represents a model for tasks with fields for ID, task description, and status.
Simple data structure used to encapsulate task-related information.

6. OnDialogCloseListener Interface:
Purpose: Defines an interface with a method (onDialogClose) for handling dialog close events.

7. RecyclerViewTouchHelper Class:
Purpose: Implements swipe actions on RecyclerView items (edit and delete).
Uses RecyclerViewSwipeDecorator for visual enhancements during swiping.

8. ToDoAdapter Class:
Purpose: Manages data binding between Model objects and the RecyclerView.
Handles creation of ViewHolders, binds data, and provides methods for updating, deleting, and editing tasks.
Implements CheckBox listener for updating task status.
Integrates an edit operation by showing the AddNewTaskStorageAdap dialog.
The application follows the typical structure of an Android application, using activities, fragments, adapters, and a SQLite database for task management. The UI is implemented with a RecyclerView, and users can interact with tasks through swipe gestures and a bottom sheet dialog.
