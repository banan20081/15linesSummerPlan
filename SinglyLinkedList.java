import java.util.NoSuchElementException;

/**
 *	SinglyLinkedList - (description)
 *
 *	@author	Banan Badran
 *	@since	4/28/2025
 */
public class SinglyLinkedList<E extends Comparable<E>>
{
	/* Fields */
	private ListNode<E> head, tail;		// head and tail pointers to list
	
	/* No-args Constructors */
	public SinglyLinkedList() {
		head = null;
		tail = null;
	}
	
	/** Copy constructor */
	public SinglyLinkedList(SinglyLinkedList<E> oldList) {
		head = oldList.head;;
		tail = oldList.tail;
	}
	
	/**	Clears the list of elements */
	public void clear() {
		//~ int count =0;
		//~ while (this.getNext() != null){
			//~ this.remove(count);
			//~ count++;
		//~ }
		
		head = null;
		tail = null;
	}
	
	/**	Add the object to the end of the list
	 *	@param obj		the object to add
	 *	@return			true if successful; false otherwise
	 */
	public boolean add(E obj) {
		ListNode<E> node = new ListNode<E>(obj); 
		if(tail ==null){
			return false;
		}
		tail.setNext(node);
		tail = node;
		return true;
	}
	
	/**	Add the object at the specified index
	 *	@param index		the index to add the object
	 *	@param obj			the object to add
	 *	@return				true if successful; false otherwise
	 *	@throws NoSuchElementException if index does not exist
	 */
	public boolean add(int index, E obj) {
		
		return false;
	}
	
	/**	@return the number of elements in this list */
	public int size() {
		int count =0;
		if(head == null || tail == null){
			return count;
		}
		count++;
		ListNode<E> current = head;
		while(current.getNext() != null){
			current = current.getNext();
			count++;
		}
		return count;
	}
	
	/**	Return the ListNode at the specified index
	 *	@param index		the index of the ListNode
	 *	@return				the ListNode at the specified index
	 *	@throws NoSuchElementException if index does not exist
	 */
	public ListNode<E> get(int index) {
		
		return null;
	}
	
	/**	Replace the object at the specified index
	 *	@param index		the index of the object
	 *	@param obj			the object that will replace the original
	 *	@return				the object that was replaced
	 *	@throws NoSuchElementException if index does not exist
	 */
	public E set(int index, E obj) {
		
		return null;
	}
	
	/**	Remove the element at the specified index
	 *	@param index		the index of the element
	 *	@return				the object in the element that was removed
	 *	@throws NoSuchElementException if index does not exist
	 */
	public E remove(int index) {
		
		return null;
	}
	
	/**	@return	true if list is empty; false otherwise */
	public boolean isEmpty() {
		
		return false;
	}
	
	/**	Tests whether the list contains the given object
	 *	@param object		the object to test
	 *	@return				true if the object is in the list; false otherwise
	 */
	public boolean contains(E object) {
		
		return false;
	}
	
	/**	Return the first index matching the element
	 *	@param element		the element to match
	 *	@return				if found, the index of the element; otherwise returns -1
	 */
	public int indexOf(E element) {
		int index=-1;
		ListNode<E> current = head;
		
		while(current.getNext()!=null){
			if(curren.equals(element)){
				return index;
			}
			index++;
		}
		
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
