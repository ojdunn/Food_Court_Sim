package SimFoodCourt;

/**************************************************************************
 * LinkedListQPerson class: This class creates a linked list of the person
 * type for a Queue. 
 * 
 * @author Owen Dunn and Josh Stewart
 * @version 4/18/2017, Project 4, CIS 163
 *************************************************************************/
public class LinkedListQPerson {
		private Person head, tail;
		private int count;
		
		public LinkedListQPerson() {
			count = 0;
			head = tail = null;
		}
		
		/******************************************************************
		 * Adds a new element to the tail of the queue.
		 * @param person
		 * @return none
		 *****************************************************************/
		public void enQ(Person person) {
			Person node = person;
			
			if(count == 0) 
				head = person;
			else
				tail.setNext(node);
				
			tail = node;
			count++;
		}
		
		/******************************************************************
		 * Remove an new element from the head of the queue and returns
		 * reference to it.
		 * @param none
		 * @return person
		 *****************************************************************/
		public Person deQ() {
			if(head == null)
				return null; // do nothing if empty
			
			Person result = head;
			head = head.getNext();
			count--;
			
			if(count == 0)
				tail = null;
			
			return result;	
		}
		
		/******************************************************************
		 * Remove an indexed element from the list.
		 * @param pos position in linked list
		 * @return none
		 *****************************************************************/
		public void remove(int pos) {
			Person next = head, prev = head;
			if(head != null)
				count--;
			
			for(int i = 0; i < pos && next != null; i++) {
				prev = next;
				next = next.getNext();
			}
			if(next != null) {
				if(next == head)
					head = next.getNext();
				else
					prev.setNext(next.getNext());
			}
		}
		
		/******************************************************************
		 * Get an indexed element from the list.
		 * @param pos position in linked list
		 * @return person
		 *****************************************************************/
		public Person get(int pos) {
			Person p = head;
			for(int i = 0;p != null && i < pos; i++) {
				p = p.getNext();
				if(p == null)
					break;
			}
			
			return p;
		}
		
		/******************************************************************
		 * Returns the size of the list.
		 * @param none
		 * @return size of the list
		 *****************************************************************/
		public int size() {
			return count;
		}
}