/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.exceptions;

public class UnpaidLoanException extends Exception {

    public UnpaidLoanException(String settle_all_unpaid_loans_before_applying_f) {
        super(settle_all_unpaid_loans_before_applying_f);
    }
    
}
