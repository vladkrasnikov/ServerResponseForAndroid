# ServerResponseForAndroid
TASK: "Write a program that checks whether a given website is available (HTTP GET request returns response 200).
Checking is performed every 5 minutes using Broadcast Receiver (BR) and Service (S). BR launches S.
S executes a network request and stores the result in the database. S launches intent for BR, etc. "
Results of checks must be shown as list in the main activity.
Additional activity displays a pie chart of percentage availability (available - not available).
