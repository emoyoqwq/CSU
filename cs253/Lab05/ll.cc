#include "ll.h" 
#include <cstdlib>
#include <iostream>


			// This linked list is empty

bool LinkedList::insert(unsigned uiData) {
    Link* new_link=new Link(uiData, m_pHead); // Fill it with data.
    m_pHead = new_link;			// Put it at the head.

    return true;				// Indicate success.
}

bool LinkedList::remove(unsigned &pData) {
    if (!m_pHead)				// Empty list?
	return false;				// Indicate failure.

    Link *temp = m_pHead;			// Point to the first node.
    m_pHead = m_pHead->m_pNext;	// Remove the first node.
    pData = temp->m_uiData;			// Obtain first node’s data.
    delete temp;

    return true;				// Indicate success.
}

void LinkedList::print(std::ostream &os){
	Link *temp=m_pHead;
	while(temp!=null){
		os<< tmep->m_uiData <<endl;
		temp=temp->m_pNext
	}
	delete tmep;
}


std::ostream& operator<<(std::ostream &os, LinkedList &l){
	l.print(os);
	return os;	
}
