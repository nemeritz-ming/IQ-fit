# New Code for Deliverable D2D

## < u7172618 > < Ming Lei >

For Deliverable D2D, I contributed the following new statements of original code:

- Added the [GUIPiece()](https://gitlab.cecs.anu.edu.au/u6891814/comp1110-ass2-tue15h/-/blob/master/src/comp1110/ass2/gui/Board.java#L75-92) class to create GUI pieces
- Added the [makeBoard()](https://gitlab.cecs.anu.edu.au/u6891814/comp1110-ass2-tue15h/-/blob/master/src/comp1110/ass2/gui/Board.java#L120-131) method to visualize the board
- Added the [makePiece()](https://gitlab.cecs.anu.edu.au/u6891814/comp1110-ass2-tue15h/-/blob/master/src/comp1110/ass2/gui/Board.java#L133-183) method to visualize the pieces
- Added the [makeSelect()](https://gitlab.cecs.anu.edu.au/u6891814/comp1110-ass2-tue15h/-/blob/master/src/comp1110/ass2/gui/Board.java#L185-297) method to create select menu and move draggable pieces
- Added the [transfer()](https://gitlab.cecs.anu.edu.au/u6891814/comp1110-ass2-tue15h/-/blob/master/src/comp1110/ass2/gui/Board.java#L298-329) method to transfrom piece type in UI to the piece placement in piece class
- Added the [canMove()](https://gitlab.cecs.anu.edu.au/u6891814/comp1110-ass2-tue15h/-/blob/master/src/comp1110/ass2/gui/Board.java#L331-339) method to check if a piece is draggable
- Added the [makeControls()](https://gitlab.cecs.anu.edu.au/u6891814/comp1110-ass2-tue15h/-/blob/master/src/comp1110/ass2/gui/Board.java#L345-389) method to create control buttons and set their event
- Added the [newGame()](https://gitlab.cecs.anu.edu.au/u6891814/comp1110-ass2-tue15h/-/blob/master/src/comp1110/ass2/gui/Board.java#L397-407) method to restart a new game
- Added the [start()](https://gitlab.cecs.anu.edu.au/u6891814/comp1110-ass2-tue15h/-/blob/master/src/comp1110/ass2/gui/Board.java#L414-427) method to start the game
- Added the [getSolution()](https://gitlab.cecs.anu.edu.au/u6891814/comp1110-ass2-tue15h/-/blob/master/src/comp1110/ass2/FitGame.java#L296-337) method to find solution to the puzzle
- etc.

(Follow the example give above to list at least 10 lines of original code contributions made by you. Notice that the example above links directly to the code as well as providing a brief description.   Please follow that example to link to your code.  You can create the link by browsing your code in gitlab, and then clicking on the line number of the first line, and then shift-clicking on the line number of the last line in the region you want to select.  If you do that correctly, the URL for that selection will be in the navigation bar of your browser.  After you commit and push your statement, you should check that all of the links are correctly working.)