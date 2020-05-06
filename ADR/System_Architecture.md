# System Architecture


## Context and Problem Statement
Service that will help potential patients schedule appointments in medical centers with doctors and allow 
 manage doctors schedules and work time
## Decision Drivers

* it is low cost prototype
* solution should be testable 
* low number of early adopters expected
* extensibility expected
* technology requirements : spring boot, kotlin(preferable), java(allowed)

## Considered Options

* monolith (aka future big ball of mud) 
* modular-monolith
* microservices 
## Decision Outcome

Chosen option: "modular-monolith", because business mentioned plans for several applications in future

### Positive Consequences

* no additional infrastructure required  
* modularization(modules with high cohesion)
* fit in one repo
* testability


### Negative Consequences

* require some time to identify boundaries between modules
* restriction in terms of coupling 
* only vertical scaling  
