package utilites;

import tasks.Task;
import java.util.*;

public class InMemoryHistoryManager implements HistoryManager {

    private static Node<Task> head;
    private static Node<Task> tail;
    private static int size;
    final private Map<Integer, Node<Task>> lastTasks = new HashMap<>();

    public List<Task> getHistoryList() {
        return getTasks();
    }

    @Override
    public void add(Task task) {
        if (!lastTasks.containsKey(task.getId())) {
            lastTasks.put(task.getId(), linkLast(task));
        } else if (lastTasks.containsKey(task.getId())){
            removeNode(lastTasks.get(task.getId()));
            lastTasks.put(task.getId(), linkLast(task));
        }
    }

    @Override
    public void remove(Task task) {
        removeNode(lastTasks.get(task.getId()));
        lastTasks.remove(task.getId());
    }

    @Override
    public void deleteAllHistory() {
        lastTasks.clear();
    }

    private Node<Task> linkLast(Task task) {
        final Node<Task> oldTail = tail;
        final Node<Task> newNode = new Node<>(oldTail, task, null);
        tail = newNode;
        if (oldTail == null) {
            head = newNode;
        } else {
            oldTail.next = newNode;
        }
        size++;
        return newNode;
    }

    private List<Task> getTasks() {

        List<Task> tasks = new ArrayList<>();
        Node<Task> currentNode = head;
        while (true) {
            if (currentNode != null) {
                tasks.add(currentNode.data);
                currentNode = currentNode.next;
            } else {
                break;
            }
        }
        return tasks;
    }

    private void removeNode(Node<Task> node) {
        Node<Task> prev = node.prev;
        Node<Task> next = node.next;

        if (node.prev != null && node.next != null) {
            prev.next = next;
            next.prev = prev;
        }
        if (node.prev == null && node.next != null) {
            next.prev = null;
            head = next;
        }
        if (node.prev != null && node.next == null) {
            prev.next = null;
            tail = prev;
        }
    }
}