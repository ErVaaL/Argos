package com.erval.argos.core.application;

/**
 * Represents a request for a page of results from a query.
 *
 * @param page      zero-based page index
 * @param size      number of items per page
 * @param sortBy    field name to sort by (e.g. "timestamp", "name")
 * @param direction sort direction (ASC or DESC)
 */
public record PageRequest(int page, int size, String sortBy, SortDirection direction) {
}
