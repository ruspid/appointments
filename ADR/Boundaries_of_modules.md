# Boundaries of modules


## Context and Problem Statement
Discovering boundaries between modules 
## Decision Drivers

* testability
* all make up one application

## Considered Options

* doctor module, patient module , appointments module 
* user module(with two kind of users doctor and patient) appointments module

## Decision Outcome

Chosen option: "doctor module, patient module , appointments module", because of 
*security constrain, doctor information and patient information should be stored on different ways, 
*expected extension of doctor application to work shift schedule
*different load on modules, naturally more patients expected than doctors(more a future benefit)   

### Positive Consequences

* no additional infrastructure required  
* modularization
* fit in one repo


### Negative Consequences

* more data repositories to handle 
* more moving parts in system  
* additional complexity
