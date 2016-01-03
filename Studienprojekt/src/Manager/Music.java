package Manager;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;
import java.net.URL;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

/**
 * @author lumpy Class for loading files from the resource/music directory
 */
public class Music {

	private static Clip clip, clip2;

	private static AudioInputStream audioIn3;
	private static SourceDataLine dataline3;

	/**
	 * Loading the Music for the Main and Ingame Menu
	 */
	public static void getMenuMusic() {
		try {
			InputStream audioSrc = Music.class
					.getResourceAsStream("/Music/menu.wav");
			InputStream bufferedInputStream = new BufferedInputStream(audioSrc);
			AudioInputStream audioInputStream = AudioSystem
					.getAudioInputStream(bufferedInputStream);
			AudioFormat af = audioInputStream.getFormat();
			int size = (int) (af.getFrameSize() * audioInputStream
					.getFrameLength());
			byte[] audio = new byte[size];
			DataLine.Info info = new DataLine.Info(Clip.class, af, size);
			bufferedInputStream.read(audio, 0, size);
			clip = (Clip) AudioSystem.getLine(info);
			clip.open(af, audio, 0, size);
			clip.start();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Loading the Backgroundmusic for the game
	 */
	public static void getGameMusic() {
		try {
			InputStream audioSrc = Music.class
					.getResourceAsStream("/Music/backgroundmusic.wav");
			InputStream bufferedInputStream = new BufferedInputStream(audioSrc);
			AudioInputStream audioInputStream = AudioSystem
					.getAudioInputStream(bufferedInputStream);
			AudioFormat af = audioInputStream.getFormat();
			int size = (int) (af.getFrameSize() * audioInputStream
					.getFrameLength());
			byte[] audio = new byte[size];
			DataLine.Info info = new DataLine.Info(Clip.class, af, size);
			bufferedInputStream.read(audio, 0, size);
			clip2 = (Clip) AudioSystem.getLine(info);
			clip2.open(af, audio, 0, size);
			clip2.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Loading the selects Sounds for the Menu's
	 */

	public static void getSelectSound() {
		try {
			InputStream audioSrc = Music.class
					.getResourceAsStream("/Music/select.wav");
			InputStream bufferedInputStream = new BufferedInputStream(audioSrc);
			audioIn3 = AudioSystem.getAudioInputStream(bufferedInputStream);
			dataline3 = AudioSystem.getSourceDataLine(audioIn3.getFormat());
			dataline3.open(audioIn3.getFormat());
			dataline3.start();
			byte[] buffer = new byte[1024];
			int count;
			while ((count = audioIn3.read(buffer, 0, 1024)) != -1) {
				dataline3.write(buffer, 0, count);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void getSelectSound2() {
		try {
			InputStream audioSrc = Music.class
					.getResourceAsStream("/Music/select2.wav");
			InputStream bufferedInputStream = new BufferedInputStream(audioSrc);
			audioIn3 = AudioSystem.getAudioInputStream(bufferedInputStream);
			dataline3 = AudioSystem.getSourceDataLine(audioIn3.getFormat());
			dataline3.open(audioIn3.getFormat());
			dataline3.start();
			byte[] buffer = new byte[1024];
			int count;
			while ((count = audioIn3.read(buffer, 0, 1024)) != -1) {
				dataline3.write(buffer, 0, count);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Loading the hitSounds
	 */
	public static void getPlayerHitSound() {
		try {
			InputStream audioSrc = Music.class
					.getResourceAsStream("/Music/hit_player.wav");
			InputStream bufferedInputStream = new BufferedInputStream(audioSrc);
			AudioInputStream audioInputStream = AudioSystem
					.getAudioInputStream(bufferedInputStream);
			AudioFormat af = audioInputStream.getFormat();
			int size = (int) (af.getFrameSize() * audioInputStream
					.getFrameLength());
			byte[] audio = new byte[size];
			DataLine.Info info = new DataLine.Info(Clip.class, af, size);
			bufferedInputStream.read(audio, 0, size);
			clip = (Clip) AudioSystem.getLine(info);
			clip.open(af, audio, 0, size);
			clip.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Loading the HitSounds for Monster -> if they harm you
	 */
	public static void getMonsterHitSound() {
		try {
			InputStream audioSrc = Music.class
					.getResourceAsStream("/Music/hit_enemy.wav");
			InputStream bufferedInputStream = new BufferedInputStream(audioSrc);
			AudioInputStream audioInputStream = AudioSystem
					.getAudioInputStream(bufferedInputStream);
			AudioFormat af = audioInputStream.getFormat();
			int size = (int) (af.getFrameSize() * audioInputStream
					.getFrameLength());
			byte[] audio = new byte[size];
			DataLine.Info info = new DataLine.Info(Clip.class, af, size);
			bufferedInputStream.read(audio, 0, size);
			clip = (Clip) AudioSystem.getLine(info);
			clip.open(af, audio, 0, size);
			clip.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Loading the Sound for the Weapon
	 */
	public static void getLaserWeaponSound() {
		try {
			InputStream audioSrc = Music.class
					.getResourceAsStream("/Music/laser.wav");
			InputStream bufferedInputStream = new BufferedInputStream(audioSrc);
			AudioInputStream audioInputStream = AudioSystem
					.getAudioInputStream(bufferedInputStream);
			AudioFormat af = audioInputStream.getFormat();
			int size = (int) (af.getFrameSize() * audioInputStream
					.getFrameLength());
			byte[] audio = new byte[size];
			DataLine.Info info = new DataLine.Info(Clip.class, af, size);
			bufferedInputStream.read(audio, 0, size);
			clip = (Clip) AudioSystem.getLine(info);
			clip.open(af, audio, 0, size);
			clip.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Loading the Sound for picking up something
	 */
	public static void getPickupSound() {
		try {
			InputStream audioSrc = Music.class
					.getResourceAsStream("/Music/pickup.wav");
			InputStream bufferedInputStream = new BufferedInputStream(audioSrc);
			AudioInputStream audioInputStream = AudioSystem
					.getAudioInputStream(bufferedInputStream);
			AudioFormat af = audioInputStream.getFormat();
			int size = (int) (af.getFrameSize() * audioInputStream
					.getFrameLength());
			byte[] audio = new byte[size];
			DataLine.Info info = new DataLine.Info(Clip.class, af, size);
			bufferedInputStream.read(audio, 0, size);
			clip = (Clip) AudioSystem.getLine(info);
			clip.open(af, audio, 0, size);
			clip.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Loding the Wind Sound for the Bossfight "Herr der Flammen"
	 */
	public static void getWindSound() {
		try {
			InputStream audioSrc = Music.class
					.getResourceAsStream("/Music/wind.wav");
			InputStream bufferedInputStream = new BufferedInputStream(audioSrc);
			AudioInputStream audioInputStream = AudioSystem
					.getAudioInputStream(bufferedInputStream);
			AudioFormat af = audioInputStream.getFormat();
			int size = (int) (af.getFrameSize() * audioInputStream
					.getFrameLength());
			byte[] audio = new byte[size];
			DataLine.Info info = new DataLine.Info(Clip.class, af, size);
			bufferedInputStream.read(audio, 0, size);
			clip = (Clip) AudioSystem.getLine(info);
			clip.open(af, audio, 0, size);
			clip.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Loading the Fire Sound for the Bossfight "Herr der Flammen"
	 */
	public static void getFireSound() {
		try {
			InputStream audioSrc = Music.class
					.getResourceAsStream("/Music/fire.wav");
			InputStream bufferedInputStream = new BufferedInputStream(audioSrc);
			AudioInputStream audioInputStream = AudioSystem
					.getAudioInputStream(bufferedInputStream);
			AudioFormat af = audioInputStream.getFormat();
			int size = (int) (af.getFrameSize() * audioInputStream
					.getFrameLength());
			byte[] audio = new byte[size];
			DataLine.Info info = new DataLine.Info(Clip.class, af, size);
			bufferedInputStream.read(audio, 0, size);
			clip = (Clip) AudioSystem.getLine(info);
			clip.open(af, audio, 0, size);
			clip.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Loading the Muffin throw Sound for the Bossfight "König Muffin"
	 */
	public static void getMuffinThrowSound() {
		try {
			InputStream audioSrc = Music.class
					.getResourceAsStream("/Music/muffin_throw.wav");
			InputStream bufferedInputStream = new BufferedInputStream(audioSrc);
			AudioInputStream audioInputStream = AudioSystem
					.getAudioInputStream(bufferedInputStream);
			AudioFormat af = audioInputStream.getFormat();
			int size = (int) (af.getFrameSize() * audioInputStream
					.getFrameLength());
			byte[] audio = new byte[size];
			DataLine.Info info = new DataLine.Info(Clip.class, af, size);
			bufferedInputStream.read(audio, 0, size);
			clip = (Clip) AudioSystem.getLine(info);
			clip.open(af, audio, 0, size);
			clip.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Loding the Sound for change from Day to Night
	 */
	public static void getWolfSound() {
		try {
			InputStream audioSrc = Music.class
					.getResourceAsStream("/Music/wolf.wav");
			InputStream bufferedInputStream = new BufferedInputStream(audioSrc);
			AudioInputStream audioInputStream = AudioSystem
					.getAudioInputStream(bufferedInputStream);
			AudioFormat af = audioInputStream.getFormat();
			int size = (int) (af.getFrameSize() * audioInputStream
					.getFrameLength());
			byte[] audio = new byte[size];
			DataLine.Info info = new DataLine.Info(Clip.class, af, size);
			bufferedInputStream.read(audio, 0, size);
			clip = (Clip) AudioSystem.getLine(info);
			clip.open(af, audio, 0, size);
			clip.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Loading the Sound for change from Night to Day
	 */
	public static void getHahnSound() {
		try {
			InputStream audioSrc = Music.class
					.getResourceAsStream("/Music/Hahn.wav");
			InputStream bufferedInputStream = new BufferedInputStream(audioSrc);
			AudioInputStream audioInputStream = AudioSystem
					.getAudioInputStream(bufferedInputStream);
			AudioFormat af = audioInputStream.getFormat();
			int size = (int) (af.getFrameSize() * audioInputStream
					.getFrameLength());
			byte[] audio = new byte[size];
			DataLine.Info info = new DataLine.Info(Clip.class, af, size);
			bufferedInputStream.read(audio, 0, size);
			clip = (Clip) AudioSystem.getLine(info);
			clip.open(af, audio, 0, size);
			clip.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Loading the Sound for Healing the Character
	 */
	public static void getPowerupSound() {
		try {
			InputStream audioSrc = Music.class
					.getResourceAsStream("/Music/powerup.wav");
			InputStream bufferedInputStream = new BufferedInputStream(audioSrc);
			AudioInputStream audioInputStream = AudioSystem
					.getAudioInputStream(bufferedInputStream);
			AudioFormat af = audioInputStream.getFormat();
			int size = (int) (af.getFrameSize() * audioInputStream
					.getFrameLength());
			byte[] audio = new byte[size];
			DataLine.Info info = new DataLine.Info(Clip.class, af, size);
			bufferedInputStream.read(audio, 0, size);
			clip = (Clip) AudioSystem.getLine(info);
			clip.open(af, audio, 0, size);
			clip.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Loading the Alert Sound for the Tutorial/Intro
	 */
	public static Clip clipAlertSound;
	public static void getAlertSound() {
		try {
			InputStream audioSrc = Music.class
					.getResourceAsStream("/Music/alert.wav");
			InputStream bufferedInputStream = new BufferedInputStream(audioSrc);
			AudioInputStream audioInputStream = AudioSystem
					.getAudioInputStream(bufferedInputStream);
			AudioFormat af = audioInputStream.getFormat();
			int size = (int) (af.getFrameSize() * audioInputStream
					.getFrameLength());
			byte[] audio = new byte[size];
			DataLine.Info info = new DataLine.Info(Clip.class, af, size);
			bufferedInputStream.read(audio, 0, size);
			clipAlertSound = (Clip) AudioSystem.getLine(info);
			clipAlertSound.open(af, audio, 0, size);
			clipAlertSound.start();
			} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Loading Computer Voice for the Tutorial/Intro
	 */
	public static Clip clipAlertplanSound;
	public static void getAlertplanSound() {
		try {
			InputStream audioSrc = Music.class
					.getResourceAsStream("/Music/alarmplan6.wav");
			InputStream bufferedInputStream = new BufferedInputStream(audioSrc);
			AudioInputStream audioInputStream = AudioSystem
					.getAudioInputStream(bufferedInputStream);
			AudioFormat af = audioInputStream.getFormat();
			int size = (int) (af.getFrameSize() * audioInputStream
					.getFrameLength());
			byte[] audio = new byte[size];
			DataLine.Info info = new DataLine.Info(Clip.class, af, size);
			bufferedInputStream.read(audio, 0, size);
			clipAlertplanSound = (Clip) AudioSystem.getLine(info);
			clipAlertplanSound.open(af, audio, 0, size);
			clipAlertplanSound.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static Clip clipChecklist1;
	public static void getChecklist1() {
		try {
			InputStream audioSrc = Music.class
					.getResourceAsStream("/Music/checkliste1.wav");
			InputStream bufferedInputStream = new BufferedInputStream(audioSrc);
			AudioInputStream audioInputStream = AudioSystem
					.getAudioInputStream(bufferedInputStream);
			AudioFormat af = audioInputStream.getFormat();
			int size = (int) (af.getFrameSize() * audioInputStream
					.getFrameLength());
			byte[] audio = new byte[size];
			DataLine.Info info = new DataLine.Info(Clip.class, af, size);
			bufferedInputStream.read(audio, 0, size);
			clipChecklist1 = (Clip) AudioSystem.getLine(info);
			clipChecklist1.open(af, audio, 0, size);
			clipChecklist1.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static Clip clipChecklist2;
	public static void getChecklist2() {
		try {
			InputStream audioSrc = Music.class
					.getResourceAsStream("/Music/checkliste2.wav");
			InputStream bufferedInputStream = new BufferedInputStream(audioSrc);
			AudioInputStream audioInputStream = AudioSystem
					.getAudioInputStream(bufferedInputStream);
			AudioFormat af = audioInputStream.getFormat();
			int size = (int) (af.getFrameSize() * audioInputStream
					.getFrameLength());
			byte[] audio = new byte[size];
			DataLine.Info info = new DataLine.Info(Clip.class, af, size);
			bufferedInputStream.read(audio, 0, size);
			clipChecklist2 = (Clip) AudioSystem.getLine(info);
			clipChecklist2.open(af, audio, 0, size);
			clipChecklist2.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Clip getClip() {
		return clip;
	}

	public static Clip getBackgroundMusic() {
		return clip2;
	}
	public boolean isPlayingAlertSound(){
		return clipAlertSound.isRunning();
	}
	public boolean isPlayingAlertplanSound(){
		return clipAlertplanSound.isActive();
	}

	/**
	 * Stop Playing the Menu Music
	 */
	public static void stopMenuMusic() {
		clip.stop();
		clip.close();

	}

	/**
	 * Stop Playing the Game Music
	 */
	public static void stopGameMusic() {
		clip2.stop();
		clip2.close();
	}

}