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
- For common pattern of exception handling, can be rewritten into a `Try`:
    - ```def Try[A](a: => A): Option[A] =
          try Some(a)
          catch ( case e: Exception => None }
       ```     

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

Unlike `Option`, can be used to add whatever info we want about failures. 
If you just want to know if a failure occurred, `Option`is good. But `Either` gives us more info.

- Like `Option`, has only 2 cases: `Left` and `Right`. Unlike `Option`, both cases carry a value. 
- Convention: `Right` = success and `Left` = failures.
- Represents values that can be one of 2 things; "disjoiint union" of 2 types
- defined `Either[LeftType, RightType]`
- Like `Option`, or common pattern of exception handling, can be rewritten into a `Try`:
  - ```def Try[A](a: => A): Either[Exception, A] =
        try Right(a)
        catch ( case e: Exception => Left(e) }
     ```     