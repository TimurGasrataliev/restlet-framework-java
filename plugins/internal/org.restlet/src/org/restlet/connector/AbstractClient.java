/*
 * Copyright 2005-2006 Noelios Consulting.
 *
 * The contents of this file are subject to the terms
 * of the Common Development and Distribution License
 * (the "License").  You may not use this file except
 * in compliance with the License.
 *
 * You can obtain a copy of the license at
 * http://www.opensource.org/licenses/cddl1.txt
 * See the License for the specific language governing
 * permissions and limitations under the License.
 *
 * When distributing Covered Code, include this CDDL
 * HEADER in each file and include the License file at
 * http://www.opensource.org/licenses/cddl1.txt
 * If applicable, add the following below this CDDL
 * HEADER, with the fields enclosed by brackets "[]"
 * replaced with your own identifying information:
 * Portions Copyright [yyyy] [name of copyright owner]
 */

package org.restlet.connector;

import org.restlet.Call;
import org.restlet.component.Component;
import org.restlet.data.Method;
import org.restlet.data.ParameterList;
import org.restlet.data.Representation;

/**
 * Abstract client connector.
 * @author Jerome Louvel (contact@noelios.com) <a href="http://www.noelios.com/">Noelios Consulting</a>
 */
public abstract class AbstractClient extends AbstractConnector implements Client
{
   /**
    * Constructor.
    * @param owner The owner component.
    * @param parameters The initial parameters.
    */
   public AbstractClient(Component owner, ParameterList parameters)
   {
   	super(owner, parameters);
   }

   /**
    * Gets the identified resource.
    * @param resourceUri The URI of the resource to get.
    * @return The returned uniform call.
    */
   public Call get(String resourceUri)
   {
      Call call = new Call();
      call.setResourceRef(resourceUri);
      call.setMethod(Method.GET);
      handle(call);
      return call;
   }

   /**
    * Post a representation to the identified resource.
    * @param resourceUri The URI of the resource to post to.
    * @param input The input representation to post.
    * @return The returned uniform call.
    */
	public Call post(String resourceUri, Representation input)
   {
      Call call = new Call();
      call.setResourceRef(resourceUri);
      call.setMethod(Method.POST);
      call.setInput(input);
      handle(call);
      return call;
   }

   /**
    * Puts a representation in the identified resource.
    * @param resourceUri The URI of the resource to modify.
    * @param input The input representation to put.
    * @return The returned uniform call.
    */
   public Call put(String resourceUri, Representation input)
   {
      Call call = new Call();
      call.setResourceRef(resourceUri);
      call.setMethod(Method.PUT);
      call.setInput(input);
      handle(call);
      return call;
   }

   /**
    * Deletes the identified resource.
    * @param resourceUri The URI of the resource to delete.
    * @return The returned uniform call.
    */
   public Call delete(String resourceUri)
   {
      Call call = new Call();
      call.setResourceRef(resourceUri);
      call.setMethod(Method.DELETE);
      handle(call);
      return call;
   }

   /**
    * Determines if a call has any concrete input.
    * @param call The call to analyze.
    * @return True if the call has any concrete input.
    */
   protected boolean hasInput(Call call)
   {
      boolean result = true;
      
      if(call.getMethod().equals(Method.GET) || call.getMethod().equals(Method.HEAD) ||
            call.getMethod().equals(Method.DELETE))
      {
         result = false;
      }
      else
      {
         result = (call.getInput() != null) && call.getInput().isContentAvailable();
      }
      
      return result;
   }

}
