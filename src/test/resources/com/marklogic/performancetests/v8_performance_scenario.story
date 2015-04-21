Narrative:
As a MarkLogic user
I want to be able to invoke Server Side Javascript (.sjs) without too much overhead on resource use.
So that I can develop modules using Javascript and expect comparable performance
 
I'm comparing performance against a similar XQuery (.xqy) process to make my initial measurements.
See Support case #15383 for a discussion of the initial problem

Scenario: average of 5 xdmp:invoke operations (XQuery) 
Given MarkLogic has an XQuery module that returns a simple string
When I invoke the XQY module 5 times 
Then response times should be under 0.0025

Scenario: average of 10 xdmp:invoke operations (XQuery) 
Given MarkLogic has an XQuery module that returns a simple string
When I invoke the XQY module 10 times 
Then response times should be under 0.0025

Scenario: average of 100 xdmp:invoke operations (XQuery) 
Given MarkLogic has an XQuery module that returns a simple string
When I invoke the XQY module 100 times 
Then response times should be under 0.0025

Scenario: average of 5 xdmp:invoke operations (Server Side JavaScript)
Given MarkLogic has a JavaScript module that returns a simple string
When I invoke the SJS module 5 times 
Then response times should be under 0.0025

Scenario: average of 10 xdmp:invoke operations (Server Side JavaScript)
Given MarkLogic has a JavaScript module that returns a simple string
When I invoke the SJS module 10 times 
Then response times should be under 0.0025

Scenario: average of 50 xdmp:invoke operations (Server Side JavaScript)
Given MarkLogic has a JavaScript module that returns a simple string
When I invoke the SJS module 50 times 
Then response times should be under 0.0025
