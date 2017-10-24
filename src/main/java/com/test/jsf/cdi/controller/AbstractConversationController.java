package com.test.jsf.cdi.controller;

import javax.enterprise.context.Conversation;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

public abstract class AbstractConversationController extends AbstractController {

    private static final long serialVersionUID = -5775926773220200569L;
    
    @Inject
    private Conversation conversation;
    
    // Conversation
    
    public void beginConversation() {
        if(!FacesContext.getCurrentInstance().isPostback() && conversation.isTransient()) {
            conversation.begin();
        }
    }
    
    public void endConversation() {
        if(!conversation.isTransient()) {
            conversation.end();
        }
    }

}
