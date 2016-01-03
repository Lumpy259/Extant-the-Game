package Entity.Boss;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import Entity.Animation;
import Entity.Entity;
import Entity.HitBox;
import Entity.Player;
import GameState.BossState;
import GameState.PlayState;
import Main.Game;
import Manager.Content;
import Manager.InputHandler;
import Manager.Music;
import World.World;

public abstract class Boss extends Entity {

	public int scale = 1;
	public int maxLife, width, heigth;
	public  int currentLife;
	private int damage;
	public double x;
	public double y;
	private Random rnd = new Random();
	private HitBox hitBox;
	private double moveSpeed;
	private int attackrange;
	protected int BossXP;
	int direction;
	private int laenge = 0;
	private int count = 0;
	public static int viewrange = (int)(Game.WIDTH*0.66);
	public Content image;	
	public Content image2;
	public String name;
	public int bosscooldown;
	public int bossmonstercooldown;
	public boolean switchanimation = false;
	public int lightningcooldown = 0;
	public int lightninghitcooldown= 0;
	public int lightnAnimation = 0;
	private Content lighting;
	public Animation lightingAnimation;
	public int freezeAnimation = 0;
	public int freezecooldown = 0;
	private Content freeze;
	public Animation freezingAnimation;
	public int freezehitcooldown = 0;
	private Content eat;
	public Animation eatAnimation;
	public int eatingAnimation = 0;
	public int throwAnimation = 0;
	public int throwcooldown = 50;
	private Content throwcake;
	public Animation throwingcakeAnimation;
	public int randomx;
	public int randomy;
	public int random2x;
	public int random2y;
	public double flightpathx;
	public double flightpathy;
	public int flightrotation;
	public double temp;
	public int flightdirection = 0;
	public boolean used = false;
	public int walkdirection = 1;
	private Content flamebreath;
	public Animation flamebreathAnimation;
	private Content attackbreath;
	public Animation flamebreathattackAnimation;
	public int flamingbreathAnimation = 0;
	private Content wind;
	public Animation windattackAnimation;
	private Content windattack;
	public Animation windbossattackAnimation;
	public int windAnimation = 0;
	public double flightpathx2;
	public double flightpathy2;
	public double flightpathx3;
	public double flightpathy3;
	public int windcooldown = 150;
	public int count2 = 0;
	public boolean used2 = false;
	private boolean once = true;
	public HashMap<Class, Integer> loot;
	public static int knockupanimation;
	
	public Boss(String name, String path, String path2, int width, int height, int damage, int maxLife, double x, double y, double moveSpeed, HitBox hitBox, Content image2, Content image3, int attackrange) {
		super();
		this.maxLife = maxLife;
		this.damage = damage;	
		this.currentLife = maxLife;
		this.image = image2;
		this.image2 = image3;
		super.x = x;
		super.y = y;
		super.height = height;
		super.width = width;
		super.hitBox = hitBox;
		this.moveSpeed = moveSpeed;
		this.attackrange = attackrange;
		this.name = name;
		temp = PlayState.player.moveSpeed ;
		lightingAnimation = new Animation(30, 3);
		lighting = new Content("LIGHTNING", "Resources/Animations/Lightninganimations.png", 59, 104, new Point(1,3));
		freezingAnimation = new Animation(30, 3);
		freeze = new Content("FREEZE", "Resources/Animations/Freezeanimation.png", 100, 64, new Point(1,3));
		eatAnimation = new Animation(50, 3);
		eat = new Content("EATING", "Resources/Animations/eatmuffin.png", 64, 64, new Point(1,3));
		throwingcakeAnimation = new Animation(1, 1);
		throwcake = new Content("THROWING", "Resources/Animations/Cupcake.png", 28, 28, new Point(1,1));
		flamebreath = new Content("BREATH", "Resources/Animations/flamebreath.png", 32, 64, new Point(1,1));
		flamebreathAnimation = new Animation(1, 1);
		attackbreath = new Content("ATTACKBREATH", "Resources/Animations/beast_attack.png", 128, 64, new Point(1,3));
		flamebreathattackAnimation = new Animation(13, 3);
		wind = new Content("WIND", "Resources/Animations/windanimation.png", 44, 62, new Point(1,1));
		windattackAnimation = new Animation(1, 1);
		windattack = new Content("WINDATTACK", "Resources/Animations/beast_rattack.png", 128, 64, new Point(1,3));
		windbossattackAnimation = new Animation(13, 3);
	}
	
	public void attack(ArrayList<Entity> entities) {
			Player.currentlife = Player.currentlife - ( (int)(damage*( 1-(((Math.log(Player.armor)/Math.log(2.0))*5 )/100))));
	}


	public void update(InputHandler input, ArrayList<Entity> entities) {
		if (bosscooldown > 0)
			bosscooldown -= 10;
		if(this.currentLife <= 0) {
			Player.xp += this.BossXP;
			PlayState.removeEntity.add(this);
			switchanimation = false;
		}
		for (Entity e : entities){
			//********************************************************************************************************************
			if(e instanceof Troll_priest) {
				if (bosscooldown > 0){
					Random rand = new Random(); 
					super.x = rand.nextInt(416)+32;
					super.y = rand.nextInt(288)+32;
					bosscooldown=0;
				}
				
				lightningcooldown -= (lightningcooldown > 0 ? 1 : 0);
				lightninghitcooldown -= (lightninghitcooldown > 0 ? 1 : 0);
				lightnAnimation -= (lightnAnimation > 0 ? 1 : 0);
				if (lightningcooldown == 0){
					Random rand = new Random(); 
					lightnAnimation = 90;
					lightningcooldown = 150;
					randomx = rand.nextInt(32);
					randomy = rand.nextInt(32);
					if (rand.nextInt(2) == 0)
						randomx *= -1;
					if (rand.nextInt(2) == 0)
						randomy *= -1;
					randomx += BossState.x - 30;
					randomy += BossState.y - 75; 
				}
				if(lightnAnimation>0 && lightnAnimation<15 && lightninghitcooldown == 0){
					if(randomy+75 > GameState.BossState.y-16 && randomy+50 < GameState.BossState.y-16 && randomx+45 > GameState.BossState.x-8 && randomx+4 < GameState.BossState.x-8){
						attack(entities); attack(entities); attack(entities);
						lightninghitcooldown = 100;
					}
				}
				freezecooldown -= (freezecooldown > 0 ? 1 : 0);
				freezehitcooldown -= (freezehitcooldown > 0 ? 1 : 0);
				freezeAnimation -= (freezeAnimation > 0 ? 1 : 0);
				if (freezecooldown == 0){
					Random rand = new Random(); 
					freezeAnimation = 90;
					freezecooldown = 150;
					random2x = (int) GameState.BossState.x-46;
					random2y = (int) GameState.BossState.y-16;
				}
				if(freezeAnimation>0 && freezeAnimation<80 &&freezehitcooldown == 0){
					if(freezeAnimation>0 && freezeAnimation<30){
						if(random2y+48 > GameState.BossState.y-16 && random2y-16 < GameState.BossState.y-16 && random2x+92 > GameState.BossState.x-8 && random2x-8 < GameState.BossState.x-8){
							attack(entities);
							freezehitcooldown = 100;
						}
					}
					if(freezeAnimation>30 && freezeAnimation<60){
						if(random2y+41 > GameState.BossState.y-16 && random2y-9 < GameState.BossState.y-16 && random2x+80 > GameState.BossState.x-8 && random2x+4 < GameState.BossState.x-8){
							attack(entities);
							freezehitcooldown = 100;
						}
					}
					else if(random2y+30 > GameState.BossState.y+2 && random2y-16 < GameState.BossState.y-16 && random2x+67 > GameState.BossState.x-8 && random2x+17 < GameState.BossState.x-8){
							attack(entities);
							freezehitcooldown = 100;
					}
					
				}
				if(freezehitcooldown >= 20){
					if (PlayState.player.moveSpeed >= 1) 
						PlayState.player.moveSpeed = PlayState.player.moveSpeed -0.02;
				}
				else PlayState.player.moveSpeed = temp;
			}
			if(e instanceof Muffin && currentLife <= maxLife/2){ 
				switchanimation = true;
			}
			//********************************************************************************************************************
			if(e instanceof Muffin){ 
				eatingAnimation -= (eatingAnimation > 0 ? 1 : 0);
				if (eatingAnimation == 1) currentLife += 100;
				if (currentLife <= maxLife/4 && used == false){
					eatingAnimation = 150;
					used = true;
				}
				throwcooldown -= (throwcooldown > 0 ? 1 : 0);
				throwAnimation -= (throwAnimation > 0 ? 1 : 0);
				if (throwcooldown == 0){
					throwAnimation = 41;
					throwcooldown = -1;
					flightpathx = super.x + 18;
					flightpathy = super.y + 18;
					if (super.x+18 < GameState.BossState.x- 16 && super.y+18 < GameState.BossState.y+28 && super.y+18  > GameState.BossState.y-16) flightdirection = 1;
					else if (super.x+18  > GameState.BossState.x- 16 && super.y+18 < GameState.BossState.y+28 && super.y+18 > GameState.BossState.y-16) flightdirection = 2;
					else if (super.y+18 < GameState.BossState.y- 17 && super.x+18 < GameState.BossState.x-4  && super.x+18 > GameState.BossState.x-38) flightdirection = 3;
					else if (super.y+18  > GameState.BossState.y- 17 && super.x+18  < GameState.BossState.x-4 && super.x+18 > GameState.BossState.x-38) flightdirection = 4;
					else throwcooldown = 50;
				}
				if(throwcooldown <= 50 && throwcooldown >= 0){
					flightpathx = super.x + 18;
					flightpathy = super.y + 18;
				}
				if(throwAnimation == 1){
					throwAnimation = 41;
				}
				if(throwAnimation%5 == 0){					
					if (flightdirection == 1) flightpathx += 5;					
					if (flightdirection == 2) flightpathx -= 5;					
					if (flightdirection == 3) flightpathy += 5;					
					if (flightdirection == 4) flightpathy -= 5;
				}
				if (throwAnimation == 2) flightrotation+= 90;
				if (flightrotation == 360) flightrotation = 0;
				if(flightpathy > Game.HEIGHT ||  flightpathy < -14 || flightpathx > Game.WIDTH ||  flightpathx < -14){
					throwAnimation = 0;
					throwcooldown = 100;
					flightpathy =0;
					flightpathx =0;
					flightdirection = 0;
				}
								
				if ((int)flightpathx+10 >= (int)GameState.BossState.x-5 && (int)flightpathx+10 <= (int)GameState.BossState.x+5 && (int)flightpathy+10 >= (int)GameState.BossState.y-15 && (int)flightpathy+10 <= (int)GameState.BossState.y+15){
					attack(entities);
					throwcooldown = 100;
					throwAnimation = 0;	
					flightpathy =0;
					flightpathx =0;
				}
			}
			//********************************************************************************************************************
			if(e instanceof Beast){
				direction = 0;
				if (super.x <= Game.WIDTH-128-40 && super.x >= Game.WIDTH-128-50) walkdirection=2;
				if (super.x >= 40 && super.x <= 50) walkdirection=1;	
				if (!(flightpathy > Game.HEIGHT/2 || !(throwcooldown == -1))){}
				else if (walkdirection==1 && !validateNextPosition(entities, super.x+moveSpeed*1.2, super.y)) super.x +=moveSpeed;
				else if (walkdirection==2 && !validateNextPosition(entities, super.x-moveSpeed*1.2, super.y)) super.x -=moveSpeed;
				//firebreath
				throwcooldown -= (throwcooldown > 0 ? 1 : 0);
				flamingbreathAnimation -= (flamingbreathAnimation > 0 ? 1 : 0);
				if (throwcooldown == 0){
					Music.getFireSound();
					flamingbreathAnimation = 41;
					throwcooldown = -1;
					flightpathx = super.x + 48;
					flightpathy = super.y + 34;
					used = false;
				}
				if(flamingbreathAnimation == 1){
					flamingbreathAnimation = 41;
				}
				if(flamingbreathAnimation %5 == 0 && flamingbreathAnimation != 0) flightpathy += 10;
				if(flightpathy > Game.HEIGHT ||  flightpathy < -14 || flightpathx > Game.WIDTH ||  flightpathx < -14){
					flamingbreathAnimation = 0;
					throwcooldown = 300;
					flightpathy =0;
					flightpathx =0;
				}	
				if ((int)flightpathx+10 >= (int)GameState.BossState.x-25 && (int)flightpathx+10 <= (int)GameState.BossState.x+25 && (int)flightpathy+10 >= (int)GameState.BossState.y-25 && (int)flightpathy+10 <= (int)GameState.BossState.y+25){
					attack(entities);
					throwcooldown = 300;
					flamingbreathAnimation = 0;	
					flightpathy =0;
					flightpathx =0;
				}
				//whirlwind
				windcooldown -= (windcooldown > 0 ? 1 : 0);
				windAnimation -= (windAnimation > 0 ? 1 : 0);
				if (windcooldown == 0 && windAnimation != 0) windcooldown = -1;
				else if (windcooldown == 0){
					Music.getWindSound();
					windAnimation = 41;
					windcooldown = -1;
					flightpathx2 = super.x-20;
					flightpathy2 = super.y + 34;
					flightpathx3 = super.x + 88;
					flightpathy3 = super.y + 34;
					used2 = false;
				}
				if(windAnimation == 1){
					windAnimation = 41;
				}
				if(windAnimation %10 == 0 && windAnimation != 0){
					if (count2 > 0 && count2 < 10) {
						flightpathx2 += 5;
						flightpathx3 += 5;
					}
					if (count2 > 10 && count2 < 20) {
						flightpathx2 -= 5;
						flightpathx3 -= 5;
					}
					flightpathy2 += 5;
					flightpathy3 += 5;
					if (count2 >20) count2 = 0;
					count2++;
				}
				if(flightpathy2 > Game.HEIGHT ||  flightpathy2 < -14 || flightpathx2 > Game.WIDTH ||  flightpathx2 < -14){
					windAnimation = 0;
					windcooldown = 400;
					flightpathy2 =0;
					flightpathx2 =0;
					flightpathy3 =0;
					flightpathx3 =0;
				}	
				if  ((int)flightpathx2+10 >= (int)GameState.BossState.x-25 && (int)flightpathx2+10 <= (int)GameState.BossState.x+25 && (int)flightpathy2+10 >= (int)GameState.BossState.y-25 && (int)flightpathy2+10 <= (int)GameState.BossState.y+25 && windcooldown == -1){
					attack(entities);
					windcooldown = 100;
					knockupanimation = 40;
				}
				if  ((int)flightpathx3+10 >= (int)GameState.BossState.x-25 && (int)flightpathx3+10 <= (int)GameState.BossState.x+55 && (int)flightpathy3+10 >= (int)GameState.BossState.y-25 && (int)flightpathy3+10 <= (int)GameState.BossState.y+25 && windcooldown == -1){
					attack(entities);
					windcooldown = 100;
					knockupanimation = 40;
				}
			}
			//********************************************************************************************************************
			if(e instanceof Muffin){ 
				if (bossmonstercooldown > 0)
					bossmonstercooldown -= 10;
				//verfolgen
				if (eatingAnimation == 0){
					if(super.y < GameState.BossState.y - 17 - (super.height-32)/2 && !validateNextPosition(entities, super.x, super.y+moveSpeed*1.2)){
						super.y+=moveSpeed;	
						direction=0;
						//System.out.println("u");
					}	
					if(super.y > GameState.BossState.y- 17 - (super.height-32)/2 && !validateNextPosition(entities, super.x, super.y-moveSpeed*1.2)){
						//+16 für 32xgrafik
						super.y-=moveSpeed;
						direction=3;
						//System.out.println("o");
					}
					if(super.x < GameState.BossState.x- 16 - (super.width-32)/2 && !validateNextPosition(entities, super.x+moveSpeed*1.4, super.y)){
						if(super.y < GameState.BossState.y-23 && super.y > GameState.BossState.y-43){
							super.x+=moveSpeed*1.2;	
							direction=2;
							//System.out.println("r");
						}
						else{ super.x+=moveSpeed;}			
					}
					if(super.x > GameState.BossState.x- 16 - (super.width-32)/2 && !validateNextPosition(entities, super.x-moveSpeed*1.4, super.y)){
						if(super.y < GameState.BossState.y-23 && super.y >GameState.BossState.y-43){
							super.x-=moveSpeed*1.2;	
							direction=1;
							//System.out.println("l");
						}
						else{ super.x-=moveSpeed;}
					}
					
					if(super.y < GameState.BossState.y-3 && super.y > GameState.BossState.y-57 && super.x < GameState.BossState.x-3 && super.x > GameState.BossState.x-40 && bossmonstercooldown == 0){
						Music.getMonsterHitSound();
						attack(entities);
						Player.knockBackY = 30;
						bossmonstercooldown = 250;
					}
					if(super.y < GameState.BossState.y+10 && super.y > GameState.BossState.y-30 && super.x < GameState.BossState.x-3 && super.x > GameState.BossState.x-40 && bossmonstercooldown == 0){
						Music.getMonsterHitSound();
						attack(entities);
						Player.knockBackY = -30;
						bossmonstercooldown = 250;
					}
					if(super.y < GameState.BossState.y+3 && super.y > GameState.BossState.y-43){
						if(super.x < GameState.BossState.x-30 && super.x > GameState.BossState.x-66 && bossmonstercooldown == 0){
							Music.getMonsterHitSound();
							attack(entities);
							Player.knockBackX = 30;
							bossmonstercooldown = 250;
						}
					}
					if(super.y < GameState.BossState.y+3 && super.y >GameState.BossState.y-43){;
						if(super.x < GameState.BossState.x+4 && super.x >GameState.BossState.x-4 && bossmonstercooldown == 0){
							Music.getMonsterHitSound();
							attack(entities);
							Player.knockBackX = -30;
							bossmonstercooldown = 250;
						}
					}
				}
			}
			
			//********************************************************************************************************************
			if(e instanceof Shadow){ 
				e.update(input, entities);
				
				
				
			}
			if(e instanceof Blob){ 
				e.update(input, entities);
			}
		}
		//super.update(input, entities);
	}
	public void draw(Graphics2D g) {
		
		if (freezeAnimation > 0) {
			freezingAnimation.update();
			g.drawImage(freeze.getImage(freezingAnimation.getFrame(), 0), null,random2x, random2y);
		}
		
		if (lightnAnimation > 0) {
			lightingAnimation.update();
			g.drawImage(lighting.getImage(lightingAnimation.getFrame(), 0), null,randomx, randomy);
		}
		
		if (eatingAnimation > 0) {
			eatAnimation.update();
			g.drawImage(eat.getImage(eatAnimation.getFrame(), 0), null, (int) super.x, (int) super.y);
		}
		else if (flamingbreathAnimation > 0 && used == false) {
			if (flamingbreathAnimation == 2) used = true;
			
			flamebreathattackAnimation.update();
			g.drawImage(attackbreath.getImage(flamebreathattackAnimation.getFrame(),0), (int)super.x, (int)super.y, null);		
		}
		else if (windAnimation > 0 && used2 == false) {
			if (windAnimation == 2) used2 = true;
				windbossattackAnimation.update();
			
			if (walkdirection==1) super.x -= moveSpeed;
			else super.x += moveSpeed;
			
			g.drawImage(windattack.getImage(windbossattackAnimation.getFrame(),0), (int)super.x, (int)super.y, null);
		}	
		else if (switchanimation == true) g.drawImage(image2.getImage(direction,animation.getFrame()), (int) super.x, (int) super.y, null);
		else {
			
			g.drawImage(image.getImage(direction,animation.getFrame()), (int) super.x, (int) super.y, super.width * scale, super.height * scale, null);
			
			//g.drawImage(image.getImage(direction,animation.getFrame()), (int) super.x, (int) super.y, null);
		}
		
		if (throwAnimation > 0) {	
			if(once == true){
				once = false;
				Music.getMuffinThrowSound();
			}
			throwingcakeAnimation.update();
			g.drawImage(throwcake.getrotatedImage(throwcake.getImage(throwingcakeAnimation.getFrame(), 0), flightrotation), (int)flightpathx, (int)flightpathy, null);
		}
		else {
			once = true;
		}
		if (windAnimation > 0) {
			windattackAnimation.update();
			g.drawImage(wind.getImage( windattackAnimation.getFrame(),0), (int)flightpathx2, (int)flightpathy2, null);
			g.drawImage(wind.getImage( windattackAnimation.getFrame(),0), (int)flightpathx3, (int)flightpathy3, null);
		} 
		if (flamingbreathAnimation > 0) {
			flamebreathAnimation.update();
			g.drawImage(flamebreath.getImage(flamebreathAnimation.getFrame(),0), (int)flightpathx, (int)flightpathy, null);
		} 
		g.setColor(Color.WHITE);
		g.drawString(name, 57, 11);
		//if (switchanimation == true) g.drawImage(image2.getImage(direction,animation.getFrame()), (int) super.x, (int) super.y, null);
		//else g.drawImage(image.getImage(direction,animation.getFrame()), (int) super.x, (int) super.y, null);
		g.setColor(Color.BLACK);
		g.fillRect(56, 15, 400, 15);
		g.setColor(Color.GRAY);
		g.fillRect(57, 16, 398, 13);
		g.setColor(Color.RED);
		g.fillRect(57, 16, (int)(currentLife*(400/maxLife))-2, 13);

	}
	
	public void getNextPosition() {
		super.getNextPosition();
	}
	
	public boolean validateNextPosition(ArrayList<Entity> entities, double xNew, double yNew) {
		return super.validateNextPosition(entities, xNew, yNew);
	}
}

