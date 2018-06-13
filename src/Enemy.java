import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

public abstract class Enemy extends GameObject {
	int health = 100;
	int speed = 10;
	int damage = 5;
	public int seekRangeSquared = (6*Grid.size)*(6*Grid.size);
	public int harmRangeSquared = (2*Grid.size)*(2*Grid.size);
	double walkCoolDown = 0.001;
	double harmCoolDown = 1;
	int value = 10;
	
	Graphics2D imageGraphics;

	Enemy(){
		image = defaultImage();
		imageGraphics = image.createGraphics();
	}
	
	@Override
	public BufferedImage defaultImage() {
		BufferedImage image = new BufferedImage(Grid.size, Grid.size, BufferedImage.TYPE_4BYTE_ABGR);
		Graphics2D g2d = image.createGraphics();
	    g2d.setColor(new Color(255, 100, 0));
	    g2d.fill(new Ellipse2D.Float(4,4,12,12));
	    g2d.dispose();
	    return image;		
	}
	
	@Override
	public void loop() {
		if(health <= 0) {
			Game.destroy(this);
			return;
		}

		imageGraphics.setColor(new Color(255*health/100, 100, 0));
		imageGraphics.fill(new Ellipse2D.Float(4,4,12,12));
	}

	@Override
	public void instantiate() {
		Game.enemies.add(this);
	}

	@Override
	public void destroy() {
		Game.money += value;
		System.out.println("Money: " + Game.money);
		Game.enemies.remove(this);
	}
}
