import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TodoListEditListTest {
    @Test
    void test_clearList() {
        //Use a function call of clear list
        //call should empty contents of items array
        //use assertNull or assertEquals to confirm items has no contents
    }

    @Test
    void test_editItem() {
        //Test that if item is created and saved to array, returns edited array
        //Will use an assertEquals for the string at array index and the temp item

        TodoListEditList todolist;
        todolist = new TodoListEditList();

        String tempItem = "Temp item for test purposes";
        //Use a function call of saveItem
        //Using the temp variables as inputs, saveItem will make strings into an array component
        //Save Item will update array
        //String[] tempArray = TodoListItems.saveItem(todolist.items);
        //todolist.items = tempArray;

        //assertArrayEquals(tempArray, todolist.items);
    }

    @Test
    void test_deleteItem() {
        //Test that if item is deleted from normal array
        //Will use an assertEquals for the string at array index and the temp item

        TodoListEditList todolist;
        todolist = new TodoListEditList();

        String tempItem = "Temp item for test purposes";
        //Test that clearing contents works
        todolist.items[0] = tempItem;
        todolist.items[0] = "";

        assertNotEquals(tempItem, todolist.items[0]);
    }

}