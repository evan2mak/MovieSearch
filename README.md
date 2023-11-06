# C323 Project 8 - Movie Search: Evan Tomak

This app allows the user to search for a movie that exists on IMDb using the OMDb API. Upon searching, the user will be able to see the movie title, movie poster, release year, rating (PG, PG-13, etc.), runtime, genre, and IMDb rating (0-10).
This app also allows the user to leave feedback via email, view the movie in more detail on IMDb, and also share the movie title and IMDb link via email.

The functionality is described in more detail below:

## Main Screen

[X] The main screen has a toolbar with a title 'Movie Search' and a feedback icon.

[X] There is also a searchbar, search button, 'View on IMDb' link, and a share button.

[X] If the searchbar is empty and the user tries to search, view on IMDb, or share, appropriate error messages will be shown indicating the user must first search for a movie.

[X] If there is not a movie that exists on IMDb, similarly an appropriate error message will be thrown for each of the above scenarios.

[X] If a movie exists on IMDb, the user will be able to see the movie title, movie poster, release year, rating (PG, PG-13, etc.), runtime, genre, and IMDb rating (0-10) after clicking the 'Search' button.

[X] The user can also click the 'View on IMDb' link in bold italics to view the movie in more detail on the IMDb website.

[X] The user can also click the 'Share' button to share the movie title and IMDb link via email.

[X] The user can also click the feedback icon on the top right corner of the toolbar to send a feedback email to the creator of the app -- that's me! :) -- The email subject line will automatically be populated with 'Feedback' and the email will be addressed to my email address.

##

The following functions/extensions are implemented:

## MainActivity

MainActivity handles UI interactions and movie search functionality.

onCreate:

The onCreate method is responsible for initializing the toolbar and Retrofit client and setting click listeners for the search button, the IMDb link, and the share button. 

searchMovie:

The searchMovie method searches for a movie using the OMDb API.

updateUI:

The updateUI method updates the UI with the movie details.

clearUI:

The clearUI method clears the movie details from the UI.

onCreateOptionsMenu:

The onCreateOptionsMenu method inflates the menu and adds items to the action bar if present.

onOptionsItemSelected:

The onOptionsItemSelected method handles the action bar item clicks for the feedback icon.

sendFeedback:

The sendFeedback method opens the email client to send feedback to the creator.

## MovieResponse

MovieResponse is a data class representing a movie's details along with the API response status and any error messages received from the OMDb API.

## OMDbApiService

OMDbApiService is an interface that defines a method searchMovie for making a GET request to the OMDb API to retrieve movie details by title using Retrofit.

## RetrofitClient

The RetrofitClient object is a singleton that initializes a Retrofit instance.

## Video Walkthroughs

Here's a walkthrough of implemented user stories:







## Notes

The biggest challenge I encountered was integrating the OMDb API into my app. I also had some trouble with updating the UI and clearing the UI with subsequent searches, so I found it was much easier to handle these situations in separate functions. 

## License

    Copyright 2023 Evan Tomak.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express implied.

    See the License for the specific language governing permissions and
    limitations under the License.


