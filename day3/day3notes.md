# Springboot 101 Day 3

### Input validation  
- This refers to ensuring that all fields should accept valid input.  
- Assume that the user is not compliant. The code or logic should catch all user errors.
- Use validation annotations (@NotNull, @NotBlank, @Size, etc.) to enforce input validation and ensure that the provided data meets the required criteria. 
- Consider using custom validation annotations or writing custom validators for more complex validation rules. 
- Validate input at both the controller level (request body, query parameters) and service/domain level if necessary. 
- Leverage validation frameworks like Hibernate Validator or Jakarta Bean Validation for streamlined validation handling. 
### Handling Exceptions
In handling exceptions, it revolves typically on:
- ErrorResponse Class
- ExceptionHandler Class
- The necessary class / service must implement methods that throws the error

Also notes:
- Use exception handling mechanisms to gracefully handle and communicate errors or exceptional conditions that occur during application execution.
- Create custom exception classes to represent specific types of errors or exceptional situations relevant to your application domain.
- Implement exception handlers to catch and handle exceptions in a centralized manner, providing appropriate responses or error messages.
- Use global exception handlers or annotated exception handlers (e.g., @ExceptionHandler) to handle specific exceptions and return consistent error responses.
- Consider leveraging frameworks like Spring's @ControllerAdvice or custom HandlerExceptionResolver for centralized exception handling.

### JPA (Database)  
- Make sure that the Database in Docker and the App in Spring Boot runs on localhost:port  
- Always check the application.properties file under resource and ensure that the parameter  
in the database is correct