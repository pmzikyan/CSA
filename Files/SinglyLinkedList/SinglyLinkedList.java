import java.util.NoSuchElementException;

/**
 *	A Singly Linked List with helper methods.
 * 	clear 	clears the list
 * 	add 	adds an object to an index (or at the end if there's no index parameter)
 * 	size	returns the size of the list
 * 	get		gets the ListNode of a specific index
 * 	set		sets the ListNode of a specific index to an object
 * 	remove	removes a ListNode of a specific index
 * 	isEmpty		returns true if the list is empty and false if it's not
 * 	contains	returns true if the list contains an object; false otherwise
 * 	indexOf		returns the index of the first instance of an object from the list
 *
 *	@author	Petros Mzikyan
 *	@since	4/28/2025
 */
public class SinglyLinkedList<E extends Comparable<E>>
{
	/* Fields */
	private ListNode<E> head, tail;		// head and tail pointers to list
	
	/* No-args Constructors */
	public SinglyLinkedList() {}
	
	/** Copy constructor */
	public SinglyLinkedList(SinglyLinkedList<E> oldList) 
	{
		if (oldList.isEmpty()) 
			head = tail = null;
		else
		{
			head = oldList.get(0);
			tail = head;
			while (tail.getNext() != null)
				tail = tail.getNext();
		}
	}
	
	/**	Clears the list of elements */
	public void clear() { head = tail = null; }
	
	/**	Add the object to the end of the list
	 *	@param obj		the object to add
	 *	@return			true if successful; false otherwise
	 */
	public boolean add(E obj) {
		if (isEmpty()) {
			head = tail = new ListNode<E>(obj);
			return true;
		}
		
		ListNode<E> newNode = new ListNode<E>(obj);
		tail.setNext(newNode);
		tail = newNode;
		return true;
	}
	
	/**	Add the object at the specified index
	 *	@param index		the index to add the object
	 *	@param obj			the object to add
	 *	@return				true if successful; false otherwise
	 *	@throws NoSuchElementException if index does not exist
	 */
	public boolean add(int index, E obj) {
		int size = size();
			
		ListNode<E> newObj = new ListNode(obj);
		if (index == 0) {
			newObj.setNext(head);
			if (isEmpty())
				tail = newObj;
			head = newObj;
			return true;
		}
		if (index == size) {
			add(obj);
		}
		
		ListNode<E> curNode = head;
		for (int i = 0; i < index - 1; i++)
		{
			if (i >= size - 1)
				throw new NoSuchElementException();
			curNode = curNode.getNext();
		}
		ListNode<E> nextObj = curNode.getNext();
		curNode.setNext(newObj);
		newObj.setNext(nextObj);
				
		return true;
	}
	
	/**	@return the number of elements in this list */
	public int size() {
		if (isEmpty())
			return 0;
		int count = 1;
		ListNode<E> curNode = head;
		
		System.out.println(head.getValue() +""+ tail.getValue());
		while (curNode != tail) 
		{
			count++;
			curNode = curNode.getNext();
		}
		
		return count;
	}
	
	/**	Return the ListNode at the specified index
	 *	@param index		the index of the ListNode
	 *	@return				the ListNode at the specified index
	 *	@throws NoSuchElementException if index does not exist
	 */
	public ListNode<E> get(int index) {
		
		int size = size();
		ListNode<E> curNode = head;
		for (int i = 0; i < index; i++)
		{
			if (i >= size)
				throw new NoSuchElementException();
			curNode = curNode.getNext();
		}
		return curNode;
	}
	
	/**	Replace the object at the specified index
	 *	@param index		the index of the object
	 *	@param obj			the object that will replace the original
	 *	@return				the object that was replaced
	 *	@throws NoSuchElementException if index does not exist
	 */
	public E set(int index, E obj) {
		int size = size();
		ListNode<E> curNode = head;
		for (int i = 0; i < index; i++)
		{
			if (i >= size)
				throw new NoSuchElementException();
			curNode = curNode.getNext();
		}
		E oldObj = curNode.getValue();
		curNode.setValue(obj);
		return oldObj;
	}
	
	/**	Remove the element at the specified index
	 *	@param index		the index of the element
	 *	@return				the object in the element that was removed
	 *	@throws NoSuchElementException if index does not exist
	 */
	public E remove(int index) {
		if (isEmpty())
			throw new NoSuchElementException();
			
		E oldObj;
		if (index == 0) {
			oldObj = head.getValue();
			if (size() > 1)
				head = head.getNext();
			else
				clear();
			return oldObj;
		}
		
		int size = size();
		ListNode<E> curNode = head;
		for (int i = 0; i < index - 1; i++)
		{
			if (i >= size)
				throw new NoSuchElementException();
			curNode = curNode.getNext();
		}
		oldObj = curNode.getNext().getValue();
		curNode.setNext(curNode.getNext().getNext());
		
		if (index == size - 1)
		{
			tail = head;
			while (tail.getNext() != null)
				tail = tail.getNext();
		}
		
		return oldObj;
	}
	
	/**	@return	true if list is empty; false otherwise */
	public boolean isEmpty() { return head == null; }
	
	/**	Tests whether the list contains the given object
	 *	@param object		the object to test
	 *	@return				true if the object is in the list; false otherwise
	 */
	public boolean contains(E object) {
		if (isEmpty())
			return false;
		ListNode<E> curNode = head;
		do {
			curNode = curNode.getNext();
			if (curNode.getValue().equals(object))
				return true;	
		}
		while (!curNode.equals(tail));
		
		return false;
	}
	
	/**	Return the first index matching the element
	 *	@param element		the element to match
	 *	@return				if found, the index of the element; otherwise returns -1
	 */
	public int indexOf(E element) {
		if (isEmpty())
			return -1;
		ListNode<E> curNode = head;
		int index = 0;
		do {
			if (curNode.getValue().equals(element))
				return index;
			curNode = curNode.getNext();
			index++;
		}
		while (!curNode.equals(tail));
		return -1;
	}
	
	/**	Prints the list of elements */
	public void printList()
	{
		ListNode<E> ptr = head;
		while (ptr != null)
		{
			System.out.print(ptr.getValue() + "; ");
			ptr = ptr.getNext();
		}
	}
	

}
