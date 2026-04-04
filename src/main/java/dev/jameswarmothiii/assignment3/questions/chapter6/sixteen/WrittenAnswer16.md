## Answer
The one public method that takes linear time is start().

## Justification
- In this two-stack design, the current element is always at the top of the first stack.
- The first element of the sequence is at the bottom of the first stack.
- The purpose of start() is to make the front element the current element.
- Since a stack only gives direct access to the top, there is no constant-time way to reach the bottom of the first stack.
- To make the first element current, the implementation would have to move elements between the two stacks until that front element reaches the top of the first stack.
- In the worst case, that means moving all n elements, so start() takes O(n) time.