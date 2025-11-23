package com.erval.argos.core.application;

import java.util.List;

/**
 * Represents a page of results returned from a query.
 *
 * @param content       list of items on the current page
 * @param totalElements total number of elements taht match the query
 * @param page          zero-based page index
 * @param size          page size
 * @param <T>           item type
 */

public record PageResult<T>(
        List<T> content,
        long totalElements,
        int page,
        int size) {
    /**
     * Calculates the total number of pages for this result.
     *
     * @return the total number of pages or 0 if page size is 0
     */
    public int totalPages() {
        if (size == 0) {
            return 0;
        }
        return (int) Math.ceil((double) totalElements / size);
    }
}
