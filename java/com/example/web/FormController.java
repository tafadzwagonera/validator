/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.web;

import com.example.helper.validate.Validator;
import com.example.helper.validate.ValidatorContext;
import com.example.helper.validate.ValidatorFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Tafadzwa
 */
public class FormController extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        boolean proceed = true;
        String[] array = null;

        //an array holding permissible fields
        array = new String[]{"firstname", "lastname", "address", "zipcode", "phone", "street", "email"};       
        List<String> allowedFields = new ArrayList<String>(Arrays.asList(array));
        
        //an array holding required fields
        array = new String[]{"firstname", "lastname", "address", "email", "zipcode"};       
        List<String> requiredFields = new ArrayList<String>(Arrays.asList(array));
        
        //a map to hold incoming data
        Map<String, String> fields = new HashMap<String, String>();

        for (String field : allowedFields) {
            fields.put(field, request.getParameter(field));
        }

        for (Map.Entry<String, String> field : fields.entrySet()) {

            //is the field allowed at all?
            if (ValidatorContext.inArray(allowedFields, field)) {

                //is the field required but empty?
                if (ValidatorContext.inArray(requiredFields, field) && field.getValue().length() == 0) {

                    //a required field should not be empty at any cost
                    proceed = false;
                    break;
                }
            }
        }
        
        //proceed if all validations were met
        if (proceed) {
            proceed = true;
            ValidatorContext validatorContext = null;
            Validator validator = null;
            ValidatorFactory validatorFactory = new ValidatorFactory();

            for (Map.Entry<String, String> field : fields.entrySet()) {
                
                //is this a required field?
                if (ValidatorContext.inArray(requiredFields, field)) {
                    
                    //then validate it using an approriate valiation algorithm
                    validator = validatorFactory.getValidator(field.getKey());
                    validatorContext = new ValidatorContext(validator);
                    if (!validatorContext.validate(field.getValue())) {
                        proceed = false;
                        break;
                    }
                }                
            }
            
            //proceed if all validations were met
            if(proceed) {
                out.println("Yey!!");
                //instantiate entity and populate with data here                
            } else {
                out.println("Some validations were not met");
            }
        } else {
            out.println("Some required fields are empty");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
