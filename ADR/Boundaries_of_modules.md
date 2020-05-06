# Boundaries of modules


## Context and Problem Statement
Discovering boundaries between modules and way of establishment 
## Decision Drivers

* testability
* all make up one application
* restrict access to components from external modules
## Considered Options

* doctor module, patient module , appointments module, package scope access modifier 
* user module(with two kind of users doctor and patient) appointments module, package scope access modifier
 
## Decision Outcome

Chosen option: "doctor module, patient module , appointments module",* doctor module, patient module 
and appointments module, package scope access modifier because of 
*security constrain, doctor information and patient information should be stored on different ways, 
*expected extension of doctor application to work shift schedule
*different load on modules, naturally more patients expected than doctors(more a future benefit)   

### Positive Consequences

* no additional infrastructure required  
* modularization
* fit in one repo
* language build in support for access restriction

### Negative Consequences

* more data repositories to handle 
* more moving parts in system  
* additional complexity
