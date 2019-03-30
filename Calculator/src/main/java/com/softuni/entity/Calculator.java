package com.softuni.entity;

public class Calculator {
    private double leftOperand;
    private String operator;
    private double rightOperand;

    public Calculator(double leftOperand, String operator, double rightOperand) {
        this.leftOperand = leftOperand;
        this.operator = operator;
        this.rightOperand = rightOperand;
    }

    public double getLeftOperand() {
        return leftOperand;
    }

    public String getOperator() {
        return operator;
    }

    public double getRightOperand() {
        return rightOperand;
    }

    public double calculate() {
        return switch (this.operator) {
            case "+" -> this.leftOperand + this.rightOperand;
            case "-" -> this.leftOperand - this.rightOperand;
            case "*" -> this.leftOperand * this.rightOperand;
            case "/" -> this.leftOperand / this.rightOperand;
            default -> throw new IllegalStateException("Unexpected operator: " + this.operator);
        };
    }
}
