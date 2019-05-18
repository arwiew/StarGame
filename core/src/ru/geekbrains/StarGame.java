package ru.geekbrains;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class StarGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	TextureRegion region; // позволяет вырезать какую-то часть текстуры из картинки

	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("fon.jpg");
		region = new TextureRegion(img, 50,50,500,500);
	}

	@Override // отвечает за действие на экране, перересовывает экран
	public void render () {
		Gdx.gl.glClearColor(0.4f, 0.3f, 0.9f, 0.5f); //МОЖНО ЗАДАТЬ ФОН
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //очистка экрана, работеат в паре с ClaerColor
		batch.begin();
	//	batch.setColor(0.33f,0.33f,0.33f,0.4f);
		batch.draw(img, 0, 0,700,700);
	//	batch.setColor(0.33f,0.7f,0.8f,0.5f);
	//	batch.draw(region,0,0);//передали на вывод вырезанную текстуру
		batch.end();
	}
	
	@Override// освобождение ресурсов
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
