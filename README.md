README File

This is a maven project. 
I did not have enough time to check if everything was working 100%.


I am aware I should have coded this using javascript as it would have been quicker for all the sides however I only remembered that halfway through.
Typically I would have cleaned up my code a lot more and commented on it more but I chose to focus on trying to fix as many coding problems but I needed more time to focus on cleaning up and fixing the interface. I did not have time to clean the code and comment and neaten it due to the work I had to catchc up for my job from the days I was away in the week. 
If you click on a pokemon then the details show up. I have included some Caching: so if you are on the all for habitats it loads 20 pokemon in everytime and stores it to list. all the previous shown pokemon is saved so it does not need to be fetched again it is locally stored. The last 5 pokemon clicked on is stored in a list, the last clicked on is stored in the first position. If a new one is clicked it is stored in the first and all of the pokemon is shifted and the last is deleted from the list. I could alter this to be a larger list however I felt 5 was ok.

Note:
I added a quick fix to make the filters dispaly the pokemon

How to run the Code:

It should be able to run as a maven project.
The main method is in the App.java file 
Location of File: "/Pokemon-Viewer/src/main/java/Application/Pokemon_Viewer/App.java"
You can open the project in eclipse and right click the App.java file and run as an application.
