# BestRoute

<h3 style="text-align:center">This is an application that gets the best route for a delivery executive who is given two orders from different restaurants and customers and has to optimize their path so as to delover the food in the shortest amount possible.</h3>

<p>My approach to this was to just brute force the solution as there can be only 6 cases possible for our particular scenario. This poses a problem for scalability in case there are multiple other orders from restaurants but this brute forcing approach works well for our use case which is kind of close to real life as food delivery executives generally don't have more than 2 orders.<br>
I have made functions to calculate  time taken for all the paths and then log the path which takes the shortest time.
<br><br>
The formula used for calculating distance between two locations is haversine formula which is used generally to calculate distances with the help of longitudes and latitudes. It gives the circle distance between two points as opposed to having the linear distance.
<br><br>
 The project is structured in a way that every total path time calculation function is in a utility class, Just the time taken to reach that location node from anywhere can be calculated from the object of that class. This provides us with abstraction as we can just get the time taken to reach that node from anywhere by just inputing the values. For now I have use some random latitude and longitude values in Kormangala for all 5 location nodes (delivery executive, restaurant 1, customer 1, restaurant 2, customer 2) but we can input those as well. I chose not to do that as it would just be cumbersome for the user to input these many values, But the code works for all possible latitude and longitude values.
</p>
