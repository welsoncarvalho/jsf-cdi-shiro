package com.test.jsf.cdi.paginator;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;

import com.test.jsf.cdi.model.AbstractModel;

public class DataPaginator<T extends AbstractModel> {

    private static final int INITIAL_PAGE_SIZE = 10;
    protected static final int INITIAL_PAGE = 0;
    
    private DataPagination<T> pagination;

    // Atributos
    private List<Integer> pages;
    private List<T> list;
    private Long rowLength;

    public DataPaginator(DataPagination<T> pagination) {
        this.pagination = pagination;
        initSearch();
    }
    
    private void initSearch() {
        this.rowLength = this.pagination.getRowCount();
        
        if(this.rowLength != null && 
                this.rowLength.compareTo(NumberUtils.LONG_ZERO) > 0) {
            
            buildPages();
            this.list = this.pagination.getListResult(INITIAL_PAGE, INITIAL_PAGE_SIZE);
            
        }
    }
    
    private void buildPages() {
        if(this.rowLength.intValue() > INITIAL_PAGE_SIZE) {
            
            this.pages = new ArrayList<>();
            int pagesLength = this.rowLength.intValue() / INITIAL_PAGE_SIZE;
            
            for(int i = 1; i <= pagesLength; i++) {
                this.pages.add(i);
            }
            
        } else {
            
            this.pages = new ArrayList<>();
            this.pages.add(1);
            
        }
    }
    
    public Boolean isEmpty() {
        return this.list == null || this.list.isEmpty();
    }
    
    public List<T> getList() {
        return list;
    }

}
