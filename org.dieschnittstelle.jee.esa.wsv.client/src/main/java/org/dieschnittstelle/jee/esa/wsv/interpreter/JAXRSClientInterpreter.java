package org.dieschnittstelle.jee.esa.wsv.interpreter;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import org.dieschnittstelle.jee.esa.wsv.interpreter.json.JSONObjectSerialiser;

/*
 * TODO: implement this class such that all crud operations declared on ITouchpointCRUDWebService in
 * .esa.wsv can be successfully called from the class UseJAXRSClientInterpreter in the .esa.wsv.client project
 */
public class JAXRSClientInterpreter implements InvocationHandler {

    // declare a baseurl

    // declare a common path segment

    // use a JSONObjectSerialiser

    // use an attribute that holds the serviceInterface for providing a
    // toString() method

    // only for demo, later: use a constructor that only takes the
    // serviceInterface and reads
    // the baseurl from the DefaultBaseUrl annotation - this is only done for
    // demoing how annotation types may be declared!

    // use a constructor that takes an annotated service interface and a baseurl
    // the implementation should read out the path annotation, we assume we
    // produce and consume
    // json, i.e. these annotations will not be considered here

    // implement this method
    @Override
    public Object invoke(Object proxy, Method meth, Object[] args)
            throws Throwable {

        // use a default http client

        // create the url using baseurl and commonpath

        // check whether we have a path annotation and append the url (path
        // params will be handled when looking at the method arguments

        // a value that needs to be sent via the http request body

        // check whether we have method arguments - only consider
        // pathparam annotations here - if no args are passed, the value of
        // args is null!
        // if no pathparam annotation is present assume that the argument
        // value is passed via the body of the http request

        // if we have a path param, we need to replace the
        // corresponding pattern in the url with the
        // parameter value

        // if we do not have a path param, we assume the argument
        // value will be sent via the body of the request

        // declare a HttpUriRequest variable

        // check which of the http method annotation is present and
        // instantiate request accordingly passing the url

        // add a header declaring that we accept json (for header names, you can use the constants declared in javax.ws.rs.core.HttpHeaders)

        // if we need to send the method argument in the request body:

        // create the entity

        // use a ByteArrayOutputStream for writing json

        // write the object to the stream using the jsonSerialiser

        // create an ByteArrayEntity from the stream's content

        // set the entity on the request which must be cast to
        // HttpEntityEnclosingRequest

        // and add a content type header

        // (end of handling the request body)

        // then send the request to the server and get the response

        // check the response code

        // declare a variable for the return value

        // if the return type is a generic type, getGenericReturnType() will
        // return allow to access the generic type and its type parameter,
        // accessible by casting the
        // Type object to ParameterizedType

        // don't forget to cleanup the entity using EntityUtils.consume()

        return null;
    }

    public static void show(Object content) {
        System.err.println(content + "\n");
    }


}
