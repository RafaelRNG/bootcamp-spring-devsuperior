package aula_junit.entities;

import java.util.Objects;

public class Financing {
	
	private final Double FINANCING_INPUT = 0.2; 
	
	private Long id;
	private Double totalAmount;
	private Double income;
	private Integer months;
	
	//Constructor
	public Financing(Long id, Double totalAmount, Double income, Integer months) {
		this.id = id;
		this.totalAmount = totalAmount;
		this.income = income;
		this.months = months;
		validation(totalAmount, income, months);
	}

	//Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		validation(totalAmount, income, months);
		this.totalAmount = totalAmount;
	}

	public Double getIncome() {
		return income;
	}

	public void setIncome(Double income) {
		validation(totalAmount, income, months);
		this.income = income;
	}

	public Integer getMonths() {
		return months;
	}

	public void setMonths(Integer months) {
		validation(totalAmount, income, months);
		this.months = months;
	}
	
	//Methods
	public Double entry() {
		return totalAmount * FINANCING_INPUT;
	}

	public Double quota() {
		return (totalAmount - entry()) / months;
	}
	
	private void validation(Double totalAmount, Double income, Integer months) {
		if((totalAmount - entry()) / months > income / 2) {
			throw new IllegalArgumentException("installment amount is not valid!");
		}
	}

	//override default methods
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Financing other = (Financing) obj;
		return Objects.equals(id, other.id);
	}
}