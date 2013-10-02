Implement a FrequencyList
=========================

The client is a company in the search business offering users (amongst other things) the ability
to search for telephone numbers. Recently they have noticed that some numbers seem to occurr disproportionately
more often than others in searches. 

The client want's you to develop a component that can:
- effectively store all number searches
- answer how many searches have been made for a specific number
- create a topK list over the K most popular numbers that have been searched (K is an arbitrary number)

All searches made within the system are published on a messaging system and can easily be fed to an external component. 