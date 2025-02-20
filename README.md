# db-vis-database-url-parser

## Questions and Answers

### How will you decide which database URL formats to support?
We will start by supporting the most common JDBC database URL formats, such as MySQL, PostgreSQL, and Oracle. The decision will be based on the popularity and usage of these databases in the industry. We will also consider the ease of parsing and the availability of documentation for these formats.

### Is the object model you design best suited for generic code, database-specific code, or both?
The object model will be designed to support both generic and database-specific code. It will provide a common interface for accessing the basic components of a database URL (such as host, port, and database name) while allowing for extensions to handle database-specific properties and logic.

### Can your solution be extended to support more database types?
Yes, the solution will be designed with extensibility in mind. By using a modular approach and defining a clear interface for parsing database URLs, new database types can be added by implementing the interface and registering the new parser with the system.

### How do you ensure your solution is correct?
To ensure the correctness of the solution, we will:
- Write unit tests for each supported database URL format to verify that the parser correctly extracts the relevant components.
- Use test-driven development (TDD) to guide the implementation and ensure that all edge cases are considered.
- Validate the parsed components against known valid and invalid database URLs to ensure robustness.
