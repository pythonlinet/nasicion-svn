/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bd1.obli2012.framework;

/**
 *
 * @author guillermo
 */
public class ExecutionResult {
    public final boolean success;
    public final String errorMessage;
    
    public ExecutionResult(boolean success,String errorMessage){
        this.success = success;
        this.errorMessage = errorMessage;
    }
    
}
