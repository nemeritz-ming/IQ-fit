## Code Review

Reviewed by: <DuoLiu> <u7190717>

Reviewing code written by: <MingLei> <u7172618>

Component: <https://gitlab.cecs.anu.edu.au/u6891814/comp1110-ass2-tue15h/-/blob/master/src/comp1110/ass2/FitGame.java#L539-701>

### Comments 

< Ming completed this extremely difficult task in a very short time, which really shocked me. As I wrote in my journal this week. In the beginning, we thought Task 11 is easy for there is no time limit. And we can just simply remove some pieces after we generate an answer randomly. But soon Ming found that when we remove to many pieces, the solution will become not unique. Ming solve this problem by separate the situation into different cases.  
  The implementation of the task is separated into two part. Firstly, generate a random answer. Secondly, create the challenge according to this answer by removing some pieces.  
  For the first part, Ming used the same strategy (depth-first searching) as he used in Task 9 to generate an answer. The methods firstly put a piece on the board randomly. And then find a legal solution according to the piece.  
  For the second part, the method will simply remove pieces away if the challenge is easy. If the challenge is difficult so that there are too many pieces removed from the board, the method will try every combination and return one challenge with a unique solution.  
  The method has met the requirement of the task, but sometimes in the hard model it will take a long time to find the final solution. I think the method can be faster if we find some rules while removing pieces.>


