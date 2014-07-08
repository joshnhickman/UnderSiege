package com.kerbii.undersiege;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public abstract class GameObject {
	
	/**
	 * Location data
	 * 
	 * Different location dependent on type (mobs top left, arrows right center, etc)
	 */
	public float x, y, angle;
	public float width, height;
	
	/**
	 * Texture data
	 */
	public int textureIndex;
	public long millisPerFrame = 150;
	public long millisSinceLastFrame = 0;
		
	/** Buffer holding vertices */
	public FloatBuffer vertexBuffer;
	/** Buffer holding textures */
	public FloatBuffer textureBuffer;
	/** Buffer holding indices */
	public ByteBuffer indexBuffer;
	
	/** Texture definition */
	public float texture[] = {
			0.0f, 1.0f,
			1.0f, 1.0f,
			0.0f, 0.0f,
			1.0f, 0.0f
						};
	
	/** The order to connect vertices */
	public byte[] indices = { 0, 1, 3, 0, 3, 2 };
	public float alpha;
	public boolean draw;
	
	public QuadTreeNode node;
	
	/**
	 * Initialize all global variables
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param vertices
	 */
	public GameObject(float x, float y, float width, float height, float[] vertices) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		ByteBuffer byteBuf = ByteBuffer.allocateDirect(vertices.length * 4);
		byteBuf.order(ByteOrder.nativeOrder());
		vertexBuffer = byteBuf.asFloatBuffer();
		vertexBuffer.put(vertices);
		vertexBuffer.position(0);
		
		byteBuf = ByteBuffer.allocateDirect(texture.length * 4);
		byteBuf.order(ByteOrder.nativeOrder());
		textureBuffer = byteBuf.asFloatBuffer();
		textureBuffer.put(texture);
		textureBuffer.position(0);
		
		indexBuffer = ByteBuffer.allocateDirect(indices.length * 2);
		indexBuffer.put(indices);
		indexBuffer.position(0);
		
		alpha = 1;
		
		draw = true;
	}
	
	public abstract void update(long deltaTime);
	
	public void draw(GL10 gl, int textures[]) {
		
		//Bind previous generated texture
		gl.glBindTexture(GL10.GL_TEXTURE_2D, textures[textureIndex]);
		
		//Point to vertex and texture buffer
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
		gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, textureBuffer);
		gl.glColor4f(1, 1, 1, alpha);
		
		//Draw
		gl.glDrawElements(GL10.GL_TRIANGLES, indices.length, GL10.GL_UNSIGNED_BYTE, indexBuffer);
	}
	
	public void setNode(QuadTreeNode node) {
		this.node = node;
	}
	
	public void changeVertices(float[] vertices) {
		ByteBuffer byteBuf = ByteBuffer.allocateDirect(vertices.length * 4);
		byteBuf.order(ByteOrder.nativeOrder());
		vertexBuffer = byteBuf.asFloatBuffer();
		vertexBuffer.put(vertices);
		vertexBuffer.position(0);
	}
	
	public float distance(float xTarget, float yTarget) {
		float distanceX = Math.abs(xTarget - (x + width / 2));
		float distanceY = Math.abs(yTarget - (y + height / 2));
		return (float) Math.sqrt(distanceX * distanceX + distanceY * distanceY);
	}
	
	public float distance(GameObject object) {
		return distance(object.x + (object.width / 2), object.y + (object.height / 2));
	}
}
