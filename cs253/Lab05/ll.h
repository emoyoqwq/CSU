#ifndef LINKED_LIST_H
#define LINKED_LIST_H
#include <iostream>

class LinkedList {
  public:
    LinkedList(): m_pHead(nullptr){};
    bool insert(unsigned uiData);
    bool remove(unsigned &pData);

  private:
    struct Link {
	unsigned m_uiData;
	Link *m_pNext;
	Link(unsigned uiData, Link *pNext): m_uiData(uiData), m_pNext(pNext){};
    } *m_pHead;
    //friend std::ostream & operator<<(std::ostream &, const LinkedList &);
};

#endif /* LINKED_LIST_H */ 
