package com.first_sprint.swe;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import java.util.Date;
@ApplicationPath("/services")
@Path("/items")
public class DeskClerk {
    
    private List<String> list = new CopyOnWriteArrayList<String>();
       
    public DeskClerk() {

        for (int i = 0; i < 20; i++) {
            list.add("Entry " + String.valueOf(i));
        }
        
        Collections.reverse(list);
    }
    
    @GET
    public Response getList() {
        Gson gson = new Gson();
        
        return Response.ok(gson.toJson(list)).build();
    }
 
    @GET
    @Path("clear/{id}")
    public Response clearListItems(@PathParam("id") String id) {
        
    	Gson gson = new Gson();
    	list.remove(Integer.parseInt(id));
        
    	return Response.ok(gson.toJson(list)).build();
    }
    
    @POST
    public Response postListItem(@FormParam("newEntry") String entry) {
    	Date date = new Date();
        list.add(0, entry + " " + "(created at: " + date + ")");

        return Response.ok().build();
    }
     
}