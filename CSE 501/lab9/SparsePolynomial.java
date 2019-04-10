package lab9;
import java.util.Map;
import lab9.MapDegreeToCoefficientUtils;
import java.util.HashMap;
import lab8.Polynomial;
//ss
public class SparsePolynomial implements Polynomial{
	
	final private Map<Integer, Double> map;
	
	public SparsePolynomial() {
		this.map = new HashMap<Integer,Double>();
	}

	public SparsePolynomial(Map<Integer,Double> map) {
		this.map = map;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((map == null) ? 0 : map.hashCode());
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
		SparsePolynomial other = (SparsePolynomial) obj;
		if (map == null) {
			if (other.map != null)
				return false;
		} else if (!map.equals(other.map))
			return false;
		return true;
	}
	/**
	 * coefficient degree input
	 * return a new Polynomial with added term
	 */
	@Override
	public Polynomial addTerm(double coefficient, int degree) {
		if(coefficient == 0) {
			return this;
		}
		else{
			return new SparsePolynomial(MapDegreeToCoefficientUtils.createNextMap(coefficient, degree, this.map));
		}
	}
	/**
	 * return the highest degree of Polynomial
	 */
	@Override
	public int degree() {
		int max = 0;
		for(int i = 1; i <= 100; ++i) {
			if(this.map.get(i) != null) {
				max = i;
				}
			}
		return max;
	}
	/**
	 * input degree
	 * return the coefficient at this degree
	 */
	@Override
	public double getCoefficientAtDegree(int degree) {
		if(this.map.get(degree) == null) {
			return 0.0;
		}
		else {
			return this.map.get(degree);
		}
	}
	/**
	 * input x
	 * caculate the vaule
	 */
	@Override
	public double evaluate(double x) {
		double sum = 0.0;
		for(int i : this.map.keySet()) {
			sum += getCoefficientAtDegree(i) * Math.pow(x, i);
		}
		return sum;
	}

	@Override
	public Polynomial derivative() {
		Map<Integer, Double> newMap = new HashMap<Integer, Double>();
		for(int i : this.map.keySet()) {
			
				newMap.put(i-1, this.map.get(i)*i);
			
			
		}
		return new SparsePolynomial(newMap);
	}

	@Override
	public Polynomial sum(Polynomial other) {
		Map<Integer, Double> newMap = MapDegreeToCoefficientUtils.copyMap(this.map);
		for (int i = 0; i <= other.degree(); ++i) {
			if(other.getCoefficientAtDegree(i) != 0.0) {
				if(newMap.get(i) != null) {
					newMap.put(i, newMap.get(i) + other.getCoefficientAtDegree(i));
				}
				else {
					newMap.put(i, other.getCoefficientAtDegree(i));
				}
			}
		}
		return new SparsePolynomial(newMap);
	}

	@Override
	public String toString() {
		String str = "";
		for(int i = 1; i<= 100; ++i) {
			if(this.map.get(i) != null) {
				if(this.map.get(i) > 0) {
					str += "+ " + "" + i + "x^" + "" + this.map.get(i) + " ";
				}
				else {
					str += "- " + "" + i + "x^" + "" + this.map.get(i) + " ";
				}
			}
		}
		if(this.map.get(0) != null) {
			str += "+" + this.map.get(0) + " ";
		}
		return str;
	}
	
	

}
