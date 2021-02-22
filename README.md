# urban-pancake

# How to run
Run `./gradlew run` in the parent directory. This will build and run the app
If you want to change the input csv from the default `taps.csv` in the resources folder,
add the csv to the resources folder and run `./gradlew run --args='filename'` if more args are supplied
it will default to the taps.csv

# Assumptions
 - Trips will only be made between Stop1, Stop2, Stop3
   - The rate will not change
 - All tap ons are with the same company
 - We will not see a tap OFF for a pan before a tap ON
 - If we get an OFF, we will have an ON
 - All the taps are sorted by time (earliest is first)
 
 
 If the trips.csv already exists a second run of the app will overwrite the old file