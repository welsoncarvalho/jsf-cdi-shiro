package com.test.jsf.cdi.paginator;

import java.util.List;

public interface DataPagination<T> {

    Long getRowCount();
    
    List<T> getListResult(int first, int pageSize);
    
}
