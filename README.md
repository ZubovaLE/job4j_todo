This repository presents a "ToDo list" application.

When the application starts, an authorization page appears:  
![ScreenShot](images/0_Authorization.png)

We must log in or register if we are not registered:  
![ScreenShot](images/1_1_Registration.png)

If the registration was successful, then we get to the success page, from where we can go to the authorization page:
![ScreenShot](images/1_2_success_registration.png)

After we log in, a home page appears, and we can view all tasks added to the to-do list:
![ScreenShot](images/2_1_all_items.png)

The home page has three links on the top (all/done/new) and a table with tasks that is changing depending on the
selected link. There are three columns in the table: name, date of creation and status (completed or not completed).

The "done" link:  
![ScreenShot](images/2_2_completed_items.png)

The "new" link:
![ScreenShot](images/2_3_new_items.png)

The home page also has the "add a task" button that takes the user to an edit page:
![ScreenShot](images/3_task_creation.png)

On the home page, clicking on a task takes us to a page with detailed description of the task:
![ScreenShot](images/4_1_description_when_not_done.png)

There are three buttons on the description page: done, edit and delete, which help us to interact with the task.

The "edit" button takes the user to the edit page where he can edit the task (its name and description):
![ScreenShot](images/5_editing.png)

If the "done" button is clicked, the task is transferred to the completed status:
![ScreenShot](images/4_2_description_when_done.png)

The "delete" button deletes the task and takes the user to the list of all tasks.
![ScreenShot](images/6_after_deleting.png)