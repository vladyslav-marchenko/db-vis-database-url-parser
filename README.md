# db-vis-database-url-parser
# Database URL Parser
Database URL parser that converts a database URL in string format to
an object from which the relevant parts can be accessed by both generic and database-specific
code in DbVisualizer.

## Questions and Answers

### How will you decide which database URL formats to support?
- We started by supporting the most common JDBC database URL formats, such as MySQL, PostgreSQL, and Oracle. 
- The decision is based on the popularity and usage of these databases in the industry. 
- We also consider the ease of parsing and the availability of documentation for these formats. However, the solution is designed to support extensibility, allowing for the addition of new database URL formats in the future (e.g. supporting SID format for Oracle).

### Is the object model you design best suited for generic code, database-specific code, or both?
- The object model is designed to support both generic and database-specific code. It provides a common interface for accessing the basic components of a database URL (such as host, port, and database name) while allowing for extensions to handle database-specific properties and logic.
- In the future, database-specific models can be created to extend the generic model (e.g. more granual support for specific databases, both relational and non-relational). 
- Currently, JDBC starts to support non-relational databases, such as MongoDB, Cassandra, etc. so that's a good direction to extend the object model.

### Can your solution be extended to support more database types?
Yes, the solution is designed with extensibility in mind.
- The object model will is designed to be extended to support more database-specific properties
- Added strategy to support parsing database-specific URLs. Abstract approach is used to support general URL format: `jdbc:mysql: /[host][,failoverhost .][:port]/[database][?propertyName1][=propertyValue
  1][&propertyName2][=propertyValue2]`.
- To parse database-specific URLs, parsing can happen in exact database-specific URL parser

### How do you ensure your solution is correct?
To ensure the correctness of the solution, we:
- Write unit tests for each supported database URL format to verify that the parser correctly extracts the relevant components.
- Use test-driven development (TDD) to guide the implementation and ensure that all edge cases are considered.
- Validate the parsed components against known valid and invalid database URLs to ensure robustness.
