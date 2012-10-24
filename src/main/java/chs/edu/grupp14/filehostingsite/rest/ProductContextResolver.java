/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chs.edu.grupp14.filehostingsite.rest;

//import com.sun.jersey.api.json.JSONConfiguration;
//import com.sun.jersey.api.json.JSONJAXBContext;
//import javax.ws.rs.ext.ContextResolver;
//import javax.ws.rs.ext.Provider;
//import javax.xml.bind.JAXBContext;

/**
 * This is used to remove '@'s from attribute names when serializing to JSON
 * (convert XML to JSON) The default serialization is "mapped" (i.e. badgerfish) 
 * here changed to "natural", see below.
 * 
 * Need compile time dependency on jersey-json see pom. 
 * 
 * See output form GlassFish "Provider classes found: ....." (should show up)
 * 
 * @author hajo
 */



//Behövs inte för tillfället, kan behövas om vi implementerar mer rest


//@Provider
public class ProductContextResolver {//implements ContextResolver<JAXBContext> {

//    private JAXBContext context;
//    private Class[] types =    null; //{ProductProxy.class};
//
//    public ProductContextResolver() throws Exception {
//        this.context = new JSONJAXBContext(JSONConfiguration.natural().build(),
//                types);
//    }
//
//    @Override
//    public JAXBContext getContext(Class<?> objectType) {
//        return (types[0].equals(objectType)) ? context : null;
//    }
}
