package heckmack;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class GameStart extends Game {

	public SpriteBatch batch;
	public BitmapFont font;
	AssetManager manager = new AssetManager();

	public void create() {
		batch = new SpriteBatch();
		font = new BitmapFont();
	}

	public void render() {
		super.render();
	}
	
	public void dispose() {
		batch.dispose();
		font.dispose();
	}
	
	public TextButton createButton(String name,TextButtonStyle style,float height,float width,ChangeListener cListener){
		TextButton button = new TextButton(name, style);
		button.setWidth(width);
		button.setHeight(height);
		button.addListener(cListener);
		return button;
	}
	
	public TextButtonStyle getButtonStyle(){
		BitmapFont blackFont = new BitmapFont();
		TextureAtlas buttonTextureAtlas = new TextureAtlas("butt/button.pack");
		Skin buttonSkin = new Skin(buttonTextureAtlas);
		TextButtonStyle textButtonStyle = new TextButtonStyle();
		textButtonStyle.up = buttonSkin.getDrawable("button.up");
		textButtonStyle.down = buttonSkin.getDrawable("button.down");
		textButtonStyle.pressedOffsetX = 1;
		textButtonStyle.pressedOffsetY = -1;
		textButtonStyle.font = blackFont;
		return textButtonStyle;
	}

}
