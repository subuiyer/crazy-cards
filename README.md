# crazy-cards

Crazy Cards Microservice

Crazy Cards is a microservice that creates, stores, shuffles card decks. It has REST API methods to PUT a new card, GET a list of created decks, GET a deck by name, POST a request to shuffle a deck and DELETE a deck by name.

The singleton class Dealer has methods that implement all of this functionality with the DealerEndpoint that gets an instance of the Dealer, registers the right store and shuffler and is the REST API endpoint.

The card deck created by this service is written to memory, but the writer implements an interface CardDeckStore. Future implementations to save to the database or cloud may implement this interface and the store used in the app can easily be changed by changing the 'store' property in the configuration with the new store implementation class name.

There are 2 shuffling implementations – SimpleRandomShuffler which uses Collections.shuffle() and a HandShuffler which repeatedly (a random number of times) splits the card deck and interleaves cards from each half. These shuffling implementations implement the Shuffler interface. The shuffler used in the app is specified in the config file and can be changed easily.

In case of any error while loading the config or creating the right instances of the shuffler or store, the default ones are used -  SimpleRandomShuffler and DefaultCardDeckStore.

This project has unit tests and the line and branch coverage is at 98%.
The unit test for HandShuffler.shuffle() does not test the actual resulting order of cards. While this can be done, I skipped this part. The unit test instead just checks to make sure that shuffling has happened and that the resulting card deck is not in the same order as it was when created.


Quick Look at the Service

This service is available in my AWS instance. To test, use Postman for the requests listed below -
to get a list of card deck names
GET http://default-environment.brkdir7ndu.us-west-2.elasticbeanstalk.com/dealer
to create a new card deck named 'blue', 'red'
PUT http://default-environment.brkdir7ndu.us-west-2.elasticbeanstalk.com/dealer/blue
PUT http://default-environment.brkdir7ndu.us-west-2.elasticbeanstalk.com/dealer/red
to see card deck names 'red' and 'blue'
GET http://default-environment.brkdir7ndu.us-west-2.elasticbeanstalk.com/dealer
to shuffle deck 'blue'
POST http://default-environment.brkdir7ndu.us-west-2.elasticbeanstalk.com/dealer/blue
to get card deck 'blue'
GET http://default-environment.brkdir7ndu.us-west-2.elasticbeanstalk.com/dealer/blue
to delete deck 'red'
DELETE http://default-environment.brkdir7ndu.us-west-2.elasticbeanstalk.com/dealer/red 


Source Code

The source is available at https://github.com/subuiyer/crazy-cards 
I have also included a zipped up file with the source code.


How to Build

The build tool is Gradle. To build war, run code coverage and generate javadocs -
gradle clean build jacocoTestReport javadoc

To change the shuffler used by the dealer, update res/config.properties with one of these -
shuffler=io.github.subuiyer.crazycards.util.SimpleRandomShuffler
OR
shuffler=io.github.subuiyer.crazycards.util.HandShuffler

The war file get created in build/libs.
The code coverage report is created in build/jacocoHtml.
And javadocs are in build/docs.


How to Test

See section Quick Look at the Service. But you can also try to delete a non existing deck, try to create the same deck again.

In the real world, I would have automated integration tests using FitNesse or some such integration testing tool, but this would be overkill for this assignment. So we'll use Postman to test this API.


Code Comments

I have comments in the endpoint class and the service class as these are interfaces that expose functionality. My comments are scarce in other classes as these are simple data structures and method names should be self explanatory. I added inline comments in areas that have some complex implementation like the HandShuffler.shuffle() method.


 And... just another lil note I would never name a package with my name, but in this case, it makes kinda sense since it is a test and I had a github account with my name.


That's it. Feel free to contact me if you have any questions.
