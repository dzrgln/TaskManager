package utilites;

import tasks.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryHistoryManager implements HistoryManager {

    private static Node<Task> head;
    private static Node<Task> tail;
    private static int size;
    private final Map<Integer, Node<Task>> lastTasks = new HashMap<>();

    public List<Task> getHistoryList() {
        return getTasks();
    }

    @Override
    public void add(Task task) {
        if (!lastTasks.containsKey(task.getId())) {
            lastTasks.put(task.getId(), linkLast(task));
        } else {
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
        Node<Task> currentNode = head;
        while (currentNode != null) {
            currentNode.setData(null);
            currentNode = currentNode.getNext();
        }
        lastTasks.clear();
    }

    private Node<Task> linkLast(Task task) {
        final Node<Task> oldTail = tail;
        final Node<Task> newNode = new Node<>(oldTail, task, null);
        tail = newNode;
        if (oldTail == null) {
            head = newNode;
        } else {
            oldTail.setNext(newNode);
        }
        size++;
        return newNode;
    }

    private List<Task> getTasks() {

        List<Task> tasks = new ArrayList<>();
        Node<Task> currentNode = head;
        while (currentNode != null) {
            if (currentNode.getData() != null) {
                tasks.add(currentNode.getData());
            }
            currentNode = currentNode.getNext();
        }
        return tasks;
    }

    private void removeNode(Node<Task> node) {

        if (node != null) {
            Node<Task> prev = node.getPrev();
            Node<Task> next = node.getNext();

            if (prev != null && next != null) {
                prev.setNext(next);
                next.setPrev(prev);
            }
            if (prev == null && next != null) {
                next.setPrev(null);
                head = next;
            }
            if (prev != null && next == null) {
                prev.setNext(null);
                tail = prev;
            }
            if (prev == null && next == null) {
                lastTasks.remove(node.getData().getId());
                node.setData(null);
                head = null;
                tail = null;
            }
            size--;
        }
    }
}