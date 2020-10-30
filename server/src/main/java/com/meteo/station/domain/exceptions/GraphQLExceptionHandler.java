package com.meteo.station.domain.exceptions;

import graphql.GraphQLException;
import graphql.kickstart.spring.error.ThrowableGraphQLError;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;

@Component
public class GraphQLExceptionHandler {

    @ExceptionHandler(GraphQLException.class)
    public ThrowableGraphQLError handle(GraphQLException e) {
        return new ThrowableGraphQLError(e);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ThrowableGraphQLError handle(ConstraintViolationException e) {
        return new ThrowableGraphQLError(e, e.getMessage());
    }
}
