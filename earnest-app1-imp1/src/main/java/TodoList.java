public class TodoList {
    private Event[] todoList;

    public Event[] getList() {
        return todoList;
    }
    public void setList(Event[] todoList) {
        this.todoList = todoList;
    }

    public TodoList(Event[] todoList) {
        this.setList(todoList);
    }
}
