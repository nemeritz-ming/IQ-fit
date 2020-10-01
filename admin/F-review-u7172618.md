## Code Review

Reviewed by: <Ming Lei>, <u7172618>

Reviewing code written by: <Xinyi Jiang> <u6891814>

Component: <https://gitlab.cecs.anu.edu.au/u6891814/comp1110-ass2-tue15h/-/blob/master/src/comp1110/ass2/FitGame.java#L495-528>

### Comments 

<Overall, I think Jiang did a great job. First, the code of the findPieceSpotOnBoard method was well-documented, she added detailed and clear comments on how to use this method with a good explanation including parameters used and return values.
Second, she used an appropriate method structure for constructing this method (i.e. a static method). In addition,  her codes fully followed Java code conventions. For example, she properly named methods and variables showed in codes. However, I 
found there are some cases she did not cover in her codes. Specifically, she did not check if the PiecePlacement was valid before dividing it into many pieces. Another thing that needs to be noticed is that before using the create a new piece in the loop,
she needs to ensure that the created piece is a valid piece, or there would be something wrong in checking if the selected point is placed by any pieces. Third, to assert the created pieces are not null and valid is also necessary, or it would get an error 
when finding the matrix of a null piece.>


