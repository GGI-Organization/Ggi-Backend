package com.ggi.payload.request;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class DefaultPageable implements Pageable {
    @Override
    public int getPageNumber() {
        return 1;
    }

    @Override
    public int getPageSize() {
        return 100;
    }

    @Override
    public long getOffset() {
        return 0;
    }

    @Override
    public Sort getSort() {
        return Sort.by(Sort.Direction.DESC, "createdAt");
    }

    @Override
    public Pageable next() {
        return null;
    }

    @Override
    public Pageable previousOrFirst() {
        return null;
    }

    @Override
    public Pageable first() {
        return null;
    }

    @Override
    public Pageable withPage(int pageNumber) {
        return null;
    }

    @Override
    public boolean hasPrevious() {
        return false;
    }
}
