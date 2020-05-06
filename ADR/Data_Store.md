# Data store


## Context and Problem Statement
Type of data store for service
## Decision Drivers

* prototype
* low load 
  

## Considered Options

* in memory implementation 
* Spring JPA 

## Decision Outcome

Chosen option: "in memory implementation", 
because of no need for external dependency , fast feed back for tests(fast test execution)
### Positive Consequences

* fast unit tests with no context start up required   

### Negative Consequences

* im memory data store should be implemented, with all features that come out of the box with JPA, 
like pagination, method generation, and CRUD operations
