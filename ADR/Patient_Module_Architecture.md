# Patient Module Architecture


## Context and Problem Statement
Type of architecture for medical center module
## Decision Drivers

* anemic data model
* hidden implementation details 
* supportive sub domain
* expected additional view models  

## Considered Options

* layered architecture 
* layered architecture with vertical slices with separated read mode for query 

## Decision Outcome

Chosen option: "layered architecture with vertical slices with separated read mode for query", 
because of expected additional view models
### Positive Consequences

* read model for complex queries 
* command and query segregation 

### Negative Consequences

* additional interface and moving part 
