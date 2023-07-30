| Match | | 
| ---------------- | ------------- | 
| Responsibilities | Collaborators| 
| Send start and end times and dates | - Match Data | 
| Send an invitation to join the match | - Invitation |
| | -User | 
| send current state of the match to Match State | - Match State | 
| Know when the game is over |  | 
| Send user's match win/loss record to Match Data  | - Match Data |
| Send abandoned matches to Match Data |  Match Data |
| Determine who goes first | |
| Once a match starts, limit each match to 2 players | |

| Registration | | 
| ---------------- | ------------- | 
| Responsibilities | Collaborators| 
| handles registration of a user | User Profile | 
| Creates a user profile | | 
| | | 
| | | 
| | |

| Results | |
| ---------------- | ------------- | 
| | - Match | 
| | | 

| Multiple Games | |
| ---------------- | ------------- | 
| | Match | 
| | | 

| Match State | | 
| ---------------- | ------------- | 
| Responsibilities | Collaborators| 
| | | 
| | | 
| | | 


| Chess Board | | 
| ---------------- | ------------- | 
| Responsibilities | Collaborators| 
| Request the state of the match from Match State | - Match State | 
| | | 
| | | 
| | | 


| Chess Piece | | 
| ---------------- | ------------- | 
| Responsibilities | Collaborators| 
| | -Chess Board | 
| Legel moves allowed | | 
| | | 
| | | 
| | | 

| Pawn | | 
| ---------------- | ------------- | 
| Responsibilities | Collaborators| 
| | -Chess Piece | 
| | | 
| | | 


| Rook | | 
| ---------------- | ------------- | 
| Responsibilities | Collaborators| 
| | -Chess Piece | 
| | | 
| | | 
| | | 

| King | | 
| ---------------- | ------------- | 
| Responsibilities | Collaborators| 
| | -Chess Piece | 
| | | 
| | | 
| | |  

| Queen | | 
| ---------------- | ------------- | 
| Responsibilities | Collaborators| 
| | -Chess Piece | 
| | | 
| | | 
| | | 

| Knight | | 
| ---------------- | ------------- | 
| Responsibilities | Collaborators| 
| | -Chess Piece | 
| | | 
| | | 
| | | 

|  Bishop | | 
| ---------------- | ------------- | 
| Responsibilities | Collaborators| 
| | -Chess Piece | 
| | | 
| | | 
| | |

| Color | | 
| ---------------- | ------------- | 
| Responsibilities | Collaborators| 
| | -Chess Piece | 
| | | 
| | | 
| | |

| Game platform | | 
| ---------------- | ------------- | 
| Responsibilities | Collaborators| 
| Log out of the platform | | 
| Log into the platform | | 
| | | 
| | | 

| User | | 
| ---------------- | ------------- | 
| Responsibilities | Collaborators| 
| | - Registration | 
| | - Invitation |
| provide a nickname | User Profile | 
| create a game  | | 
| quit a game | | 
| accept an invitation | |
| reject an invitation | | 
| join a match | | 
| play a game | |
| make a move with a chess piece | |

| Match Data | | 
| ---------------- | ------------- | 
| Responsibilities | Collaborators| 
| | | 
| | | 
| | | 
| | | 

| User Profile | | 
| ---------------- | ------------- | 
| Responsibilities | Collaborators| 
| | - User | 
| Requests match data played by a user | - Match Data | 
| | | 
| | |  

| Invitation | | 
| ---------------- | ------------- | 
| Responsibilities | Collaborators| 
| invite a player to a match | - User | 
| | | 
| | |

| Notifications | | 
| ---------------- | ------------- | 
| Responsibilities | Collaborators| 
| send a notification to the user if they are invited | - Invitation | 
| send a notification if the invite has been rejected | - User | 
| | | 
| | |
