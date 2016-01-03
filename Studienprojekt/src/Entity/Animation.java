package Entity;

public class Animation {

	private int count;
	private int currentFrame;
	
	private int numFrames;
	private int delay;
	private boolean freeze;
	
	public Animation(int delay, int numFrames) {
		this.count = 0;
		this.currentFrame = 0;
		this.numFrames = numFrames;
		this.delay = delay;
		this.freeze = false;
	}
	
	public void setDelay(int delay) {
		this.delay = delay;
	}
	
	public void setNumFrames(int numFrames) {
		this.numFrames = numFrames;
	}
	
	public void setFreeze(boolean state) {
		this.freeze = state;
	}
	
	public void update() {
		if (freeze) {
			currentFrame = numFrames;
			return;
		}
		
		count++;
		
		if(count == delay) {
			currentFrame++;
			count = 0;
		}
		if(currentFrame >= numFrames) {
			currentFrame = 0;
		}
	}	
	
	public int getFrame() { return currentFrame; }
}
