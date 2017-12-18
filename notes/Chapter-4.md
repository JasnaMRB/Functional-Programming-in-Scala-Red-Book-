# Chapter 4: Handling errors without exceptions

## Summary

- referential transparency
- pros and cons of exceptions
- alternatives to exceptions: `Option` (and related lazy non-strict `Try` + `orElse`, `
getOrElse`) and `Either`
  - Implementing `Option` and `Either`
- lifting, for-comprehensions
- wrapping exception-oriented APIs 

> ...we can represent exceptions as ordinary values and use higher-order functions to 
> encapsulate common patterns of handling and propagating errors. 


## Referential transparency

- global reasoning vs. local reasoning
- context dependence vs. not
  
## Exceptions

- (+) consolidation of error-handling logic 
  - Bad alternatives
    - return bogus value when exception should occur, which allows silent errors, 
    ^ boilerplate code, isn't polymorphic, makes calling function complicated when 
    handling bogus value
    - add param to a function to specify a return value when exception should occur, 
    forcing user to know what to do and limits type of return value
- (-) not type-safe
- (-) not referentially transparent

## Good Alternatives to Exceptions

Say upfront in *return type* that function won't always work as expected. 
Defer to caller to handle error-handling. Reserve exception throwing for truly unrecoverable
conditions.

### `Option`

- `Some` (defined, returned with valid inputs, wrapping core return type) 
or `None` (undefined, returned with invalid inputs)
- In Scala library, used in a lot of places, e.g.,
  - Map for given key
  - `headOption`, `lastOption`
- Like `List` with at most one item

#### Common usages

- pattern matching
- converting from `Option` to `<whatever>` using `filter`/`map`/`flatMap` with `getOrElse`, 
deferring error handling to the end
  - can also use `orElse`, which returns another `Option`
- **lift**ing functions so functions need not be aware of `Option`s at all
  
#### For-comprehensions

- syntactic construct for lifting functions
- Syntax: `for { ... } yield <something>`

### `Either`

TODO
