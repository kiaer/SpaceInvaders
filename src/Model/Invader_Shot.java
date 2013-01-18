package Model;

import spaceinvaders.Constants;
import View.View;


	public class Invader_Shot extends GameObjects {
		private View view;
		private boolean used = false;
		public Invader_Shot(View view, String ref, int x, int y) {
			super(ref, x, y);

			this.view = view;
			dy = Constants.INV_SHOT_MOVE_SPEED;
			
		}



		public void move(long delta) {
			super.move(delta);
			if(y>Constants.INV_REMOVE_SHOT){
				this.view.RemoveDead(this);
			}
		}
		
		public void collide(GameObjects other) {

			if (used) {
				return;
			}

			if (other instanceof Player) {
				view.life=view.life-1;
				System.out.println(view.life);
				view.RemoveDead(this);
				if(view.life==0){
					view.gamestart=false;
				
				
				
				
				view.RemoveDead(other);

				used = true;
				}
			}
		}


	}