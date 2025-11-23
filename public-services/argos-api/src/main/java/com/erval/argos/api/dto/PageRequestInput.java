package com.erval.argos.api.dto;

import com.erval.argos.core.application.SortDirection;

/**
 * GraphQL input mirroring {@link com.erval.argos.core.application.PageRequest}.
 */
public record PageRequestInput(
        Integer page,
        Integer size,
        String sortBy,
        SortDirection sortDirection) {
}
