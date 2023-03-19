Group 7 Iteration 3 Project code (Tyler Morgan)

I pity the poor, hypothetical children who are condemned to amuse themselves by playing game after game of Can't Stop.
The Jar file is fully functional on my distribution of Linux Mint, and the project code compiles this time. 

There are several aspects of functionality that are not fully implemented. 
The Game is missing a well-defined win message when 3 columns are won. 
The Computer players have not been programmed yet, but this will be my main task for iteration 4.

Things are coming together okay, but my code violates a few object-oriented design principles, and I am aware of this.
If I had more time, I would try to completely decouple game logic and the load/save system from the StopBoard class. 
I am aware that the code violates the principle of seperation of concerns, and I hope to mitigate much of this discontinuity in Iteration 4,
time permitting. I also know that there are many places where I violate the DRY principle. If I had more time and help with code analysis, 
the classes would be a lot cleaner and more self-contained.

I am going to try to eliminate most of the semantic and logical errors in my rehash of the project during iteration 4, 
and I understand that there is some discontinuity between the documentation and implementation, but will say that it has
been rather difficult to coordinate the progression of the project given that my group members, for the most part, have not had a hands-on
role when it comes to reading, analyzing and providing feedback on my code. The design process, from my point of view, has been
relatively one-sided. 
The documents that we produced are good, but I regret that we did not come together more to talk about concrete details
of the implementation as I was developing the code. This would have taken a lot of the stress and confusion of analysis and design
off of my shoulders solely, but given the circumstances, I think that I have done a good job. 

Responsibility for the TurnOrder class was assigned to Taranpreet Singh, The class is incomplete and suffers from some rendering
and logical errors. I am hoping that he gets it up to standard by the Iteration 4 Deadline.

I Implemented the load/save System, which works effectively barring a few bugs with the JFileChooser popups.
The happy path, however, seems to be fully satisfied.

I am not happy with the current state of the project, but in addition to my other course work, I have not
had the time or energy to put more hours into it. Taranpreet and I are the only group members who contributed in any way
to this code, and a large majority of the code was been written and tested by me. 
I am aware that there are a lot of issues, and take responsibility for them,
but I have put in an honest effort given the time contraints and my other obligations.
