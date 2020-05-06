# Appointments Module Architecture


## Context and Problem Statement
Service that will help potential patients schedule appointments in medical centers with doctors and allow 
 manage doctors schedules and work time
## Decision Drivers

* high testability
* configurability 
* core domain 
* extensibility expected
* communication with external modules
* hype driven development 

## Considered Options

* layered architecture  
* hexagonal architecture 
 
## Decision Outcome

Chosen option: "hexagonal architecture", because of high configurability 
and dependency inversion required for core domain 

### Positive Consequences

* high testability  
* high configurability



### Negative Consequences

* additional complexity for implementation  
