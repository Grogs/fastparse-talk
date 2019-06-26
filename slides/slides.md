## An Introduction to Fastparse

Greg Dorrell  


FastParse is a Scala library for creating parsers
Note:
Recently I used it at work and found it really pleasant to use, so I thought I'd talk about it.


A parser takes text and turns it into structured data
Note:
- What is a parser
- ...
- I first learnt about recursive descent parsers back at uni where I used lex and yacc to build a C-like language
- They were pretty awkward to use, they were written in C, and I had to learn a "metalanguage"/DSL for lexing an parsing


FastParse is a recursive descent parser
Note:
- Let's dig into FastParse, its' syntax, and its' features a bit, using an example from my work:
  - Written by Li Haoyi.
  - An improvement on parboiled and Scala's built in parser combinator library



### Usecase
Note:
- I work in the Growth Tech team at Vrbo, a market place for vacation rentals that's part of Expedia Group.
- So I'm an application developer, not a programming language author, or even really a library author. 
- So why would I be writing a parser?
- It's not to implement some general purpose language, or parse existing languages like JSON/SQL
- Well, one of our systems is a tool to empower our marketing team to create links for the destinations where we have vacation rentals
- We decided a DSL would be useful for our users.



### Domain Specific Languages
Note:
- For my team, a compelling usecasewas creating a DSL for our users.
- So let's talk about DSLs for a little bit


There's two types of DSLs


Internal DSLs
Note:
As a Scala developer you already use lots of internal DSLs


Examples:
- ScalaTest
- SBT
- Akka Streams Graph DSL
- akka-http routing DSL.
- Free Monads/Tagless Final
Note:
- With ScalaTest you have a DSL for matchers, and different testing styles (e.g. WordSpec) which each have their own DSLs for defining and structuring tests
- With SBT; it's scala, but you have a DSL for creating projects and updating settings which doesn't look too much Scala code
- akka-http, and finch which we use at work, provide DSLs for creating HTTP services


External DSLs

Note:
You're also using lots of those.


Examples:

- CSS
- SQL
- regex


Internal vs External
Note:
  - Internal DSLs are embedded in a language. What's cool about that is you have access to the full power of the language… But, you run code in internal DSL, you need runtime for the underlying language, you're generally expected to know that underlying language.
  - External DSLs are much more limited.



## Example DSL
Note:
- Were recently used FastParse to provide a DSL for our users.
- Let's take a look at what the problem was, and how we used FastParse to solve it.


| Destination ID |
|----------------|
| 1              |
| 2              |
| 3              |

Note:
Our users provide a table of destination IDs to generate links for


### URL Templates - Before

|Text|URL|
|----|---|
|Compare holiday rentals in {destination}|/d/{destination_id}|
|Pet friendly holiday rentals in {destination}|/search/keywords:{destination_keyword}?petIncluded=true|

Note:
- This is what we had before. Our users could specify "for each destination, generate a link with this text and this relative URL" by providing these two tables
- The Text has variables in it, and the "URL"s are a URL fragment which we can concate to form a URL


### URL Templates - After

|Text|URL|
|----|---|
|Compare holiday rentals in {destination}|LandingPage|
|Pet friendly holiday rentals in {destination}|SearchPage(petIncluded=true)|

Note:
- We introduced a DSL for specifying the URLs
- This DSL is more human readable than URL suffix's
- We can turn this into structured data that's nicer to work with than string concatination
- Only valid URLs can be specified and generated
- Hopefully easier to author for our users, or at least harder to make mistakes
- As we're generating structred data, we can do fancy stuff like "ensure we have some pet friendly vacation rentals in that destination" otherwise don't generate a link.




Note:
    - Dig into variable substitution/templated text
      - Simple example; but already better than what we had before (string replacement)
    - Dig into URL DSL
      - Would be quite awkward doing it with regex
      - Look at error messages
      - Compilation speed?


