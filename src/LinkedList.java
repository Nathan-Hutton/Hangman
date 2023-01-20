/**
 * Implementation of the List interface.
 * 
 * This implementation involves a single-linked list.
 * 
 * @author Nathan Hutton
 *
 */
public class LinkedList implements List {
	// reference to the head of the linked list
	private Node head;

	// number of elements in the list
	private int numberOfElements;

	public LinkedList() {
		head = null;
	}

	/** 
	 * Inner class representing a node in the linked list
	 */

	private class Node
	{
		private Object data;
		private Node next;

		private Node(Object data) {
			this(data,null);
		}

		private Node (Object data, Node next) {
			this.data = data;
			this.next = next;
		}
	}

	// Methods

	@Override
	public void add(Object item) {

		// adds (appends) an item to the rear of the list

		Node newNode = new Node(item);
		Node current = head;

		if (isEmpty()) {
			// special case - first element being added to the list
			head = newNode;
		}
		else {
			while (current.next != null) {
				current = current.next;
			}

			// current now references the last item in the list
			current.next = newNode;
		}

		newNode.next = null;
		++numberOfElements;
	}

	@Override
	public boolean add(Object item, int index) {
		if(isEmpty())
		{
			return false;
		}
		Node current = head;
		Node newNode = new Node(item, null);
		int i = 0;
		if(index == 0)
		{
			newNode.next = current;
			head = newNode;
		}
		while(current != null)
		{
			if(i == index - 1)
			{
				newNode.next = current.next;
				current.next = newNode;
			}
			current = current.next;
			i++;
		}
		return true;
	}

	@Override
	public boolean contains(Object item) {
		Node current = head;
		boolean found = false;

		while (current != null && !found) {
			if (current.data.equals(item)) {
				found = true;
			}

			current = current.next;
		}

		return found;

	}

	@Override
	public Object get(int index) {
		Node current = head;
		int currentIndex = 0;
		while(currentIndex != index)
		{
			if(current.next == null)
			{
				return null;
			}
			current = current.next;
			currentIndex++;
		}
		return current.data;
	}

	@Override
	public boolean remove(Object item) {
		Node current = head;
		int index = 0;
		boolean removed = false;
		while(current != null)
		{
			if(current.data.equals(item))
			{
				remove(index);
				removed = true; 
				break;
			}
			current = current.next;
			index++;
		}
		return removed;
	}

	@Override
	public Object remove(int index) {
		Object rv = null;

		if (isEmpty() || index >= numberOfElements) {
			rv = null;
		}
		else if (index == 0) {
			// special case - first element in the list
			rv = head.data;
			head = head.next;
			numberOfElements--;
		}
		else {
			int currentIndex = 0;
			Node current = head;

			while (currentIndex < (index - 1)) {
				current = current.next;
				currentIndex++;
			}

			// current references the node we want to remove
			rv = current.next.data;
			current.next = current.next.next;
			numberOfElements--;
		}

		return rv;
	}

	@Override
	public int getLength() {
		return numberOfElements;
	}

	@Override
	public boolean isEmpty() {
		if(head == null&&numberOfElements == 0)
		{
			return true;
		}
		return false;
	}

	@Override
	public int getFrequency(Object item) {
		if(head == null)
		{
			return 0;
		}
		int frequency = 0;
		Node current = head;
		while(current != null)
		{
			if(current.data == item)
			{
				frequency++;
			}
			current = current.next;
		}
		return frequency;
	}

	@Override
	public void clear() {
		head = null;
		numberOfElements = 0;
	}
}
