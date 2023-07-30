### Goal:
Create a platform that allows users to play the game online against each other.

### User Stories:
1. As a user, I would like to be able to register for the platform.
   - Note: Register using e-mail (E-mail must be unique)
   - Note: E-mail is required
   - Acceptance Criteria:
     - Test sign-up with unique and valid email (pass)
     - Test sign-up with unique and invalid email (fail)
     - Test sign-up with non-unique and valid email (fail)
     - Test sign-up with non-unique and invalid email (fail)
     - Test system damaging input in email field (fail)
     - Test time to register < 1s (pass)
2. As a user, I would like to provide an alias (nickname) when I register.
   - Note: Requires Nickname to register
   - Note: Nickname must contain valid characters
   - Acceptance Criteria:
     - Test sign-up with valid characters in Nickname (pass)
     - Test sign-up with non-valid characters in Nickname (fail)
     - Test system damaging input in Nickname field (fail)
3. As a user, I would like to provide a password when I register.
   - Note: Requires Password to register
   - Note: Password must contain valid characters
   - Note: Passwords should be kept securly
   - Acceptance Criteria:
     - Test sign-up with valid characters in Password (pass)
     - Test sign-up with non-valid characters in Password (fail)
     - Test saved password matches actual password (fail)
     - Test system damaging input in password field (fail)
4. As a user, I would like to be able to create a match.
   - Note: Should there be a limit on how many matches a user can have going at once?
   - Acceptance Criteria:
     - Test match creation (pass)
     - Test time to create match < 2s
5. As a user, I would like to be able to create multiple matches.
   - Note: Should there be a limit on how many matches a user can have going at once?
   - Acceptance Criteria:
     - Test match creation (pass)
     - Test match creation after concurrent match limit has been met (fail)
     - Test time to create match < 2s
6. As a user, I would like to be able to provide a user name to invite to a match.
   - Note: A user should be able to invite another user to their match.
   - Note: Only valid characters are accepted
   - Acceptance Criteria:
     - Test user inviting (pass)
     - Test invite user with invalid user (fail)
     - Test invie no one (fail)
     - Test invite self (fail)
     - Test system damaging input in invite user field (fail)
     - Test time to invite < 1s (pass)
7. As a user, I would like to be able to invite multiple other users to a match.
   - Note: A user should be able to invite multiple other users to their match.
   - Acceptance Criteria:
     - Test user inviting more than one user (pass)
     - Test user inviting the maximum number of users (pass)
     - Test user inviting after invite limit has been met (fail)
     - Test time to invite the maximum number of users < 2s (pass)
8. As the system accepting responses, only allow a user with the first accepted response to accept an invitation to be the second player in a match
   - Note: Only the first invited user to accept an invitation gets to play in the match.
   - Note: An invited user should be able to reject an invitation.
   - Note: A user sending an invite that gets rejected should receive a notification that their invite was rejected.
   - Acceptance Criteria:
     - Test that the first user to accept an invite gets to play the match (pass)
     - Test three responses reject, accept, reject and the second user is the opponent (pass)
     - Test three responses accept, reject, reject and the first user is the opponent (pass)
     - Test three responses reject, reject, accept and the third user is the opponent (pass)
     - Test the maximum number of responses with the last response as accept and the last user is the opponent (pass)
     - Test all responses are reject and no opponent is selected (pass)
9. As an invited user, I want to be able to accept an invite to play in a match.
   - Note: A user should be able to accept an invitation.
   - Acceptance Criteria:
     - Test if the user is the first user to accept then they are the opponent (pass)
     - Test if the user is not the first user to accept then they are not the opponent (pass)
     - Test time to accept an invitation < 1s (pass)
     - Test code injection is not allowed (pass)
     - Test parameter injection is not allowed (pass)
9. As an invited user, I want to be able to reject an invite to play in a match.
   - Note: A user should be able to reject an invitation.
   - Acceptance Criteria:
     - Test if the user rejects the invitation then they are not the opponent (pass)
     - Test time to reject an invitation < 1s (pass)
     - Test code injection is not allowed (pass)
     - Test parameter injection is not allowed (pass)
10. As a user that sent invites, I want to receive a notification that their invite was accepted.
   - Note: A user that has sent an invite that gets accepted should receive a notification that their invite was accepted.
   - Acceptance Criteria:
     - Test a notification was sent to the user who sent the invite when the invitation was accepted (pass)
     - Test a notification is recieved < 1m after the invitation was responded to (pass)
11. As a user that sent invites, I want to receive a notification that their invite was rejected.
   - Note: A user that has sent an invite and the invite gets rejected should receive a notification that their invite was rejected.
   - Acceptance Criteria:
     - Test a notification was sent to the user who sent the invite when the invitation was rejected (pass)
     - Test a notification is recieved < 1m after the invitation was responded to (pass)
12. As a user, I would like to be able to manage multiple games.
   - Note: After logging into the game a user can click a button to go to the manage games page
   - Note: A game should be in an active state.
   - Note: All game objects are saved
   - Note: Limit the number of active games
   - Acceptance Criteria:
      - Test The expected number of active games (pass)
      - Test The expected order of games (pass)
13. As a user, I would like to see some data about active games to help identify the game
   - Note: There should be some data about the game to help the user identify the game
   - Acceptance Criteria:
      - Test The games have the expected identifying information (pass)
14. As a user, I would like to click on a game to resume the game
   - Note: The user should be able to click on the game record to open the game
   - Acceptance Criteria:
      - Test A user opens a game and the game loads in the correct state (pass)
      - Test A user opens a game for a different user (fail)
15. As a user, I would like to have a Profile (includes history of matches?)
   - Note: After logging into the game a user can click their game id to go to the profile page
   - Note: The user should not be able to access another users page
   - Acceptance Criteria:
      - Test The page loads with the user's profile information (pass)
      - Test The page loads with a different user's profile information (fail)
16. As a user, I would like to play a chess game with chess rules
   - Note: Each chess piece will follow chess rules
   - Note: Invalid moves will not be allowed.
   - Acceptance Criteria:
      - Test the player belongs to the game (pass)
      - Test [all pieces] and a valid move (pass)
      - Test [all pieces] and an invalid move on the board (fail)
      - Test [all pieces] and an invalid move off the board (fail)
      - Test [all pieces] not owned by the player (fail)
17. As a user, I would like to play the game whenever I want.
   - Note: save the game state after user takes a move.
   - Note: load the lastest game state when user comese back.
   - Acceptance Criteria:
     - Test loading an ongoing game (pass)
     - Test loading a finished game (fail)
     - Test loading an abandoned game (fail)
18. As a user, I would like to be able to see a chess board.
    - Note: the UI should be display a chess board.
    - Note: the chess board should have a grid pattern
    - Note: the chess board should have alternating colors
    - Acceptance Criteria:
      - Test the chess board loads (pass)
19. As a user, I would like to see shess pieces on the chess board.
    - Note: the UI should load the chess pieces
    - Note: The user wants to interact with app smoothly throught UI.
    - Note: the layout of UI should be concise.
    - Acceptance Criteria:
      - Test the chess pieces load (pass)
20. As a user, I would like to be able to move the chess pieces.
    - Note: the UI should allow the user to move a chess piece to another location on the chess board.
    - Note: moving a chess piece should not have any visual interuption.
    - Acceptance Criteria:
      - Test moving a chess piece does not have any visual interuption (pass)
      - Test moving a test pieces to another location (pass)
21. As a user, chess pieces removed from play should not be visable on the chess board.
    - Note: when a chess piece is removed from play, that piece is removed.
    - Acceptance Criteria:
      - Test when a chess piece is removed from play, that piece is removed (pass) 
22. As a user, I would like to quit a game.
    - Acceptance Criteria:
      - Test quiting a match (pass)
      - Test rejoining a match that the user previously quit (fail)
23. As a user, I would like to view other user profiles.
    - Note: Only registered users should be able to view other user profiles.
    - Acceptance Critera:
      - Test viewing a user's profile as a registered user (pass)
      - Test viewing a user's profile as an unregistered user (fail)
24. As a user, I would like the system to know when a game is over.
    - Acceptance Critera:
      - Test that the system displays game over after a completed match (pass)
      - Test that the system displays nothing after a completed match (fail)
25. As a user, I would like the system to notify the user who won, according to the rules.
    - Acceptance Critera:
      - Test that the match winner recieved a "winner" notification (pass)
      - Test that no notification was sent to the winner after a completed match (fail)
26. As a user, I would like the system to notify the user who lost, according to the rules.
    - Acceptance Critera:
      - Test that the match loser recieved a "loser" notification (pass)
      - Test that no notification was sent to the loser after a completed match (fail)
