package com.test.jsf.cdi.qualifier;

import javax.enterprise.context.SessionScoped;
import javax.inject.Qualifier;

@Qualifier
@SessionScoped
public @interface CustomSession {

}
