package tevalcourse.theory.hashtable;


/*
  requirement  - if x.equals(y) then x.hashCode() == y.hashCode()
  desirable    - if !x.equals(y) then x.hashCode() != y.hashCode()
  default implementation - memory address of x


  user defined types:
  - start from a prime number
  - for reference types use hashCode()
  - for primitive types use hasCode() of wrapper types
  - use 31*x + y rule, where x is previous calc of hash and y is the next hashCode

  using hashCode() we get int value between -2^31 to 2^31-1
  in a hash table of size M we need a value between 0 and M
  => get absolute value of hash%M => (key.hashCode() & 0x7fffffff) % M

  collision - 2 distinct keys hashing to the same index

  separate chaining:
    - linked list for each of the table positions
    - array containing the lists
    - insertion at the front of the ll if not there
    - search over only one list
    - optimal - make M = N/5 to balance time and memory

  linear probing open addressing:
    - use array for index and the table storage
    - the list array should be bigger than the number of keys
    - hash value -> use as index -> if empty input, if not increase i until empty space reached
    - on search - hash -> go to index -> if not found until an empty space reached then not in table

  double-hashing is a variant of linear probing where
    - the skip is not over 1 but over multiple spaces at once
    - eliminates the clustering and allows table to grow
    - hard to implement delete for it

  two-probe hashing is a valiant of separate chaining where
    - hash to 2 position and insert into the one with less items

  cuckoo hashing variant of linear probing:
    - hash to 2 positions and insert into either
    - if that place is occupied move it to the other hash
    - constant worst time for search


  hash table vs balanced search tree:
    - hash tables are easier to impl and are faster for simple keys
    - tree - performance guarantee, easier to implement compareTo() than equals() and hasCode()


 */
public class HashTableT {

}
