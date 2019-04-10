package lab8;

import java.util.Arrays;

public class DensePolynomial implements Polynomial {
	
	private final double[] coefficient;
	
	
	public DensePolynomial() {
		this.coefficient = new double[]{0.0};
	}
	
	public DensePolynomial(double[] co) {
		this.coefficient = co;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(coefficient);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DensePolynomial other = (DensePolynomial) obj;
		if (!Arrays.equals(coefficient, other.coefficient))
			return false;
		return true;
	}

	@Override
	public Polynomial addTerm(double coefficient, int degree) {
		if(coefficient == 0) {
			double[] co = new double[this.coefficient.length];
			for(int i = 0; i < co.length; ++i) {
				co[i] = this.coefficient[i];
			}
			return new DensePolynomial(co);
		}
		else {
			if (degree < this.coefficient.length) {
				double[] co = new double[this.coefficient.length];
				for(int i = 0; i < co.length; ++i) {
					co[i] = this.coefficient[i];
				}
				co[degree] += coefficient;
				return new DensePolynomial(co);
			}
			else {
				double[] co = new double[degree + 1];
				for(int i = 0; i < this.coefficient.length; ++i) {
					co[i] = this.coefficient[i];
				}
				co[degree] = coefficient;
				return new DensePolynomial(co);
			}
		}
		
	}

	@Override
	public int degree() {
		return this.coefficient.length - 1;
	}

	@Override
	public double getCoefficientAtDegree(int degree) {
		if(degree < this.coefficient.length) {
			return this.coefficient[degree];
		}
		else {
			return 0.0;
		}
	}

	@Override
	public double evaluate(double x) {
		double curr;
		double res = 0;
		for(int i = 0; i < this.coefficient.length; ++i) {
			curr = this.coefficient[i] * Math.pow(x, i);
			res += curr;
		}
		return res;
	}

	@Override
	public Polynomial derivative() {
		if(this.coefficient.length > 1) {
			double[] co = new double[this.coefficient.length - 1];
			for(int i = 0; i < co.length; ++i) {
				co[i] = this.coefficient[i + 1] *(i+1);
			}
			return new DensePolynomial(co);
		}
		else {
			return new DensePolynomial();
		}
	}

	@Override
	public Polynomial sum(Polynomial other) {
		if(this.coefficient.length > other.degree()+1) {
			double[] co = new double[this.coefficient.length];
			for(int j = 0; j < this.coefficient.length; ++j) {
				co[j] = this.coefficient[j];
			}
			for(int k = 0; k < other.degree()+1; ++k) {
				co[k] += other.getCoefficientAtDegree(k);
			}
			return new DensePolynomial(co);
		}
		else {
			double[] co = new double[other.degree()+1];
			for(int j = 0; j < other.degree()+1; ++j) {
				co[j] = other.getCoefficientAtDegree(j);
			}
			for(int k = 0; k < this.coefficient.length; ++k) {
				co[k] += this.coefficient[k];
			}
			return new DensePolynomial(co);
		}
	}

	@Override
	public String toString() {
		String res = "";
		String curr;
		for(int i = 0; i < this.coefficient.length; ++i) {
			if(i == this.coefficient.length-1) {
				curr = ""+this.coefficient[i] + "x^" + ""+ i;
			}
			else{
				curr = ""+this.coefficient[i] + "x^" + ""+ i + " + ";
			}
			res += curr;
		}
		return res;
	}
	
	

}
