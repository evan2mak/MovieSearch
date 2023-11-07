# C323 Project 8 - Movie Search: Evan Tomak

This app allows the user to search for movies that exist on IMDb using the OMDb API. Upon searching, the user will be able to see a list of movies that each display their respective movie title, movie poster, release year, rating (PG, PG-13, etc.), runtime, genre, and IMDb rating (0-10).
This app also allows the user to leave feedback via email, view a movie in more detail on IMDb, and also share a movie title and IMDb link via email.

The functionality is described in more detail below:

## Main Screen

[X] The main screen has a toolbar with a title 'Movie Search' and a feedback icon.

[X] There is also a searchbar, search button, 'View on IMDb' link, and a share button.

[X] If the searchbar is empty and the user tries to search, an appropriate error message will be shown indicating the user must first search for a movie.

[X] If there is not a movie or movies that exist on IMDb, similarly an appropriate error message will be shown.

[X] If there are movies on IMDb that contain the keyword from the search, the user will be able to see a list of all the movies that contain the keyword, including the movie title, movie poster, release year, rating (PG, PG-13, etc.), runtime, genre, and IMDb rating (0-10) -- (after clicking the 'Search' button).

[X] The user can also click the 'View on IMDb' link in bold italics to view a movie in more detail on the IMDb website.

[X] The user can also click the 'Share' button to share a movie title and IMDb link via email.

[X] The user can also click the feedback icon on the top right corner of the toolbar to send a feedback email to the creator of the app -- that's me! :) -- The email subject line will automatically be populated with 'Feedback' and the email will be addressed to my email address.

##

The following functions/extensions are implemented:

## MainActivity

MainActivity handles UI interactions and movie search functionality.

onCreate:

The onCreate method is responsible for initializing the toolbar, recyler view, and Retrofit client, and for setting a click listener for the search button. 

searchMovie:

The searchMovie method searches for all movies containing a keyword using the OMDb API.

onCreateOptionsMenu:

The onCreateOptionsMenu method inflates the menu and adds items to the action bar if present.

onOptionsItemSelected:

The onOptionsItemSelected method handles the action bar item clicks for the feedback icon.

sendFeedback:

The sendFeedback method opens the email client to send feedback to the creator.

## MovieAdapter

MovieAdapter is a recycler view adapter class that binds movie data to a list and handles view creation and binding for a movie list UI.

onCreateViewHolder:

Inflates the movie item layout and returns a new view holder for the RecyclerView

onBindViewHolder:

Binds movie data to the view holder at the specified position in the RecyclerView.

getItemCount:

Returns the total number of items in the movie list data set.

MovieViewHolder:

ViewHolder class that contains and binds views for individual movie items in the RecyclerView.

bind:

Binds movie details to the views and sets click listeners for actions such as viewing on IMDb or sharing

## MovieListResponse

MovieListResponse is a data class representing the response from a movie search query, including a list of movies, total results, and status of the response.

## MovieResponse

MovieResponse is a data class representing a movie's details along with the API response status and any error messages received from the OMDb API.

## OMDbApiService

OMDbApiService is an interface that defines a method searchMovie for making a GET request to the OMDb API to retrieve movie details by title using Retrofit.

## RetrofitClient

The RetrofitClient object is a singleton that initializes a Retrofit instance.

## Video Walkthroughs

Here's a walkthrough of implemented user stories:

1. Empty search error message and unknown movie error message.
![studio64_lDlegZIyIo](https://github.com/evan2mak/MovieSearch/assets/128643914/7bde39ac-fc72-4ddd-9ac7-9c79ed57351e)


2. Accurate search functionality.
![studio64_5aBTxbBc9x](https://github.com/evan2mak/MovieSearch/assets/128643914/766b666b-fdcb-4ef7-bde3-ca593f3946c2)


3. View on IMDb. (Note the Internet is really bad so IMDb does not load fully, but this does show that the link works and navigates the user to IMDb correctly).
![studio64_MEbckQhXd1](https://github.com/evan2mak/MovieSearch/assets/128643914/c885080a-7cee-4b0b-8378-7679ac17d87c)


4. Share button functionality.
![studio64_y6qSKdnkzX](https://github.com/evan2mak/MovieSearch/assets/128643914/dd41204f-78b4-43d7-a3c0-cfcf0e8adbc6)


5. Feedback icon functionality.
![studio64_VPoUNtGRHs](https://github.com/evan2mak/MovieSearch/assets/128643914/70b39129-0008-42f1-b279-f25492812fe5)


## Notes

The biggest challenge I encountered was integrating the OMDb API into my app. I also had some trouble with retrieving information from IMDb, such as running time, genre, and IMDb rating. However, it sounds like this is a common issue, so I decided not to spend additional time on it. I also had some trouble listing all of the movies using recycler view.

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


