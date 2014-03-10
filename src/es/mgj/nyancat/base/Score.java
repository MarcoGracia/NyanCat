package es.mgj.nyancat.base;

public class Score implements Comparable<Score>{
	
	private String name;
	private int rainbows;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getRainbows() {
		return rainbows;
	}
	public void setRainbows(int rainbows) {
		this.rainbows = rainbows;
	}
	@Override
	public int compareTo(Score arg0) {
		
		if(rainbows > arg0.getRainbows())
			return -1 ;
		else if(rainbows < arg0.getRainbows())
			return 1 ;
		
		return 0;
		
	}
	
	
}
